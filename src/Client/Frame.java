package Client;
import Client.Tool.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class Frame extends JFrame
{
	private final int MIN=50;
	private Frame frame=this;
	private JPanel panel=null;
	private Head head=null;
	private FileManager fileManager=null;
	
	private CMouse mouse=null;
	private CView view=null;
	private CKeybord keybord=null;
	private CConsole console=null;
	private CFile file=null;
	
	private Socket client=null;
	private DataInputStream in=null;
	public DataOutputStream out=null;
	
	public String ip=null;
	public Frame()
	{
		super("Panda 1.0");
		this.setVisible(false);
	}
	public boolean connect(String ip,String name,String password) throws Exception 
	{
		try{client=new Socket(ip,Define.MPort);}
		catch(Exception ex){client=new Socket(ip,Define._MPort);}
		in=new DataInputStream(client.getInputStream());
		out=new DataOutputStream(client.getOutputStream());
		out.writeUTF(name);
		out.writeUTF(password);
		int reInt=in.readInt();
		if(reInt==Define.OK)
		{
			this.ip=ip;
			return true;
		}
		else 
		{
			return false;
		}
		
	}
	public void start()
	{
		init();

        head.view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    			String strRate=JOptionPane.showInputDialog(frame,"Input the rate(ms/pix)","Input Rate",JOptionPane.INFORMATION_MESSAGE);
    			int rate=Integer.parseInt(strRate);
    			if(rate<MIN)rate=MIN;
				try
				{
    				out.writeInt(Define.NewImage);
    				out.flush();
    				view=new CView(ip,rate,panel);
    				head.view.setEnabled(false);
    				head.mouse.setEnabled(true);
    				head.keybord.setEnabled(true);
				}
				catch(Exception ex){ex.printStackTrace();}
          }
        });
        head.file.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	fileManager.setVisible(true);
          }
        });
        head.mouse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				try
				{
    				out.writeInt(Define.NewMouse);
    				out.flush();
    				mouse=new CMouse(ip,panel);
    				head.mouse.setEnabled(false);
				}
				catch(Exception ex){ex.printStackTrace();}
          }
        });
        head.keybord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				try
				{
					if(keybord==null)
					{
						out.writeInt(Define.NewKeybord);
						out.flush();
						keybord=new CKeybord(ip,panel);
					}
    				panel.requestFocus(true);//keyborad focus
				}
				catch(Exception ex){ex.printStackTrace();}
          }
        });
        head.command.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		try{
        			out.writeInt(Define.NewConsole);
        			out.flush();
        			console=new CConsole(ip);
        			head.command.setEnabled(false);
        		}
        		catch(Exception ex){ex.printStackTrace();}
          }
        });
	}
	
	private void init()
	{
		panel=new JPanel();
		panel.setSize(800,600);
		panel.setVisible(true);
		this.getContentPane().add(new JScrollPane(panel),BorderLayout.CENTER);
		
		head=new Head(ip);
		this.getContentPane().add(head, BorderLayout.NORTH);
		
		fileManager=new FileManager(out,ip);
		this.setSize(800,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
