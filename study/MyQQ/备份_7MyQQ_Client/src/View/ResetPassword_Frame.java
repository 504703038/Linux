package View;

/*
 * ÕÒ»ØÃÜÂë½çÃæ¡£
 */

import javax.swing.*;
import javax.swing.text.*;
import Bussiness.*;
import Common.*;
import java.awt.*;
import java.awt.event.*;

public class ResetPassword_Frame extends JFrame implements ActionListener
{
	private JTextField account = new JTextField(15);
	private JTextField phonenumber = new JTextField(16);
	private JPasswordField newpassword = new JPasswordField(15);
	private JPasswordField confirmnewpassword = new JPasswordField(15);
	private JButton reset = new JButton("È·  ÈÏ");
	private JLabel laccount = new JLabel("       ÕËºÅ£º");
	private JLabel lphonenumber = new JLabel("  ÊÖ»úºÅÂë:");
	private JLabel lnewpassword = new JLabel("   ĞÂÃÜÂë£º   ");
	private JLabel lconfirmnewpassword = new JLabel("È·ÈÏĞÂÃÜÂë£º");
	private JPanel pnewpassword = new JPanel();
	private JPanel pconfirmnewpassword = new JPanel();
	private JPanel paccount = new JPanel();
	private JPanel pphonenumber = new JPanel();
	private JPanel preset = new JPanel();
	private User user = new User();
	
	public ResetPassword_Frame()
	{
		super("ÕÒ»ØÃÜÂë");
		this.setResizable(true);
		this.setBounds(670,250,600,600);
		this.setLayout(new GridLayout(7,1));
		this.setIconImage(new ImageIcon("E:\\±à³Ì\\JAVA\\³ÌĞò\\MyQQ_Client\\src\\com.client.Images\\qq.png").getImage());
		//ÉèÖÃ×ÖÌå
		reset.setFont(new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,20));
		laccount.setFont(new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,20));
		lphonenumber.setFont(new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,20));
		lnewpassword.setFont(new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,20));
		lconfirmnewpassword.setFont(new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,20));
		phonenumber.setFont(new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,20));
		account.setFont(new Font("Î¢ÈíÑÅºÚ",Font.BOLD,20));
		newpassword.setFont(new Font("Î¢ÈíÑÅºÚ",Font.BOLD,20));
		confirmnewpassword.setFont(new Font("Î¢ÈíÑÅºÚ",Font.BOLD,20));
		newpassword.setEchoChar('¡ñ');
		confirmnewpassword.setEchoChar('¡ñ');
		reset.setSize(50,50);
		
		//Ìí¼Ó×é¼ş
		pnewpassword.add(lnewpassword);
		pnewpassword.add(newpassword);
		pconfirmnewpassword.add(lconfirmnewpassword);
		pconfirmnewpassword.add(confirmnewpassword);
		paccount.add(laccount);
		paccount.add(account);
		pphonenumber.add(lphonenumber);
		pphonenumber.add(phonenumber);
		preset.add(reset);
		reset.addActionListener(this);
		this.add(new JPanel());
		this.add(paccount);
		this.add(pphonenumber);
		this.add(pnewpassword);
		this.add(pconfirmnewpassword);
		this.add(preset);
		this.add(new JPanel());
		this.setVisible(true);
		this.setDefaultCloseOperation(2);
	}
	public void actionPerformed(ActionEvent e) 
	{
		new ResetPassword(user);
		dispose();
	}
}
