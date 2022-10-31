/**
* <This is basic node class. holds info of the current node and has a reference
* to the previous node and the next node>
* Known Bugs: <Explanation of known bugs or “None”>
*
* @author Benjamin Beizer
* <benjaminbeizer@brandeis.edu>
* no bugs
* <4, 12, 2020>
* COSI 21A PA2
*/
package main;

public class Node<T> {
	T data;
	Node<T> next;
	Node<T> prev;
	
	/**
	 * constructor for a node. sets the next and previous field to null
	 * and sets the datas element
	 * O(1)
	 * @param element
	 */
	public Node(T element) {
		data = element;
		next = null;
		prev = null;
	}
}
