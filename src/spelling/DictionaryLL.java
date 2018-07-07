package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    // TODOdone: Add a constructor
    public DictionaryLL(){
        dict = new LinkedList();
    }

    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// TODOdone: Implement this method
         
        String myWord = word.toLowerCase(); // Convert
        
        if (!dict.contains(myWord)){ // wasn't there
            dict.add(myWord);
            return true;  // true
        } 
        
        return false;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        // TODOdone: Implement this method
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        //TODOdone: Implement this method
        
        String myWord = s.toLowerCase();

        if (dict.contains(myWord)){
            return true;
        }

        return false;
    }

    
}
