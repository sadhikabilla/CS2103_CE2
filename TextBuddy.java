package textBuddy;
import java.util.*;


/**
 TextBuddy class
 * This class is used to create and manipulate a file
 * The user only creates and modifies one file at a time
 * The file is readable and writable 
 * The command format is given by the example interaction below
 * @author sadhikabilla
 
 
 mytextfile.txt
 Welcome to TextBuddy 
 command: display
 mytextfile.txt is empty
 command: add hello
 added to mytextfile.txt: hello
 command: display
 1. hey
 command: 1eewgy653
 Invalid command
 command: clear
 all content deleted from mytextfile.txt
 command: display
 mytextfile.txt is empty
 command: exit
 Goodbye
 
 
 */

public class TextBuddy {
	
	private static final String MESSAGE_GOODBYE = "Goodbye";
	private static final String MESSAGE_INVALID = "Invalid command";
	private static final String MESSAGE_WELCOME = "Welcome to TextBuddy %1$s is ready for use";
	private static final String MESSAGE_CLEAR   = "all content deleted from %1$s";
	private static final String MESSAGE_ADD     = "added to %1$s: \"%2$s\"";
	
	
	
	private static Scanner scn = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		String filename = args[0];
		ProcessFile pf = new ProcessFile(filename);
		//showToUser(filename);
        showToUser(String.format(MESSAGE_WELCOME, filename));
		executeMain(filename, pf);
	}

	
	private static void executeMain(String filename, ProcessFile pf) {
		//while true: continue
        while(identifyCommand(filename,pf)){
			//do nothing
			}
	}

	/**
	 * This operation determines which of the supported commands the user
	 * wants to perform
	 * 
	 * @param filename & ProcessFile object
	 *            
	 * @return boolean            
	 */
	private static boolean identifyCommand(String filename, ProcessFile pf) {
		System.out.print("command: ");
		String inputString = scn.nextLine();
		
		
		if(inputString.equals("exit")){
			showToUser(MESSAGE_GOODBYE);
			return false;
			
		}else if(inputString.equals("display")){
			pf.display(filename);
			return true;
		}else if(inputString.equals("clear")){
			pf.clear(filename);
			showToUser(String.format(MESSAGE_CLEAR, filename));
			return true;
		}else if(getFirstWord(inputString).equals("add")){
			pf.insert(filename, getRemainingSentence(inputString));
			showToUser(String.format(MESSAGE_ADD, filename, getRemainingSentence(inputString)));
			return true;
		}else if((getFirstWord(inputString).equals("delete")) && (isInteger(getRemainingSentence(inputString)) == true)){
			pf.delete(filename, Integer.parseInt(getRemainingSentence(inputString)));
			return true;
		}else{
			showToUser(MESSAGE_INVALID);
			return true;
		}
	}
	
	


	/*Displays message to user*/
	private static void showToUser(String text) {
		System.out.println(text);
	}
	
	/**
	 * This operation determines if the String that is entered is an Integer
	 * 
	 * @param String
	 *            
	 * @return boolean            
	 *         true: if it is an integer
	 *         false: if not an integer
	 */
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	private static String getFirstWord(String userCommand) {
		String [] arr = userCommand.split(" ",2);
		String command = arr[0];
		return command;
	}
	
	private static String getRemainingSentence(String userCommand){
		String [] arr = userCommand.split(" ",2);
		String message = arr[1];
		return message;
	}
	
	
}


