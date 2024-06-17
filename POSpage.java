
import java.util.*;
import java.util.Date;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class POSpage implements ActionListener{
  int id =1 ;
  Date currentdate = new Date();
  SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");

  
  PosSystem pos = new PosSystem();
  DecimalFormat df = new DecimalFormat("0.00");

  JFrame frame = new JFrame("JCCV's SYSTEM");
  JComboBox<String> paymentMethod = new JComboBox<String>();
  JTabbedPane tab = new JTabbedPane();
  JLabel bg1 = new JLabel();
  JLabel PM = new JLabel("Payment Method");
  JLabel total1 = new JLabel("Total");
  JLabel CASH = new JLabel("Cash");
  JLabel CHANGE = new JLabel("Change");
  JPanel Category = new JPanel();
  JPanel Category1 = new JPanel();
  JPanel Category2 = new JPanel();
  JPanel buttons = new JPanel();
  JPanel preview = new JPanel();
  JPanel PayRemNew = new JPanel();
  JPanel PTCC = new JPanel();
  static JTextField total = new JTextField();
  static JTextField cash = new JTextField("Enter Total Cash");
  JTextField change = new JTextField();
  JScrollPane scroll = new JScrollPane();
  static JTable table = new JTable();
  JButton one = new JButton("1");
  JButton two = new JButton("2");
  JButton three = new JButton("3");
  JButton four = new JButton("4");
  JButton five = new JButton("5");
  JButton six = new JButton("6");
  JButton seven = new JButton("7");
  JButton eight = new JButton("8");
  JButton nine = new JButton("9");
  JButton dot = new JButton(".");
  JButton zero = new JButton("0");
  JButton c = new JButton("C");
  static JButton pay = new JButton("PAY");
  JButton remove = new JButton("REMOVE");
  JButton edit = new JButton("CLEAR");
  JButton NewCostumer = new JButton("TOTAL");
  



public POSpage(){

    ImageIcon bg = new ImageIcon("icons/i.png");
    bg1.setIcon(bg);
    bg1.setBounds(0, 0, 1280, 680);

//For JTABLE AND SCROLLPANE
  
    table = new JTable();
    DefaultTableModel model = new DefaultTableModel();
    Object[] column = {"Name", "Quantity", "Price"};
    model.setColumnIdentifiers(column);
    table.setModel(model);
    JScrollPane sp = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JScrollPane sp1 = new JScrollPane(Category,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JScrollPane sp2 = new JScrollPane(Category1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JScrollPane sp3 = new JScrollPane(Category2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);    

    sp.setBounds(900,65,320,525);

//FOR FOOD TAB

    ArrayList<String> foods = new ArrayList<String>();
    foods.addAll(pos.getFoodList());

    Category.setLayout(new GridLayout(0,3,10,35));
    
    //for adding total price
    

    
    for(int i = 0; i < foods.size(); i++) {
      pos.searchProduct(foods.get(i));
      String price = df.format(pos.getProductPrice());
      byte[] byteIcon = pos.getProductImg();
      ImageIcon icon = new ImageIcon(new ImageIcon(byteIcon).getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH));      
      JButton bfood = new JButton();
      bfood.setPreferredSize(new Dimension(50,220));
      bfood.setFocusable(false);
      bfood.setIcon(icon);
      bfood.setIconTextGap(15);
      bfood.setText(foods.get(i)+" "+"₱"+price); //
      bfood.setHorizontalTextPosition(JButton.CENTER);
      bfood.setVerticalTextPosition(JButton.BOTTOM);
      bfood.setBackground(Color.WHITE);
      bfood.setName(foods.get(i));
      String name = foods.get(i);
      bfood.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String [] data = {name,"1",price};
          
          model.addRow(data);          
          total.setText(price);
        }
      });
     

 

        
        Category.add(bfood);
     
      

    }

  //FOR DRINK TAB

  Category1.setLayout(new GridLayout(0,3,10,35));
  ArrayList<String> drinks = new ArrayList<String>();
  drinks.addAll(pos.getDrinkList());

  
  for(int i = 0; i < drinks.size(); i++) {
    pos.searchProduct(drinks.get(i));
    String price = df.format(pos.getProductPrice());
    byte[] byteIcon = pos.getProductImg();
    ImageIcon icon = new ImageIcon(new ImageIcon(byteIcon).getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH));      
    JButton bdrink = new JButton();
    bdrink.setPreferredSize(new Dimension(150,220));
    bdrink.setFocusable(false);
    bdrink.setIcon(icon);
    bdrink.setIconTextGap(15);
    bdrink.setText(drinks.get(i)+" "+"₱"+price);
    bdrink.setHorizontalTextPosition(JButton.CENTER);
    bdrink.setVerticalTextPosition(JButton.BOTTOM);
    bdrink.setBackground(Color.WHITE);
    String name = drinks.get(i);
    bdrink.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String [] data = {name,"1",price};

        model.addRow(data);
        total.setText(price);
      }
      });

    bdrink.setName(drinks.get(i));
    Category1.add(drinks.get(i), bdrink);
  }

  //FOR DESSERT TAB

  Category2.setLayout(new GridLayout(0,3,10,35));
  ArrayList<String> dessert = new ArrayList<String>();
  dessert.addAll(pos.getDessertList());

  
  for(int i = 0; i < dessert.size(); i++) {
    pos.searchProduct(dessert.get(i));
    String price = df.format(pos.getProductPrice());
    byte[] byteIcon = pos.getProductImg();
    ImageIcon icon = new ImageIcon(new ImageIcon(byteIcon).getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH));      
    JButton bdessert = new JButton();
    bdessert.setPreferredSize(new Dimension(150,220));
    bdessert.setFocusable(false);
    bdessert.setIcon(icon);
    bdessert.setIconTextGap(15);
    bdessert.setText(dessert.get(i)+" "+"₱"+price);
    bdessert.setHorizontalTextPosition(JButton.CENTER);
    bdessert.setVerticalTextPosition(JButton.BOTTOM);
    bdessert.setBackground(Color.WHITE);
    String name = dessert.get(i);
    bdessert.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String [] data = {name,"1",price};
        model.addRow(data);
        total.setText(price);
      }
      });

    bdessert.setName(dessert.get(i));
    Category2.add(dessert.get(i), bdessert);
  }

    Category.setBackground(new Color(0xD1F2EB));
    Category1.setBackground(new Color(0xD1F2EB));
    Category2.setBackground(new Color(0xD1F2EB));

    tab.add("FOOD", sp1);
    tab.add("DRINK", sp2);
    tab.add("DESSERT", sp3);
    tab.setBounds(55, 45, 505, 620);
    tab.setVisible(true);
    tab.setFocusable(false);
 
