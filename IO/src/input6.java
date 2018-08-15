import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class input6 {
	public static void main(String[] args) {
		Student[] students = new Student[3];
		try(ObjectInputStream a = new ObjectInputStream(new FileInputStream("C:\\Users\\lenovo\\Desktop\\data.txt")))
		{
			for(int i=0;i<3;i++)
			{
				Object o = a.readObject();
				if(o instanceof Student)
				{
					students[i] = (Student) o;
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
		for(int j=0;j<3;j++)
		{
			System.out.println(students[j].name+students[j].age+students[j].studentnumber);
		}
	}
}
