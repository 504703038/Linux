package Bussiness;

/*
 * 功能：识别信息类型并做出反应
 */

import Common.*;
import Data.*;

public class Server_Lauch 
{
	JDBC_Add_Account add_account = new JDBC_Add_Account();
	JDBC_Add_Message add_message = new JDBC_Add_Message();
	JDBC_Add_GroupMessage add_groupmessages = new JDBC_Add_GroupMessage();
	
	JDBC_Updata updata = new JDBC_Updata();
	
	JDBC_Select_User select_user = new JDBC_Select_User();
	JDBC_Select_Messages select_messages = new JDBC_Select_Messages();
	JDBC_Select_GroupMessages select_groupmessages = new JDBC_Select_GroupMessages();
	JDBC_Select_Groupmates select_groupmates = new JDBC_Select_Groupmates();
	JDBC_Select_HistoryMessages select_historymessages = new JDBC_Select_HistoryMessages();
	
	SendMessagesToClient smtc = new SendMessagesToClient();
	Server_Login login = new Server_Login();
	
	public UserInfoBean  Lauch(UserInfoBean uib)
	{
		
		if (uib.getMesType().equals("1"))
		{
			add_account.Add_Account(uib);
			return uib;
		}
		
		if (uib.getMesType().equals("1_2"))
		{
			User user = select_user.SelectUser(uib.getUser().getId());
			if (user!=null && user.getPhonenumber().equals(uib.getUser().getPhonenumber()))
			return uib;
			return null;
		}
		
		if (uib.getMesType().equals("1_3"))
		{
			updata.ResetPassword(uib.getUser());
			return null;
		}
		
		if (uib.getMesType().equals("2"))
		{
			if (!Manag_ReceiveMessagesFromClient_Thread.search(uib.getUser().getId()))
			return login.Server_Login(uib);
			else
			{
				UserInfoBean u = new UserInfoBean();
				u.setMesType("2_2");
				return u;
			}
		}
		
		if (uib.getMesType().equals("2_1"))
		{
			User u = select_user.SelectUser(uib.getUser().getId());
			if (u==null) return null;
			uib.setUser(u);
			return uib;
		}
		
		if (uib.getMesType().equals("4"))
		{
			add_message.Add_Message(uib);
			if (Manag_ReceiveMessagesFromClient_Thread.search(uib.getReceiver())) smtc.SendMessages(uib);
			return null;
		}
		
		if (uib.getMesType().equals("4_1"))
		{
			//向接收文件的客户端发送消息，让其接收文件
			smtc.SendMessages(uib);
			Server_FileTransfer rffc = new Server_FileTransfer();
			System.out.println("asdasdasdas");
		}
		
		if (uib.getMesType().equals("4_2"))
		{
			if (Manag_ReceiveMessagesFromClient_Thread.search(uib.getReceiver()))
			smtc.SendMessages(uib);
			return null;
		}
		
		if (uib.getMesType().equals("4_3"))
		{
			select_messages.Select_Messages(uib.getReceiver(),uib.getSender(),"4_3");
			return null;
		}
		
		if (uib.getMesType().equals("4_5"))
		{
			add_groupmessages.Add_GroupMessage(uib);
			uib.setMesType("4_6");
			uib.setMessages_Group(uib.getReceiver());
			Group_Mate[] groupmates = select_groupmates.Select_Groupmates(uib.getReceiver());
			int total =groupmates[0].getTotal_groupmates();
			for(int i=1;i<=total;i++)
			{
				User user = select_user.SelectUser(groupmates[i].getId());
				if (user.getOnLine().equals("1"))
				{
					uib.setReceiver(user.getId());
					smtc.SendMessages(uib);
				}
			}
			return null;
		}
		
		if (uib.getMesType().equals("4_6"))
		{
			select_groupmessages.Select_GroupMessages(uib);
			return null;
		}
		
		if (uib.getMesType().equals("4_7"))
		{
			select_historymessages.Select_HistoryMessages(uib.getSender(), uib.getReceiver(), "4_7");
			return null;
		}
		
		if (uib.getMesType().equals("4_8") || uib.getMesType().equals("4_9"))
		{
			if (Manag_ReceiveMessagesFromClient_Thread.search(uib.getReceiver())) smtc.SendMessages(uib);
			return null;
		}
		
		if (uib.getMesType().equals("5"))
		{
			ReceiveMessagesFromClient_Thread rmfct = Manag_ReceiveMessagesFromClient_Thread.getClientThread(uib.getUser().getId());
			Manag_ReceiveMessagesFromClient_Thread.remove(uib.getUser().getId());
			updata.Logout(uib.getUser().getId());
			try 
			{
				rmfct.s.close();
				rmfct.exit=true;
				rmfct.join();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return null;
	}
}
