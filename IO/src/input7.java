import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class input7 {
	public static void main(String[] args) {
		Student1[] s = new Student1[3];
		double[] avg = new double[3];
		double sum=0;
		try(ObjectInputStream a= new ObjectInputStream(new BufferedInputStream(new FileInputStream("C:\\Users\\lenovo\\Desktop\\data.txt"))))
		{
			for(int i=0;i<3;i++)
			{
				Object o = a.readObject();
				if(o instanceof Student1)
				{
					s[i] = (Student1) o;
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				sum=sum+s[i].grade[j];
			}
		avg[i] = sum/3;
		sum=0;
		}
		for(int j=0;j<3;j++)
		{
			System.out.println(s[j].name+" "+s[j].studentnumber+" "+avg[j]);
		}
	}
}
