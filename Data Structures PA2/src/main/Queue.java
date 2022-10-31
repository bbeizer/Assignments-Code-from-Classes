/**
* <This is a Queue that acts as a linked list. keeps track of the ends of the queue
* to have easy insertions and deletions. also keeps track of size>
* Known Bugs: <Explanation of known bugs or “None”>
*
* @author Benjamin Beizer
* <benjaminbeizer@brandeis.edu>
* <4, 12, 2020>
* COSI 21A PA2
*/
package main;

public class Queue {
	private Node<WorkEntrySearchNode> head;
	private Node<WorkEntrySearchNode> tail;
	int size;
	
	/**
	 * creates a queue with a head a tail and a size variable to keep track of the front 
	 * end and size of the queue
	 */
	public Queue() {
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * adds a node to the end of 
	 * the linked list, increasing the size and shifting the tail 
	 * to the newly added node 
	 * O(1)
	 * @param node
	 */
	public void enqueue(Node<WorkEntrySearchNode> node) {
		if (head == null) {
			head = node;
			tail = node;
			size++;
		} else {
			tail.next = node;
			node = tail;
			size++;
		}
	}

	/**
	 * dequeues the first element of the queue and returns it while setting the head of the
	 * queue list to the next node and and adjusting the size
	 * O(1)
	 * @return
	 */
	public Node<WorkEntrySearchNode> dequeue() {
		if (head == null) {
			return null;
		}
		Node<WorkEntrySearchNode> front = head;
		head = head.next;
		size--;
		return front;
	}
	
	/**
	 * returns the data of the front node of the queue
	 * O(1)
	 * @return
	 */
	public WorkEntrySearchNode front() {
		return head.data;
	}
	
	/**
	 * returns the size of the queue
	 * O(1)
	 * @return
	 */
	public int size() {
		return size;
	}
}
