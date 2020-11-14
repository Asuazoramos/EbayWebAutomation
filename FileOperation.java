package FileReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadWrite {


	private static final BufferedWriter Bufwriter = null;
	private static final char[] strData = null;


	public static void main(String[] args) {
	ReadWrite readwrite = new ReadWrite();
	 String angelTextFile = "./record.txt";
	 
	 readwrite.ReadFile(angelTextFile);
	String angelTextData = "Hello may name is angel";
     readwrite.WriteFile("./record.txt", angelTextData);
	readwrite.ReadFile("./record.txt");
     
     
     
     
	}

	public void WriteFile(String angelFile, String angelData)
	{
		
		try ( BufferedWriter bufwritter = new BufferedWriter(new FileWriter(angelFile, true)))
		{
			
			
			bufwritter.write(angelData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

public void ReadFile(String angelFile)
{
	String angelBuffer;
	try(BufferedReader buffRead = new BufferedReader(new FileReader(angelFile)))
	{
		
		while( (angelBuffer = buffRead.readLine()) != null)
		{
	
	System.out.println(angelBuffer);
	
		}
	}
catch(IOException e)
	{
	
	e.printStackTrace();
	}

}
}


















