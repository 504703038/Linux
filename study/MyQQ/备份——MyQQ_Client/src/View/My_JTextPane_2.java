package View;

import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class My_JTextPane_2 extends JTextPane
{
	private String message = "";
	private String last_content = "";
	public My_JTextPane_2()
	{
		this.setBackground(Color.WHITE);
		this.setEditable(true);
	}
	
	public void insertIcon(String path) 
	{
		//获取pane中的内容
		if(this.getText().length()>last_content.length())
		{
			message += this.getText().substring(last_content.length(), this.getText().length());
			last_content = "" + this.getText();
		}
		Document doc=this.getDocument();
		this.setCaretPosition(doc.getLength()); // 设置插入位置
		this.insertIcon(new ImageIcon(path)); // 插入图片	
		message +="%)$&)#)#*" +path +"%)$&)#)#*";
	}
	
	public String get_message()
	{
		return message;
	}
	
	public void reset_message()
	{
		message = "";
		last_content="";
	}
	
}