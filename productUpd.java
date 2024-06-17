import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class productUpd {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    int currentQuant, newQuant;
    String SQL;
    int quantity;

    productUpd(String SQL, Integer quantity){
        conn = db.java_db();
        this.SQL = SQL;
        this.quantity = quantity;
    }

    void prodUpdate(){
        try {
            String sql = "SELECT * FROM inventory where Product = '"+SQL+"' ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                                
                currentQuant = rs.getInt("Quantity");
            }
        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, a, "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception a) {
                
            }
        }
    }
}


class add extends productUpd{

    add(String SQL, Integer quantity) {
        super(SQL, quantity);
        prodUpdate();
    }
    void prodUpdate(){
        String sql = "SELECT * FROM inventory where Product = '"+SQL+"' ";

        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                                
                currentQuant = rs.getInt("Quantity");
            }
        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, a, "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception a) {
                
            }
        }



        //

        try {
            newQuant = currentQuant + quantity;
            String sql1 = "UPDATE inventory SET Quantity ='"+newQuant+"'  WHERE Product ='"+SQL+"'";

            pst = conn.prepareStatement(sql1);
            pst.execute();
            

            JOptionPane.showMessageDialog(null,"Record Updated");
        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, a, "Error!", JOptionPane.ERROR_MESSAGE);
        }finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception a) {
                
            }
        }
    }
}

class remove extends productUpd{
    
    remove(String SQL, Integer quantity) {
        super(SQL, quantity);
        prodUpdate();
    }

    void prodUpdate(){
        String sql = "SELECT * FROM inventory where Product = '"+SQL+"' ";

                 try {
                     pst = conn.prepareStatement(sql);
                     rs = pst.executeQuery();
                     
                     while (rs.next()) {
                                         
                         currentQuant = rs.getInt("Quantity");
                     }
                 } catch (Exception a) {
                     JOptionPane.showMessageDialog(null, a, "Error!", JOptionPane.ERROR_MESSAGE);
                 } finally {
                     try {
                         pst.close();
                         rs.close();
                     } catch (Exception a) {
                         
                     }
                 }
         
 
 
                 //
 
                 try {
                     newQuant = currentQuant - quantity;
                     String sql1 = "UPDATE inventory SET Quantity ='"+newQuant+"'  WHERE Product ='"+SQL+"'";
         
                     pst = conn.prepareStatement(sql1);
                     pst.execute();
            
                 } catch (Exception a) {
                     JOptionPane.showMessageDialog(null, a, "Error!", JOptionPane.ERROR_MESSAGE);
                 }finally {
                     try {
                         pst.close();
                         rs.close();
                     } catch (Exception a) {
                         
                     }
                 }
    }
}

