import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class Input {
	public static void main(String[] args) {
		int c=0;
		byte[] data = new byte[20];
		FileInputStream a = null;
		try
		{
			a = new FileInputStream("C:\\Users\\lenovo\\Desktop\\data.txt");
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
		catch(IOException e)
		{
			e.printStackTrace();
		}
		for(int j=0;j<data.length;j++)
		{
			System.out.println(data[j]);
		}
		System.out.println("<"+c+">");
	}
}
