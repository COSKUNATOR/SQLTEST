package template.models.services;

import template.MySQL;
import template.models.Adresse;
import template.models.Bestellung;
import template.models.Kunde;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class BestellungService {
    public static void selectBestellung(){
        try(Connection connection = MySQL.getConnection();
            Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery("SELECT * FROM BESTELLUNG");

            while(rs.next()){
                int nummer = rs.getInt("nummer");
                LocalDateTime datum = rs.getObject("datum", LocalDateTime.class);
                Kunde kunde = Kunde.kunde.get(rs.getInt("kunde"));
                Adresse rechnungsadresse = Adresse.adresse.get(rs.getInt("rechnungsadresse"));
                Adresse lieferadresse = Adresse.adresse.get(rs.getInt("lieferadresse"));

                new Bestellung(nummer, datum, kunde, rechnungsadresse, lieferadresse);
            }
        }

        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
