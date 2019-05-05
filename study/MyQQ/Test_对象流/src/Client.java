import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client 
{
	public static void main(String[] args)
	{
		new Client();
	}
	Client()
	{
		try 
		{
			Socket s = new  Socket("127.0.0.1",3456);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			User u = new User();
			u.setId("123456");
			u.setPassword("123456");
			oos.writeObject(u);
			s.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
