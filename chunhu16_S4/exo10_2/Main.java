
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		System.out.println("\n\nThis program will ask you to select an input file");
	      System.out.println("It will read that file and make an alphabetical");
	      System.out.println("list of all the words in the file.  After reading");
	      System.out.println("the file, the program asks you to select an output");
	      System.out.println("file.  If you select a file, the list of words will");
	      System.out.println("be written to that file; if you cancel, the list");
	      System.out.println("be written to standard output.  All words are converted");
	      System.out.println("lower case, and duplicates are eliminated from the list.\n\n");
	      
	      String errorFirstSet = "First set error";
	      String errorSecondSet = "Second set error";
	      String errorSep = "Separator error";
	      char errorSepC = 'Z';
	      
	      try {
	         if (TextIO.readUserSelectedFile() == false) {
	            System.out.println("No input file selected.  Exiting.");
	             System.exit(1);
	         }
	         String line = TextIO.getln();
	         int nbLine = 1;
	         ArrayList<TreeSet<Integer>> results = new ArrayList<TreeSet<Integer>>();
	         while (!line.equals("")){
	        	 
	        	 String trimmedLine = "";
	        	 for(char c : line.toCharArray()){
	        		 if(c != ' '){
	        			 trimmedLine += c;
	        		 }
	        	 }
	        	 line = trimmedLine;
	        	 
        		 TreeSet<Integer> s1 = new TreeSet<Integer>();
        		 TreeSet<Integer> s2 = new TreeSet<Integer>();
        		 
        		 // First set
        		 Object[] fIter = iterate(line, false);
        		 String afterFirstSet = "";
        		 if(fIter == null){
        			 // error in this line
        			 appendError(errorFirstSet, nbLine);
        			 break;
        		 }else{
        			 // good first set
        			 s1 = (TreeSet<Integer>) fIter[0];
        			 afterFirstSet = (String) fIter[1];
        		 }
        		 
        		 // Separator
        		 char sep = errorSepC;
        		 String afterSep = "";
        		 if(afterFirstSet.length() != 0){
        			 // can continue process for sep
            		 sep = getSep(afterFirstSet, errorSepC);
            		 if(afterFirstSet.length() > 1) {
            			 // can continue process after sep
            			 afterSep = afterFirstSet.substring(1);
            		 } else {
            			 // cannot continue process after sep
            			 appendError(errorSep, nbLine);
            			 break;
            		 }
        		 } else {
        			// cannot continue process to sep
        			 appendError(errorSep, nbLine);
        			 break;
        		 }
        		 
        		 // Second set
        		 
        		 if(afterSep.length() == 0){
        			 // error on sep
        			 appendError(errorSep, nbLine);
        			 break;
        		 } else {
        			 // continue to second set
        			 Object[] sIter = iterate(afterSep, true);
        			 String afterSecondSet = "";
        			 if(sIter == null){
        				 // pb on second set
        				 appendError(errorSecondSet, nbLine);
        				 break;
        			 } else {
        				 // good second set
        				 s2 = (TreeSet<Integer>) sIter[0];
            			 afterSecondSet = (String) sIter[1];
            			 if(afterSecondSet.length() != 0){
            				 appendError("Error on end of line",  nbLine);
            				 break;
            			 }
        			 }
        		 }
        		 
        		 // compute on line result
        		 if(sep == '*'){
        			 s1.retainAll(s2);
        			 results.add(s1);
        		 } else if (sep == '+'){
        			 s1.addAll(s2);
        			 results.add(s1);
        		 } else if (sep == '-'){
        			 s1.removeAll(s2);
        			 results.add(s1);
        		 } else {
        			 appendError(errorSep, nbLine);
        			 break;
        		 }
        		 
        		 
        		 
	        	 nbLine++;
	        	 try{
	        		 line = TextIO.getln();
	        	 } catch(IllegalArgumentException e){
	        		 // reached eof
	        		 line = "";
	        	 }
	         }
	         String toWrite = "";
	         BufferedWriter writer = null;
	         try{
	 		     writer = new BufferedWriter( new FileWriter(getOutputPath()));
	         }catch ( Exception e)
	 		 {
	 		 	 System.out.println("error open file");
	 		 }
	 		 
	         for(TreeSet<Integer> oneSet : results){
	        	 toWrite += oneSet.toString() + System.getProperty("line.separator");
	         }
	         appendResult(toWrite, writer);
	         
	      }catch(Exception e){
	    	  System.out.println(e.toString());
	      }
	}
	
	private static char getSep(String line, char errorC){
		char c = line.charAt(0);
		if(c == '*'){
			// intersection
		} else if(c == '-'){
			// difference
			
		} else if(c == '+'){
			// union
		} else {
			// error waiting for above separator
			c = errorC;
		}
		return c;
	}
	
 	private static Object[] iterate(String line, boolean secondSet){
		char begC = '[';
		char endC = ']';
		char sepC = ',';
		
		Object[] res = new Object[2];
		TreeSet<Integer> res0 = new TreeSet<Integer>();
		
		boolean begB = false;
		boolean endB = false;
		int pos = -1;
		
		String number = "";
		
		
		for(char c : line.toCharArray()){
			if(c != ' '){
			pos++;
			if(!begB && !endB){
				// first char for set
				if(c == begC){
					// good first char for set
					begB = true;
				}else{
					// bad first char
					return null;
				}
			}else if(!begB && endB){
				// Unreachable
			}else if(begB && !endB){
				// in set
				if(number.isEmpty()){
					// we are waiting for an int
					if(Character.isDigit(c)){
						// make the number longer
						number = Character.toString(c);
					}else{
						// error
						return null;
					}
				}else{
					// we encountered an int (still can catch an int or a sep or an endSet)
					if(Character.isDigit(c)){
						// make the number longer
						number += Character.toString(c);
					}else if(c == endC){
						// end of set make the return 
						// append number to res0
						Integer num = (Integer) Integer.parseInt(number);
						res0.add(num);
						number = "";
						
						res[0] = res0;
						pos++;
						if(secondSet){
							res[1] = "";
						}else{
							res[1] = line.substring(pos);
						}
						return res;
					} else if(c == sepC){
						// on a sep append number to res0
						Integer num = (Integer) Integer.parseInt(number);
						res0.add(num);
						number = "";
					}
				}
			} else if(begB && endB){
				// end of set char is operator
				// shouldn't be reachable
				res[0] = res0;
				if(secondSet){
					res[1] = "";
				}else{
					res[1] = line.substring(pos);
				}
				return res;
			}
			}
		}
		return null;
	}

	private static void appendError(String s, int line){
		System.out.println("Error line " + line + " : " + s);
	}
	
	private static void appendResult(String toWrite, BufferedWriter writer){
		try
		 {
		     writer.write(toWrite);

		 }
		 catch ( Exception e)
		 {
		 	 System.out.println("Error on writing file.");
		 }
		 finally
		 {
		     try
		     {
		         if ( writer != null)
		         writer.close( );
		     }
		     catch ( Exception e)
		     {
		     	 System.out.println("Error on closing writer.");
		     }
		 }
	}
public static String getOutputPath() {
		
		JFileChooser fileDialog = new JFileChooser();

        fileDialog.setDialogTitle("Select File for Output");
        File selectedFile;
        while (true) {
            int option = fileDialog.showSaveDialog(null);
            if (option != JFileChooser.APPROVE_OPTION)
                return "";  // user canceled
            selectedFile = fileDialog.getSelectedFile();
            if (selectedFile.exists()) {
                int response = JOptionPane.showConfirmDialog(null,
                        "The file \"" + selectedFile.getName() + "\" already exists.  Do you want to replace it?",
                        "Replace existing file?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (response == JOptionPane.YES_OPTION)
                    break;
            }
            else {
                break;
            }
        }
        
       
        return selectedFile.getPath();
    }
	
}
