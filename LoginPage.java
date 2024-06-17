import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginPage{

  Connection conn = null;
  PreparedStatement pst = null;
  ResultSet rs = null;
   
  JFrame frame = new JFrame("LoginPage");
  JLabel Header = new JLabel();
  JLabel Header2 = new JLabel();
  JLabel Header3 = new JLabel();
  JLabel label = new JLabel();
  JLabel BG1 = new JLabel();
  JLabel BG2 = new JLabel();
  JLabel BG3 = new JLabel();
  JLabel BG4 = new JLabel();
  JPanel MainPanel = new JPanel();
  JPanel TextPanel = new JPanel();
  JPanel PasswordPanel = new JPanel();
  JPanel logINPanel = new JPanel();
  JTextField userName = new JTextField();
  JPasswordField passwordField = new JPasswordField();
  JButton login = new JButton("LOGIN");
  
  public LoginPage() {
    
    conn = db.java_db();
    ImageIcon BackGround = new ImageIcon("icons/b1.png");
    ImageIcon user = new ImageIcon("icons/ser.png");
    ImageIcon pass = new ImageIcon("icons/password.png");
    ImageIcon key = new ImageIcon("icons/key.png");

    Header.setText("Welcome to ");
    Header.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 40));
    Header.setBounds(760, -50, 300, 300);

    label.setText("(Your SATISFACTION is our TOP PRIORITY)");
    label.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
    label.setForeground(Color.GRAY);
    label.setBounds(763, 41, 500, 300);

    Header2.setText("JJCV's");
    Header2.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 40));
    Header2.setBounds(730, -45, 500, 400);
    Header2.setForeground(new Color(0xB670F));
    Header3.setText("SYSTEM");
    Header3.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 40));
    Header3.setBounds(895, -45, 500, 400);
    Header3.setForeground(new Color(0xb2912e));
    
    userName.setFont(new Font("Consolas", Font.PLAIN,25));
    userName.setBounds(808, 255, 220, 30);
    userName.setText("UserName");
    userName.setBorder(null);
    userName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				 if (userName.getText().equals("UserName")) {
          userName.setText("");					
				}
				 else {
					userName.selectAll();
				}
			}
		});
    passwordField.setFont(new Font("Consolas", Font.PLAIN,25));
    passwordField.setBounds(810, 340, 220, 30);
    passwordField.setText("Password");
    passwordField.setBorder(null);
    passwordField.setEchoChar((char)0);
    passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				passwordField.setEchoChar('●');
				 if (passwordField.getText().equals("Password")) {
          passwordField.setText("");					
				}
				 else {
          passwordField.selectAll();
				}
			}
		});
    TextPanel.setBounds(755, 246, 280, 40);
    TextPanel.setBackground(Color.WHITE);
    TextPanel.add(BG2);
    TextPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
     
		login.setBounds(810,445,180,40);
    login.setBackground(Color.WHITE);
    login.setFont(new Font("Consolas", Font.BOLD,20));
		login.setFocusable(false);
    PasswordPanel.setBounds(755,330, 285, 45);
    PasswordPanel.setBackground(Color.WHITE);
    PasswordPanel.add(BG3);
    PasswordPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

    BG1.setIcon(BackGround);
    BG2.setIcon(user);
    BG3.setIcon(pass);
    BG4.setIcon(key);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1280,720);
    frame.setResizable(false);
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
   
    frame.add(Header);
    frame.add(label);
    frame.add(Header2);
    frame.add(Header3);
    frame.add(TextPanel);
    frame.add(userName);
    frame.add(passwordField);
    frame.add(PasswordPanel);
    frame.add(login);
    frame.add(BG1);

  }

  public static void main(String[] args) {
    new LoginSystem();    
  }
}