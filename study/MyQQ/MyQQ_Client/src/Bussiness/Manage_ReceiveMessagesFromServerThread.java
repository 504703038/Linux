package Bussiness;

/*
 * 功能：接收信息线程的管理器
 */

import java.util.*;
public class Manage_ReceiveMessagesFromServerThread 
{
	static HashMap hash = new HashMap<String ,ReceiveMessagesFromServer_Thread>();
	
	
	public static void addReceiveMessagesFromServer(String id,ReceiveMessagesFromServer_Thread rmfs)
	{
		hash.put(id, rmfs);
	}
			
	
	public static ReceiveMessagesFromServer_Thread getReceiveMessagesFromServer(String id)
	{
		return (ReceiveMessagesFromServer_Thread)hash.get(id);
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
