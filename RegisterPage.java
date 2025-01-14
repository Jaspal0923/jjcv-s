/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class RegisterPage extends javax.swing.JFrame {
    String type;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    private static void ui() {
    }

      
    public RegisterPage() {
        initComponents();
        
        conn = db.java_db();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        g1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        userB = new javax.swing.JRadioButton();
        admin = new javax.swing.JRadioButton();
        pass = new javax.swing.JTextField();
        user = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        reg = new javax.swing.JButton();
        back = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Register");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, 50));

        jLabel3.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("UserName");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, 60));

        g1.add(userB);
        userB.setText("User");
        jPanel1.add(userB, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, -1, -1));

        g1.add(admin);
        admin.setForeground(new java.awt.Color(51, 51, 51));
        admin.setText("Admin");
        jPanel1.add(admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, -1, -1));
        jPanel1.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 150, 30));
        jPanel1.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 150, 30));

        jLabel4.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("PassWord");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, 60));

        reg.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        reg.setForeground(new java.awt.Color(51, 51, 51));
        reg.setText("Register");
        reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regActionPerformed(evt);
            }
        });
        jPanel1.add(reg, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, -1, -1));

        back.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        back.setForeground(new java.awt.Color(51, 51, 51));
        back.setText("Back");
        back.setPreferredSize(new java.awt.Dimension(113, 31));
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        jPanel1.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, -1, -1));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 490, 270);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("icons/pic.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 490, 300);

        setSize(new java.awt.Dimension(501, 308));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regActionPerformed
    try{
    
        String sql = "INSERT into users(userID, password, type)values(?,?,?)";

        pst = conn.prepareStatement(sql);
        pst.setString(1, user.getText());
        pst.setString(2, pass.getText());
        if(admin.isSelected()){
        
        type = "admin";
        }if(userB.isSelected()){        
        type = "user";       
        }
        pst.setString(3, type);
         
        pst.execute();
        JOptionPane.showMessageDialog(null,"inserted succesfully");
         
        } catch(SQLException e) {
    
            JOptionPane.showMessageDialog(null,e,null,JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (Exception e) {

            }
        }
       
        
        
    }//GEN-LAST:event_regActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        
        AdminPage admin = new AdminPage();
        admin.frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        ui();//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegisterPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JRadioButton admin;
    public javax.swing.JButton back;
    public javax.swing.ButtonGroup g1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JTextField pass;
    public javax.swing.JButton reg;
    public javax.swing.JTextField user;
    public javax.swing.JRadioButton userB;
    // End of variables declaration//GEN-END:variables
}
