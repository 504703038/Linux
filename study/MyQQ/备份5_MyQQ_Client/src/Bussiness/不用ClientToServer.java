package Bussiness;
//package Bussiness;
//
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//import Common.User;
//import Common.UserInfoBean;
//
//public class ClientToServer {
//
////	public static void main(String[] args) 
////	{
////		new ClientToServer();
////	}
//	
//	
////	public ClientToServer(Socket socket)
////	{
////		this.socket=socket;
////		
////	}
//	
//	public static Socket socket = null;
//	
//	public UserInfoBean SendLoginIfoTOServer(UserInfoBean uib) 
//	{
//		try
//		{
//			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//			
//			//������Ϣ
//			oos.writeObject(uib);
//			
//			//������Ϣ
//			uib = (UserInfoBean)ois.readObject();
//			
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		return uib;
//	}
//
//}
