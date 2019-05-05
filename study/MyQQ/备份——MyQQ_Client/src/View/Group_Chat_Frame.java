package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import javax.swing.UIManager;

import Bussiness.Manage_ChatFrame;
import Bussiness.Manage_GroupChatFrame;
import Bussiness.SendFileToServer;
import Bussiness.SendMessagesToServer;
import Common.Group;
import Common.Group_Mate;
import Common.User;
import Common.UserInfoBean;

public class Group_Chat_Frame extends JFrame implements ActionListener,MouseListener,KeyListener
{
	My_JTextPane_1 textpane1 = new My_JTextPane_1();	//������Ϣ��ʾ��
	My_JTextPane_2 textpane2 = new My_JTextPane_2();	//�����
	JPanel panel1 = new JPanel(new GridLayout(9,1,4,4));
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
	Group group = new Group();
	Group_Mate[] groupmates = new Group_Mate[10];
	JPanel[] groupmates_panel = new JPanel[10];
	JLabel[] groupmates_label = new JLabel[10];
	
	SendMessagesToServer smts = new SendMessagesToServer();
	SimpleDateFormat time;  //���ڻ�ȡ��ǰʱ��
	public Group_Chat_Frame(User user,Group group)
	{
		//��ʼ��
		super(group.getGropu_Name());
		this.group=group;
		this.user=user;
		//���ò��ִ�С
		this.setBounds(450,200,1000,750);
		this.setLayout(new BorderLayout());
		this.setIconImage(new ImageIcon("E:\\ѧϰ\\��һ��\\JAVA\\����\\����ļ�\\Images\\qq.png").getImage());
		this.setResizable(false);
		//�������
		component_setings();
		//�Ҳ��Ա����
		right_setings();
		
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
				Manage_GroupChatFrame.remove(user.getId()+group.getGroup_Id());
				dispose();
			}
			public void windowDeactivated(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}
		});
}
	
	//�������
	public void component_setings()
	{
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
		//��������
		sent_message.setFont(new Font("΢���ź�",Font.PLAIN,15));
		close_this_frame.setFont(new Font("΢���ź�",Font.PLAIN,15));
		textpane2.setFont(new Font("΢���ź�",Font.PLAIN,20));		
	}
	//�Ҳ��Ա����
	public void right_setings()
	{
		//�Ҳ��Ա�б�
		panel1.setPreferredSize(new Dimension(200,700));
		panel1.setBackground(Color.white);
		panel1.setBorder(BorderFactory.createEtchedBorder());
		JLabel members = new JLabel("Ⱥ��Ա:");
		members.setFont(new Font("΢���ź�",Font.PLAIN,20));
		panel1.add(members);
		groupmates=group.getGroupmates();
		for(int i=1;i<=group.getTotal_Groupmate();i++)
		{
			groupmates_panel[i] = new JPanel(new FlowLayout(0));
			groupmates_label[i] = new JLabel(groupmates[i].getNickname()+"("+groupmates[i].getId()+")",new ImageIcon("E:\\���\\JAVA\\����\\MyQQ_Client\\src\\com.client.Images\\qq.png"),JLabel.LEFT);
			groupmates_panel[i].setName(groupmates[i].getId());
			groupmates_panel[i].add(groupmates_label[i]);
			groupmates_panel[i].setBackground(Color.WHITE);
			groupmates_panel[i].addMouseListener(new MouseListener() 
				{
					public void mouseClicked(MouseEvent e) {}
					public void mouseEntered(MouseEvent e) {JPanel panel =(JPanel)e.getSource();		panel.setBackground(new Color(230,235,240)); }
					public void mouseExited(MouseEvent e) {JPanel panel =(JPanel)e.getSource();		panel.setBackground(Color.WHITE); }
					public void mousePressed(MouseEvent e) {}
					public void mouseReleased(MouseEvent e) {}
				});
			panel1.add(groupmates_panel[i]);
		}
		this.add(panel1,BorderLayout.EAST);
	}
	
//������Ϣ
	public void SendMessages(String MesType,String Message)
	{
		if (Message.equals("")||Message.equals("\n")) return;
		UserInfoBean uib = new UserInfoBean();
		uib.setMesType(MesType);
		uib.setMessage(Message);
		uib.setSender(user.getId());
		uib.setReceiver(group.getGroup_Id());
		uib.setSendTime(new Date());
		uib.setSender_nickname(user.getNickname());
		smts.SendMessageTOServer(uib);
//		this.showMessages(uib);
	}
	
//��ʾ���ͺͽ��յ�����Ϣ
	public void showMessages(UserInfoBean uib)
	{
		time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String info =time.format(uib.getSendTime())+"  "+uib.getSender_nickname()+" :\n"+uib.getMessage()+"\n\n";
		textpane1.insert(info);
	}
		
//��ť������
	public void actionPerformed(ActionEvent event) 
	{
		
		if (event.getSource()==close_this_frame) {Manage_GroupChatFrame.remove(user.getId()+group.getGroup_Id()); dispose();}
		if (event.getSource()==sent_message)
		{
			textpane2.insertIcon("");
			this.SendMessages("4_5",textpane2.get_message());
			textpane2.setText("");
			textpane2.reset_message();
		}
		
		//����
		if (event.getSource()==biaoqing) 
		{
			JWindow window = new JWindow();
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
		if (event.getSource()==liaotianjilu){}
		if (event.getSource()==paint) {}
	}
	
//��������
	public void mouseClicked(MouseEvent event) {}
	public void mouseEntered(MouseEvent event) 
	{
		if (event.getSource()==textpane1)
		{
			//������Text�����Ϊ�ı�����ָ��
			textpane1.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		}
	}
	public void mouseExited(MouseEvent event) 
	{
		//����뿪Text����ָ�Ĭ����̬
		textpane1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
				this.SendMessages("4_5",textpane2.get_message());
				textpane2.setText("");
				textpane2.reset_message();
			}
		}
	}
	public void keyTyped(KeyEvent event) {}
	
}
