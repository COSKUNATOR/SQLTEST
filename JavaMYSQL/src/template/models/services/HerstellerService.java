package template.models.services;

import template.MySQL;
import template.models.Hersteller;

import java.sql.*;

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

    public static Hersteller createHersteller(String name){
        Hersteller h = null;

        try(Connection connection = MySQL.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO hersteller (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1, name);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()) {
                int nummer = rs.getInt(1);
                h = new Hersteller(nummer, name);
            }


        }catch(SQLException e){
            e.printStackTrace();
        }

        return h;
    }

    public static boolean updateHersteller(Hersteller hersteller, String attribut, Object wert){
        try(Connection connection = MySQL.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE hersteller SET "+attribut+" = ? WHERE nummer = ?")){

            statement.setObject(1, wert);
            statement.setInt(2, hersteller.getNummer());

            int anzahl = statement.executeUpdate();
            selectHersteller();
            return anzahl == 1;

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }

    }
}
