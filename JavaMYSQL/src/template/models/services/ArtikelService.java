package template.models.services;

import template.MySQL;
import template.models.Artikel;
import template.models.Hersteller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ArtikelService {
    public static void selectArtikel(){
        try(Connection connection = MySQL.getConnection();
            Statement statement = connection.createStatement()){

            ResultSet rs = statement.executeQuery("SELECT * FROM artikel");

            while(rs.next()) {
                int nummer = rs.getInt("nummer");
                String bezeichnung = rs.getString("bezeichnung");
                BigDecimal preis = rs.getBigDecimal("preis");

                Hersteller hersteller = Hersteller.hersteller.get(rs.getInt("hersteller"));

                new Artikel(nummer, bezeichnung, preis, hersteller);
            }
        }

        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
