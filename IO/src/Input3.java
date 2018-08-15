
import java.io.BufferedInputStream;


import java.io.DataInputStream;

import java.io.FileInputStream;

import java.io.FileReader;
import java.io.IOException;

public class Input3 {
	public static void main(String[] args) {
	int[] data = new int[10];
	try(DataInputStream s=new DataInputStream(new BufferedInputStream(new FileInputStream("C:\\Users\\lenovo\\Desktop\\data.txt"))))
	{
		for(int i=0;i<3;i++)
		{
			data[i]=s.readInt();
		}	
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
	for(int j=0;j<3;j++)
	{
		System.out.print(data[j]+" ");
	}
	}
}

