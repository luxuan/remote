package Client;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.DataInputStream;
import java.io.File;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Client.Define;

public class CView extends JLabel
{
	private String ip=null;
	private int rate=1000;
	public CView(String ip,int hz,JPanel panel)throws Exception 
	{
		super();
		this.ip=ip;
		this.rate=hz;
		this.setVisible(true);
		run();
		panel.add(this);
	}
	public void run()
	{
		CReceivea pic=null; 
		try{pic=new CReceivea(ip,this);}
		catch(Exception ex){ex.printStackTrace();}
		Timer t=new Timer();
		t.schedule(pic,0, rate);
		System.out.println("CView run.");
	}
}

class CReceivea extends TimerTask 
{
	private static int serialNum=0;
	private String ip=null;
	private JLabel pane=null;

	Socket client=null;
	public CReceivea(String ip,JLabel pane)throws Exception
	{
		this.ip=ip;
		this.pane=pane;
	}
	public void run()
	{
		try
		{
			Socket client=new Socket(ip,Define.ImagePort);
			/*
			DataInputStream in = new DataInputStream(client.getInputStream());
			int w,h,size;
			w=in.readInt();
			h=in.readInt();
			System.out.println(w+" "+h);
			size=w*h;
			int value[]=new int[size];
			int counter,tv;
			for(int i=0;i<size;)
			{
				counter=in.readInt();
				tv=in.readInt();
				for(int j=0;j<counter;j++,i++)
				{
					value[i]=tv;
					//System.out.println(i+"= "+value[i]);
				}
			}
			System.out.println("ok");
			Image screenshot=pane.createImage(new MemoryImageSource(w,h,value,0,w));
			*/
			BufferedImage screenshot=ImageIO.read(client.getInputStream());
			pane.setIcon(new ImageIcon(screenshot));

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
