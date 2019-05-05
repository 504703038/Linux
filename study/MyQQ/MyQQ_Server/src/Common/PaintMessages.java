package Common;

import java.awt.Color;

public class PaintMessages implements java.io.Serializable
{
	Color color;
	String shape;
	int x1,x2,y1,y2;
	String painter_size;
	
	public PaintMessages(String shape, Color color,String painter_size, int x1, int y1, int x2, int y2) 
	{
		this.color=color;
		this.shape=shape;
		this.painter_size=painter_size;
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
	}

	public Color getColor() 
	{
		return color;
	}
	
	public void setColor(Color color) 
	{
		this.color = color;
	}
	
	public String getShape() 
	{
		return shape;
	}
	
	public void setShape(String shape) 
	{
		this.shape = shape;
	}
	
	public int getX1() 
	{
		return x1;
	}
	
	public void setX1(int x1) 
	{
		this.x1 = x1;
	}
	
	public int getX2() 
	{
		return x2;
	}
	
	public void setX2(int x2) 
	{
		this.x2 = x2;
	}

	public int getY1() 
	{
		return y1;
	}

	public void setY1(int y1) 
	{
		this.y1 = y1;
	}

	public int getY2() 
	{
		return y2;
	}

	public void setY2(int y2) 
	{
		this.y2 = y2;
	}

	public String getPainter_size() 
	{
		return painter_size;
	}

	public void setPainter_size(String painter_size) 
	{
		this.painter_size = painter_size;
	}
	
	
	
}
