import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class input5 {
	public static void main(String[] args) {
		String[] s = new String[5];
		try(BufferedReader a = new BufferedReader(new FileReader("C:\\Users\\lenovo\\Desktop\\data.txt")))
		{
			for(int i=0;i<5;i++)
			{
				s[i]=a.readLine();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		for(int j=0;j<5;j++)
		{
			System.out.println(s[j]);
		}
	}
}
