import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
public class checkoutlenght {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ArrayList<String> checkLengthOut = new ArrayList<>();
    checkoutlenght(){
        conn = db.java_db();
        try {
            pst = conn.prepareStatement("SELECT * FROM stockOUT");
            rs = pst.executeQuery();
            while (rs.next()) {
              String date = rs.getString("Date");
              checkLengthOut.add(date);
                              
              
            }
          } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
          }
    }
}
