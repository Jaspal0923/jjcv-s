import java.util.ArrayList;

import javax.swing.JOptionPane;

public class PosSystem extends InventorySystem{
    
    private ArrayList<String> foodList = new ArrayList<String>();
    private ArrayList<String> drinkList = new ArrayList<String>();
    private ArrayList<String> dessertList = new ArrayList<String>();
    
    
    ArrayList<String> getFoodList() {
        try {

            String sql = "SELECT Product FROM inventory WHERE category = 'Food' ";
            pst = conn.prepareStatement(sql);            
            rs = pst.executeQuery();
            while (rs.next()) {
                foodList.add(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
                
            }
            
        }   
        return foodList;
    }

    ArrayList<String> getDrinkList() {
        try {
            
            String sql = "SELECT (Product) FROM inventory WHERE Category = 'Drink' ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                    drinkList.add(rs.getString(1));           
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
                
            }

        }
        return drinkList;
    }

    ArrayList<String> getDessertList() {
        try {
            
            String sql = "SELECT Product FROM inventory WHERE Category = 'Dessert' ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                dessertList.add(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
                
            }

        }
        return dessertList;
    }
}
