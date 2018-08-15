import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class input4 {
	public static void main(String[] args) {
		String name="";
		int studentnumber = -1;
		double[] grades  = new double[3];
		try(DataInputStream a = new DataInputStream(new BufferedInputStream(new FileInputStream("C:\\Users\\lenovo\\Desktop\\data.txt"))))
		{
			name = a.readUTF();
			studentnumber = a.readInt();
			for(int i=0;i<3;i++)
			{
				grades[i]=a.readDouble();
			}	
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("name:"+name+"studentnumber: "+studentnumber+"grades:");
		for(int j=0;j<3;j++)
		{
			System.out.println(grades[j]);
		}
	}
}
