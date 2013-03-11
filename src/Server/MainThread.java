package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class MainThread extends Thread 
{
	static int counter;
	DataInputStream in=null;
	DataOutputStream out=null;
	public MainThread(DataInputStream in,DataOutputStream out)
	{
		this.in=in;
		this.out=out;
	}
	public void run()
	{
		SMouse mouse=null;
		SCamera send=null;
		SKeybord keybord=null;
		SFile file=null;
		SConsole console=null;
		try
		{
			while(true)
			{			
				int command=in.readInt();
				switch(command)
				{
					/***********
					 * Image
					 ***********/
					case Define.NewImage:
						send=new SCamera();
						send.start();
						break;
					case Define.StopImage:
						send.wait();
						break;
					case Define.ContinueImage:
						send.notify();
						break;
						
					/***********
					 * Mouse
					 ***********/
					case Define.NewMouse:
						mouse=new SMouse();
						mouse.start();
						break;
					case Define.StopMouse:
						mouse.wait();
						break;
					case Define.ContinueMouse:
						mouse.notify();
						break;
						
					/***********
					 * Keybord
					 ***********/		
					case Define.NewKeybord:
						keybord=new SKeybord();
						keybord.start();
						break;
					case Define.StopKeybord:
						keybord.wait();
						break;
					case Define.ContinueKeybord:
						keybord.notify();
						break;
						
					/***********
					 * Console
					 ***********/						
					case Define.NewConsole:
						console=new SConsole();
						console.start();
						break;
					case Define.StopConsole:
						console.wait();
						break;
					case Define.ContinueConsole:
						console.notify();
						break;
						
					/***********
					 * File
					 ***********/
					case Define.NewFile:
						file=new SFile();
						break;
					case Define.StopFile:
						file.wait();
						break;
					case Define.ContinueFile:
						file.notify();
						break;
					default:break;
				}
			}
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
}
