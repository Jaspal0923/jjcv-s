import java.sql.*;
import javax.swing.*;

/**
 * db
 */
public class db {

    Connection conn = null;
    public static Connection java_db() {

        try {
            
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:projdatabase.sqlite");
            return conn;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
            return null;
            
        }
    }
}