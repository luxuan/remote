package Server;

import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class SMouse extends Thread {
	private Socket client=null;
	private DataInputStream in=null;
	private Robot robot = null;
	public SMouse() throws Exception  
	{
		ServerSocket server=new ServerSocket(Define.MousePort);
		this.client=server.accept();
		//System.out.println("mouse in!");
		this.in=new DataInputStream(client.getInputStream());
		this.robot = new Robot();
	}
	public void run()
	{
		try{
			while(true)
			{
				boolean mousePress=in.readBoolean();
				int type=in.readInt();
				int x=in.readInt();
				int y=in.readInt();
				
				int cInt=-1;
				if(type==MouseEvent.BUTTON1)cInt=MouseEvent.BUTTON1_MASK;
				else if(type==MouseEvent.BUTTON2)cInt=MouseEvent.BUTTON2_MASK;
				else if(type==MouseEvent.BUTTON3)cInt=MouseEvent.BUTTON3_MASK;
				this.robot.mouseMove(x,y);
				if(mousePress)
				{
					this.robot.mousePress(cInt);
				}
				else
				{
					this.robot.mouseRelease(cInt);
				}
			}
		}
		catch(IOException ex)
		{
			//ex.printStackTrace();
		}
	}

}
