package Bussiness;

/*
 * 功能：聊天窗口的管理器
 */

import java.util.*;

import View.*;

public class Manage_ChatFrame 
{
	private static HashMap hash = new HashMap<String,Chat_Frame>();
	
	public static void addChatFrame(String s,Chat_Frame frame)
	{
		hash.put(s, frame);
	}
	
	public static Chat_Frame getChatFrame(String s)
	{
		return (Chat_Frame)hash.get(s);
	}
	
	public static void remove(String id)
	{
		hash.remove(id);
	}
	
	public static boolean search(String id)
	{
		return hash.containsKey(id);
	}
}	
