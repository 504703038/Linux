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
	}
	
	public void run()
	{
		
//		file=uib.getFile();
		JFrame frame = new JFrame();
		JProgressBar jpb= new JProgressBar();
		jpb.setMaximum(100);
		jpb.setMinimum(0);
		jpb.setStringPainted(true);
		jpb.setValue(0);
		jpb.setPreferredSize(new Dimension(400,50));
		frame.add(jpb);
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
//			System.out.println("======== 开始传输文件 ========");
			byte[] bytes = new byte[1024]; 
			int length = 0;  
            long progress = 0;  
            while((length = fis.read(bytes, 0, bytes.length)) != -1) 
            {  
                dos.write(bytes, 0, length);  
                dos.flush();  
                progress += length;  
//                System.out.print("| " + (100*progress/file.length()) + "% |");  
                jpb.setValue((int) (100*progress/file.length()));
            }
//            System.out.println();  
//            System.out.println("======== 文件传输成功 ========");  
            JOptionPane.showMessageDialog(null,"传输完成","提示", 2, new ImageIcon(""));
            frame.dispose();
            fis.close();  
            dos.close(); 
            socket.close();
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
//		System.out.println("发送成功。");
	}
	
}
