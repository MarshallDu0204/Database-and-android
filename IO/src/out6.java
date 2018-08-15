import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class out6 {
	public static void main(String[] args) {
		Student1[] s = new Student1[3];
		double[] g1 = new double[]{1.2,1.5,1.7};
		double[] g2 = new double[]{1.5,1.5,1.9};
		double[] g3 = new double[]{2.0,1.6,1.7};
		s[0] = new Student1("sean",123,g1);
		s[1] = new Student1("dw",124,g2);
		s[2] = new Student1("czy",125,g3);
		try(ObjectOutputStream a = new ObjectOutputStream(new FileOutputStream("C:\\Users\\lenovo\\Desktop\\data.txt")))
		{
			for(int i=0;i<3;i++)
			{
				a.writeObject(s[i]);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}	
	}
}
