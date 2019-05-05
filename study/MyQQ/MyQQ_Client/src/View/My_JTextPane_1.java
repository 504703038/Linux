package View;
/*
 * ������Ϣ��ʾ��
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
		StyleConstants.setFontFamily(attrset, "΢���ź�");
	}
	
	//�����������Ҫ����;�ǽ��ַ������뵽JTextPane�С�
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
			Document docs=this.getDocument();//����getDocument()����ȡ��JTextPane��Document instance.0
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
	//����ͼƬ
	public void insertIcon(String path) 
	{
		 Document doc=this.getDocument();
		  this.setCaretPosition(doc.getLength()); // ���ò���λ��
		  this.insertIcon(new ImageIcon(path)); // ����ͼƬ
	}
}
