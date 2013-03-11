package Server;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;



public class SCamera extends Thread {
	public SCamera(){}
	public void run()
	{
		try
		{
			ServerSocket server=new ServerSocket(Define.ImagePort);
			while(true)
			{
				Socket client=server.accept();
				try 
				{
					BufferedImage screenshot = (new Robot()).createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
					
			        int w=screenshot.getWidth();
			        int h=screenshot.getHeight();
					int currentPixArray[] =getPixArray(screenshot,w,h);
					RGBtoGray(currentPixArray);
					
			        System.out.println("end caculate");
					DataOutputStream out=new DataOutputStream(client.getOutputStream());
					out.writeInt(w);
					out.writeInt(h);
					int value,lastValue,counter;
					int buffc[]=new int[Define.BufferSize];
					int buffv[]=new int[Define.BufferSize];
					lastValue=currentPixArray[0];
					counter=1;
			        System.out.println("before transfer");
					for(int i=1,j=0;i<currentPixArray.length;i++)
					{
						value=currentPixArray[i];
						if(value==lastValue)
						{
							counter++;
						}
						else
						{

							buffc[j]=counter;
							buffv[j]=lastValue;
							j++;
							if(j==Define.BufferSize)
							{
//								out.write
//								out.write(arg0, arg1, arg2)
//								j=0;	
							}


							out.writeInt(counter);
							out.writeInt(lastValue);
							lastValue=value;
							counter=1;
						}
					}
					out.writeInt(counter);
					out.writeInt(lastValue);
			        System.out.println("end transfer");
			        out.close();
					
					ImageIO.write(screenshot, Define.ImageFormat, client.getOutputStream());
					
					client.close();
				}
				catch (Exception ex) {
					//ex.printStackTrace();
				}
			}
		}
		catch(Exception ex){
			//ex.printStackTrace();
		}
	}
///////////////////////////////////////////////////////////////////////////////////	

//////////////////»ñÈ¡Í¼ÏñÏñËØ¾ØÕó\\\\\\\\\
	private int[]getPixArray(Image im,int w,int h){
	       int[] pix=new int[w*h];
	       PixelGrabber pg=null;
	       try{
	         pg = new PixelGrabber(im, 0, 0, w, h, pix, 0, w);
	         if(pg.grabPixels()!=true)
	           try{
	             throw new java.awt.AWTException("pg error"+pg.status());
	           }catch(Exception eq){
	                   eq.printStackTrace();
	           }
	       } catch(Exception ex){
	               ex.printStackTrace();

	       }
	      return pix;
	    }
    //////////////////»Ò¶È×ª»»\\\\\\\\\\\
	private void RGBtoGray(int[] ImageSource){
		int length=ImageSource.length;
	    ColorModel colorModel=ColorModel.getRGBdefault();
	    int k,r,g,b;
	    for(k=0;k<length;k++)
	    {
	         r = colorModel.getRed(ImageSource[k]);
	         g = colorModel.getGreen(ImageSource[k]);
	         b = colorModel.getBlue(ImageSource[k]);
	         int gray=(int)(r*0.3+g*0.59+b*0.11);
	         r=g=b=gray;
	         ImageSource[k]=(255 << 24) | (r << 16) | (g << 8 )| b;
	    }
	}

}
