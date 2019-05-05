package Bussiness;

/*
 * 管理主界面的类
 */

import java.util.HashMap;
import View.Main_Frame;

public class Manage_MainFrame 
{
	private static HashMap hash = new HashMap<String,Main_Frame>();
	
	public static void addMainFrame(String s,Main_Frame frame)
	{
		hash.put(s, frame);
	}
	
	public static Main_Frame getMainFrame(String s)
	{
		return (Main_Frame)hash.get(s);
	}
	
	public static void remove(String id)
	{
		hash.remove(id);
	}
	
	public static boolean search(String id)
	{
		return !hash.containsKey(id);
	}
}
