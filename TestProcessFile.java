package textBuddy;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;



public class TestProcessFile {

	ProcessFile testFile = new ProcessFile("file.txt");
	
	@Test
	public void testInsert() throws IOException{
		testFile.insert("file.txt", "hello");
		
		//opening the file to read content
		String filePath = new File("").getAbsolutePath();
		File myfile = new File(filePath +"/"+ "file.txt");
		FileReader fr = new FileReader(myfile);
		BufferedReader br = new BufferedReader(fr);
		
		String s = br.readLine();
		
		br.close();
		fr.close();
		
		assertTrue(s.equals("hello"));
		
	}
	
	@Test
	public void testSearch(){
		testFile.insert("file.txt", "hello how are you");
		testFile.insert("file.txt", "how hello are you");
		//testing for case "null" 
		ArrayList<String> listOne = testFile.search("file.txt", "null");
		
		assertTrue(listOne.get(0).equals("The search word does not match anything in your file"));
		
		//testing for a case when a specific word "bye" is not present in the file
		ArrayList<String> listTwo = testFile.search("file.txt", "bye");
		
		assertTrue(listTwo.get(0).equals("The search word does not match anything in your file"));
		
		//testing for a case when any given word not present in the file
		ArrayList<String> listThree = testFile.search("file.txt", "anything");
		
		assertTrue(listThree.get(0).equals("The search word does not match anything in your file"));
		
		//testing for a case when the word present in the file
		ArrayList<String> listFour = testFile.search("file.txt", "hello");
		
		assertTrue(listFour.get(0).equals("hello"));
		
		//testing for case when there is more than 1 word in the file and both are equal to the given word
		ArrayList<String> listFive = testFile.search("file.txt", "hello");
		
		assertTrue(listFive.get(0).equals("hello"));
		//assertTrue(listFive.get(1).equals("hello"));
		//assertTrue(listFive.size() == 2);
		
		//testing for case where is more than 1 word in the file and both are not equal to the given word
		ArrayList<String> listSix = testFile.search("file.txt", "hello");
		assertTrue(listSix.get(0).equals("hello"));
		//assertFalse(listSix.size() == 2);
		
		//testing for case where the search word is the first word of a sentence 
		ArrayList<String> listSeven = testFile.search("file.txt", "hello");
		assertTrue(listSeven.get(0).equals("hello"));
		assertTrue(listSeven.get(1).equals("hello how are you"));
		//assertTrue(listSeven.size() == 2);
		
		//testing for case when the search word is anywhere in the sentence
		ArrayList<String> listEight = testFile.search("file.txt", "hello");
		assertTrue(listEight.get(0).equals("hello"));
		assertTrue(listEight.get(1).equals("hello how are you"));
		assertTrue(listEight.get(2).equals("how hello are you"));
		assertTrue(listSeven.size() == 3);
		
		
		
		}

}
