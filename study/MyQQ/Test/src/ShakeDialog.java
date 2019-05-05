import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class ShakeDialog 
{
	private JDialog dialog;
	private static Point start;
	private Timer shakeTimer;
	static JOptionPane pane = new JOptionPane("°¢ÈøµÂ",JOptionPane.WARNING_MESSAGE);
	static JDialog d=pane.createDialog(null, "Õð¶¯");
	static Date time = new Date();
	public static void main(String[] args) 
	{
		d.pack();
		d.setModal(false);
		d.setVisible(true);
		start=d.getLocation();
		System.out.println(time);
		shake();
		stop();
	}
	
	public static void shake()
	{
		int t=0;
		while (t<=3)
		{
			int[][] a = {{-1,1},{-2,2},{-3,3},{-4,4},{-5,5},{-6,4},{-7,3},{-8,2},{-9,1},{-10,0},{-9,-1},{-8,-2},{-7,-3},{-6,-4},{-5,-5},{-4,-4},{-3,-3},{-2,-2},{-1,-1},{0,0}};
			for(int j=19;j>=0;j--)
			{
				for(int i=1;i<20000000;i++){}
				d.setLocation(start.x+a[j][0], start.y+a[j][1]);
			}
			t++;
		}
	}
	
	private static int abs(int i) 
	{
		if (i<0)
		return -i;
		return i;
	}

	public static void stop()
	{
		
	}

}
