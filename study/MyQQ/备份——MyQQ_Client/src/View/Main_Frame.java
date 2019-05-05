package View;

/*
 * 主界面（好友列表）。
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
	CardLayout cardlayout = new CardLayout();
	JPanel up = new JPanel();
	JPanel friend_panel = new JPanel();
	JPanel group_panel = new JPanel();
	JPanel friend_and_group_panel = new JPanel();
	JPanel friend_and_group_button_Panel = new JPanel();
	JScrollPane group_list = new JScrollPane(group_panel);
	JButton friend_button = new JButton("好友");
	JButton group_button = new JButton("群聊");
	JLabel nickname;
	JLabel head_image = new JLabel(new ImageIcon("E:\\编程\\JAVA\\程序\\MyQQ_Client\\src\\com.client.Images\\head_image.jpg"));
	SendMessagesToServer smts = new SendMessagesToServer();
	//Card_1;好友界面
	int myfriend_total;
	JLabel [] label1 = new JLabel[15];
	JLabel [] m_label1 = new JLabel[15];
	JPanel [] panel1 = new JPanel[15];
	int[] unread1 = new int[15];
	JPanel down1 =new JPanel();
	JPanel myfriend_buootn_panel = new JPanel();
	JPanel myfriend = new JPanel();
	JScrollPane myfriend_list = new JScrollPane(myfriend);
	JButton myfriend_button1 = new JButton("﹀ 我的好友");
	JButton stranger_button1 = new JButton("＞ 陌生人");
	JButton blacklist_button1 = new JButton("＞ 黑名单");
	JButton group_button1 = new JButton("＞  我加入的群");
	//Card_2;陌生人界面
	int stranger_total;
	JLabel [] label2 = new JLabel[15];
	JPanel [] panel2 = new JPanel[15];
	JLabel [] m_label2 = new JLabel[15];
	int[] unread2 = new int[15];
	JPanel down2 =new JPanel();
	JPanel stranger_buootn_panel = new JPanel();
	JPanel stranger = new JPanel();
	JScrollPane stranger_list = new JScrollPane(stranger);
	JButton myfriend_button2 = new JButton("＞ 我的好友");
	JButton stranger_button2 = new JButton("﹀  陌生人");
	JButton blacklist_button2 = new JButton("＞ 黑名单");
	JButton group_button2 = new JButton("＞  我加入的群");
	//Card_3;黑名单界面
	int blacklist_total;
	JLabel [] label3 = new JLabel[15];
	JPanel [] panel3 = new JPanel[15];
	JLabel [] m_label3 = new JLabel[15];
	int[] unread3 = new int[15];
	JPanel down3 =new JPanel();
	JPanel blacklist_buootn_panel = new JPanel();
	JPanel blacklist = new JPanel();
	JScrollPane blacklist_list = new JScrollPane(blacklist);
	JButton myfriend_button3 = new JButton("＞ 我的好友");
	JButton stranger_button3 = new JButton("＞ 陌生人");
	JButton blacklist_button3 = new JButton("﹀  黑名单");
	JButton group_button3 = new JButton("＞  我加入的群");
	//Card_0;
	JPanel down0 =new JPanel();
	JButton myfriend_button0 = new JButton("＞ 我的好友");
	JButton stranger_button0 = new JButton("＞ 陌生人");
	JButton blacklist_button0 = new JButton("＞ 黑名单");
	JButton group_button0 = new JButton("＞  我加入的群");
	JPanel buootn_panel = new JPanel();
	
	Friend[] friends = new Friend[200];
	int total_friends;
	public Main_Frame(User user)
	{
		super("QQ");
		this.setBounds(1500,100,350,800);
		this.setLayout(null);
		this.user=user;
		this.setResizable(false);
		this.setBackground(Color.WHITE);
		this.setIconImage(new ImageIcon("E:\\编程\\JAVA\\程序\\MyQQ_Client\\src\\com.client.Images\\qq.png").getImage());
		//上面部分设置
		up_setings();
		//中间部分设置
		middle_setings();
		//下面部分设置
		down_setings();
		//各部分组件设置
		setings();
		//好友界面设置
		friend_panel_stings();
		//群聊界面设置
		group_panel_setings();
		//添加组件
		this.add(up);
		this.add(friend_and_group_button_Panel);
		this.add(friend_and_group_panel);
		this.setVisible(true);
		this.addWindowListener(new WindowListener()
				{
			
					public void windowActivated(WindowEvent e) {}
					public void windowClosed(WindowEvent e) {}
					public void windowClosing(WindowEvent e) 
					{
						Manage_MainFrame.remove(user.getId());
						UserInfoBean uib = new UserInfoBean();
						//向服务器发送客户端关闭的信息
						uib.setMesType("5");
						uib.setUser(user);
						uib.setSender(user.getId());
						smts.SendMessageTOServer(uib);
						//关闭客户端接收信息的线程
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
						System.exit(0);
					}
					public void windowDeactivated(WindowEvent e) {}
					public void windowDeiconified(WindowEvent e) {}
					public void windowIconified(WindowEvent e) {}
					public void windowOpened(WindowEvent e) {}
					
				});
	}
//接收离线信息提示
	public void ReceiveMessages_OffLine(String Id)
	{
		for(int i=1;i<=myfriend_total;i++)
		{
			if (panel1[i].getName().equals(Id))
			{
				unread1[i]++;
				if (unread1[i]>10)
					unread1[i]=11;
				panel1[i].remove(m_label1[i]);
				m_label1[i] = new JLabel(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\主界面\\"+unread1[i]+".png"));
				panel1[i].add(m_label1[i]);
				m_label1[i].setBounds(250,5,50,50);
				return;
			}
		}
		for(int i=1;i<=stranger_total;i++)
		{
			if (panel2[i].getName().equals(Id))
			{
				unread2[i]++;
				if (unread2[i]>10)
					unread2[i]=11;
				panel2[i].remove(m_label2[i]);
				m_label2[i] = new JLabel(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\主界面\\"+unread2[i]+".png"));
				panel2[i].add(m_label2[i]);
				m_label2[i].setBounds(250,5,50,50);
				return;
			}
		}
		for(int i=1;i<=blacklist_total;i++)
		{
			if (panel3[i].getName().equals(Id))
			{
				unread3[i]++;
				if (unread3[i]>10)
					unread3[i]=11;
				panel3[i].remove(m_label3[i]);
				m_label3[i] = new JLabel(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\主界面\\"+unread3[i]+".png"));
				panel3[i].add(m_label3[i]);
				m_label3[i].setBounds(250,5,50,50);
				return;
			}
		}
	}
//更新好友在线信息
	public void UpdataOnlineInfo(String Id)
	{
		for(int i=1;i<=myfriend_total;i++)
		{
			if (panel1[i].getName().equals(Id))
			{
				label1[i].setEnabled(true);
				return;
			}
		}
		
		for(int i=1;i<=stranger_total;i++)
		{
			if (panel2[i].getName().equals(Id))
			{
				label2[i].setEnabled(true);
				return;
			}
		}
		
		for(int i=1;i<=blacklist_total;i++)
		{
			if (panel3[i].getName().equals(Id))
			{
				label3[i].setEnabled(true);
				return;
			}
		}
		
	}
//好友列表按钮监听器
	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource()==myfriend_button0 || event.getSource()==myfriend_button1 || event.getSource()==myfriend_button2 || event.getSource()==myfriend_button3)
		{
			if (current_card==1)
			{
				cardlayout.show(friend_panel, "0");
				current_card=0;
			}
			else
			{
				cardlayout.show(friend_panel, "1");
				current_card=1;
			}
		}
		if (event.getSource()==stranger_button0 || event.getSource()==stranger_button1 || event.getSource()==stranger_button2 || event.getSource()==stranger_button3)
		{
			if (current_card==2)
			{
				cardlayout.show(friend_panel, "0");
				current_card=0;
			}
			else
			{
				cardlayout.show(friend_panel, "2");
				current_card=2;
			}
		}
		if (event.getSource()==blacklist_button0 || event.getSource()==blacklist_button1 || event.getSource()==blacklist_button2 || event.getSource()==blacklist_button3)
		{
			if (current_card==3)
			{
				cardlayout.show(friend_panel, "0");
				current_card=0;
			}
			else
			{
				cardlayout.show(friend_panel, "3");
				current_card=3;
			}
		}
	}
//鼠标监听器
	public void mouseClicked(MouseEvent event) 
	{
		JPanel panel = (JPanel)event.getSource();
		if (event.getClickCount()==2)
		{
			User receiver = new User();
			receiver.setId(panel.getName());
			for(int i=1;i<=total_friends;i++)
			{
				if (friends[i].getId().equals(receiver.getId()))
				{
					receiver.setNickname(friends[i].getNickname());
				}
			}
			//以后要修改的内容 : panel.getName()中储存接收用户的ID，再通过数据库获得该ID对应用户的数据传入Chat_Frame(UserInfoBean);
			if (!Manage_ChatFrame.search(user.getId()+"&"+receiver.getId()))
			{
				//创建聊天界面
				Chat_Frame chat = new Chat_Frame(user,receiver);
				Manage_ChatFrame.addChatFrame(user.getId()+"&"+receiver.getId(), chat);
				//发送主动接收好友消息的数据包
				UserInfoBean uib = new UserInfoBean();
				uib.setMesType("4_3");
				uib.setReceiver(receiver.getId());
				uib.setSender(user.getId());
				smts.SendMessageTOServer(uib);
				//清除消息未读标志
				String Id =receiver.getId();
				for(int i=1;i<=myfriend_total;i++) {if (panel1[i].getName().equals(Id)) {panel1[i].remove(m_label1[i]); unread1[i]=0; return;}}
				for(int i=1;i<=stranger_total;i++) {if (panel2[i].getName().equals(Id)) {panel2[i].remove(m_label2[i]); unread2[i]=0; return;}}
				for(int i=1;i<=blacklist_total;i++) {if (panel3[i].getName().equals(Id)) {panel2[i].remove(m_label3[i]); unread3[i]=0; return;}}
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
		panel.setBackground(Color.WHITE);
	}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
//各部分组件设置
	public void setings()
	{
//设置背景颜色
		up.setBackground(Color.WHITE);
		friend_panel.setBackground(Color.WHITE);
		group_panel.setBackground(Color.WHITE);
		nickname.setBackground(Color.WHITE);
		down1.setBackground(Color.WHITE);
		myfriend_buootn_panel.setBackground(Color.WHITE);
		myfriend.setBackground(Color.WHITE);
		myfriend_list.setBackground(Color.WHITE);
		down2.setBackground(Color.WHITE);
		stranger_buootn_panel.setBackground(Color.WHITE);
		stranger.setBackground(Color.WHITE);
		stranger_list.setBackground(Color.WHITE);
		down3.setBackground(Color.WHITE);
		blacklist_buootn_panel.setBackground(Color.WHITE);
		blacklist.setBackground(Color.WHITE);
		blacklist_list.setBackground(Color.WHITE);
		down0.setBackground(Color.WHITE);
		buootn_panel.setBackground(Color.WHITE);
		myfriend_button0.setBackground(Color.WHITE);
		stranger_button0.setBackground(Color.WHITE);
		blacklist_button0.setBackground(Color.WHITE);
		myfriend_button1.setBackground(Color.WHITE);
		stranger_button1.setBackground(Color.WHITE);
		blacklist_button1.setBackground(Color.WHITE);
		myfriend_button2.setBackground(Color.WHITE);
		stranger_button2.setBackground(Color.WHITE);
		blacklist_button2.setBackground(Color.WHITE);
		myfriend_button3.setBackground(Color.WHITE);
		stranger_button3.setBackground(Color.WHITE);
		blacklist_button3.setBackground(Color.WHITE);
//Button按钮设置
		myfriend_button0.setFont(new Font("微软雅黑",Font.BOLD,15));
		stranger_button0.setFont(new Font("微软雅黑",Font.BOLD,15));
		blacklist_button0.setFont(new Font("微软雅黑",Font.BOLD,15));
		myfriend_button1.setFont(new Font("微软雅黑",Font.BOLD,15));
		stranger_button1.setFont(new Font("微软雅黑",Font.BOLD,15));
		blacklist_button1.setFont(new Font("微软雅黑",Font.BOLD,15));
		myfriend_button2.setFont(new Font("微软雅黑",Font.BOLD,15));
		stranger_button2.setFont(new Font("微软雅黑",Font.BOLD,15));
		blacklist_button2.setFont(new Font("微软雅黑",Font.BOLD,15));
		myfriend_button3.setFont(new Font("微软雅黑",Font.BOLD,15));
		stranger_button3.setFont(new Font("微软雅黑",Font.BOLD,15));
		blacklist_button3.setFont(new Font("微软雅黑",Font.BOLD,15));
		friend_button.setFont(new Font("微软雅黑",Font.BOLD,15));
		group_button.setFont(new Font("微软雅黑",Font.BOLD,15));
//设置按钮颜色
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
//设置按钮字体位置
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
	}
//好友界面设置
	public void friend_panel_stings()
	{
		friends=user.getFriends();
		total_friends=user.getTotal_friend();
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
//Card_1;我的好友 
		down1.setLayout(new BorderLayout());
		down1.setBounds(0, 0, this.getWidth(), this.getHeight()-100);
		myfriend_buootn_panel.setLayout(new GridLayout(2,1));
		myfriend_buootn_panel.add(stranger_button1);
		myfriend_buootn_panel.add(blacklist_button1);
		myfriend_button1.addActionListener(this);
		stranger_button1.addActionListener(this);
		blacklist_button1.addActionListener(this);
		myfriend.setLayout(new GridLayout(9,1,4,4));
		myfriend.setBounds(0,0,350,400);
		myfriend_total=0;
		for(int i=1;i<=total_friends;i++)
		{
			if (friends[i].getGroup().equals("我的好友"))
			{
				myfriend_total++;
				label1[myfriend_total] = new JLabel(friends[i].getNickname(),new ImageIcon("E:\\编程\\JAVA\\程序\\MyQQ_Client\\src\\com.client.Images\\qq.png"),JLabel.LEFT);
				panel1[myfriend_total] = new JPanel(null);
				panel1[myfriend_total].setName(friends[i].getId());
				panel1[myfriend_total].setBackground(Color.white);
				label1[myfriend_total].setBackground(Color.white);
				if (!friends[i].getOnLine())
				label1[myfriend_total].setEnabled(false);
				panel1[myfriend_total].add(label1[myfriend_total]);
				label1[myfriend_total].setBounds(0, 5, 100, 50);
				myfriend.add(panel1[myfriend_total]);
				panel1[myfriend_total].addMouseListener(this);
				//好友消息提示
				unread1[myfriend_total]=friends[i].getTotal_messages();
				if (unread1[myfriend_total]>10)
					unread1[myfriend_total]=11;
				m_label1[myfriend_total] = new JLabel(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\主界面\\"+unread1[i]+".png"));
				panel1[myfriend_total].add(m_label1[myfriend_total]);
				m_label1[myfriend_total].setBounds(250,5,50,50);	
			}
			
		}
		down1.add(myfriend_button1,BorderLayout.NORTH);
		down1.add(myfriend_list, BorderLayout.CENTER);
		down1.add(myfriend_buootn_panel, BorderLayout.SOUTH);	
//Card_2;陌生人
		down2.setLayout(new BorderLayout());
		stranger_buootn_panel.setLayout(new GridLayout(2,1));
		stranger_buootn_panel.add(myfriend_button2);
		stranger_buootn_panel.add(stranger_button2);
		myfriend_button2.addActionListener(this);
		stranger_button2.addActionListener(this);
		blacklist_button2.addActionListener(this);
		stranger.setLayout(new GridLayout(9,1,4,4));
		stranger.setBounds(0,0,350,400);
		stranger_total=0;	
		for(int i=1;i<=total_friends;i++)
		{
			if (friends[i].getGroup().equals("陌生人"))
			{
				stranger_total++;
				label2[stranger_total] = new JLabel(friends[i].getNickname(),new ImageIcon("E:\\编程\\JAVA\\程序\\MyQQ_Client\\src\\com.client.Images\\qq.png"),JLabel.LEFT);
				panel2[stranger_total] = new JPanel(null);
				panel2[stranger_total].setName(friends[i].getId());
				panel2[stranger_total].setBackground(Color.white);
				label2[stranger_total].setBackground(Color.white);
				if (!friends[i].getOnLine())
				label2[stranger_total].setEnabled(false);
				panel2[stranger_total].add(label2[stranger_total]);
				label2[stranger_total].setBounds(0, 5, 100, 50);
				stranger.add(panel2[stranger_total]);
				panel2[stranger_total].addMouseListener(this);
				//好友消息提示
				unread2[stranger_total]=friends[i].getTotal_messages();
				if (unread2[stranger_total]>10)
					unread2[stranger_total]=11;
				m_label2[stranger_total] = new JLabel(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\主界面\\"+unread2[i]+".png"));
				panel2[stranger_total].add(m_label2[stranger_total]);
				m_label2[stranger_total].setBounds(250,5,50,50);	
			}
		}	
		down2.add(blacklist_button2,BorderLayout.SOUTH);
		down2.add(stranger_list, BorderLayout.CENTER);
		down2.add(stranger_buootn_panel, BorderLayout.NORTH);				
//Card_3;黑名单
		down3.setLayout(new BorderLayout());
		blacklist_buootn_panel.setLayout(new GridLayout(3,1));
		blacklist_buootn_panel.add(myfriend_button3);
		blacklist_buootn_panel.add(stranger_button3);
		blacklist_buootn_panel.add(blacklist_button3);
		myfriend_button3.addActionListener(this);
		stranger_button3.addActionListener(this);
		blacklist_button3.addActionListener(this);
		blacklist.setLayout(new GridLayout(9,1,4,4));
		blacklist.setBounds(0,0,350,400);
		blacklist_total=0;
		for(int i=1;i<=total_friends;i++)
		{
			if (friends[i].getGroup().equals("黑名单"))
			{
				blacklist_total++;
				label3[blacklist_total] = new JLabel(friends[i].getNickname(),new ImageIcon("E:\\编程\\JAVA\\程序\\MyQQ_Client\\src\\com.client.Images\\qq.png"),JLabel.LEFT);
				panel3[blacklist_total] = new JPanel(null);
				panel3[blacklist_total].setName(friends[i].getId());
				panel3[blacklist_total].setBackground(Color.white);
				label3[blacklist_total].setBackground(Color.white);
				if (!friends[i].getOnLine())
				label3[blacklist_total].setEnabled(false);
				panel3[blacklist_total].add(label3[stranger_total]);
				label3[blacklist_total].setBounds(0, 5, 100, 50);
				blacklist.add(panel3[blacklist_total]);
				panel3[blacklist_total].addMouseListener(this);
				//好友消息提示
				unread3[blacklist_total]=friends[i].getTotal_messages();
				if (unread3[blacklist_total]>10)
					unread3[blacklist_total]=11;
				m_label3[blacklist_total] = new JLabel(new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\主界面\\"+unread3[i]+".png"));
				panel3[blacklist_total].add(m_label3[blacklist_total]);
				m_label3[blacklist_total].setBounds(250,5,50,50);	
			}
		}
		down3.add(blacklist_list, BorderLayout.CENTER);
		down3.add(blacklist_buootn_panel, BorderLayout.NORTH);		
//添加Card;
		friend_panel.setLayout(cardlayout);
		friend_panel.add(down0,"0");
		friend_panel.add(down1,"1");
		friend_panel.add(down2,"2");
		friend_panel.add(down3,"3");
	}
//群聊界面设置
	public void group_panel_setings()
	{
		int total_group=user.getTotal_group();
		Group[] groups = user.getGroups();
		group_panel.setLayout(new GridLayout(9,1,4,4));
		JPanel[] panel4 = new JPanel[total_group+5];
		JLabel[] label4 = new JLabel[total_group+5];
		for(int i=1;i<=total_group;i++)
		{
			panel4[i]= new JPanel(new FlowLayout(0));
			panel4[i].setBackground(Color.white);
			panel4[i].setName(""+i);
			label4[i]= new JLabel(groups[i].getGropu_Name(),new ImageIcon("E:\\学习\\大一下\\JAVA\\课设\\相关文件\\Images\\QQ_Group.png"),JLabel.LEFT);
			panel4[i].add(label4[i]);
			group_panel.add(panel4[i]);
			panel4[i].addMouseListener(new MouseListener() 
				{
					public void mouseClicked(MouseEvent e) 
					{
						if (e.getClickCount()==2) 
						{
							int i;
							JPanel panel = (JPanel)e.getSource();
							i=Integer.parseInt(panel.getName());
							if (!Manage_GroupChatFrame.search(user.getId()+groups[i].getGroup_Id())) 
							{
								Group_Chat_Frame group_chat_frame = new Group_Chat_Frame(user,groups[i]);
								Manage_GroupChatFrame.addChatFrame(user.getId()+groups[i].getGroup_Id(), group_chat_frame);
								//主动接收群聊消息
								UserInfoBean uib = new UserInfoBean();
								uib.setMesType("4_6");
								uib.setReceiver(groups[i].getGroup_Id());
								uib.setSender(user.getId());
								smts.SendMessageTOServer(uib);	
							}
						}
					}
					public void mouseEntered(MouseEvent e) {JPanel panel =(JPanel)e.getSource();		panel.setBackground(new Color(230,235,240)); }
					public void mouseExited(MouseEvent e) {JPanel panel =(JPanel)e.getSource();		panel.setBackground(Color.WHITE); }
					public void mousePressed(MouseEvent e) {}
					public void mouseReleased(MouseEvent e) {}
				});
			
		}
		
	}
//上面部分设置
	public void up_setings()
	{
		nickname = new JLabel(user.getNickname());
		nickname.setFont(new Font("微软雅黑",Font.BOLD,20));
		up.setBounds(0,0,this.getWidth(),100);
		up.setLayout(new FlowLayout(0));
		head_image.setBounds(0,0,100,100);
		up.add(head_image);
		up.add(nickname);
	}
//中间部分设置
	public void middle_setings()
	{
		friend_and_group_button_Panel.setBounds(0,100,340,40);
		friend_and_group_button_Panel.setBackground(Color.WHITE);
		friend_and_group_button_Panel.setLayout(new GridLayout(1,2));
		friend_and_group_button_Panel.add(friend_button);
		friend_and_group_button_Panel.add(group_button);
		friend_button.addActionListener(new ActionListener() 
			{ public void actionPerformed(ActionEvent e) {cardlayout.show(friend_and_group_panel, "friend");} });
		
		group_button.addActionListener(new ActionListener() 
			{ public void actionPerformed(ActionEvent e) {cardlayout.show(friend_and_group_panel, "group");} });
	
	}
//下面部分设置
	public void down_setings()
	{
		friend_and_group_panel.setBounds(0,140,this.getWidth()-10,this.getHeight()-177);
		friend_and_group_panel.setLayout(cardlayout);
		friend_and_group_panel.add(friend_panel,"friend");
		friend_and_group_panel.add(group_list,"group");
//		cardlayout.show(friend_and_group_panel, "group");
	}
}