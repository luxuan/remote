package Server;
import java.awt.Robot;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;


public class SKeybord extends Thread {
	private Socket client=null;
	private DataInputStream in=null;
	private Robot robot = null;
	public SKeybord() throws Exception 
	{
		ServerSocket server=new ServerSocket(Define.KeybordPort);
		this.client=server.accept();
		//System.out.println("keybord in!");
		this.in=new DataInputStream(client.getInputStream());
		this.robot = new Robot();
	}
	public void run()
	{
		try{
			while(true)
			{
				boolean keyUp=in.readBoolean();
				int read=in.readInt();
				if(keyUp){this.robot.keyPress(read);}
				else{this.robot.keyRelease(read);}
			}
		}
		catch(IOException ex)
		{
			//ex.printStackTrace();
		}
	}
}
