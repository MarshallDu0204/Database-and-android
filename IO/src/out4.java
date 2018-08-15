import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class out4 {
	public static void main(String[] args) {
		String[] s = new String[]{"hi","my","name"};
		try(BufferedWriter a= new BufferedWriter(new FileWriter("C:\\Users\\lenovo\\Desktop\\data.txt")))
		{
			for(int i=0;i<s.length;i++)
			{
				a.write(s[i]+"\n");
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
