import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

public class Main {

    public static ArrayList<String> get() throws Exception {
        String table = "authors";

        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT AuthorID, Aname FROM " + table);
            ResultSet result = statement.executeQuery();
            ArrayList<String> array = new ArrayList<String>();
            while (result.next()) {
                array.add(result.getString("Aname").trim());
            }
            return array;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static Author getBio(String name) throws Exception {
        String table = "authors";

        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(
                    "SELECT Institution, email, phone, citations, hindex, i10index, influencescore FROM " + table
                            + " WHERE Aname = \"" + name + "\";");
            ResultSet result = statement.executeQuery();
            return new Author(result);

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public static Connection getConnection() throws Exception {
        Scanner scanner = new Scanner(new File("../.env"));
        HashMap<String, String> config = new HashMap<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("=");
            config.put(line[0].trim(), line[1].trim());
        }
        if (config.get("DB_DRIVER").equals("mysql")) {
            Class.forName("com.mysql.jdbc.Driver");
        }

        String connectionUrl = String.format("jdbc:%s://%s:3306/%s", config.get("DB_DRIVER"), config.get("DB_HOST"),
                config.get("DB_NAME"));

        try (Connection conn = DriverManager.getConnection(connectionUrl, config.get("DB_USER"),
                config.get("DB_PASSWORD"));) {
            System.out.println("Connected");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}