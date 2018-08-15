import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class out {

	public static void main(String[] args) {
		byte[] data = new byte[]{12,35,46,57,89};
		FileOutputStream a= null;
		try
		{
			a = new FileOutputStream("C:\\Users\\lenovo\\Desktop\\data.txt");
			for(int i=0;i<data.length;i++)
			{
				a.write(data[i]);
			}
			a.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
