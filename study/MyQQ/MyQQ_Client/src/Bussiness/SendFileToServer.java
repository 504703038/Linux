package Bussiness;

import Common.*;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.*;
import java.awt.*;


public class SendFileToServer extends Thread
{
	private Socket socket;
	
	private File file;
    private FileInputStream fis;  
    private DataOutputStream dos;  
    
	public SendFileToServer(File file)
	{
		this.file=file;
		
		//连接服务器的ReceivFileFromClient
		try 
		{
			socket = new Socket("127.0.0.1",10000);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		//启动线程
		start();
		return;
	}
	
	public void run()
	{
		JFrame frame = new JFrame();
		JProgressBar jpb= new JProgressBar();
		jpb.setMaximum(100);
		jpb.setMinimum(0);
		jpb.setStringPainted(true);
		jpb.setValue(0);
		jpb.setPreferredSize(new Dimension(400,50));
		frame.add(jpb);
		frame.setLocation(850,500);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(2);
		try 
		{
			fis = new FileInputStream(file);
			dos = new DataOutputStream(socket.getOutputStream());
			//文件名和长度
			dos.writeUTF(file.getName());
			dos.flush();
			dos.writeLong(file.length());
			dos.flush();
			//开始传输
			byte[] bytes = new byte[1024]; 
			int length = 0;  
            long progress = 0;  
            while((length = fis.read(bytes, 0, bytes.length)) != -1) 
            {  
                dos.write(bytes, 0, length);  
                dos.flush();  
                progress += length;  
                jpb.setValue((int) (100*progress/file.length()));
            }
            JOptionPane.showMessageDialog(null,"传输完成","提示", 2, new ImageIcon(""));
            frame.dispose();
            fis.close();  
            dos.close(); 
            socket.close();
            return;
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
	}
	
}
