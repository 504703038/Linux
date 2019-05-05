package View;

/*
 * �����棨�����б���
 */

import Bussiness.*;
import Common.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class Main_Frame extends JFrame implements ActionListener,MouseListener
{
	int current_card=0;
	User user = null;
	CardLayout layout = new CardLayout();
	JPanel up = new JPanel();
	JPanel down = new JPanel();
	JLabel nickname;
	JLabel head_image = new JLabel(new ImageIcon("E:\\���\\JAVA\\����\\MyQQ_Client\\src\\com.client.Images\\head_image.jpg"));
	
	SendMessagesToServer smts = new SendMessagesToServer();
	
	//Card_1;���ѽ���
	JPanel down1 =new JPanel();
	JPanel myfriend_buootn_panel = new JPanel();//�ҵĺ��ѽ����°�ť�����
	JPanel myfriend = new JPanel();
	JScrollPane myfriend_list = new JScrollPane(myfriend);
	JButton myfriend_button1 = new JButton("�� �ҵĺ���");
	JButton stranger_button1 = new JButton("�� İ����");
	JButton blacklist_button1 = new JButton("�� ������");
	
	
	//Card_2;İ���˽���
	JPanel down2 =new JPanel();
	JPanel stranger_buootn_panel = new JPanel();
	JPanel stranger = new JPanel();
	JScrollPane stranger_list = new JScrollPane(stranger);
	JButton myfriend_button2 = new JButton("�� �ҵĺ���");
	JButton stranger_button2 = new JButton("��  İ����");
	JButton blacklist_button2 = new JButton("�� ������");
	
	
	//Card_3;����������
	JPanel down3 =new JPanel();
	JPanel blacklist_buootn_panel = new JPanel();
	JPanel blacklist = new JPanel();
	JScrollPane blacklist_list = new JScrollPane(blacklist);
	JButton myfriend_button3 = new JButton("�� �ҵĺ���");
	JButton stranger_button3 = new JButton("�� İ����");
	JButton blacklist_button3 = new JButton("��  ������");

	//Card_0;
	JPanel down0 =new JPanel();
	JButton myfriend_button0 = new JButton("�� �ҵĺ���");
	JButton stranger_button0 = new JButton("�� İ����");
	JButton blacklist_button0 = new JButton("�� ������");
	JPanel buootn_panel = new JPanel();
	
	//������壺�ҵĺ��ѣ�İ���ˣ�������
	public Main_Frame(User user)
	{
		super("QQ");
		this.setBounds(1500,100,350,800);
		this.setLayout(null);
		this.user=user;
		nickname = new JLabel(user.getNickname());
//�ϰ벿��
		this.setIconImage(new ImageIcon("E:\\���\\JAVA\\����\\MyQQ_Client\\src\\com.client.Images\\qq.png").getImage());
		nickname.setFont(new Font("΢���ź�",Font.BOLD,20));
		up.setBounds(0,0,this.getWidth(),100);
		up.setLayout(new FlowLayout(0));
		head_image.setBounds(0,0,100,100);
		up.add(head_image);
		up.add(nickname);
		
//�°벿��
		
		//Card_0;
		down0.setLayout(new BorderLayout());
		buootn_panel.setLayout(new GridLayout(3,1));
		buootn_panel.add(myfriend_button0);
		buootn_panel.add(stranger_button0);
		buootn_panel.add(blacklist_button0);
		down0.add(buootn_panel, BorderLayout.NORTH);
		myfriend_button0.addActionListener(this);
		stranger_button0.addActionListener(this);
		blacklist_button0.addActionListener(this);
		
		//Card_1;�ҵĺ��� 
		down1.setLayout(new BorderLayout());
		myfriend_buootn_panel.setLayout(new GridLayout(2,1));
		myfriend_buootn_panel.add(stranger_button1);
		myfriend_buootn_panel.add(blacklist_button1);
		myfriend_button1.addActionListener(this);
		stranger_button1.addActionListener(this);
		blacklist_button1.addActionListener(this);
		myfriend.setLayout(new GridLayout(50,1,4,4));
		myfriend.setBounds(0,0,350,400);
		JLabel [] label1 = new JLabel[20];
		JPanel [] panel1 = new JPanel[20];
		for(int i=0;i<20;i++) 
		{
			label1[i] = new JLabel(i+1+"",new ImageIcon("E:\\���\\JAVA\\����\\MyQQ_Client\\src\\com.client.Images\\qq.png"),JLabel.LEFT);
			panel1[i] = new JPanel(new FlowLayout(0));
			panel1[i].setName(i+1+"");
			panel1[i].setBackground(new Color(238,238,238));
			panel1[i].add(label1[i]);
			myfriend.add(panel1[i]);
			panel1[i].addMouseListener(this);
		}
		down1.add(myfriend_button1,BorderLayout.NORTH);
		down1.add(myfriend_list, BorderLayout.CENTER);
		down1.add(myfriend_buootn_panel, BorderLayout.SOUTH);

		
		//Card_2;İ����
		down2.setLayout(new BorderLayout());
		stranger_buootn_panel.setLayout(new GridLayout(2,1));
		stranger_buootn_panel.add(myfriend_button2);
		stranger_buootn_panel.add(stranger_button2);
		myfriend_button2.addActionListener(this);
		stranger_button2.addActionListener(this);
		blacklist_button2.addActionListener(this);
		stranger.setLayout(new GridLayout(50,1,4,4));
		stranger.setBounds(0,0,350,400);
		JLabel [] label2 = new JLabel[20];
		JPanel [] panel2 = new JPanel[20];
		for(int i=0;i<20;i++) 
		{
			label2[i] = new JLabel(i+1+"",new ImageIcon("E:\\���\\JAVA\\����\\MyQQ_Client\\src\\com.client.Images\\qq.png"),JLabel.LEFT);
			panel2[i] = new JPanel(new FlowLayout(0));
			panel2[i].setName(i+1+"");
			panel1[i].setBackground(new Color(238,238,238));
			panel2[i].add(label2[i] );
			stranger.add(panel2[i]);
			panel2[i].addMouseListener(this);
		}
		down2.add(blacklist_button2,BorderLayout.SOUTH);
		down2.add(stranger_list, BorderLayout.CENTER);
		down2.add(stranger_buootn_panel, BorderLayout.NORTH);
		
		
		//Card_3;������
		down3.setLayout(new BorderLayout());
		blacklist_buootn_panel.setLayout(new GridLayout(3,1));
		blacklist_buootn_panel.add(myfriend_button3);
		blacklist_buootn_panel.add(stranger_button3);
		blacklist_buootn_panel.add(blacklist_button3);
		myfriend_button3.addActionListener(this);
		stranger_button3.addActionListener(this);
		blacklist_button3.addActionListener(this);
		blacklist.setLayout(new GridLayout(50,1,4,4));
		blacklist.setBounds(0,0,350,400);
		JLabel [] label3 = new JLabel[20];
		JPanel [] panel3 = new JPanel[20];
		for(int i=0;i<20;i++) 
		{
			label3[i] = new JLabel(i+1+"",new ImageIcon("E:\\���\\JAVA\\����\\MyQQ_Client\\src\\com.client.Images\\qq.png"),JLabel.LEFT);
			panel3[i] = new JPanel(new FlowLayout(0));
			panel3[i].setName(i+1+"");
			panel1[i].setBackground(new Color(238,238,238));
			panel3[i].add(label3[i]);
			blacklist.add(panel3[i]);
			panel3[i].addMouseListener(this);
		}
		down3.add(blacklist_list, BorderLayout.CENTER);
		down3.add(blacklist_buootn_panel, BorderLayout.NORTH);
		
		
		//���Card;
		down.setBounds(0,100,this.getWidth()-20,this.getHeight()-200);
		down.setLayout(layout);
		down.add(down0,"0");
		down.add(down1,"1");
		down.add(down2,"2");
		down.add(down3,"3");
		
	//Button��ť����
		myfriend_button0.setFont(new Font("΢���ź�",Font.BOLD,15));
		stranger_button0.setFont(new Font("΢���ź�",Font.BOLD,15));
		blacklist_button0.setFont(new Font("΢���ź�",Font.BOLD,15));
		myfriend_button1.setFont(new Font("΢���ź�",Font.BOLD,15));
		stranger_button1.setFont(new Font("΢���ź�",Font.BOLD,15));
		blacklist_button1.setFont(new Font("΢���ź�",Font.BOLD,15));
		myfriend_button2.setFont(new Font("΢���ź�",Font.BOLD,15));
		stranger_button2.setFont(new Font("΢���ź�",Font.BOLD,15));
		blacklist_button2.setFont(new Font("΢���ź�",Font.BOLD,15));
		myfriend_button3.setFont(new Font("΢���ź�",Font.BOLD,15));
		stranger_button3.setFont(new Font("΢���ź�",Font.BOLD,15));
		blacklist_button3.setFont(new Font("΢���ź�",Font.BOLD,15));
			//���ð�ť��ɫ
		myfriend_button0.setBackground(new Color(255,255,255));
		stranger_button0.setBackground(new Color(255,255,255));
		blacklist_button0.setBackground(new Color(255,255,255));
		myfriend_button1.setBackground(new Color(255,255,255));
		stranger_button1.setBackground(new Color(255,255,255));
		blacklist_button1.setBackground(new Color(255,255,255));
		myfriend_button2.setBackground(new Color(255,255,255));
		stranger_button2.setBackground(new Color(255,255,255));
		blacklist_button2.setBackground(new Color(255,255,255));
		myfriend_button3.setBackground(new Color(255,255,255));
		stranger_button3.setBackground(new Color(255,255,255));
		blacklist_button3.setBackground(new Color(255,255,255));
			//���ð�ť����λ��
		myfriend_button0.setHorizontalAlignment(JButton.LEFT);
		stranger_button0.setHorizontalAlignment(JButton.LEFT);
		blacklist_button0.setHorizontalAlignment(JButton.LEFT);
		myfriend_button1.setHorizontalAlignment(JButton.LEFT);
		stranger_button1.setHorizontalAlignment(JButton.LEFT);
		blacklist_button1.setHorizontalAlignment(JButton.LEFT);
		myfriend_button2.setHorizontalAlignment(JButton.LEFT);
		stranger_button2.setHorizontalAlignment(JButton.LEFT);
		blacklist_button2.setHorizontalAlignment(JButton.LEFT);
		myfriend_button3.setHorizontalAlignment(JButton.LEFT);
		stranger_button3.setHorizontalAlignment(JButton.LEFT);
		blacklist_button3.setHorizontalAlignment(JButton.LEFT);
		
		
		this.add(up);
		this.add(down);
		this.setVisible(true);
//		this.setDefaultCloseOperation(3);
		
		this.addWindowListener(new WindowListener()
				{
			
					public void windowActivated(WindowEvent e) {}
					public void windowClosed(WindowEvent e) {}
					public void windowClosing(WindowEvent e) 
					{
						UserInfoBean uib = new UserInfoBean();
						
						//����������Ϳͻ��˹رյ���Ϣ
						uib.setMesType("5");
						uib.setUser(user);
						uib.setSender(user.getId());
						smts.SendMessageTOServer(uib);
						
						//�رտͻ��˽�����Ϣ���߳�
						ReceiveMessagesFromServer_Thread rmfst = Manage_ReceiveMessagesFromServerThread.getReceiveMessagesFromServer(user.getId());
						Manage_ReceiveMessagesFromServerThread.remove(user.getId());
						try 
						{
							rmfst.s.close();
							rmfst.exit=true;
							rmfst.join();
						} catch (Exception e1) 
						{
							e1.printStackTrace();
						}
						
//						System.out.println("Ӧ�ó����ѹر�");
						System.exit(0);
					}
					public void windowDeactivated(WindowEvent e) {}
					public void windowDeiconified(WindowEvent e) {}
					public void windowIconified(WindowEvent e) {}
					public void windowOpened(WindowEvent e) {}
					
				});
	}
	
//�����б�ť������
	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource()==myfriend_button0 || event.getSource()==myfriend_button1 || event.getSource()==myfriend_button2 || event.getSource()==myfriend_button3)
		{
			if (current_card==1)
			{
				layout.show(down, "0");
				current_card=0;
			}
			else
			{
				layout.show(down, "1");
				current_card=1;
			}
		}
		if (event.getSource()==stranger_button0 || event.getSource()==stranger_button1 || event.getSource()==stranger_button2 || event.getSource()==stranger_button3)
		{
			if (current_card==2)
			{
				layout.show(down, "0");
				current_card=0;
			}
			else
			{
				layout.show(down, "2");
				current_card=2;
			}
		}
		if (event.getSource()==blacklist_button0 || event.getSource()==blacklist_button1 || event.getSource()==blacklist_button2 || event.getSource()==blacklist_button3)
		{
			if (current_card==3)
			{
				layout.show(down, "0");
				current_card=0;
			}
			else
			{
				layout.show(down, "3");
				current_card=3;
			}
		}
	}

//��������
	public void mouseClicked(MouseEvent event) 
	{
		JPanel panel = (JPanel)event.getSource();
		if (event.getClickCount()==2)
		{
			User receiver = new User();
			receiver.setId(panel.getName());
			receiver.setNickname(panel.getName());
			//�Ժ�Ҫ�޸ĵ����� : panel.getName()�д�������û���ID����ͨ�����ݿ��ø�ID��Ӧ�û������ݴ���Chat_Frame(UserInfoBean);
			if (Manage_ChatFrame.search(user.getId()+"&"+receiver.getId()))
			{
				Chat_Frame chat = new Chat_Frame(user,receiver);
				Manage_ChatFrame.addChatFrame(user.getId()+"&"+receiver.getId(), chat);
			}
		}
	}
	public void mouseEntered(MouseEvent event) 
	{
		JPanel panel =(JPanel)event.getSource();
		panel.setBackground(new Color(230,235,240));
	}
	public void mouseExited(MouseEvent event) 
	{
		JPanel panel =(JPanel)event.getSource();
		panel.setBackground(new Color(238,238,238));
	}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
}
