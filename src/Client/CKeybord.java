package Client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;


public class CKeybord
{
	private CKeybord keybord=this;
	private Socket client=null;
	private DataOutputStream out=null;
	public CKeybord(String ip,JPanel frame) throws Exception 
	//public CKeybord(String ip,Frame frame) throws Exception 
	{
		this.client=new Socket(ip,Define.KeybordPort);
		this.out=new DataOutputStream(client.getOutputStream());
		addKey(frame);
	}
	public void addKey(JPanel frame)
	//public void addKey(Frame frame)
	{
		frame.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				keybord.send(true,e.getKeyCode());
			}
			public void keyReleased(KeyEvent e)
			{
				keybord.send(false,e.getKeyCode());
			}
			public void keyTyped(KeyEvent e){}
		});
	}

	public void send(boolean type,int keyCode)
	{
		try {
			out.writeBoolean(type);
			out.writeInt(keyCode);
			System.out.println(type+" "+keyCode);
			out.flush();
		} 
		catch(Exception ex){ex.printStackTrace();}
	}
}
