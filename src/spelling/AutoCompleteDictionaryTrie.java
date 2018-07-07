package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author Menglong Xing
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODOdone: Implement this method.
	    TrieNode node = root;

	    for (Character c : word.toLowerCase().toCharArray()){
            TrieNode child = node.getChild(c);

            if (child != null){   // Still have child to go through
                node = child;
            } else {  // End of a branch
            	node = node.insert(c);
            }

	    }

        // Now the node is pointing to a endsWord (May already exist, maybe was added)
        if (node.endsWord()){  // already
            return false;
        } else {  // newly added. Because isWord inialized to be false
            node.setEndsWord(true);
            size++;
            return true;
        }

	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODOdone: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODOdone: Implement this method
	    TrieNode node = root;

	    for (Character c : s.toLowerCase().toCharArray()){
            TrieNode child = node.getChild(c);

            if (child != null){   // Still have child to go through
                node = child;
            } else {  // End of a branch
            	return false;
            }

	    }

        // Now the node is pointing to a endsWord (May already exist, maybe was added)
        if (node.endsWord()){  // already
            return true;
        } else {  // newly added. Because isWord inialized to be false
            return false;
        }
	    
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODOdone: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    2.1 Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    2.2 Create a list of completions to return (initially empty)
    	 //    2.3 While the queue is not empty and you don't have enough completions:
    	 //       2.3.1 remove the first Node from the queue
    	 //       2.3.2 If it is a word, add it to the completions list
    	 //       2.3.3 Add all of its child nodes to the back of the queue
    	 // Return the list of completions
         TrieNode node = root;
         
         //1
	     for (Character c : prefix.toLowerCase().toCharArray()){
            TrieNode child = node.getChild(c);

            if (child != null){   // Still have child to go through
                node = child;
            } else {
            	return Collections.emptyList(); // return empty list if the stem does not appear
            	//return new ArrayList<String>(); // return empty list if the stem does not appear

            }
	     }

         //2
         Queue<TrieNode> queue = new LinkedList<TrieNode>(); //2.1
         List<String> completions = new LinkedList<String>(); //2.2
         queue.offer(node);

         while(!queue.isEmpty() && numCompletions > 0){ //2.3
             
             TrieNode t = queue.poll(); //2.3.1

             if (t.endsWord()){  // 2.3.2
             	completions.add(t.getText());
             	--numCompletions;
             }

             for (Character c : t.getValidNextCharacters()){  // 2.3.3
             	queue.offer(t.getChild(c));
             }

         }
    	 
    	 return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);  //recursive
 		}
 	}
 	

	
}