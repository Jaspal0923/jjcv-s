import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.sql.*;
public class addStockWindow extends store{

    int prodid;

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    JLabel stockAdd = new JLabel("Stock Add");
    JTextField stockAddField = new JTextField(20);
    ImageIcon BackGround = new ImageIcon("pic.png");
    JLabel background = new JLabel();
    JFrame frame = new JFrame();

    PosSystem pos = new PosSystem();
    
    

    JButton confirm = new JButton("Confirm");
    JButton cancel = new JButton("cancel");


    JDateChooser dateChooser = new JDateChooser();

    SimpleDateFormat dateForm = new SimpleDateFormat("YYYY-MM-dd");
    JLabel selectDate = new JLabel("Select Date");
    
    
    String upName;
    int quant;
    boolean access;
    addStockWindow(){
        selectDate.setBounds(35,30,150,40);
        selectDate.setFont(new Font("Heavitas", Font.PLAIN, 20));
        frame.add(selectDate);

        dateChooser.setBounds(200,30,200,40);
        frame.add(dateChooser);

        ArrayList<String> allList = new ArrayList<>();
        
        //adding all to array list
        allList.addAll(pos.getFoodList());
        allList.addAll(pos.getDrinkList());
        allList.addAll(pos.getDessertList());

        String[] foodTest = allList.toArray(new String[0]);
        JLabel selectItem = new JLabel("Select Item");
        JComboBox<String> itemList = new JComboBox<>(foodTest);

        conn = db.java_db();
        background.setIcon(BackGround);
        background.setBounds(0,0,500,300);
        frame.add(background);

        selectItem.setBounds(35,45,150,100);
        selectItem.setFont(new Font("Heavitas", Font.PLAIN, 20));
        frame.add(selectItem);

        itemList.setBounds(200,75,200,40);
        itemList.setPreferredSize(new Dimension(200,30));
        frame.add(itemList);

        stockAdd.setBounds(50,90,150,100);
        stockAdd.setFont(new Font("Heavitas", Font.PLAIN, 20));
        frame.add(stockAdd);

        stockAddField.setBounds(200,120,200,40);
        stockAddField.setPreferredSize(new Dimension(200,30));
        frame.add(stockAddField);
        
        confirm.setBounds(200,200,100,50);
        confirm.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    quantity = Integer.parseInt(stockAddField.getText());
                    name = itemList.getSelectedItem().toString();
                    for(int i = 0; i<checkLength.size(); i++){
                        prodid +=1;
                    }
                    
                    stockInModel.addRow(new Object[]{
                        prodid,
                        dateForm.format(dateChooser.getDate()),
                        name,
                        quantity,
                    });
                    String sql = "INSERT INTO stockIN (Date, ProductName, Quantity,ProdID) VALUES (?,?,?,?)";

                    try {
                            
                        pst = conn.prepareStatement(sql);
            
                        pst.setString(1, dateForm.format(dateChooser.getDate()));
                        pst.setString(2, name);
                        pst.setInt(3, quantity);
                        pst.setInt(4,prodid);


            
                        pst.execute();
                        JOptionPane.showMessageDialog(null,"Data is saved successfully");
                    } catch (Exception a) {
                        
                    } finally {
                        try {
                            pst.close();
                        } catch (Exception a) {
                            System.out.println(a);
                        }
                    }
                    if(dateForm.format(dateChooser.getDate()) != null && stockAddField.getText() == ""){
                        access = false;
                    }else{
                        access = true;
                    }
                    frame.dispose();
                    store st = new store();
                    st.frame.dispose();
                } catch (Exception a) {
                    
                    JOptionPane.showMessageDialog(null, "Please fill the blank");
                }
               if(access == true){
                 //search
                 
               }
            }
               
            });

        frame.add(confirm);

        cancel.setBounds(300,200,100,50);
        cancel.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                frame.dispose();
            }
        });
        frame.add(cancel);

        
        background.setIcon(BackGround);
        frame.add(background);
        frame.setSize(500,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new addStockWindow();
    }

    
    
}
