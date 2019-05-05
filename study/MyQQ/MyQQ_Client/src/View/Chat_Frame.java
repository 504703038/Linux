package View;

/*
 * �������.
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
	My_JTextPane_1 textpane1 = new My_JTextPane_1();	//������Ϣ��ʾ��
	My_JTextPane_1 history_pane;
	My_JTextPane_2 textpane2 = new My_JTextPane_2();	//�����
	JPanel panel1 = new JPanel(new BorderLayout());
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();  
	JPanel panel4 = new JPanel();
	JScrollPane jspanel_tarea1 = new JScrollPane(textpane1);
	JScrollPane jspanel_tarea2 = new JScrollPane(textpane2);
	JToolBar toolbar = new JToolBar();
	JButton biaoqing = new JButton(new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\�������\\������\\����.png"));
	JButton doudong = new JButton(new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\�������\\������\\��������.png"));
	JButton chuanwenjian = new JButton(new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\�������\\������\\�����ļ�.png"));
	JButton liaotianjilu = new JButton(new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\�������\\������\\�����¼.png"));
	JButton paint = new JButton(new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\�������\\������\\��ͼ.png"));
	JButton sent_message = new JButton("����");
	JButton close_this_frame = new JButton("�ر�");
	
	User user = new User();
	User receiver = new User();
	SendMessagesToServer smts = new SendMessagesToServer();
	SimpleDateFormat time;  //���ڻ�ȡ��ǰʱ��
	public Chat_Frame(User user,User receiver)
	{
		//��ʼ��
		super(user.getNickname()+"  ���ں�   "+receiver.getNickname()+"  ����");
		this.user=user;
		this.receiver=receiver;
		//���ò��ִ�С
		this.setBounds(450,200,1000,750);
		this.setLayout(new BorderLayout());
		this.setIconImage(new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\qq.png").getImage());
		this.setResizable(false);
		//����������
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
		//��������ťȥ�߿�
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
		//���ò���
		panel2.setLayout(new BorderLayout());
		panel4.setLayout(new BorderLayout());
		panel3.setBackground(Color.WHITE);
		panel3.setLayout(new FlowLayout(2));
		//�������ȥ�߿�
		jspanel_tarea1.setBorder(null);
		jspanel_tarea2.setBorder(null);
		//���ô�С
		jspanel_tarea1.setPreferredSize(new Dimension(800,500));
		toolbar.setPreferredSize(new Dimension(800,40));
		//������
		panel3.add(sent_message);
		panel3.add(close_this_frame);
		panel2.add(toolbar,"North");
		panel2.add(jspanel_tarea2,"Center");
		panel2.add(panel3,"South");
		panel4.add(jspanel_tarea1,"North");
		panel4.add(panel2,"Center");
		this.add(panel4,BorderLayout.CENTER);
		//���QQ��
		panel1.setPreferredSize(new Dimension(200,700));
		JLabel show1 = new JLabel(new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\�������\\QQ��\\QQ��_Ů.jpg"));
		JLabel show2 = new JLabel(new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\�������\\QQ��\\QQ��_��.jpg"));
		panel1.add(show1,"North");
		panel1.add(show2,"South");
		this.add(panel1,BorderLayout.EAST);
		//��������
		sent_message.setFont(new Font("΢���ź�",Font.PLAIN,15));
		close_this_frame.setFont(new Font("΢���ź�",Font.PLAIN,15));
		textpane2.setFont(new Font("΢���ź�",Font.PLAIN,20));
		//��Ӽ�����
		close_this_frame.addActionListener(this);
		sent_message.addActionListener(this);
		textpane1.addMouseListener(this);
		textpane2.addKeyListener(this);
		//���ÿɼ�
		this.setVisible(true);
		//�ر��������
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
	
//���ڶ���
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

//������Ϣ
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
	
//��ʾ���ͺͽ��յ�����Ϣ
	public void showMessages(UserInfoBean uib)
	{
		time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String info =time.format(uib.getSendTime())+"  "+uib.getSender_nickname()+" :\n"+uib.getMessage()+"\n";
		textpane1.insert(info);
	}
	
//��ʾ��ʷ��¼
	public void showHistoryMessages(UserInfoBean uib)
	{
		time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String info =time.format(uib.getSendTime())+"  "+uib.getSender_nickname()+" :\n"+uib.getMessage()+"\n";
		history_pane.insert(info);
	}
	
	
//�����ļ�
	public String receiveFile() 
	{
		int f = JOptionPane.showConfirmDialog(null, "�Է����㷢����һ���ļ����Ƿ���գ�", "��ʾ",JOptionPane.YES_NO_OPTION);
		if (f==1)
		return null;
		String path=null;
		JFileChooser fileChooser = new JFileChooser("D:\\");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setApproveButtonText("����");
		fileChooser.setDialogTitle("ѡ���ļ��ļ���");
		int returnVal = fileChooser.showOpenDialog(fileChooser);
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			path = fileChooser.getSelectedFile().getAbsolutePath();
		}
		return path;
	}
	
//��ť������
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
				emo[i] = new JButton(new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\�������\\������\\����\\1\\����"+i+".png"));
				emo[i].setName(""+i);
				emo[i].setBorder(null);
				emo[i].addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent e) 
						{
							textpane2.insertIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\�������\\������\\����\\1\\����"+((JButton)e.getSource()).getName()+".png");
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
			
			SendMessages("4_2","��������һ�����ڶ�����\n");
			this.shake();
		}
		//�����ļ�
		if (event.getSource()==chuanwenjian)
		{
			
			JFileChooser fileChooser = new JFileChooser("D:\\");
			File file = null;
			
			fileChooser.setApproveButtonText("ȷ��");
			fileChooser.setDialogTitle("ѡ���ļ�");
			
			int result=fileChooser.showOpenDialog(fileChooser);
			if(result==JFileChooser.APPROVE_OPTION) 
			{
				file = fileChooser.getSelectedFile();		
				
				SendMessages("4_1","%)$&)#)#*%)$&)#)#*\n");
				
				SendFileToServer sfts = new SendFileToServer(file);
			}
			else if(result==JFileChooser.CANCEL_OPTION)
			{
				System.out.println("û��ѡ���ļ�");
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
	
//�򿪻���
	public void OpenDrawingBoard()
	{
		if(f_draw) return;
		f_draw=true;
		drawingboard = new DrawingBoard(user.getId(),receiver.getId());
	}
	
//���ƻ���
	public void Drawing(PaintMessages paintmessages)
	{
		drawingboard.Drawing(paintmessages);
	}
	
//��������
	public void mouseClicked(MouseEvent event) {}
	public void mouseEntered(MouseEvent event) 
	{
		((My_JTextPane_1)event.getSource()).setCursor(new Cursor(Cursor.TEXT_CURSOR));
		
	}
	public void mouseExited(MouseEvent event) 
	{
		//����뿪Text����ָ�Ĭ����̬
		((My_JTextPane_1)event.getSource()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}

//���̼�����
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