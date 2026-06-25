package template;

import java.math.BigDecimal;
import java.sql.*;

public class Service {
    public static String selectAlleBestellung(){
        try (Connection connection = MySQL.getConnection();
             Statement statement = connection.createStatement()){

            ResultSet rs = statement.executeQuery("SELECT * FROM selectAlleBestellungen");

            StringBuilder sb = new StringBuilder();
            sb.append(" | ");

            for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                sb.append(rs.getMetaData().getColumnName(i)).append(" | ");
            }
            sb.append("\n");

            while(rs.next()) {
                sb.append(" | ");
                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    sb.append(rs.getString(i)).append(" | ");
                }
                sb.append("\n");
            }

            return sb.toString();

        }catch(SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String selectAnzahlGekaufterprodukte(int kundennummer){
        try(Connection connection = MySQL.getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM selectanzahlgekaufterprodukte WHERE nummer = ?")){

            statement.setInt(1,kundennummer);

            ResultSet rs = statement.executeQuery();
            StringBuilder sb = new StringBuilder();

            if(rs.next()){
                sb.append("Kunde: ").append(kundennummer).append(" - Anzahl: ").append(rs.getInt("anzahl"));
            }

            return sb.toString();

        }catch(SQLException e){
            e.printStackTrace();
            return "";
        }
    }

    public static int insertArtikel(String bezeichnung, BigDecimal preis, int hersteller){
        try(Connection connection = MySQL.getConnection();
        CallableStatement statement = connection.prepareCall("{CALL insertArtikel(?,?,?,?)}")){

            statement.setString(1, bezeichnung);
            statement.setBigDecimal(2, preis);
            statement.setInt("h", hersteller);

            statement.registerOutParameter("n", JDBCType.INTEGER);

            if(statement.executeUpdate()>0){
                return statement.getInt(4);
            }

            return -1;

        }catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int updateArtikelPreis(int nummer, BigDecimal preis){
        try(Connection connection = MySQL.getConnection();
        CallableStatement statement = connection.prepareCall("{CALL updateArtikelPreis(?,?)}")){
            statement.setInt("nummer", nummer);
            statement.setBigDecimal("preis", preis);

            return statement.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }
}