//BUTTONS
    buttons.setBounds(580,290, 300,300);
    buttons.setBorder(new BevelBorder(BevelBorder.LOWERED));
    buttons.setBackground(new Color(0xd7e3fc));
    buttons.setLayout(null);
    seven.setBounds(20, 20, 80, 50);
    seven.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 25));
    seven.setFocusable(false);
    seven.addActionListener(this);
    eight.setBounds(110, 20, 80, 50);
    eight.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 25));
    eight.setFocusable(false);
    eight.addActionListener(this);
    nine.setBounds(200, 20, 80, 50);
    nine.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 25));
    nine.setFocusable(false);
    nine.addActionListener(this);
    four.setBounds(20, 90, 80, 50);
    four.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 25));
    four.setFocusable(false);
    four.addActionListener(this);
    five.setBounds(110, 90, 80, 50);
    five.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 25));
    five.setFocusable(false);
    five.addActionListener(this);
    six.setBounds(200, 90, 80, 50);
    six.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 25));
    six.setFocusable(false);
    six.addActionListener(this);
    one.setBounds(20, 160, 80, 50);
    one.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 25));
    one.setFocusable(false);
    one.addActionListener(this);
    two.setBounds(110, 160, 80, 50);
    two.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 25));
    two.setFocusable(false);
    two.addActionListener(this);
    three.setBounds(200, 160, 80, 50);
    three.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 25));
    three.setFocusable(false);
    three.addActionListener(this);
    zero.setBounds(20, 230, 80, 50);
    zero.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 25));
    zero.setFocusable(false);
    zero.addActionListener(this);
    dot.setBounds(110, 230, 80, 50);
    dot.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 25));
    dot.setFocusable(false);
    dot.addActionListener(this);
    c.setBounds(200, 230, 80, 50);
    c.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));
    c.setFocusable(false);
    c.addActionListener(this);

    PayRemNew.setBounds(580,597, 640, 70);
    PayRemNew.setBorder(new BevelBorder(BevelBorder.LOWERED));
    PayRemNew.setBackground(new Color(0xF5B7B1));
    PayRemNew.setLayout(null);
    pay.setBounds(10, 15, 110, 40);
    pay.setFont(new Font("Orange Fizz", Font.BOLD, 25));
    pay.setFocusable(false);
    pay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pay.setBackground(new Color(0x66BB6A));
			}
			@Override
			public void mouseExited(MouseEvent e) {
        pay.setBackground(new Color(0xD6EAF8));
			}
		});
    pay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pay.setBackground(new Color(0x66BB6A));
          
          
          try {
            //receipt
            File file = new File("receiptPOSpage.txt");
                    setGet s = new setGet();
                   FileWriter fw = new FileWriter(file);
                    PrintWriter pw = new PrintWriter(fw);
                    long time = System.currentTimeMillis();
                    java.sql.Date d = new java.sql.Date(time);
            //
            double getTotal = Double.parseDouble(total.getText());
            double getCash = Double.parseDouble(cash.getText());
            double getChange = getCash - getTotal;
            if(getCash<getTotal){
              JOptionPane.showMessageDialog(null, "Please count the cash properly");
            }else{
              int order =JOptionPane.showConfirmDialog(null, "Do you want to change anything?");
              if(order==1){
              change.setText(Double.toString(getChange));

            JOptionPane.showMessageDialog(null, "Ordered Confirmed");
            checkoutlenght co =  new checkoutlenght();

            for(int i = 0; i < co.checkLengthOut.size(); i++){
              id += 1;
            }
            try {
              //receipt
              s.setDate(d.toString());
                    pw.println("");
                    pw.println("JJVC Cafeteria" + "\n"+s.getDate());
                    pw.println("         \n");

              for(int i = 0; i < model.getRowCount(); i++){
                id+=1;
                String date = sdf.format(currentdate).toString();
                String prodName = model.getValueAt(i, 0).toString();
                int prodQuant = Integer.parseInt(model.getValueAt(i, 1).toString());
                String prodPrice = model.getValueAt(i, 2).toString();

                //Receipt
                //---------------------
                
              
                   s.setName(table.getValueAt(i, 0).toString());
                   s.setQuantity(table.getValueAt(i, 1).toString());
                   s.setPrice(table.getValueAt(i, 2).toString());
                   
                   
                    
                       
                      
                      pw.println(s.getName()+" "+ s.getQuantity()+" "+s.getPrice());
                      pw.println("");
                      System.out.println(s.getDate()+" "+s.getName()+" "+ s.getQuantity()+" "+s.getPrice());

                   

                new toStockOUT(id, date, prodName, prodQuant, Double.parseDouble(prodPrice));
                prodQuant = 0;
              }
            } catch (Exception a) {
              JOptionPane.showMessageDialog(null, a);
            }
            }  
            //receipt
          Double cas = Double.valueOf(cash.getText());
          Double tutal = Double.valueOf(total.getText());
          Double cinge = cas - tutal;
            pw.println("CASH:" + "       " + cash.getText());
            pw.println("TOTAL:" + "      " + total.getText());
            pw.println("CHANGE:" + "     " + cinge.toString());
             pw.close(); 

          //clear
          edit.setBackground(new Color(0xFFF176));
          change.setText("");
          total.setText("");
          cash.setText("");
          model.setRowCount(0);
          id = 0;
          }
          
          
            
          } catch (Exception ea) {
            JOptionPane.showMessageDialog(null, "Please fill up the blank");
          }
          

			}
			
		});
    remove.setBounds(135, 15, 130, 40);
    remove.setFont(new Font("Orange Fizz", Font.BOLD, 20));
    remove.setFocusable(false);
    remove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				remove.setBackground(new Color(0xD32F2F));
			}
			@Override
			public void mouseExited(MouseEvent e) {
        remove.setBackground(new Color(0xD6EAF8));
			}
		});
    remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove.setBackground(new Color(0xD32F2F));
        int removerow = table.getSelectedRow();
        model.removeRow(removerow);

			}

		});
    edit.setBounds(285, 15, 130, 40);
    edit.setFont(new Font("Orange Fizz", Font.BOLD, 25));
    edit.setFocusable(false);
    edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				edit.setBackground(new Color(0xFFF176));
			}
			@Override
			public void mouseExited(MouseEvent e) {
        edit.setBackground(new Color(0xD6EAF8));
			}
		});
    edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
        change.setText("");
        total.setText("");
        cash.setText("");
        model.setRowCount(0);
        id = 0;
			}
		});
    NewCostumer.setBounds(430,15, 200, 40);
    NewCostumer.setFont(new Font("Orange Fizz", Font.BOLD, 20));
    NewCostumer.setFocusable(false);
    NewCostumer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				NewCostumer.setBackground(new Color(0x81D4FA)); 

        int numrow = table.getRowCount();
        double totsum = 0;

        for(int i = 0; i <numrow;i++){
          double val = Double.valueOf(table.getValueAt(i,2).toString());
          totsum +=val;
        }

        total.setText(Double.toString(totsum));
        
			}
			@Override
			public void mouseExited(MouseEvent e) {
        NewCostumer.setBackground(new Color(0xD6EAF8));
			}
		});
    
   //LABELS and TextFields
   PTCC.setBounds(570, 65, 325, 200);
   PTCC.setBackground(new Color(0xF6DDCC));
   PTCC.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(128, 128, 128), Color.GRAY));

    PM.setBounds(580, -10, 200, 200);
    PM.setFont(new Font("Consolas",Font.BOLD,16));

     String[] JOBRANK = {"Select Payment Method","Cash" , "Debit Card" , "Gcash"};
     paymentMethod = new JComboBox<String>(JOBRANK);
     paymentMethod.setBounds(710, 75, 180, 30);
     paymentMethod.setFont(new Font("Consolas",Font.BOLD, 14));
     paymentMethod.setFocusable(false);	
    
    total.setBounds(715, 135, 170, 30);
    total.setFont(new Font("Consolas",Font.PLAIN, 15));
    total.setHorizontalAlignment(SwingConstants.CENTER);
    total.setEditable(false);
    cash.setBounds(715, 175, 170, 30);
    cash.setFont(new Font("Consolas",Font.PLAIN, 15));
    cash.setHorizontalAlignment(SwingConstants.CENTER);
    cash.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				 if (cash.getText().equals("Enter Total Cash")) {
          cash.setText("");					
							}
							 else {
                cash.selectAll();
							}
			      }
			@Override
			public void focusLost(FocusEvent e) {
			        if(cash.getText().equals("")){
			        	cash.setText("Enter Total Cash");
			        }		
			}
		});
    change.setBounds(715, 215, 170, 30);
    change.setFont(new Font("Consolas",Font.PLAIN, 15));
    change.setHorizontalAlignment(SwingConstants.CENTER);
    change.setEditable(false);


    total1.setBounds(605, 138, 170, 30);
    total1.setFont(new Font("Consolas",Font.BOLD, 20));
    CASH.setBounds(605, 178, 170, 30);
    CASH.setFont(new Font("Consolas",Font.BOLD, 20));
    CHANGE.setBounds(605, 218, 170, 30);
    CHANGE.setFont(new Font("Consolas",Font.BOLD, 20));


    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1296,720);
    frame.setLayout(null);
    frame.setResizable(false);
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
    
    buttons.add(seven);
    buttons.add(eight);
    buttons.add(nine);
    buttons.add(four);
    buttons.add(five);
    buttons.add(six);
    buttons.add(one);
    buttons.add(two);
    buttons.add(three);
    buttons.add(zero);
    buttons.add(dot);
    buttons.add(c);
    PayRemNew.add(pay);
    PayRemNew.add(edit);
    PayRemNew.add(remove);
    PayRemNew.add(NewCostumer);
    frame.add(PM);
    frame.add(paymentMethod);
    frame.add(total);
    frame.add(total1);
    frame.add(cash);
    frame.add(CASH);
    frame.add(change);
    frame.add(CHANGE);
    frame.add(sp);
    frame.add(PayRemNew);
    frame.add(PTCC);
    frame.add(buttons);
    frame.add(tab);
    frame.add(bg1);

  }
  public static void main(String[] args) {
    new POSpage();
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == zero){
      cash.setText(cash.getText() + "0");
    }
    if(e.getSource() == one){
      cash.setText(cash.getText() + "1");
    }
    if(e.getSource() == two){
      cash.setText(cash.getText() + "2");
    }
    if(e.getSource() == three){
      cash.setText(cash.getText() + "3");
    }
    if(e.getSource() == four){
      cash.setText(cash.getText() + "4");
    }
    if(e.getSource() == five){
      cash.setText(cash.getText() + "5");
    }
    if(e.getSource() == six){
      cash.setText(cash.getText() + "6");
    }
    if(e.getSource() == seven){
      cash.setText(cash.getText() + "7");
    }
    if(e.getSource() == eight){
      cash.setText(cash.getText() + "8");
    }
    if(e.getSource() == nine){
      cash.setText(cash.getText() + "9");
    }
    if(e.getSource() == dot){
      cash.setText(cash.getText() + ".");
    }
    if(e.getSource() == c){
      cash.setText("");
    }
    
  }

}

class toStockOUT{
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pst = null;
  toStockOUT(Integer id, String date, String name, Integer quantity, Double price){
    conn = db.java_db();
    String sql = "INSERT INTO stockOUT (ProdID, Date, ProductName, Quantity, Price) VALUES (?,?,?,?,?)";

        try {
                
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setString(2, date);
            pst.setString(3, name);
            pst.setInt(4, quantity);
            pst.setDouble(5, price);

            pst.execute();
            //JOptionPane.showMessageDialog(null,"Data is saved successfully");
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
  public static void main(String[] args) {
    new POSpage();
  }
}