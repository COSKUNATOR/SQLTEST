package template.models.services;

import template.MySQL;
import template.models.Hersteller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HerstellerService {

    public static void selectHersteller(){
        try(Connection connection = MySQL.getConnection();
            Statement statement = connection.createStatement())
        {
            ResultSet rs = statement.executeQuery("SELECT * FROM hersteller");

            while(rs.next()) {
                int nummer = rs.getInt("nummer");
                String name = rs.getString("name");

                new Hersteller(nummer, name);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
