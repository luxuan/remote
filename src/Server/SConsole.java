package Server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class SConsole extends Thread {
	public SConsole() {}
	public void run()
	{
		try
		{
			ServerSocket server=new ServerSocket(Define.ConsolePort);
			while(true)
			{
				Socket client=server.accept();
				new MyThread(client);
			}
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
		}
	}
	

}
class MyThread extends Thread 
{

	private Socket client=null;
	public MyThread(Socket client) 
	{
		this.client=client;
		this.start();
	}
	public void run() 
	{
		DataInputStream in=null;
		DataOutputStream out=null;
		
		try
		{
			BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
			in=new DataInputStream(client.getInputStream());
			out=new DataOutputStream(client.getOutputStream());
			out.writeInt(Define.OK);
			while(true)
			{
				try
				{
					String command=in.readUTF();
					if(command.equals("exit"))break;
				    Runtime r=Runtime.getRuntime();
				    Process p=r.exec(command);
				    BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
				    StringBuffer sb=new StringBuffer();
				    String inline;
				    while(null!=(inline=br.readLine()))
				    {
				    	sb.append(inline);
				    	sb.append("\n");
				    }
				    out.writeUTF(sb.toString());
					out.flush();
				}
				catch(Exception ex){out.writeUTF(ex.toString());}
			}
			stdin.close();
			out.close();
			in.close();
			client.close();
		}
		catch(Exception ex) 
		{
			//System.out.println("MyThread  "+ex.toString());
		}

	}

}
