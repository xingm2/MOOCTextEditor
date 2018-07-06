package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODOdone: Implement this method
        size = 0;
        head = new LLNode<E> (null);
        tail = new LLNode<E> (null);
	    head.next = tail;
        tail.prev = head;
    }

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) throws NullPointerException 
	{
		// TODOdone: Implement this method

        // Exception
        if (element == null) {
            throw new NullPointerException("MyLinkedList object cannot store null pointers.");
        }

        // Create new node
        LLNode<E> newAdd = new LLNode<E> (element);
        // Link new node in
        newAdd.prev      = tail.prev;
        newAdd.next      = newAdd.prev.next; // or: tail could be used here? 
        tail.prev        = newAdd;
        newAdd.prev.next = newAdd;
        // Update Size
        size++;

		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODOdone: Implement this method.

        if (index < 0 || index > (size - 1) ){ // Throws IOOBE if the index is out of bounds
            throw new IndexOutOfBoundsException("IOOBE get.");
        }

        int cnt = 0;
        LLNode<E> getNode = head;
        while (cnt <= index){ // Note that head is not the element at index 0. head.next is.
            getNode = getNode.next;
            cnt++;
        }

		return getNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODOdone: Implement this method
        if (element == null) {
            throw new NullPointerException("MyLinkedList object cannot store null pointers 2.");
        }
        
        if (index < 0 || index > size  ){
            throw new IndexOutOfBoundsException("IOOBE add.");
        }

        
        if (index == size){  // same to: Appends to the end of the list (Which is add())
            this.add(element);  
        } else {
            int cnt = 0;
            LLNode<E> n = new LLNode<E> (element);
            LLNode<E> mover = head; 
            while (cnt <= index){
                mover = mover.next; 
                cnt++;
            }
            n.prev = mover.prev;
            n.next = mover;
            mover.prev.next = n;
            mover.prev = n;

            size++;
        }


	}


	/** Return the size of the list */
	public int size() 
	{
		// TODOdone: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODOdone: Implement this method
        if (index < 0 || index > (size - 1) ){
            throw new IndexOutOfBoundsException("IOOBE remove.");
        }
        
        int cnt = 0;
        LLNode<E> n = head;
        
        while (cnt <= index){
            n = n.next;
            cnt++;
        }
        n.prev.next = n.next;
        n.next.prev = n.prev;
        n.next = null;
        n.prev = null;

        size--;

		return n.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODOdone: Implement this method
        if (element == null) {
            throw new NullPointerException("MyLinkedList object cannot store null pointers set.");
        }
        
        if (index < 0 || index > size-1  ){
            throw new IndexOutOfBoundsException("IOOBE set.");
        }
        
        int cnt = 0;
        LLNode<E> n = head;
        
        while (cnt <= index){
            n = n.next;
            cnt++;
        }
        
        E copy = n.data; 
        
        n.data = element;

        return copy;
	}  

    //@Override public String toString() {
    //    return String.valueOf(size);
    //}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODOdone: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
    
    // Additional Constructor for LLNode
	public LLNode(E e, LLNode<E> prevNode, LLNode<E> nextNode) 
	{
		this(e);
		this.next = prevNode.next;
		this.prev = nextNode.prev;
        prevNode.next = this;
        nextNode.prev = this;
	}
   
    //@Override public String toString() {
    //    return e.toString();
    //}

}
