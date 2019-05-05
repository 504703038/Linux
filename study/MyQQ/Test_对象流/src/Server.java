import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
	public static void main(String[] args)
	{
		new Server();
	}
	Server()
	{
		try
		{
			System.out.println("ÕýÔÚ¼àÌý");
			ServerSocket ss = new ServerSocket(3456);
			Socket s=ss.accept();	
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			User u = (User)ois.readObject();
			System.out.println(u.getId()+"\n"+u.getPassword());
			s.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
