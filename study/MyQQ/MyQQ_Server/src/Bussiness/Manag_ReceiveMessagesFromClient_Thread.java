package Bussiness;

/*
 * ���ܣ�������Ϣ�̵߳Ĺ�����
 * 
 */

import java.util.*;

public class Manag_ReceiveMessagesFromClient_Thread 
{
	public static HashMap  hash = new HashMap<String ,ReceiveMessagesFromClient_Thread>();
	
	//��HashMap������߳�
	public static void addClientThread(String id,ReceiveMessagesFromClient_Thread stc)
	{
		hash.put(id, stc);
	}
	
	public static ReceiveMessagesFromClient_Thread getClientThread(String id)
	{
		return (ReceiveMessagesFromClient_Thread)hash.get(id);
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
