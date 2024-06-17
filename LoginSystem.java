
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;

public class LoginSystem extends LoginPage{
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public LoginSystem(){
        conn = db.java_db();
         login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              String sql =  "SELECT * FROM users WHERE userID =? AND password =?";
              try{                    
                    String username = userName.getText();
                    String password = passwordField.getText();
                    
                    pst = conn.prepareStatement(sql);
                    pst.setString(1,username);
                    pst.setString(2,password);
                    rs = pst.executeQuery();
                    if(rs.next()){
                      
                     String identifier = rs.getString("type");
                      
                      pst.close();
                      rs.close();
                      if(identifier.equals("admin")){
                        //call adminPage
                        Admin admin = new Admin(username, password);
                        admin.useAdminPage();
                        frame.dispose();
                      
                      } else{
                        //call posPage
                        User user = new User(username,password);
                        user.usePoSPage();
                        frame.dispose();
                                }
                    }else{
                            JOptionPane.showMessageDialog(null, "Incorrect Fields",null,JOptionPane.ERROR_MESSAGE);
                      }                                
              }catch(SQLException e1){
                JOptionPane.showMessageDialog(null, e1, null, JOptionPane.WARNING_MESSAGE);
              }
               
            }
        });
        
        
    
    
    
    }
    
    
    
}
