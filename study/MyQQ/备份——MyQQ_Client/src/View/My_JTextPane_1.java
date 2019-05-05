package View;
/*
 * 聊天信息显示框
 */
import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class My_JTextPane_1 extends JTextPane
{
	SimpleAttributeSet attrset=new SimpleAttributeSet();
	public My_JTextPane_1()
	{
		this.setBackground(Color.WHITE);
		this.setEditable(false);
		StyleConstants.setForeground(attrset,Color.BLACK);
		StyleConstants.setItalic(attrset,false);
		StyleConstants.setFontSize(attrset,20);
		StyleConstants.setFontFamily(attrset, "微软雅黑");
	}
	
	//这个方法最主要的用途是将字符串插入到JTextPane中。
	public void insert(String str)
	{
		String message="";
		String image="";
		int i=0;
		
		str+='\n';
		while (i<str.length())
		{
			int j=str.substring(i).indexOf("%)$&)#)#*");
			if (j==0)
			{
				j=i+9;
				i+=9;
				while (str.charAt(i)!='%') i++;
				image=str.substring(j, i);
				insertIcon(image);
				i+=9;
			}
			Document docs=this.getDocument();//利用getDocument()方法取得JTextPane的Document instance.0
			try
			{
				docs.insertString(docs.getLength(),""+str.charAt(i),attrset);     
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			i++;
		}
		
	}
	//插入图片
	public void insertIcon(String path) 
	{
		 Document doc=this.getDocument();
		  this.setCaretPosition(doc.getLength()); // 设置插入位置
		  this.insertIcon(new ImageIcon(path)); // 插入图片
	}
}
