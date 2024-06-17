import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class AdminPage {
	JLabel label;
	JFrame frame;
	JLabel Header = new JLabel();
  JButton button1 = new JButton();
	JButton button2 = new JButton();
	JButton button3 = new JButton();
	JButton button4 = new JButton();
	JButton button5 = new JButton();
	
	public AdminPage() {
		
		label = new JLabel();
		frame = new JFrame();
		
		ImageIcon icon = new ImageIcon("icons/Welcome.png");
		ImageIcon store = new ImageIcon("icons/store.png");
		ImageIcon exit = new ImageIcon("icons/exit.png");
		ImageIcon REG = new ImageIcon("icons/REG.png");
		ImageIcon invent = new ImageIcon("icons/inventory.png");
		
		Header.setText("Welcome ADMIN! ");
		Header.setBounds(350, -240, 600, 650);
		Header.setFont(new Font("Copperplate Gothic Bold" , Font.PLAIN , 60));
		
   
		button1.setBounds(350,180,230,150);
		button1.setIcon(store);
    	button1.setBackground(new Color(0xFF6962));
		button1.setFocusable(false);
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				new store();
			}
		});
		
		button2.setBounds(650,180,230,150);
		button2.setIcon(REG);
    	button2.setBackground(new Color(0xFFB347));
		button2.setFocusable(false);
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				new RegisterPage().setVisible(true);
			}
		});
		
		
		button4.setBounds(350,380,230,150);
		button4.setIcon(invent);
    	button4.setBackground(new Color(0xE1B894));
		button4.setFocusable(false);
		button4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				new inventory();
			}
		});
		
		button5.setBounds(650,380,230,150);
		button5.setIcon(exit);
    	button5.setBackground(new Color(0xE9E2D7));
		button5.setFocusable(false);
		button5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
			}
		});
		
		label.setIcon(icon);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(button1);
		frame.add(button2);
		frame.add(button3);
		frame.add(button4);
		frame.add(button5);
		frame.add(Header);
		frame.add(label);
		
		
	}
  public static void main(String[] args) {
    new AdminPage();
  }
}
