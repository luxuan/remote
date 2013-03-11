package Client.Tool;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;

import javax.swing.*;

import Client.CFile;
import Client.Define;

import Client.Frame;
public class FileManager extends JFrame
{
	private FileManager manager=this;
	private String clientFile=null;
	private String serverFile=null;
	private JLabel lblClient=null;
	private JLabel lblServer=null;
	private JTextField txtClient=null;
	private JTextField txtServer=null;
	private JButton download=null;
	private JButton upload=null;
	
	public FileManager(DataOutputStream out,String ip) 
	{
		super("File Manager");
		init(out,ip);
	}
	private void init(final DataOutputStream out,final String ip)
	{
		lblClient=new JLabel("client file:");
		lblServer=new JLabel("server file:");
		txtClient=new JTextField();
		txtServer=new JTextField();
		download=new JButton("Downlad");
		upload=new JButton("Upload");
		download.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
            {
            	if(!(txtClient.getText().equals("")||txtServer.getText().equals("")))
            	{
	            	try{
	            		out.writeInt(Define.NewFile);
	            		out.flush();
	            		new CFile(ip,Define.Download,txtClient.getText(),txtServer.getText());
	            	}
	            	catch(Exception ex){ex.printStackTrace();}
            	}
            }
        });
		upload.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
            {
            	if(!(txtClient.getText().equals("")||txtServer.getText().equals("")))
            	{
	            	try{
	            		out.writeInt(Define.NewFile);
	            		out.flush();
	            		new CFile(ip,Define.Upload,txtClient.getText(),txtServer.getText());
	             	}
	            	catch(Exception ex){ex.printStackTrace();}
            	}
            }
        });
        this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				manager.setVisible(false);
			}
		});
		
		
		
		this.setLayout(null);
		lblClient.setBounds(20, 30, 75, 30);
		lblServer.setBounds(20,70,75,30);
		txtClient.setBounds(90,30,160,30);
		txtServer.setBounds(90, 70, 160, 30);
		download.setBounds(40, 120, 90, 25);
		upload.setBounds(150, 120, 90, 25);

		this.add(lblClient);
		this.add(lblServer);
		this.add(txtClient);
		this.add(txtServer);
		this.add(download);
		this.add(upload);
		this.setSize(300,200);
		this.setVisible(false);
	}


}
