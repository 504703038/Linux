package Bussiness;

import java.util.HashMap;

import View.Group_Chat_Frame;

public class Manage_GroupChatFrame 
{
	private static HashMap hash = new HashMap<String,Group_Chat_Frame>();
	
	public static void addChatFrame(String s,Group_Chat_Frame frame)
	{
		hash.put(s, frame);
	}
	
	public static Group_Chat_Frame getChatFrame(String s)
	{
		return (Group_Chat_Frame)hash.get(s);
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
