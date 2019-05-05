package View;

/*
 * 功能：服务器启动界面。
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
	JButton start = new JButton("启动服务器");
	JButton stop = new JButton("关闭服务器");
	boolean run =false;
//	Server_Thread server = new Server_Thread();
	
	Server_AccountManage check = new Server_AccountManage();

	public static void main(String[] args) 
	{
		   java.awt.EventQueue.invokeLater(new Runnable() 
		   {
		        public void run() 
		        {

		            //写在要运行代码的最前端
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
		
		start.setFont(new Font("微软雅黑",Font.PLAIN,20));
		stop.setFont(new Font("微软雅黑",Font.PLAIN,20));
		
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
				JLabel title = new JLabel("服务器已启动请勿重复启动");
				title.setFont(new Font("微软雅黑", Font.BOLD, 15));
//				System.out.println("服务器已启动请勿重复启动");
				JOptionPane.showMessageDialog(null,title,"提示", 2,new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\False.png"));
			}
		}
		
		if (event.getSource()==stop)
		{
			System.exit(0);
		}
	}
}