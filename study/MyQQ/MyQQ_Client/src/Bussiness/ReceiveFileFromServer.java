package Bussiness;

import java.awt.Dimension;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.Socket;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class ReceiveFileFromServer extends Thread
{
	private Socket socket;
	private DataInputStream dis;
	private FileOutputStream fos;
	private String address=null;
	private static DecimalFormat df = null;  
	private boolean f =false;
	
    static {  
        // �������ָ�ʽ������һλ��ЧС��  
        df = new DecimalFormat("#0.0");  
        df.setRoundingMode(RoundingMode.HALF_UP);  
        df.setMinimumFractionDigits(1);  
        df.setMaximumFractionDigits(1);  
    }
	
	public ReceiveFileFromServer(String address)
	{
		this.address=address;
		//�����������
		try 
		{
			socket=new Socket("127.0.0.1",10001);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
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
		frame.setLocation(850,500);
		jpb.setPreferredSize(new Dimension(400,50));
		frame.add(jpb);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(2);
		try 
		{
			dis = new DataInputStream(socket.getInputStream());
			String fileName = dis.readUTF();  
            long fileLength = dis.readLong();  
            if (address!=null)
            {
            	f=true;
            	File directory = new File(address);  
                if(!directory.exists())
                {  
                    directory.mkdir();  
                } 
                File file = new File(directory.getAbsolutePath() + File.separatorChar + fileName);  
                fos = new FileOutputStream(file); 
            }
            // ��ʼ�����ļ�  
            byte[] bytes = new byte[1024];  
            int length = 0;  
            long progress = 0;  
            while((length = dis.read(bytes, 0, bytes.length)) != -1)
            {  
            	if (f)
            	{ 
            		fos.write(bytes, 0, length);
            		fos.flush();  
            		progress += length;  
            		jpb.setValue((int) (100*progress/fileLength));
            	}
            }  
            if (f)
            JOptionPane.showMessageDialog(null,"�������","��ʾ", 2, new ImageIcon(""));
            frame.dispose();
            dis.close();
            socket.close();
            return;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private String getFormatFileSize(long length) 
	{  
        double size = ((double) length) / (1 << 30);  
        if(size >= 1) 
        {  
            return df.format(size) + "GB";  
        }  
        size = ((double) length) / (1 << 20);  
        if(size >= 1) 
        {  
            return df.format(size) + "MB";  
        }  
        size = ((double) length) / (1 << 10);  
        if(size >= 1) 
        {  
            return df.format(size) + "KB";  
        }  
        return length + "B";  
    } 
	
}
