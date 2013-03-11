package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.util.ArrayList;

import Server.Define;
public class StartServer {
	public static void main(String[] args) 
	{
		int counter=0;
		ArrayList<String> ipArray=new ArrayList<String>();
		try
		{
			ServerSocket server;
			try{server=new ServerSocket(Define.MPort);}
			catch(Exception ex){server=new ServerSocket(Define._MPort);}
			while(true)
			{
				Socket client=server.accept();
				String ip=client.getInetAddress().toString();
				ipArray.add(ip);
				++counter;
				//System.out.print(counter+" connect ");
				
				DataInputStream in=new DataInputStream(client.getInputStream());
				DataOutputStream out=new DataOutputStream(client.getOutputStream());
				String name=in.readUTF();
				String code=in.readUTF();
				if(name.equals("lius")&&code.equals("hello"))
				{
					out.writeInt(Define.OK);
					new MainThread(in,out).start();
					//System.out.println(name+"("+ip+") working...");
				}
				else
				{
					out.writeInt(Define.BAD);
					//System.out.println(name+"("+ip+") return");
				}
			}
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
		}
	}
}
