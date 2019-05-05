package View;

/*
 * �������.
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
	JTextArea tarea1 = new JTextArea();			//������Ϣ��ʾ��
	JTextArea tarea2 = new JTextArea();			//�����
	JPanel panel1 = new JPanel(new BorderLayout());
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();  
	JPanel panel4 = new JPanel();
	JScrollPane jspanel_tarea1 = new JScrollPane(tarea1);
	JScrollPane jspanel_tarea2 = new JScrollPane(tarea2);
	
	JPanel toolbar = new JPanel(new FlowLayout(0));
	JButton biaoqing = new JButton(new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\�������\\������\\����.png"));
	JButton doudong = new JButton(new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\�������\\������\\��������.png"));
	JButton chuanwenjian = new JButton(new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\�������\\������\\�����ļ�.png"));
	JButton liaotianjilu = new JButton(new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\�������\\������\\�����¼.png"));
	
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
		this.setIconImage(new ImageIcon(".\\bin\\Images\\qq.png").getImage());
		
		//����������
		biaoqing.addActionListener(this);
		doudong.addActionListener(this);
		chuanwenjian.addActionListener(this);
		liaotianjilu.addActionListener(this);
		biaoqing.setBackground(Color.white);
		doudong.setBackground(Color.white);
		chuanwenjian.setBackground(Color.white);
		liaotianjilu.setBackground(Color.white);
		toolbar.setBackground(Color.white);
		toolbar.setBorder(BorderFactory.createEtchedBorder());
		//��������ťȥ�߿�
		biaoqing.setBorder(null);
		doudong.setBorder(null);
		chuanwenjian.setBorder(null);
		liaotianjilu.setBorder(null);
		
		toolbar.add(biaoqing);
		toolbar.add(doudong);
		toolbar.add(chuanwenjian);
		toolbar.add(liaotianjilu);
		
		//���ı�������Ϊ���ɱ༭
		tarea1.setEditable(false);
		
		//�Զ�����
		tarea1.setLineWrap(true);
		tarea2.setLineWrap(true);
		
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
		jspanel_tarea2.setPreferredSize(new Dimension(800,500));
		toolbar.setPreferredSize(new Dimension(800,40));
		panel3.setPreferredSize(new Dimension(800,40));
		
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
		tarea1.setFont(new Font("΢���ź�",Font.PLAIN,20));
		tarea2.setFont(new Font("΢���ź�",Font.PLAIN,20));

		//��Ӽ�����
		close_this_frame.addActionListener(this);
		sent_message.addActionListener(this);
		tarea1.addMouseListener(this);
		tarea2.addKeyListener(this);
		
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
		time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UserInfoBean uib = new UserInfoBean();
		uib.setMesType(MesType);
		uib.setMessage(Message);
		uib.setSender(user.getId());
		uib.setReceiver(receiver.getId());
		uib.setSendTime(time.format(new Date()));
		smts.SendMessageTOServer(uib);
		this.showMessages(uib);
	}
	
//��ʾ���ͺͽ��յ�����Ϣ
	public void showMessages(UserInfoBean uib)
	{
		String info =uib.getSendTime()+"  "+uib.getSender()+" :\n"+uib.getMessage()+"\n\n";
		tarea1.append(info);
	}
	
//�����ļ�
	public String receiveFile() 
	{
		
//		JLabel title = new JLabel("�Է����㷢����һ���ļ����Ƿ���գ�");
//		title .setFont(new Font("΢���ź�", Font.BOLD, 15));
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
		if (event.getSource()==close_this_frame) {dispose();}
		if (event.getSource()==sent_message)
		{
			this.SendMessages("4",tarea2.getText());
			tarea2.setText("");
		}
		if (event.getSource()==biaoqing) {}
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
			if(result==fileChooser.APPROVE_OPTION) 
			{
				file = fileChooser.getSelectedFile();
//				time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				UserInfoBean uib = new UserInfoBean();
//				uib.setMesType("4_1");
//				uib.setSender(user.getId());
//				uib.setReceiver(receiver.getId());
//				uib.setSendTime(time.format(new Date()));
//				smts.SendMessageTOServer(uib);				
				SendMessages("4_1","");

				SendFileToServer sfts = new SendFileToServer(file);
			}
			else if(result==fileChooser.CANCEL_OPTION)
			{
				System.out.println("û��ѡ���ļ�");
			}
		}
		if (event.getSource()==liaotianjilu){}
	}
	
//��������
	public void mouseClicked(MouseEvent event) {}
	public void mouseEntered(MouseEvent event) 
	{
		if (event.getSource()==tarea1)
		{
			//������Text�����Ϊ�ı�����ָ��
			tarea1.setCursor(new Cursor(Cursor.TEXT_CURSOR));
			
		}
	}
	public void mouseExited(MouseEvent event) 
	{
		//����뿪Text����ָ�Ĭ����̬
		tarea1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}

//���̼�����
	public void keyPressed(KeyEvent event) {}
	public void keyReleased(KeyEvent event) 
	{
		if (event.getSource()==tarea2)
		{
			if (event.getKeyCode()==KeyEvent.VK_ENTER)
			{
				this.SendMessages("4",tarea2.getText());
				this.tarea2.setText("");
			}
		}
	}
	public void keyTyped(KeyEvent event) {}
	
//	public static void main(String[] args)
//	{
//		User u1 = new User();
//		User u2 = new User();
//		u1.setNickname("1");
//		u2.setNickname("2");
//		new Chat_Frame(u1,u2);
//	}
	
}
