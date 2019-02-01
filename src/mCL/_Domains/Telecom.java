package mCL._Domains;

import java.awt.Color;
import java.awt.Font;

public class Telecom {
	
	private String name;
	private Color[] colorPrefs;
	private Font[] fontPrefs;
	
	public Telecom() throws Exception{
		setName("");
	}
	
	public Telecom(String name) throws Exception{
		setName(name);
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		return name;
	}
	
	public void setColorPrefs(Color[] colors)
	{
		colorPrefs = colors;
	}
	
	public Color[] getColorPrefs()
	{
		return colorPrefs;
	}
	
	public void setFontPrefs(Font[] fonts)
	{
		fontPrefs = fonts;
	}
	
	public Font[] getFontPrefs()
	{
		return fontPrefs;
	}
	
}