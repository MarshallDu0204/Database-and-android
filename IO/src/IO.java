import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class IO {
	public int countBytes(String s)
	{
		int c=0;
		byte[] data = new byte[1000];
		FileInputStream a = null;
		try
		{
			a = new FileInputStream(s);
			for(int i=0;i<data.length;i++)
			{
				data[i]=(byte) a.read();
				if(data[i]==-1)
				{
					c=i;
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return c;
	}
	public int countChar(String s)
	{
		int c=0;
		char[] data = new char[1000];
		try(FileReader a = new FileReader(s))
		{
			for(int i=0;i<data.length;i++)
			{
				char ch = (char) a.read();
				data[i]=ch;
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		for(int j=0;j<data.length;j++)
		{
			if((byte)data[j]==-1)
			{
				c=j;
				break;
			}
		}
		return c;
	}
	void averageintegers(String s)
	{
		int t = countChar(s)/4+1;
		System.out.println("<"+t+">");
		int[] data = new int[1000];
		int max;
		int min;
		int sum;
		int avg;
		try(DataInputStream a = new DataInputStream(new BufferedInputStream(new FileInputStream(s))))
		{
			for(int i=0;i<t;i++)
			{
				data[i]=a.readInt();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		max=min=sum=data[0];
		for(int j=1;j<t;j++)
		{
			if(max<data[j])
			{
				max=data[j];
			}
			else if(min>data[j])
			{
				min=data[j];
			}
			sum = sum+data[j];
		}
		avg = sum/t;
		System.out.println("The max number is:"+max+"The min number is:"+min+"avg is:"+avg);
	}
	void calculategrade(String s)
	{
		
	}
	public static void main(String[] args) {
		IO a = new IO();
		a.averageintegers("C:\\Users\\lenovo\\Desktop\\data.txt");
		//System.out.println(i);
	}
}
