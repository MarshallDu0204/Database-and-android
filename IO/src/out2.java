import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class out2 {
	public static void main(String[] args) {
		int[] data = new int[]{1,2,0,4,5,6,2,4,1,-1,200,-5};
		try(DataOutputStream s=new DataOutputStream(new BufferedOutputStream(new FileOutputStream("C:\\Users\\lenovo\\Desktop\\data.txt"))))
		{
			for(int i=0;i<data.length;i++)
			{
				s.writeInt(data[i]);
			}	
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		}
}
