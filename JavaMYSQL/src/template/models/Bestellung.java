package template.models;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Bestellung {
    public static final Map<Integer, Bestellung> bestellung = new HashMap<>();

    private final int nummer;
    private LocalDateTime datum;
    private final Kunde kunde;
    private final Adresse rechnungsadresse;
    private final Adresse lieferadresse;

    public Bestellung(int nummer, LocalDateTime datum, Kunde kunde, Adresse rechnungsadresse, Adresse lieferadresse) {
        this.nummer = nummer;
        this.datum = datum;
        this.kunde = kunde;
        this.rechnungsadresse = rechnungsadresse;
        this.lieferadresse = lieferadresse;

        bestellung.put(nummer, this);
    }

    public int getNummer() {
        return nummer;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public Adresse getRechnungsadresse() {
        return rechnungsadresse;
    }

    public Adresse getLieferadresse() {
        return lieferadresse;
    }

    @Override
    public String toString() {
        return "Bestellung{" +
                "nummer=" + nummer +
                ", datum=" + datum +
                ", kunde=" + kunde +
                ", rechnungsadresse=" + rechnungsadresse +
                ", lieferadresse=" + lieferadresse +
                '}';
    }
}
