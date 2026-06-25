import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;

public class Mitarbeiter {
    private String mitarbeiternummer;
    private String email;
    private String vorname;
    private String nachname;
    private int einstellungsjahr;
    private int einstellungsmonat;

    public Mitarbeiter(String mitarbeiternummer, String email, String vorname, String nachname, int einstellungsjahr, int einstellungsmonat) {
        this.mitarbeiternummer = mitarbeiternummer;
        this.email = email;
        this.vorname = vorname;
        this.nachname = nachname;
        this.einstellungsjahr = einstellungsjahr;
        this.einstellungsmonat = einstellungsmonat;
    }

    public String getMitarbeiternummer() {
        return mitarbeiternummer;
    }

    public String getEmail() {
        return email;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public int getEinstellungsjahr() {
        return einstellungsjahr;
    }

    public int getEinstellungsmonat() {
        return einstellungsmonat;
    }

    @Override
    public String toString() {
        return "Mitarbeiter{" +
                "mitarbeiternummer='" + mitarbeiternummer + '\'' +
                ", email='" + email + '\'' +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", einstellungsjahr=" + einstellungsjahr +
                ", einstellungsmonat=" + einstellungsmonat +
                '}';
    }

    /**
     * Erzeugt eine einstellige Prüfzahl (0-9) basierend auf den ASCII-Werten
     der E-Mail.
     */
    private static int calculateEmailPruefzahl(String email) {
        if (email == null || email.isEmpty()) return 0;
        int[] asciiWerte = new int[email.length()];
        for (int i = 0; i < email.length(); i++) {
            asciiWerte[i] = (int) email.charAt(i);
        }
        int summe = 0;
        for (int wert : asciiWerte) {
            summe += wert;
        }
        return summe % 10;
    }
    /**
     * Wandelt den PIN-String in ein int-Array um und verschlüsselt ihn
     * in ein unlesbares Byte-Array für die Spalte 'pin_hash' (VARBINARY).
     */
    private static byte[] hashPin(String pin) {
        if (pin == null) return new byte[0];
        int[] ziffern = new int[pin.length()];
        for (int i = 0; i < pin.length(); i++) {
            ziffern[i] = Character.getNumericValue(pin.charAt(i));
        }
        byte[] hash = new byte[ziffern.length];
        for (int i = 0; i < ziffern.length; i++) {
            hash[i] = (byte) ((ziffern[i] * 13 + 27) % 128);
        }
        return hash;
    }
    /**
     * Generiert einen 6-stelligen PIN-String (5 Stellen Zufall + 1 Stelle EMail-Prüfzahl).
     */
    private static String generate6DigitPin(String email) {
        Random rand = new Random();
        int randomFuenfsteller = 10000 + rand.nextInt(90000);
        int pruefzahl = calculateEmailPruefzahl(email);
        return String.valueOf(randomFuenfsteller) + pruefzahl;
    }

    public static String registerEmployee(String vorname, String nachname,
                                          String email, int jahr, int monat) {
        String pin = generate6DigitPin(email);
        byte[] hashPin = hashPin(pin);

        String namenKuerzel = ""
                + vorname.charAt(0)
                + vorname.charAt(1)
                + nachname.charAt(0)
                + nachname.charAt(1);

        String mitarbeiternummer = "MA-"
                + namenKuerzel.toUpperCase()
                + "-"
                + jahr
                + "-"
                + monat;

        String sql = """
                INSERT INTO mitarbeiter
                (mitarbeiternummer, email, pin_hash, vorname, nachname,
                 einstellungsjahr, einstellungsmonat)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try(Connection connection = MySQL.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, mitarbeiternummer);
            ps.setString(2, email);
            ps.setBytes(3, hashPin);
            ps.setString(4, vorname);
            ps.setString(5, nachname);
            ps.setInt(6, jahr);
            ps.setInt(7, monat);

            int rows = ps.executeUpdate();

            if (rows == 1) {
                return pin;
            }


        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static Mitarbeiter verifyLogin(String email, String eingegebenerPin){
        char letztesZeichen = eingegebenerPin.charAt(eingegebenerPin.length() - 1);
        int letzteZiffer = Character.getNumericValue(letztesZeichen);

        if(letzteZiffer != calculateEmailPruefzahl(email)) {
            System.out.println("Einloggen fehlgeschlagen");
            return null;
        }

        String sql = """
            SELECT mitarbeiternummer, email, pin_hash, vorname, nachname,
                   einstellungsjahr, einstellungsmonat
            FROM mitarbeiter
            WHERE email = ?
            """;

        try (Connection connection = MySQL.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    byte[] gespeicherterPinHash = rs.getBytes("pin_hash");
                    byte[] eingegebenerPinHash = hashPin(eingegebenerPin);

                    if (Arrays.equals(gespeicherterPinHash, eingegebenerPinHash)) {
                        System.out.println("Einloggen erfolgreich");
                        return new Mitarbeiter(
                                rs.getString("mitarbeiternummer"),
                                rs.getString("email"),
                                rs.getString("vorname"),
                                rs.getString("nachname"),
                                rs.getInt("einstellungsjahr"),
                                rs.getInt("einstellungsmonat")
                        );
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Einloggen fehlgeschlagen: E-Mail oder PIN falsch.");
        return null;
    }
}
