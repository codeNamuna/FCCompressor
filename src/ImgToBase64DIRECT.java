import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

class CharacToBase64
{
	String ImageString=new String();
	public char intToBase64(int d)
	{	char c;
		if(d>=0 && d<=25)
		{	d+=65;
			c= (char)d;
		}
		else if(d>=26 && d<=51)
		{
			d+=71;
			c= (char)d;
		}
		else if(d>=52 && d<=61)
		{
			d-=52;
			c= String.valueOf(d).charAt(0);
		}
		else if(d==62)
			c='+';
		else c='/';
		
		return c;
	}
	
	public String toBinaryStringOfLength(int value, int length) {
	    String binaryString = Integer.toBinaryString(value); 
	    StringBuilder leadZeroes = new StringBuilder();
	    for(int index = 0; index < length - binaryString.length(); index++) {
	        leadZeroes = leadZeroes.append("0");
	    }
	    return leadZeroes + binaryString;
	}
	
	public String encodeBytes(byte[] imageBytes) throws UnsupportedEncodingException
	{
		ImageString = new String(imageBytes, "UTF-8");
		String str2=new String();
		String str3=new String();
		String temp=new String();
		int i,d, len, Blen, ctr;
		len=ImageString.length();
		for(i=0; i<len; i++)	//string converted to 7-bit binary
		{
			d=ImageString.charAt(i);
			str2+=toBinaryStringOfLength(d, 8);
		}
		
		Blen=str2.length();
		if((Blen%6)!=0)
		{
			ctr=6-(Blen%6);
			for(i=1; i<=ctr; i++)
				str2=str2+"0";
		}
		Blen=str2.length();
		for(i=0; i<(Blen); i+=6)
		{
			temp=str2.substring(i, (i+6));
			d=Integer.parseInt(temp, 2);
			str3=str3+intToBase64(d);
		}
				
		if(len%3!=0)
		{	
			for(i=1; i<=(3-(len%3)); i++)
				str3+="=";
		}
		return str3;
	}
}

public class ImgToBase64DIRECT{
	
	public static BufferedImage decToImage(String imageString)		//USER DEFINED METHOD
	{
        BufferedImage image = null;
        byte[] imageBytes;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageBytes = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
        	System.out.println("IOException occured!"+e);
        }
        return image;
    }
	
	
	public static String encToString(BufferedImage image, String type)		//USER DEFINED METHOD
	{
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try
        {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();
            bos.flush();
            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);
           /* CharacToBase64 enc=new CharacToBase64();
            imageString = enc.encodeBytes(imageBytes);*/
            bos.close();
            
        }catch (IOException e) {
           System.out.println("IOException occured!"+e);
        }
        return imageString;
    }
	
	
	public static void compress_main(String diri1, String diri2, String diro) throws IOException
	{
		Scanner n=new Scanner(System.in);
		File fileIn=new File(diri1);
		BufferedImage img =ImageIO.read(fileIn);
        String imgstr;
        imgstr = encToString(img, "jpg");
       
        File f=new File(diri2.concat("Yeahh.txt"));
        FileWriter fout=new FileWriter(f);
        fout.write(imgstr);
        fout.flush();
        fout.close();
        BufferedImage newImg = decToImage(imgstr);
        File fileOut=new File(diro);
        ImageIO.write(newImg, "jpg", fileOut);
        f.delete();
        System.out.println("Done!");
	}
}
