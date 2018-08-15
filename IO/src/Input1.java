import java.io.FileReader;
import java.io.IOException;

public class Input1 {
	public static void main(String[] args) {
		char[] a = new char[30];
		int b = 0;
		try(FileReader s = new FileReader("C:\\Users\\lenovo\\Desktop\\data.txt"))
		{
			for(int i=0;i<a.length;i++)
			{
				char c = (char) s.read();
				a[i]=c;
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		for(int j=0;j<a.length;j++)
		{
			System.out.println(a[j]);
			if((byte)a[j]==-1)
			{
				b=j;
				break;
			}
		}
		System.out.println(b);
	}
}
