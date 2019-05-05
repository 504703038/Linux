package View;

/*
 * 聊天界面.
 */

import Bussiness.*;
import Common.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Chat_Frame extends JFrame implements ActionListener,MouseListener,KeyListener
{
	JTextArea tarea1 = new JTextArea();			//聊天信息显示框
	JTextArea tarea2 = new JTextArea();			//输入框
	JPanel panel1 = new JPanel(new BorderLayout());
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();  
	JPanel panel4 = new JPanel();
	JScrollPane jspanel_tarea1 = new JScrollPane(tarea1);
	JScrollPane jspanel_tarea2 = new JScrollPane(tarea2);
	
	JToolBar toolbar = new JToolBar();
	JButton biaoqing = new JButton(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\聊天界面\\工具栏\\表情.png"));
	JButton doudong = new JButton(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\聊天界面\\工具栏\\抖动窗口.png"));
	JButton chuanwenjian = new JButton(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\聊天界面\\工具栏\\传送文件.png"));
	JButton liaotianjilu = new JButton(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\聊天界面\\工具栏\\聊天记录.png"));
	
	JButton sent_message = new JButton("发送");
	JButton close_this_frame = new JButton("关闭");
	
	User user = new User();
	User receiver = new User();
	
	SendMessagesToServer smts = new SendMessagesToServer();
	
	SimpleDateFormat time;  //用于获取当前时间

	public Chat_Frame(User user,User receiver)
	{
		//初始化
		super(user.getNickname()+"  正在和   "+receiver.getNickname()+"  聊天");
		this.user=user;
		this.receiver=receiver;
		
		//设置布局大小
		this.setBounds(450,200,1000,750);
		this.setLayout(new BorderLayout());
		this.setIconImage(new ImageIcon(".\\bin\\Images\\qq.png").getImage());
		
		//工具条设置
		biaoqing.addActionListener(this);
		doudong.addActionListener(this);
		chuanwenjian.addActionListener(this);
		liaotianjilu.addActionListener(this);
		
		toolbar.add(biaoqing);
		toolbar.add(doudong);
		toolbar.add(chuanwenjian);
		toolbar.add(liaotianjilu);
		toolbar.setFloatable(false);
		toolbar.setRollover(true);
		
		//将文本域设置为不可编辑
		tarea1.setEditable(false);
		
		//自动换行
		tarea1.setLineWrap(true);
		tarea2.setLineWrap(true);
		
		//设置布局
		panel2.setLayout(new BorderLayout());
		panel4.setLayout(new BorderLayout());
		panel3.setBackground(Color.WHITE);
		panel3.setLayout(new FlowLayout(2));
		
		//滚动面板去边框
		jspanel_tarea1.setBorder(null);
		jspanel_tarea2.setBorder(null);
		
		//设置大小
		jspanel_tarea1.setPreferredSize(new Dimension(800,500));
		jspanel_tarea2.setPreferredSize(new Dimension(800,500));
		toolbar.setPreferredSize(new Dimension(800,40));
		panel3.setPreferredSize(new Dimension(800,40));
		
		//添加组件
		panel3.add(sent_message);
		panel3.add(close_this_frame);
		panel2.add(toolbar,"North");
		panel2.add(jspanel_tarea2,"Center");
		panel2.add(panel3,"South");
		panel4.add(jspanel_tarea1,"North");
		panel4.add(panel2,"Center");
		this.add(panel4,BorderLayout.CENTER);
		
		//添加QQ秀
		panel1.setPreferredSize(new Dimension(200,700));
		JLabel show1 = new JLabel(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\聊天界面\\QQ秀\\QQ秀_女.jpg"));
		JLabel show2 = new JLabel(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\聊天界面\\QQ秀\\QQ秀_男.jpg"));
		panel1.add(show1,"North");
		panel1.add(show2,"South");
		this.add(panel1,BorderLayout.EAST);
		
		//字体设置
		sent_message.setFont(new Font("微软雅黑",Font.PLAIN,15));
		close_this_frame.setFont(new Font("微软雅黑",Font.PLAIN,15));
		tarea1.setFont(new Font("微软雅黑",Font.PLAIN,20));
		tarea2.setFont(new Font("微软雅黑",Font.PLAIN,20));

		//添加监听器
		close_this_frame.addActionListener(this);
		sent_message.addActionListener(this);
		tarea1.addMouseListener(this);
		tarea2.addKeyListener(this);
		
		//设置可见
		this.setVisible(true);

		//关闭聊天界面
		this.addWindowListener(new WindowListener()
		{
	
			public void windowActivated(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {}
			public void windowClosing(WindowEvent e) 
			{
				Manage_ChatFrame.remove(user.getId()+"&"+receiver.getId());
				dispose();
			}
			public void windowDeactivated(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}
			
		});
		
}
	

//发送信息
	public void SendMessages(String s)
	{
		time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UserInfoBean uib = new UserInfoBean();
		uib.setMesType("4");
		uib.setMessage(s);
		uib.setSender(user.getId());
		uib.setReceiver(receiver.getId());
		uib.setSendTime(time.format(new Date()));
		smts.SendMessageTOServer(uib);
		this.showMessages(uib);
	}
	
//显示发送和接收到的信息
	public void showMessages(UserInfoBean uib)
	{
		String info =uib.getSendTime()+"  "+uib.getSender()+" :\n"+uib.getMessage()+"\n\n";
		tarea1.append(info);
	}
	
	
//按钮监听器
	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource()==close_this_frame)
		dispose();
		if (event.getSource()==sent_message)
		{
			
			this.SendMessages(tarea2.getText());
			tarea2.setText("");
		}
		
		if (event.getSource()==biaoqing)
		{
			
		}
		if (event.getSource()==doudong)
		{
			
		}
		if (event.getSource()==chuanwenjian)
		{
			JFileChooser fileChooser = new JFileChooser("D:\\");
			fileChooser.setApproveButtonText("确定");
			fileChooser.setDialogTitle("选择文件");
			int result=fileChooser.showOpenDialog(this);
			File file = null;
			if(result==fileChooser.APPROVE_OPTION) 
			{
				file = fileChooser.getSelectedFile();
				System.out.println(file.getPath()+"\n"+file.getName()+"\n");
			}
			else if(result==fileChooser.CANCEL_OPTION)
			{
				System.out.println("没有选择文件");
			}
			
//			读取文本文档中的内容并输出:
//			int i;
//			try 
//			{
//				FileInputStream fin = new FileInputStream(file);
//				FileOutputStream fout = FileOutputStream(file);
//				while((i=fin.read())!=-1) 
//				{  
//	                System.out.print(String.valueOf((char)i));  
//				}
//			}
//			catch (Exception e) 
//			{
//				e.printStackTrace();
//			}  
//			fin.close();
//			fout.close();
		}
		if (event.getSource()==liaotianjilu)
		{
			
		}
	}

	
//鼠标监听器
	public void mouseClicked(MouseEvent event) 
	{
		
		
	}
	public void mouseEntered(MouseEvent event) 
	{
		if (event.getSource()==tarea1)
		{
			//鼠标进入Text区后变为文本输入指针
			tarea1.setCursor(new Cursor(Cursor.TEXT_CURSOR));
			
		}
	}
	public void mouseExited(MouseEvent event) 
	{
		//鼠标离开Text区后恢复默认形态
		tarea1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}

//键盘监听器
	public void keyPressed(KeyEvent event) {}
	public void keyReleased(KeyEvent event) 
	{
		if (event.getSource()==tarea2)
		{
			if (event.getKeyCode()==KeyEvent.VK_ENTER)
			{
				this.SendMessages(tarea2.getText());
				this.tarea2.setText("");
			}
		}
	}
	public void keyTyped(KeyEvent event) {}
	
	public static void main(String[] args)
	{
		User u1 = new User();
		User u2 = new User();
		u1.setNickname("1");
		u2.setNickname("2");
		new Chat_Frame(u1,u2);
	}
	
}
