package View;

/*
 * ���ܣ��������������档
 */

import Bussiness.*;

import javax.swing.*;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.painter.border.StandardBorderPainter;
import org.pushingpixels.substance.api.shaper.ClassicButtonShaper;
import org.pushingpixels.substance.api.skin.EmeraldDuskSkin;

import java.awt.*;
import java.awt.event.*;

public class Server_Frame extends JFrame implements ActionListener
{
	JPanel panel = new JPanel();
	JButton start = new JButton("����������");
	JButton stop = new JButton("�رշ�����");
	boolean run =false;
//	Server_Thread server = new Server_Thread();
	
	Server_AccountManage check = new Server_AccountManage();

	public static void main(String[] args) 
	{
		   java.awt.EventQueue.invokeLater(new Runnable() 
		   {
		        public void run() 
		        {

		            //д��Ҫ���д������ǰ��
		            try{
		                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()) ;
		                new Server_Frame();
		            } catch (Exception e){ }
		        }
		    });
	}
	public Server_Frame()
	{
		this.setBounds(700,350,500,400);
		this.add(panel);
		
		panel.add(start);
		panel.add(stop);
		
		start.addActionListener(this);
		stop.addActionListener(this);
		
		start.setFont(new Font("΢���ź�",Font.PLAIN,20));
		stop.setFont(new Font("΢���ź�",Font.PLAIN,20));
		
		this.setVisible(true);;
		this.setDefaultCloseOperation(3);
	}

	public void actionPerformed(ActionEvent event) 
	{
		
		if (event.getSource()==start)
		{
			if (!run)
			{
				run=true;
				check.start();
			}
			else 
			{
				JLabel title = new JLabel("�����������������ظ�����");
				title.setFont(new Font("΢���ź�", Font.BOLD, 15));
//				System.out.println("�����������������ظ�����");
				JOptionPane.showMessageDialog(null,title,"��ʾ", 2,new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\False.png"));
			}
		}
		
		if (event.getSource()==stop)
		{
			System.exit(0);
		}
	}
}