import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class out3 {
	public static void main(String[] args) {
		String name = "sean";
		int sn = 123456;
		double[] grades = new double[]{39.5,38.8,36.3};
		try(DataOutputStream a = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("C:\\Users\\lenovo\\Desktop\\data.txt"))))
		{
			a.writeUTF(name);
			a.writeInt(sn);
			for(int i=0;i<3;i++)
			{
				a.writeDouble(grades[i]);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
