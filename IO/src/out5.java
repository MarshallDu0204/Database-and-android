import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class out5 {
	public static void main(String[] args) {
		Student[] students = new Student[]{new Student("sean",30,1231),new Student("wer",32,12341),new Student("dert",45,1234)};
		try(ObjectOutputStream a= new ObjectOutputStream(new FileOutputStream("C:\\Users\\lenovo\\Desktop\\data.txt")))
		{
			for(int i=0;i<3;i++)
			{
				a.writeObject(students[i]);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
