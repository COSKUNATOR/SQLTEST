package template.models.services;

import template.MySQL;
import template.models.Adresse;
import template.models.Kunde;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdresseService {
    public static void selectAdresse(){
        try(Connection connection = MySQL.getConnection();
            Statement statement = connection.createStatement()){

            ResultSet rs = statement.executeQuery("SELECT * FROM adresse");

            while (rs.next()) {
                int id = rs.getInt("id");
                String strassenr = rs.getString("strasseNr");
                String plz = rs.getString("plz");
                String ort = rs.getNString("ort");

                Kunde kunde = Kunde.kunde.get(rs.getInt("kunde"));

                new Adresse(id, strassenr, plz, ort, kunde);
            }
        }

        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
