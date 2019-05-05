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
	
	JToolBar toolbar = new JToolBar();
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
		
		toolbar.add(biaoqing);
		toolbar.add(doudong);
		toolbar.add(chuanwenjian);
		toolbar.add(liaotianjilu);
		toolbar.setFloatable(false);
		toolbar.setRollover(true);
		
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
	

//������Ϣ
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
	
//��ʾ���ͺͽ��յ�����Ϣ
	public void showMessages(UserInfoBean uib)
	{
		String info =uib.getSendTime()+"  "+uib.getSender()+" :\n"+uib.getMessage()+"\n\n";
		tarea1.append(info);
	}
	
	
//��ť������
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
			fileChooser.setApproveButtonText("ȷ��");
			fileChooser.setDialogTitle("ѡ���ļ�");
			int result=fileChooser.showOpenDialog(this);
			File file = null;
			if(result==fileChooser.APPROVE_OPTION) 
			{
				file = fileChooser.getSelectedFile();
				System.out.println(file.getPath()+"\n"+file.getName()+"\n");
			}
			else if(result==fileChooser.CANCEL_OPTION)
			{
				System.out.println("û��ѡ���ļ�");
			}
			
//			��ȡ�ı��ĵ��е����ݲ����:
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

	
//��������
	public void mouseClicked(MouseEvent event) 
	{
		
		
	}
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
