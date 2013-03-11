package Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import Client.Define;

public class CConsole extends Thread
{
	private Socket client=null;
	BufferedReader stdin=null;
	DataInputStream in=null;
	DataOutputStream out=null;
	public CConsole(String ip) throws Exception
	{
		client=new Socket(ip,Define.ConsolePort);
		stdin=new BufferedReader(new InputStreamReader(System.in));
		in=new DataInputStream(client.getInputStream());
		out=new DataOutputStream(client.getOutputStream());
		if(in.readInt()==Define.OK)
		{
			this.start();
			System.out.println("CConsole start.");
		}
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				String command=stdin.readLine();
				out.writeUTF(command);
				out.flush();
				String str=in.readUTF();
				System.out.println(str);
			}
		}
		catch(Exception ex){ex.printStackTrace();}
	}

}
