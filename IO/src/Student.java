import java.io.Serializable;

public class Student implements Serializable{
	String name;
	int age;
	int studentnumber;
	public Student(String n,int a,int s)
	{
		name=n;
		age=a;
		studentnumber =s;
	}
}
