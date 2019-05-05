package Bussiness;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;

public class Server_FileTransfer extends Thread
{
	private Socket send_s;
	private Socket receive_s;
	private ServerSocket send_ss;
	private ServerSocket receive_ss;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	private static DecimalFormat df = null;  
    static {  
        // 设置数字格式，保留一位有效小数  
        df = new DecimalFormat("#0.0");  
        df.setRoundingMode(RoundingMode.HALF_UP);  
        df.setMinimumFractionDigits(1);  
        df.setMaximumFractionDigits(1);  
    }
	
	public Server_FileTransfer()
	{
		//与客户端连接
		try 
		{
			//接收文件
			receive_ss = new ServerSocket(10000);
			send_ss = new ServerSocket(10001);
			receive_s=receive_ss.accept();
			send_s=send_ss.accept();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		start();
	}
	
	public void run()
	{
		try 
		{
			dis = new DataInputStream(receive_s.getInputStream());
			dos = new DataOutputStream(send_s.getOutputStream());
			
			String fileName = dis.readUTF();  
            long fileLength = dis.readLong();  
            //文件名和长度
            dos.writeUTF(fileName);
			dos.flush();
			dos.writeLong(fileLength);
			dos.flush();
            // 开始接收文件  
            byte[] bytes = new byte[1024];
            int length = 0;  
            
            while((length = dis.read(bytes, 0, bytes.length)) != -1)
            {  
            	dos.write(bytes, 0, length);  
                dos.flush();
            }  
            dis.close();
            receive_ss.close();
            receive_s.close();
            send_s.close();
            send_ss.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
