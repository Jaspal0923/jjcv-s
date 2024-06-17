import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.sql.*;
import java.util.ArrayList;

public class store{
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pst = null;
  DefaultTableModel model = new DefaultTableModel();
  DefaultTableModel stockInModel = new DefaultTableModel();
  DefaultTableModel stockOutModel = new DefaultTableModel();
  JFrame frame = new JFrame("STORE STATUS");
  JTable table = new JTable();
  JPanel panel = new JPanel();
  JPanel panel1 = new JPanel();
  JPanel panel2 = new JPanel();
  JTable tb = new JTable();
  JLabel image = new JLabel();
  JLabel Header = new JLabel();
  JTable table6;
  
  ArrayList <String>checkLength = new ArrayList<>();
  ArrayList <String>checkLengthOut = new ArrayList<>();
  String warning,name;
  int quantity;



  store(){
    conn = db.java_db();
    ImageIcon bg = new ImageIcon("i.png");
    image = new JLabel();
    image.setIcon(bg);
    image.setBounds(-15,0,1280,680);

    Header.setBounds(500, -100, 400, 300);
    Header.setText("STORE STATUS");
    Header.setFont(new Font("Cooper Black" , Font.BOLD,40));

    panel.setBounds(650, 80 , 550 , 455);
    panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
    panel.setBackground(new Color(0xd7e3fc));
    panel.setLayout(null);

    panel1.setBounds(45, 80 , 590 , 270);
    panel1.setBorder(new BevelBorder(BevelBorder.LOWERED));
    panel1.setBackground(new Color(0xFCF3CF));
    panel1.setLayout(null);

    panel2.setBounds(650, 550 , 550 ,80);
    panel2.setBorder(new BevelBorder(BevelBorder.LOWERED));
    panel2.setBackground(new Color(0xBFC9CA));
    panel2.setLayout(null);

    JPanel panel3 = new JPanel();
    panel3.setBounds(45, 360 , 590 ,270);
    panel3.setBorder(new BevelBorder(BevelBorder.LOWERED));
    panel3.setBackground(new Color(0xD5F5E3));
    panel3.setLayout(null);

    JLabel StockIn = new JLabel();
    StockIn.setText("Stock in");
    StockIn.setBounds(220, -65, 300 , 200);
    StockIn.setFont(new Font ("Consolas" , Font.BOLD , 30));

    JLabel StockOut = new JLabel();
    StockOut.setText("Stock out");
    StockOut.setBounds(220, -65, 300 , 200);
    StockOut.setFont(new Font ("Consolas" , Font.BOLD , 30));

    JLabel Total = new JLabel();
    Total.setText("Total Income : ");
    Total.setBounds(80, 420, 300 , 200);
    Total.setFont(new Font ("Consolas" , Font.BOLD , 30));

    //Stock IN
    
    Object [] column3 = {"ID", "Date" , "Product Name" , "Quantity"};
    stockInModel.setColumnIdentifiers(column3);
    JTable table4 = new JTable();
    table4.setModel(stockInModel);
    table4.setDefaultEditor(Object.class, null);
    JScrollPane sp3 = new JScrollPane(table4);

    //db Connection for stockIn
    stockIn si = new stockIn();
    si.updateProduct();
    
    //Stock Out
    Object [] column1 = {"Date" , "Product Name" , "Quantity"};
    stockOutModel.setColumnIdentifiers(column1);
    JTable table3 = new JTable();
    table3.setModel(stockOutModel);
    JScrollPane sp1 = new JScrollPane(table3);

    //db connection for stock out
    new stockOut().updateProduct();
    
    


    //Product update
    Object [] column6 = {"Inventory" , "Product Name" , "Quantity", "Image" , "Procurement Suggestion"};
    model.setColumnIdentifiers(column6);
    table6 = new JTable();
    table6.setModel(model);
    table6.setDefaultEditor(Object.class, null);
    JScrollPane sp6 = new JScrollPane(table6);
    

    //db connection for product update
    product prod = new product();
    prod.updateProduct();
    

    JButton Return = new JButton("Admin");
    Return.setBounds(420, 20, 100, 40);
    Return.setFont(new Font ("Consolas" , Font.PLAIN , 20));
    Return.setFocusPainted(false);
    Return.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        frame.dispose();
        new AdminPage();
      }
    });

    JButton ViewTransactions = new JButton("Refresh");
    ViewTransactions.setBounds(15, 20, 100, 40);
    ViewTransactions.setFont(new Font ("Consolas" , Font.PLAIN , 15));
    ViewTransactions.setFocusPainted(false);
    ViewTransactions.addActionListener(new ActionListener(){
      public void actionPerformed (ActionEvent e){
        frame.dispose();
        new store();
      }
    });

    JButton total = new JButton("Add Stock");
    total.setBounds(125, 20, 150, 40);
    total.setFont(new Font ("Consolas" , Font.PLAIN , 20));
    total.setFocusPainted(false);
    total.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        frame.dispose();
        new addStockWindow();

      }
    });

    JButton remove = new JButton("Remove");
    remove.setBounds(280, 20, 130, 40);
    remove.setFont(new Font ("Consolas" , Font.PLAIN , 20));
    remove.setFocusPainted(false);
    remove.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        int row = table4.getSelectedRow();
        String cellID = table4.getModel().getValueAt(row, 0).toString();
        String name = table4.getModel().getValueAt(row, 2).toString();
        String quantity = table4.getModel().getValueAt(row, 3).toString();
        

        try {
          pst = conn.prepareStatement("DELETE FROM stockIN WHERE ProdID =?");
          pst.setInt(1, Integer.parseInt(cellID));
          pst.executeUpdate();
          JOptionPane.showMessageDialog(null, "Stock Remove");
          new remove(name, Integer.parseInt(quantity));
          frame.dispose();
          new store();

        } catch (Exception a) {
          JOptionPane.showMessageDialog(null, a);
        }
      }
    });
    

    sp1.setBounds(30,60,515,200);
    sp3.setBounds(30,60,515,200);
    sp6.setBounds(15,20,520,410);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
    frame.setLayout(null);
		frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    

    panel.add(sp6);

    panel1.add(StockIn);
    panel1.add(sp3);
    panel1.add(Total);
    

    panel2.add(Return);
    panel2.add(total);
    panel2.add(ViewTransactions);
    panel2.add(remove);
    

    panel3.add(sp1);
    panel3.add(StockOut);

    frame.add(Header);
    frame.add(panel);
    frame.add(panel1);
    frame.add(panel2);
    frame.add(panel3);
    frame.add(image);
    frame.setVisible(true);

    
  }


  class product{
    void updateProduct(){
      table6.getColumn("Image").setCellRenderer(new myTableCellRenderer());
      try {
        String sql = "SELECT * FROM inventory";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
          String quantity = rs.getString("Quantity");
          String id = rs.getString("Id");
          String product = rs.getString("Product");
          byte[] image = rs.getBytes("Image");        
          JLabel img = new JLabel();        
  
          try {          
          ImageIcon imageIcon = new ImageIcon(image);
          Image image2 = imageIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
          img.setIcon(new ImageIcon(image2));
          } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Image Error");
          }
  
          if(Integer.parseInt(quantity) <= 30){
            warning = "Add Stock!";
          }else{
            warning = "Good";
            
          }
                          
          model.addRow(new Object[]{
                   
            id,
            product,
            quantity,
            img,
            warning,
     
          });
        }
        
        table.setModel(model);
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
      }
    }
  }
  class stockIn extends product{
    void updateProduct(){
      try {
        pst = conn.prepareStatement("SELECT * FROM stockIN");
        rs = pst.executeQuery();
        while (rs.next()) {
          String date = rs.getString("Date");
          String name = rs.getString("ProductName");
          int quantity = rs.getInt("Quantity");  
          int prodid = rs.getInt("ProdID"); 
          
          checkLength.add(date);
          
                         
          stockInModel.addRow(new Object[]{
              
            prodid,
            date,
            name,
            quantity,
            
     
          });
        }
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
      }
    }

  }

  class stockOut extends product{
    void updateProduct(){
      try {
        pst = conn.prepareStatement("SELECT * FROM stockOUT");
        rs = pst.executeQuery();
        while (rs.next()) {
          String date = rs.getString("Date");
          String name = rs.getString("ProductName");
          int quantity = rs.getInt("Quantity");   
          checkLengthOut.add(date);
                          
          stockOutModel.addRow(new Object[]{
                   
            date,
            name,
            quantity,
     
          });
        }
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
      }
    }
  }
  

  class myTableCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        
        TableColumn tb = table.getColumn("Image");
        tb.setMaxWidth(120);
        tb.setMinWidth(120);
        table.setRowHeight(120);
        table.setRowMargin(5);
        return (Component) value;
    }
  }

  

  

    public static void main(String[] args) {
      
      new store();
    }
}
