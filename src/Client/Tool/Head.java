package Client.Tool;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JLabel;


public class Head extends JToolBar 
{
	public JButton view=null;
	public JButton file=null;
	public JButton mouse=null;
	public JButton keybord=null;
	public JButton command=null;
	private JLabel label=null;
	public Head(String ip) 
	{
		super();
		view=new JButton(" View ");
		file=new JButton("   File   ");
		mouse=new JButton("Mouse");
		mouse.setEnabled(false);
		keybord=new JButton("Keybord");
		keybord.setEnabled(false);
		command=new JButton("Command");
		label=new JLabel("    IP :  "+ip);

        this.add(view);
        this.add(file);
        this.add(mouse);
        this.add(keybord);
        this.add(command);
        this.add(label);
	}

}
