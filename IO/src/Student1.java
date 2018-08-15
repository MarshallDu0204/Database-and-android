import java.io.Serializable;

public class Student1 implements Serializable{
	String name;
	int studentnumber;
	double[] grade = new double[3];
	public Student1(String n,int sn,double[] g)
	{
		name = n;
		studentnumber = sn;
		for(int i=0;i<3;i++)
		{
			grade[i]=g[i];
		}
	}
}
