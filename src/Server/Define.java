package Server;

public class Define {
	public static final char FileDevide='/';
	
	public static final String ImageFormat="png";
	
	public static final int OK=100;
	public static final int BAD=101;	
	public static final int BufferSize=8192;
	public static final boolean Upload=false;
	public static final boolean Download=true;
	
	public static final int MPort=8911;
	public static final int FilePort=8912;
	public static final int ConsolePort=8913;
	public static final int ImagePort=8914;
	public static final int VoicePort=8915;
	public static final int MousePort=8916;
	public static final int KeybordPort=8917;
	
	public static final int _MPort=8918;
	public static final int _FilePort=8919;
	public static final int _ConsolePort=8920;
	public static final int _ImagePort=8921;
	public static final int _VoicePort=8922;
	public static final int _MousePort=8923;
	public static final int _KeybordPort=8924;

	
	
	public static final byte NewFile=1;
	public static final byte DestroyFile=-1;
	public static final byte ContinueFile=2;
	public static final byte StopFile=-2;
	
	public static final byte NewConsole=3;
	public static final byte DestroyConsole=-3;
	public static final byte ContinueConsole=4;
	public static final byte StopConsole=-4;
	
	public static final byte NewImage=5;
	public static final byte DestroyImage=-5;
	public static final byte ContinueImage=6;
	public static final byte StopImage=-6;
	
	public static final byte NewVoice=7;
	public static final byte DestroyVoice=-7;
	public static final byte ContinueVoice=8;
	public static final byte StopVoice=-8;
	
	public static final byte NewMouse=9;
	public static final byte DestroyMouse=-9;
	public static final byte ContinueMouse=10;
	public static final byte StopMouse=-10;
	
	public static final byte NewKeybord=11;
	public static final byte DestroyKeybord=-11;
	public static final byte ContinueKeybord=12;
	public static final byte StopKeybord=-12;
	
}
