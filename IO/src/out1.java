import java.io.FileWriter;
import java.io.IOException;

public class out1 {
	public static void main(String[] args) {
		String s = "hello";
		try(FileWriter a = new FileWriter("C:\\Users\\lenovo\\Desktop\\data.txt"))
		{
			for(int i=0;i<s.length();i++)
			{
				char c = s.charAt(i);
				a.write(c);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
