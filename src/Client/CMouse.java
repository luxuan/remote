package Client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JPanel;

public class CMouse
{
	private Socket client=null;
	private DataOutputStream out=null;

	public CMouse(String ip,JPanel frame) throws IOException
	{
		this.client=new Socket(ip,Define.MousePort);
		this.out=new DataOutputStream(client.getOutputStream());
		addKey(frame);
	}
	private void addKey(final JPanel frame)
	{
		frame.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e)
			{
				send(true,e.getButton(),e.getX(),e.getY());
			}
			public void mouseReleased(MouseEvent e)
			{
				send(false,e.getButton(),e.getX(),e.getY());
 			}
		});
	}
	private void send(boolean isPress,int type,int x,int y )
	{
		try {
			out.writeBoolean(isPress);
			out.writeInt(type);
			out.writeInt(x);
			out.writeInt(y);
			out.flush();
		} 
		catch(Exception ex){ex.printStackTrace();}
	}

}
