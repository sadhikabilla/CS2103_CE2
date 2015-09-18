package textBuddy;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;



public class TestProcessFile {

	ProcessFile testFile = new ProcessFile("myfile.txt");
	
	@Test
	public void testInsert() throws IOException{
		testFile.insert("myfile.txt", "hello");
		
		//opening the file to read content
		String filePath = new File("").getAbsolutePath();
		File myfile = new File(filePath +"/"+ "myfile.txt");
		FileReader fr = new FileReader(myfile);
		BufferedReader br = new BufferedReader(fr);
		
		String s = br.readLine();
		
		br.close();
		fr.close();
		
		assertTrue(s.equals("hello"));
		
	}

}