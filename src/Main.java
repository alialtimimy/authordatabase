import java.sql.*;
import java.util.ArrayList;

public class Main {


    public static ArrayList<String> get() throws Exception{
        String table = "authors";

        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT AuthorID, Aname FROM " + table);
            ResultSet result = statement.executeQuery();
            ArrayList<String> array = new ArrayList<String>();
            while(result.next()){
                array.add(result.getString("Aname").trim());
            }
            return array;
        } catch(Exception e) { System.out.println(e); }
        return null;
    }

    public static Author getBio(String name) throws Exception {
        String table = "authors";

        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT Institution, email, phone, citations, hindex, i10index, influencescore FROM " + table + " WHERE Aname = \"" + name + "\";");
            ResultSet result = statement.executeQuery();
            return new Author(result);

        } catch(Exception e) { System.out.println(e); }

        return null;
    }

    public static Connection getConnection() throws Exception{
        try{
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/alidatabase";
            String username = "root";
            String password = "M5198189565m-";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
            return conn;
        } catch(Exception e) { System.out.println(e); }
        return null;
    }



}