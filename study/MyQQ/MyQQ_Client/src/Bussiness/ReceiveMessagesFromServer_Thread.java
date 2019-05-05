package Bussiness;

/*
 * 功能：从服务器获取消息的线程
 */

import Common.*;
import View.*;

import java.net.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.io.*;

public class ReceiveMessagesFromServer_Thread extends Thread
{
		public Socket s;
		private UserInfoBean uib = new UserInfoBean();
		public  boolean exit = false; 
		
		public ReceiveMessagesFromServer_Thread(Socket s)
		{
			this.s=s;
		}
		
		public void run()
		{
			while (!exit)
			{
				//从服务器不停的读取消息。
				try
				{
					ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
					uib = (UserInfoBean)ois.readObject();
//					System.out.println(uib.getMesType()+"\n"+uib.getSender()+"  To  "+uib.getReceiver()+"\n"+uib.getMessage()+"\n");
					
					if (uib.getMesType().equals("2_3"))
					{
						Main_Frame main_frame=Manage_MainFrame.getMainFrame(uib.getReceiver());
						main_frame.UpdataOnlineInfo(uib.getSender());
					}
					
					if (uib.getMesType().equals("4"))
					{
						if (!Manage_ChatFrame.search(uib.getReceiver()+"&"+uib.getSender()))
						{
							Main_Frame main_frame =Manage_MainFrame.getMainFrame(uib.getReceiver());
							main_frame.ReceiveMessages_OffLine(uib.getSender());
						}
						else
						{
							Chat_Frame chat = (Chat_Frame)Manage_ChatFrame.getChatFrame(uib.getReceiver()+"&"+uib.getSender());
							chat.showMessages(uib);
						}
					}
					
					if (uib.getMesType().equals("4_1"))
					{	
						Chat_Frame chat = (Chat_Frame)Manage_ChatFrame.getChatFrame(uib.getReceiver()+"&"+uib.getSender());
						String address = chat.receiveFile();
						//接收文件
						ReceiveFileFromServer rffs = new ReceiveFileFromServer(address);
					}
					
					if (uib.getMesType().equals("4_2"))
					{
						if (!Manage_ChatFrame.search(uib.getReceiver()+"&"+uib.getSender()))
						{
							System.out.println(uib.getSender()+"|||||"+uib.getReceiver());
							User receiver = new User();
							receiver.setId(uib.getSender());
							User user = new User();
							user.setId(uib.getReceiver());
							Chat_Frame chat_frame = new Chat_Frame(user,receiver);
							Manage_ChatFrame.addChatFrame(user.getId()+"&"+receiver.getId(), chat_frame);
						}
						for(int i=1;i<=100000000;i++) {}
						Chat_Frame chat = (Chat_Frame)Manage_ChatFrame.getChatFrame(uib.getReceiver()+"&"+uib.getSender());
						uib.setMessage(uib.getSender()+"给您发送了一个窗口抖动。\n");
						chat.showMessages(uib);
						chat.shake();
					}
					
					if (uib.getMesType().equals("4_6"))
					{
						if (Manage_GroupChatFrame.search(uib.getReceiver()+uib.getMessages_Group()))
						{
							Group_Chat_Frame group_chat_frame=Manage_GroupChatFrame.getChatFrame(uib.getReceiver()+uib.getMessages_Group());
							group_chat_frame.showMessages(uib);
						}
					}
					
					if (uib.getMesType().equals("4_7"))
					{
						Chat_Frame chat = (Chat_Frame)Manage_ChatFrame.getChatFrame(uib.getChat_Frame_Key());
						chat.showHistoryMessages(uib);
					}
					
					if (uib.getMesType().equals("4_8"))
					{
						if (Manage_ChatFrame.search(uib.getReceiver()+"&"+uib.getSender()))
						{
							JOptionPane.showMessageDialog(null,"对方请求画图功能","提示", 2, new ImageIcon(""));
							Chat_Frame chat = (Chat_Frame)Manage_ChatFrame.getChatFrame(uib.getReceiver()+"&"+uib.getSender());
							chat.OpenDrawingBoard();
						}
					}
					
					if (uib.getMesType().equals("4_9"))
					{
						if (Manage_ChatFrame.search(uib.getReceiver()+"&"+uib.getSender()))
						{
							Chat_Frame chat = (Chat_Frame)Manage_ChatFrame.getChatFrame(uib.getReceiver()+"&"+uib.getSender());
							chat.Drawing(uib.getPaintmessages());
						}
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
}