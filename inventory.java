
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

public class inventory{

  Connection conn = null;
  PreparedStatement pst = null;
  ResultSet rs = null;

  InventorySystem system = new InventorySystem();
  DecimalFormat df = new DecimalFormat("0.00");
  byte[] ProductImg;
  String filename;

  JFrame frame = new JFrame();
  JPanel panel = new JPanel();
  JTextField ID = new JTextField("Enter ID");
  JTextField ProductName = new JTextField("Enter Name");
  JTextField Price = new JTextField("Enter Price");
  JTextField Quantity = new JTextField("Enter Quantity");
  JTextField Search = new JTextField("Search...");
  
  JLabel invent, categ, prodID, prodName, prodPrice, prodQuantity,bg1;
  JComboBox<String> category = new JComboBox<String>(system.getCategory());
  
  JTable table = new JTable();
  DefaultTableModel model = new DefaultTableModel();

  private void tableHeader() {

    model = (DefaultTableModel) table.getModel();
    Object [] column = {"Category","Product ID","Product Name", "Image", "Price"};
    model.setColumnIdentifiers(column);
    table.getColumn("Image").setCellRenderer(new myTableCellRenderer());
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

  private void updateTable() {

    tableHeader();
    try {
      String sql = "SELECT * FROM inventory";
      pst = conn.prepareStatement(sql);
      rs = pst.executeQuery();
      while (rs.next()) {
        String data1 = rs.getString("Category");
        String data2 = rs.getString("Id");
        String data3 = rs.getString("Product");
        byte[] data4 = rs.getBytes("Image");        
        JLabel img = new JLabel();        
        Double data5 = Double.parseDouble(rs.getString("Price"));

        try {          
        ImageIcon imageIcon = new ImageIcon(data4);
        Image image = imageIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        img.setIcon(new ImageIcon(image));
        } catch (Exception e) {
          
        }        
                        
        model.addRow(new Object[]{
                 
          data1,
          data2,
          data3,
          img,
          df.format(data5),          
   
        });
      }
      table.setModel(model);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }
  }
  inventory(){

    conn = db.java_db();
    ImageIcon bg = new ImageIcon("icons/i.png");
    bg1 = new JLabel();
    bg1.setIcon(bg);
    bg1.setBounds(0,0,1280,680);

    ImageIcon s = new ImageIcon("icons/s.png");
    ImageIcon r = new ImageIcon("icons/r.png");
    ImageIcon a = new ImageIcon("icons/a.png");
    ImageIcon e = new ImageIcon("icons/e.png");
    ImageIcon addImage1 = new ImageIcon("icons/add.png");
    ImageIcon rem = new ImageIcon("icons/rem.png");
    ImageIcon ret = new ImageIcon("icons/ret.png");

    JButton add = new JButton("Add New Product", a);
    JButton edit = new JButton("Edit Product" ,e);
    JButton addImage = new JButton("Add Image" , addImage1);
    JButton remove = new JButton("Remove Product", rem);
    JButton returnhome = new JButton("Return to Admin Page" , ret);

    JScrollPane sp = new JScrollPane(table);
    sp.setBounds(410,100,800,540);

    updateTable();
    
    table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int i = table.getSelectedRow();
        TableModel model = table.getModel();
        category.setSelectedItem(model.getValueAt(i, 0));
        ID.setText(model.getValueAt(i, 1).toString());
        ProductName.setText(model.getValueAt(i, 2).toString());
        Price.setText(model.getValueAt(i, 4).toString());
        
			}
      @Override
      public void mouseReleased(MouseEvent e) {
        table.clearSelection();
      }
		});
    invent = new JLabel();
    invent.setBounds(75, -100, 300, 300);
    invent.setText("Inventory");
    invent.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 50));
    
    categ = new JLabel("Category");
    categ.setFont(new Font("Consolas", Font.BOLD, 20));
    categ.setBounds(70, 100, 150, 30);
    prodID = new JLabel("Product ID");
    prodID.setFont(new Font("Consolas", Font.BOLD, 20));
    prodID.setBounds(70, 153, 150, 30);
    prodName = new JLabel("Product Name");
    prodName.setFont(new Font("Consolas", Font.BOLD, 20));
    prodName.setBounds(70, 206, 150, 30);
    prodPrice = new JLabel("Product Price");
    prodPrice.setFont(new Font("Consolas", Font.BOLD, 20));
    prodPrice.setBounds(70, 253, 150, 30);
    prodQuantity = new JLabel("Product Quantity");
    prodQuantity.setFont(new Font("Consolas", Font.BOLD, 18));
    prodQuantity.setBounds(70, 303, 200, 30);

    category.setBounds(230, 100, 150, 30);
    ID.setBounds(230, 150, 150, 30);
    ID.setFont(new Font("Consolas", Font.PLAIN, 12));
    ID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				 if (ID.getText().equals("Enter ID")) {
          ID.setText("");					
							}
							 else {
                ID.selectAll();
							}
			      }
			@Override
			public void focusLost(FocusEvent e) {
			        if(ID.getText().equals("")){
			        	ID.setText("Enter ID");
			        }		
			}
		});
    ProductName.setBounds(230, 200, 150, 30);
    ProductName.setFont(new Font("Consolas", Font.PLAIN, 12));
    ProductName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				 if (ProductName.getText().equals("Enter Name")) {
          ProductName.setText("");					
							}
							 else {
                ProductName.selectAll();
							}
			      }
			@Override
			public void focusLost(FocusEvent e) {
			        if(ProductName.getText().equals("")){
			        	ProductName.setText("Enter Name");
			        }		
			}
		});
    Price.setBounds(230, 250, 150, 30);
    Price.setFont(new Font("Consolas", Font.PLAIN, 12));
    Price.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				 if (Price.getText().equals("Enter Price")) {
          Price.setText("");					
							}
							 else {
                Price.selectAll();
							}
			      }
			@Override
			public void focusLost(FocusEvent e) {
			        if(Price.getText().equals("")){
			        	Price.setText("Enter Price");
			        }		
			}
		});
    Quantity.setBounds(250, 300, 130, 30);
    Quantity.setFont(new Font("Consolas", Font.PLAIN, 12));
    Quantity.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				 if (Quantity.getText().equals("Enter Quantity")) {
          Quantity.setText("");					
							}
							 else {
                Quantity.selectAll();
							}
			      }
			@Override
			public void focusLost(FocusEvent e) {
			        if(Quantity.getText().equals("")){
			        	Quantity.setText("Enter Quantity");
			        }		
			}
		});
    Search.setBounds(410, 35, 470, 40);
    Search.setFont(new Font("Consolas", Font.BOLD, 25));
    Search.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				 if (Search.getText().equals("Search...")) {
          Search.setText("");					
							}
							 else {
                Search.selectAll();
							}
			      }
			@Override
			public void focusLost(FocusEvent e) {
			        if(Search.getText().equals("")){
			        	Search.setText("Search...");
			        }		
			}
		});

    JButton search = new JButton("Search", s);
    search.setBounds(890, 35, 150, 40);
    search.setFont(new Font("Consolas", Font.BOLD, 15));
    search.setFocusable(false);
    search.setBackground(new Color(0xD6EAF8));
    search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				search.setBackground(new Color(0x2ECC71));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
        search.setBackground(new Color(0xD6EAF8));
			}
		});
    search.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          system.searchProductId(Search.getText());;
          system.searchProduct(Search.getText());;

          category.setSelectedItem(system.getCategory());
          ID.setText(system.getProductId());
          ProductName.setText(system.getProduct());
          Price.setText(String.valueOf(df.format(system.getProductPrice())));
        } catch (Exception e1) {
          JOptionPane.showMessageDialog(null, e);
        }
      }
    });
    JButton refresh = new JButton("Refresh", r);
    refresh.setBounds(1055, 35, 150, 40);
    refresh.setFont(new Font("Consolas", Font.BOLD, 15));
    refresh.setFocusable(false);
    refresh.setBackground(new Color(0xD6EAF8));
    refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				refresh.setBackground(new Color(0x00FF66));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
        refresh.setBackground(new Color(0xD6EAF8));
			}
		});
    refresh.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {        
        inventory j = new inventory();
        j.frame.setVisible(true);
        frame.dispose();               
      }
    });
    add.setBounds(135, 390, 200, 40);
    add.setFont(new Font("Consolas", Font.BOLD, 15));
    add.setFocusable(false);
    add.setBackground(new Color(0xD6EAF8));
    add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				add.setBackground(new Color(0x03A9F4 ));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
        add.setBackground(new Color(0xD6EAF8));
			}
		});
    add.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      
      try {
        
        String add1 = ID.getText();
        String add2 = ProductName.getText();
        Double add3 = Double.parseDouble(Price.getText());
        byte[] add5 = ProductImg;
        int add6 = category.getSelectedIndex();

        int x = JOptionPane.showConfirmDialog(null, "Are you sure to add product?", "Add Product", JOptionPane.YES_NO_OPTION);
        if (x==0) {         
        system.addProduct(add1,add2,add3,add5,add6); 
        }
      } catch(Exception e1) {
        JOptionPane.showMessageDialog(null, e1, "Error!", JOptionPane.ERROR_MESSAGE);
      }
      }
    });
    edit.setBounds(135, 440, 200, 40);
    edit.setFont(new Font("Consolas", Font.BOLD, 15));
    edit.setFocusable(false);
    edit.setBackground(new Color(0xD6EAF8));
    edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				edit.setBackground(new Color(0xA569BD ));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
        edit.setBackground(new Color(0xD6EAF8));
			}
		});
    edit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String edit1 = ID.getText();
          String edit2 = ProductName.getText();
          Double edit3 = Double.parseDouble(Price.getText());
          String edit5 = filename;
          int edit6 = category.getSelectedIndex();



          int x = JOptionPane.showConfirmDialog(null, "Are you sure to update record?", "Edit Product", JOptionPane.YES_NO_OPTION);
        if (x==0) {
          system.updateProduct(edit1, edit2, edit3, edit5, edit6);
        }
        } catch (Exception e1) {
          JOptionPane.showMessageDialog(null, e1, "Error!", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    addImage.setBounds(135, 490, 200, 40);
    addImage.setFont(new Font("Consolas", Font.BOLD, 15));
    addImage.setFocusable(false);
    addImage.setBackground(new Color(0xD6EAF8));
    addImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				addImage.setBackground(new Color(0xF1C40F));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
        addImage.setBackground(new Color(0xD6EAF8));
			}
		});
    addImage.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        
        try {
          
          filename = f.getAbsolutePath();
          File image = new File(filename);
          try (FileInputStream fix = new FileInputStream(image)) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];

            for(int number; (number = fix.read(buf)) != -1;) {

              bos.write(buf, 0, number);
            }
            ProductImg = bos.toByteArray();
          }
          JOptionPane.showMessageDialog(null, "Image has been added");
        } catch (Exception e1) {
          
        }
        
      }
    });
    remove.setBounds(135, 540, 200, 40);
    remove.setFont(new Font("Consolas", Font.BOLD, 15));
    remove.setFocusable(false);
    remove.setBackground(new Color(0xD6EAF8));
    remove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				remove.setBackground(new Color(0xE74C3C));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
        remove.setBackground(new Color(0xD6EAF8));
			}
		});
    remove.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String remove = ID.getText();
        try {
          int x = JOptionPane.showConfirmDialog(null, "Are you sure to remove product?", "Remove Product", JOptionPane.YES_NO_OPTION);
        if (x==0) {
          system.removeProduct(remove);
        }
        } catch (Exception e1) {
          JOptionPane.showMessageDialog(null, e1, "Error!", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    returnhome.setBounds(110, 590, 250, 40);
    returnhome.setFont(new Font("Consolas", Font.BOLD, 15));
    returnhome.setFocusable(false);
    returnhome.setBackground(new Color(0xD6EAF8));
    returnhome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				returnhome.setBackground(new Color(0xFBFCFC));
        frame.dispose();
        new AdminPage();
			}

		});

    panel.setBounds(85,380,300,260);
    panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
    panel.setBackground(new Color(0xd7e3fc));
    panel.setLayout(null);
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
    frame.setLayout(null);

		frame.setResizable(false);
    frame.setLocationRelativeTo(null);

    frame.setVisible(true);

    frame.add(invent);
    frame.add(categ);
    frame.add(category);
    frame.add(prodID);
    frame.add(prodName);
    frame.add(prodPrice);
    frame.add(ID);
    frame.add(ProductName);
    frame.add(Price);
    frame.add(Search);
    frame.add(search);
    frame.add(refresh);
    frame.add(addImage);
    frame.add(remove);
    frame.add(returnhome);
    frame.add(sp);
    frame.add(add);
    frame.add(edit);
    frame.add(panel);
    
    frame.add(bg1);
   

  }
  public static void main(String[] args) {
    new inventory();
  }
}