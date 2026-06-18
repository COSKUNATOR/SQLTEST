package template.models.services;



import template.MySQL;
import template.models.Artikel;
import template.models.Bestellposition;
import template.models.Bestellung;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BestellpositionService
{
    public static void selectBestellposition()
    {
        try (Connection connection = MySQL.getConnection();
             Statement statement = connection.createStatement())
        {
            ResultSet rs = statement.executeQuery("select * from bestellposition");
            while (rs.next())
            {
                Bestellung bestellung = Bestellung.bestellung.get(rs.getInt("bestellung"));
                Artikel artikel = Artikel.artikel.get(rs.getInt("artikel"));

                int anzahl = rs.getInt("anzahl");

                new Bestellposition(bestellung, artikel, anzahl);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

