package textBuddy;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
	
	@Test
	public void testSearch(){
		ArrayList<String> listOne = testFile.search("myfile.txt", null);
		
		assertTrue(listOne.get(0).equals("The search word does not match anything in your file"));
		
		ArrayList<String> listTwo = testFile.search("myfile.txt", "bye");
		
		assertTrue(listTwo.get(0).equals("The search word does not match anything in your file"));
		
		ArrayList<String> listThree = testFile.search("myfile.txt", "anything");
		
		assertTrue(listThree.get(0).equals("The search word does not match anything in your file"));
		
		}

}
