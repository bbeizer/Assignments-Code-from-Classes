/**
* <This is DLL for the work entries for a given WorkEntrySearchNodes>
*no bugs
* @author Benjamin Beizer
* <benjaminbeizer@brandeis.edu>
* <4, 12, 2020>
* COSI 21A PA2
*/
package main;

public class DLL<T> {
	Node<T> head;
	Node<T> tail;
	WorkEntry data;

	/**
	 * constructs the DLL and sets the head variable to null
	 * O(1)
	 */
	public DLL() {
		head = null;
	}

	/**
	 * returns head node
	 * O(1)
	 * @return
	 */
	public Node<T> getFirst() {
		return head;
	}

	/**
	 * inserts node at the end of the DLL
	 * O(1)
	 * @param element
	 */
	public void insert(T element) {
		Node<T> x = new Node<T>(element);
		if(head == null) {
			head = x;
			tail = x;
		} else {
			Node<T> temp = x;
			tail.next = temp;
			tail = tail.next;
		}
	}
	/**
	 * given the specified integer this method deletes the ith
	 * work entry in the DLL 
	 * returns to or false if the head node exists which is needed for the
	 * del method for the WorkEntrySearchNode
	 * O(n)
	 * @param j
	 * @return
	 */
	public boolean delete(int j) {
		if (j == 1) {
			head = head.next;
		} else {
			Node<T> pointer = head;
			for (int i = 1; i < j; i++) {
				pointer = pointer.next;
				if(pointer == null) {
					throw new IndexOutOfBoundsException();
				}
				if (pointer.prev != null) {
					(pointer.prev).next = pointer.next;
				}
				if (pointer.next != null) {
					(pointer.next).prev = pointer.prev;
				}
			}
		}
		if (head == null) {
			return false;
		} else {
			return true;
		}
	}
}
