package Client;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Login extends JFrame
{
	private Login login=this;
	
	private JLabel info=null;
	private JLabel lblIP=null;
	private JTextField txtIP=null;
	private JLabel lblName=null;
	private JTextField txtName=null;
	private JLabel lblPassword=null;
	private JTextField txtPassword=null;
	private JButton submit=null;
	private JButton reset=null;

	public Login(Frame frame) 
	{
		super("Welcome to Login");
		init(frame);
		test();
	}
	private void test()
	{
		txtIP.setText("192.168.149.24");
		txtName.setText("lius");
		txtPassword.setText("hello");
	}
	private void init(final Frame frame)
	{
		info=new JLabel();
		lblIP=new JLabel("IP:");
		txtIP=new JTextField();
		lblName=new JLabel("Name:");
		txtName=new JTextField();
		lblPassword=new JLabel("Password:");
		txtPassword=new JTextField();
		submit=new JButton("Login");
		reset=new JButton("Reset");
		
		this.setLayout(null);
		lblIP.setBounds(60, 50, 75, 30);
		lblName.setBounds(60,90,75,30);
		lblPassword.setBounds(60, 130, 75, 30);
		info.setBounds(60,170,300,30);
		txtIP.setBounds(160,50,160,30);
		txtName.setBounds(160, 90, 160, 30);
		txtPassword.setBounds(160, 130, 160, 30);
		submit.setBounds(100, 210, 70, 25);
		reset.setBounds(190, 210, 70, 25);
		
		this.add(lblIP);
		this.add(lblName);
		this.add(lblPassword);
		this.add(info);
		this.add(txtIP);
		this.add(txtName);
		this.add(txtPassword);
		this.add(submit);
		this.add(reset);
		this.setSize(400,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				try
				{
					if(frame.connect(txtIP.getText(),txtName.getText(),txtPassword.getText()))
					{
						
						frame.start();
						login.setVisible(false);
						frame.setVisible(true);
					}
					else
					{
						txtPassword.setText("");
						info.setBackground(Color.RED);
						info.setText("Failed!Please check the ip,name and passsword.");
					}
				}
				catch(Exception ex){ex.printStackTrace();}
          }
        });
		reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	txtIP.setText("");
            	txtName.setText("");
            	txtPassword.setText("");
          }
        });
	}
	public static void main(String[] args) 
	{
		try
		{
			Frame frame=new Frame();
			Login login=new Login(frame);
		}
		catch(Exception ex){ex.printStackTrace();}
	}
}
