
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;


public class InventorySystem {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    private final String[] category = {"Food","Drink","Dessert"};
    private String ProductCategory;
    private String ProductId;
    private String Product;
    private double ProductPrice = 0;
    private int ProductQuantity = 0;
    private byte[] ProductImg;

    InventorySystem() {

        conn = db.java_db();
    }

    void addProduct(String id, String product, double price, byte[] img, int category) {
        //int category --> index of category[]
        String sql = "INSERT INTO inventory (Id,Product,Price,Quantity,Image,Category) VALUES (?,?,?,?,?,?)";

        try {
                
            pst = conn.prepareStatement(sql);


            this.ProductImg = img;
            pst.setString(1, id);
            pst.setString(2, product);
            pst.setDouble(3, price);
            pst.setInt(4, ProductQuantity);
            pst.setBytes(5, this.ProductImg);                
            pst.setString(6, this.category[category]);

            pst.execute();
            JOptionPane.showMessageDialog(null,"Data is saved successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
                
            }
        }
    }
    
    void updateProduct(String id, String product, double price, String imgFilename, int category) {
                    
        String update1 = id;
        String update2 = product;
        Double update3 = price;
        int update4 = 0;
        String update6 = this.category[category];

        try {

            String sql1 = "UPDATE inventory SET Product ='"+update2+"',Price ='"+update3+"',Quantity ='"+update4+"',"
            +"Category ='"+update6+"'  WHERE Id ='"+update1+"'";

            pst = conn.prepareStatement(sql1);
            pst.execute();
            
            try {
            
                String sql2 = "UPDATE inventory SET Id ='"+update1+"' WHERE Product ='"+update2+"' ";
                pst = conn.prepareStatement(sql2);
                pst.execute();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e, "Error!", JOptionPane.ERROR_MESSAGE);
            } 

            JOptionPane.showMessageDialog(null,"Record Updated");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error!", JOptionPane.ERROR_MESSAGE);
        } try {
            
            File file = new File(imgFilename);
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] update5 = new byte[(int)file.length()];
                fis.read(update5);

                String sql = "UPDATE inventory SET Image =? WHERE Id = '"+update1+"' ";

                pst = conn.prepareStatement(sql);
                pst.setBytes(1, update5);
            }
            pst.executeUpdate();
            pst.close();
            
        } catch (Exception e) {
            
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
                
            }
        }
    }

    void removeProduct(String id) {
        String sql = "DELETE FROM inventory WHERE id ='"+id+"' ";
        try {
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Record Deleted");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {
                
            }
        }
    }

    void searchProductId(String id) {
        String sql = "SELECT * FROM inventory where Id = '"+id+"' ";

        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                                
                this.ProductId = rs.getString("Id");
                this.Product = rs.getString("Product");
                this.ProductPrice = rs.getDouble("Price");
                this.ProductQuantity = rs.getInt("Quantity");
                this.ProductImg = rs.getBytes("Image");
                this.ProductCategory = rs.getString("Category");
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

    }

    void searchProduct(String product) {
        String sql = "SELECT * FROM inventory where Product = '"+product+"' ";

        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                                
                this.ProductId = rs.getString("Id");
                this.Product = rs.getString("Product");
                this.ProductPrice = rs.getDouble("Price");
                this.ProductQuantity = rs.getInt("Quantity");
                this.ProductImg = rs.getBytes("Image");
                this.ProductCategory = rs.getString("Category");
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

    }    
    public String[] getCategory() {
        return category;
    }
    public String getProductCategory() {
        return ProductCategory;
    }
    public String getProduct() {
        return Product;
    }
    public String getProductId() {
        return ProductId;
    }
    public byte[] getProductImg() {
        return ProductImg;
    }
    public double getProductPrice() {
        return ProductPrice;
    }
    public int getProductQuantity() {
        return ProductQuantity;
    }

}