package View;

/*
 * 聊天界面.
 */

import Bussiness.*;
import Common.*;

import javax.swing.*;
import javax.swing.text.Document;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Chat_Frame extends JFrame implements ActionListener,MouseListener,KeyListener
{
	boolean f_emo=false;
	boolean f_history=false;
	boolean f_draw=false;
	JWindow window,history_window;
	DrawingBoard drawingboard;
	My_JTextPane_1 textpane1 = new My_JTextPane_1();	//聊天信息显示框
	My_JTextPane_1 history_pane;
	My_JTextPane_2 textpane2 = new My_JTextPane_2();	//输入框
	JPanel panel1 = new JPanel(new BorderLayout());
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();  
	JPanel panel4 = new JPanel();
	JScrollPane jspanel_tarea1 = new JScrollPane(textpane1);
	JScrollPane jspanel_tarea2 = new JScrollPane(textpane2);
	JToolBar toolbar = new JToolBar();
	JButton biaoqing = new JButton(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\聊天界面\\工具栏\\表情.png"));
	JButton doudong = new JButton(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\聊天界面\\工具栏\\抖动窗口.png"));
	JButton chuanwenjian = new JButton(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\聊天界面\\工具栏\\传送文件.png"));
	JButton liaotianjilu = new JButton(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\聊天界面\\工具栏\\聊天记录.png"));
	JButton paint = new JButton(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\聊天界面\\工具栏\\画图.png"));
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
		this.setIconImage(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\qq.png").getImage());
		this.setResizable(false);
		//工具条设置
		biaoqing.addActionListener(this);
		doudong.addActionListener(this);
		chuanwenjian.addActionListener(this);
		liaotianjilu.addActionListener(this);
		paint.addActionListener(this);
		biaoqing.setBackground(Color.white);
		doudong.setBackground(Color.white);
		chuanwenjian.setBackground(Color.white);
		liaotianjilu.setBackground(Color.white);
		paint.setBackground(Color.white);
		toolbar.setBackground(Color.white);
		toolbar.setBorder(BorderFactory.createEtchedBorder());
		//工具条按钮去边框
		biaoqing.setBorder(null);
		doudong.setBorder(null);
		chuanwenjian.setBorder(null);
		liaotianjilu.setBorder(null);
		paint.setBorder(null);
		toolbar.setFloatable(false);
		toolbar.add(biaoqing);
		toolbar.add(doudong);
		toolbar.add(chuanwenjian);
		toolbar.add(liaotianjilu);
		toolbar.add(paint);
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
		toolbar.setPreferredSize(new Dimension(800,40));
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
		textpane2.setFont(new Font("微软雅黑",Font.PLAIN,20));
		//添加监听器
		close_this_frame.addActionListener(this);
		sent_message.addActionListener(this);
		textpane1.addMouseListener(this);
		textpane2.addKeyListener(this);
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
				if (window!=null)
				window.dispose();
				if (history_window!=null)
				history_window.dispose();
				dispose();
			}
			public void windowDeactivated(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}
		});
}
	
//窗口抖动
	public  void shake()
	{
		Point start=this.getLocation();
		int t=0;
		while (t<=3)
		{
			int[][] a = {{-1,1},{-2,2},{-3,3},{-4,4},{-5,5},{-6,4},{-7,3},{-8,2},{-9,1},{-10,0},{-9,-1},{-8,-2},{-7,-3},{-6,-4},{-5,-5},{-4,-4},{-3,-3},{-2,-2},{-1,-1},{0,0}};
			for(int j=19;j>=0;j--)
			{
				for(int i=1;i<20000000;i++){}
				
				this.setLocation(start.x+a[j][0], start.y+a[j][1]);
			}
			t++;
		}
		this.setLocation(start);
	}

//发送信息
	public void SendMessages(String MesType,String Message)
	{
		if (Message.equals("")||Message.equals("\n")) return;
		UserInfoBean uib = new UserInfoBean();
		uib.setMesType(MesType);
		uib.setMessage(Message);
		uib.setSender(user.getId());
		uib.setReceiver(receiver.getId());
		uib.setSendTime(new Date());
		uib.setSender_nickname(user.getNickname());
		smts.SendMessageTOServer(uib);
		if (!MesType.equals("4_7") && !MesType.equals("4_8") && !MesType.equals("4_1"))
		this.showMessages(uib);
	}
	
//显示发送和接收到的信息
	public void showMessages(UserInfoBean uib)
	{
		time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String info =time.format(uib.getSendTime())+"  "+uib.getSender_nickname()+" :\n"+uib.getMessage()+"\n";
		textpane1.insert(info);
	}
	
//显示历史记录
	public void showHistoryMessages(UserInfoBean uib)
	{
		time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String info =time.format(uib.getSendTime())+"  "+uib.getSender_nickname()+" :\n"+uib.getMessage()+"\n";
		history_pane.insert(info);
	}
	
	
//接收文件
	public String receiveFile() 
	{
		int f = JOptionPane.showConfirmDialog(null, "对方向你发送了一个文件，是否接收？", "提示",JOptionPane.YES_NO_OPTION);
		if (f==1)
		return null;
		String path=null;
		JFileChooser fileChooser = new JFileChooser("D:\\");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setApproveButtonText("保存");
		fileChooser.setDialogTitle("选择文件文件夹");
		int returnVal = fileChooser.showOpenDialog(fileChooser);
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			path = fileChooser.getSelectedFile().getAbsolutePath();
		}
		return path;
	}
	
//按钮监听器
	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource()==close_this_frame) {Manage_ChatFrame.remove(user.getId()+"&"+receiver.getId()); dispose();}
		if (event.getSource()==sent_message)
		{
			textpane2.insertIcon("");
			this.SendMessages("4",textpane2.get_message());
			textpane2.setText("");
			textpane2.reset_message();
		}
		if (event.getSource()==biaoqing) 
		{
			if (f_emo)
			{
				window.dispose();
				f_emo=false;
				return;
			}
			f_emo=true;
			window = new JWindow();
			window.setSize(new Dimension(550,350));
			window.setBackground(Color.WHITE);
			window.setLocation(this.getX(), this.getY()+180);
			window.setLayout(new GridLayout(6,12));
			JButton[] emo = new JButton[75];
			for(int i=1;i<=72;i++)
			{
				emo[i] = new JButton(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\聊天界面\\工具栏\\表情\\1\\表情"+i+".png"));
				emo[i].setName(""+i);
				emo[i].setBorder(null);
				emo[i].addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent e) 
						{
							textpane2.insertIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\聊天界面\\工具栏\\表情\\1\\表情"+((JButton)e.getSource()).getName()+".png");
							window.dispose();
							f_emo=false;
						}
					});
				window.add(emo[i]);
			}
			
			window.addFocusListener(new FocusListener() 
				{
					public void focusGained(FocusEvent arg0) {}
					public void focusLost(FocusEvent arg0) 
					{dispose();}
				});
			
			window.setVisible(true);
		}
		if (event.getSource()==doudong) 
		{
			
			SendMessages("4_2","您发送了一个窗口抖动。\n");
			this.shake();
		}
		//发送文件
		if (event.getSource()==chuanwenjian)
		{
			
			JFileChooser fileChooser = new JFileChooser("D:\\");
			File file = null;
			
			fileChooser.setApproveButtonText("确定");
			fileChooser.setDialogTitle("选择文件");
			
			int result=fileChooser.showOpenDialog(fileChooser);
			if(result==JFileChooser.APPROVE_OPTION) 
			{
				file = fileChooser.getSelectedFile();		
				
				SendMessages("4_1","%)$&)#)#*%)$&)#)#*\n");
				
				SendFileToServer sfts = new SendFileToServer(file);
			}
			else if(result==JFileChooser.CANCEL_OPTION)
			{
				System.out.println("没有选择文件");
			}
			
			file = null;
			fileChooser = null;
			
		}
		if (event.getSource()==liaotianjilu)
		{
			if (f_history)
			{
				f_history=false;
				history_window.dispose();
				return;
			}
			f_history=true;
			history_window = new JWindow();
			history_window.setSize(new Dimension(550,this.getHeight()));
			history_window.setBackground(Color.black);
			history_window.setLocation(this.getX()+800,this.getY());
			history_pane = new My_JTextPane_1();
			history_pane.setEditable(false);
			JScrollPane history_scrollpane = new JScrollPane(history_pane);
			history_window.add(history_scrollpane);
			history_pane.addMouseListener(this);
			history_window.setVisible(true);
			this.SendMessages("4_7", "%)$&)#)#*%)$&)#)#*\n");
		}
		if (event.getSource()==paint) 
		{
			if (f_draw)
			{
				f_draw=false;
				drawingboard.dispose();
				return;
			}
			f_draw=true;
			drawingboard = new DrawingBoard(user.getId(),receiver.getId());
			SendMessages("4_8", "%)$&)#)#*%)$&)#)#*\n");
		}
	}
	
//打开画板
	public void OpenDrawingBoard()
	{
		if(f_draw) return;
		f_draw=true;
		drawingboard = new DrawingBoard(user.getId(),receiver.getId());
	}
	
//绘制画板
	public void Drawing(PaintMessages paintmessages)
	{
		drawingboard.Drawing(paintmessages);
	}
	
//鼠标监听器
	public void mouseClicked(MouseEvent event) {}
	public void mouseEntered(MouseEvent event) 
	{
		((My_JTextPane_1)event.getSource()).setCursor(new Cursor(Cursor.TEXT_CURSOR));
		
	}
	public void mouseExited(MouseEvent event) 
	{
		//鼠标离开Text区后恢复默认形态
		((My_JTextPane_1)event.getSource()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}

//键盘监听器
	public void keyPressed(KeyEvent event) {}
	public void keyReleased(KeyEvent event) 
	{
		if (event.getSource()==textpane2)
		{
			if (event.getKeyCode()==KeyEvent.VK_ENTER)
			{
				textpane2.insertIcon("");
				this.SendMessages("4",textpane2.get_message());
				this.textpane2.setText("");
				textpane2.reset_message();
			}
		}
	}
	public void keyTyped(KeyEvent event) {}
	
}