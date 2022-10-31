package main;

/**
* <A Node that holds current node and next and prev>
* Known Bugs: <none>
*
* @author Benjamin Beizer
* <Benjaminbeizer@brandeis.edu>
* <2, 27, 2020>
* COSI 21A PA1
*/
public class Node<T> {
	Node next;
	Node prev;
	T data;
	
	/**
	 * @param element
	 * node constructor creates node
	 * O(1)
	 */
	public Node(T element) {
		data = element;
		next = null;
		prev = null;
	}
	
	/**
	 *sets the element of the node
	 *O(1)
	 */
	public void setElement(T element) {
		data = element;
	}
	
	/** sets the the next node for this current node
	 * O(1)
	 * @param next
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	/**
	 * sets the previous node of the current node
	 * O(1)
	 * @param 
	 */
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}
	
	/**
	 * returns the next node of the current node
	 * O(1)
	 * @param 
	 */
	public Node<T> getNext() {
		return next;
	}
	
	/**
	 *  returns the previous node of the current node
	 * O(1)
	 * @param prev
	 */
	public Node<T> getPrev() {
		return prev;
	}
	
	/**
	 * @returns the data field of the node
	 */
	public T getElement() {
		return data;
	}
	
	@Override
	public String toString() {
		return null;
	}
}
