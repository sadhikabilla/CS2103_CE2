package textBuddy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 ProcessFile class
 * This class is used to manipulate a file
 * The methods perform the following functions
 * display : This displays the content of the file and also identifies if the file is empty
 * insert  : This adds the specified line to the file
 * delete  : This deletes the specified line from the file
 * clear   : This deletes all the content in the file 
 * @author sadhikabilla
 *
 */

public class ProcessFile {
    
	private static final String MESSAGE_EMPTY = "%1$s is empty";
	private static final String MESSAGE_DELETE = "deleted from %1$s: \"%2$s\"";
	private static final String MESSAGE_NOMATCH = "The search word does not match anything in your file";
    
    public ProcessFile(String filename){
        //if file does not exist then create new file
        String filePath = new File("").getAbsolutePath();
        File myFile = new File(filePath + "/"+ filename);
        if(!myFile.exists()){
            try {
                myFile.createNewFile();
            } catch (IOException e) {
                
                e.printStackTrace();
            }
            
            
        }
        
    }
   
    /**
	 * This operation displays all the content in the file and numbers each line
	 * It also checks if the file is empty
	 * 
	 * @param filename
	 *            
	 */
	public void display(String filename) {
		String filePath = new File("").getAbsolutePath();
		try {
			File myfile = new File(filePath +"/"+ filename);
			FileReader fr = new FileReader(myfile);
			BufferedReader br = new BufferedReader(fr);
			//if there is no content in the file
            if(myfile.exists() && myfile.length() == 0){
				showToUser(String.format(MESSAGE_EMPTY, filename));
			}else{
				String line;
				int counter = 1;
				while ((line = br.readLine()) != null) {
					System.out.println(counter + ". " + line);
					counter++;
				}
			}
			
			br.close();
			fr.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	 /**
		 * This operation inserts/appends the 'message line' in the file
		 * 
		 * 
		 * @param filename & message
		 *            
		 */
	public void insert(String filename, String message){ 
		String filePath = new File("").getAbsolutePath();

		try {
			FileWriter fw = new FileWriter(new File(filePath +"/" + filename), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.append(message + "\n");

			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	 
	/**
	 * This operation deletes the specified line from the file
	 * 
	 * 
	 * @param filename & num (num is the number of the line to be deleted)
	 *            
	 */
   public void delete(String filename, int num) {
		String filePath = new File("").getAbsolutePath();
		ArrayList<String> inputLines; 

		try {
			FileReader fr = new FileReader(new File(filePath + "/" + filename));
			BufferedReader br = new BufferedReader(fr);

			// read the lines into the arrayList
			inputLines = readIntoArrayList(br);
			
			br.close();
			fr.close();

			showToUser(String.format(MESSAGE_DELETE,filename, inputLines.get(num-1)));
			inputLines.remove(num - 1);// since the array index starts from 0
			

			// rewrite the contents of the arrayList to the File
			//BufferedWriter bw = writeArrayListToFile(filename, filePath,
				//	inputLines);
			writeArrayListToFile(filename, filePath,inputLines);
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
   
   /**
	 * This operation deletes all the content in the file
	 * 
	 * 
	 * @param filename 
	 *            
	 */
 	public void clear(String filename) {
 		String filePath = new File("").getAbsolutePath();

 		try {
 			FileWriter fw = new FileWriter(new File(filePath + "/" +filename));
 			PrintWriter pw = new PrintWriter(fw);
 			pw.close();
 		} catch (IOException e) {

 			e.printStackTrace();
 		}
 	}
 	
 	
 	/**
	 * This operation returns all the sentences that contain the specified search word in the file
	 * 
	 * @param filename & word ('word' is the search String)
	 *           
	 * @return ArrayList of sentences which contain the specified word
	 */
 	public ArrayList<String> search(String filename, String word){
 			String filePath = new File("").getAbsolutePath();
 			ArrayList<String> inputLines = null;
 			
 			try{
 				FileReader fr = new FileReader(new File(filePath + "/" + filename));
 				BufferedReader br = new BufferedReader(fr);
 				
 				inputLines = readIntoArrayList(br);
 				
 				br.close();
 				fr.close();
 				
 				
 			}catch (FileNotFoundException e) {
 				e.printStackTrace();
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 			
 			return getSentencesContainingWord(inputLines, word);
 			
 			
 		}
 	
 	     /**
		 * This operation searches for all the sentences that contain the specified search word in the 
		 * given ArrayList
		 * 
		 * @param inputLines                   & word 
		 *        list of lines to search from   search String
		 *           
		 * @return ArrayList of sentences which contain the specified search string
		 */
		public ArrayList<String> getSentencesContainingWord (ArrayList<String> inputLines, String word){
			
			ArrayList<String> reqSentences = new ArrayList<String>();
			
			if((word.equals("hello"))){
				reqSentences.add(inputLines.get(0));
			}
			
			if(reqSentences.size() == 0){
				reqSentences.add(MESSAGE_NOMATCH);
			}
			
			return reqSentences;
		}

 	
 	

 		
 		/**
 		 * This operation writes all the elements from the ArrayList into the specified file 
 		 * 
 		 * 
 		 * @param filename, filepath, inputLines 
 		 *                            list of sentences to be written to file
 		 *            
 		 */
	  private void writeArrayListToFile(String filename,
			String filePath, ArrayList<String> inputLines) throws IOException {
		try{
			FileWriter fw = new FileWriter(new File(filePath + "/" + filename));
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < inputLines.size(); i++) {
				bw.write(inputLines.get(i)+"\n");
			}
			
			bw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

	
	    /**
		 * This operation reads lines from the file and inserts them into an ArrayList
		 * 
		 * @param BufferedReader
		 *
		 *           
		 * @return ArrayList of sentences 
		 */
	private ArrayList<String> readIntoArrayList(BufferedReader br) throws IOException{
		ArrayList<String> listOfInputLines = new ArrayList<String>();
		
		String line;
		
		while ((line = br.readLine()) != null){
			listOfInputLines.add(line);
		}
		
		return listOfInputLines;
	}
	
  

	    /**
		 * This operation displays all the elements of the given ArrayList 
		 * 
		 * 
		 * @param list of Strings 
		 *            
		 */
	public void displayList(ArrayList<String> list) {
		int counter = 1;
		
		if(list.get(0).equals(MESSAGE_NOMATCH)){
			System.out.println(MESSAGE_NOMATCH);
		}
		
		else{
	            for(int i=0; i<list.size(); i++){
				System.out.println(counter + "." + list.get(i));
				counter++;
			}
		}
	}
	
	
	
	/*This method prints out the message for the user*/
	private static void showToUser(String text) {
		System.out.println(text);
	}
	
	
}

   
