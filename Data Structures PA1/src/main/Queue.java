/**
* <A Queue>
* Known Bugs: <none>
*
* @author Benjamin Beizer
* <Benjaminbeizer@brandeis.edu>
* <2, 27, 2020>
* COSI 21A PA1
*/
package main;

import java.util.NoSuchElementException;

public class Queue<T> {

	public T[] q;
	public int head;
	public int tail;
	public int numEntries;

	/** creates the queue initializing tail and head to zero
	 * O(n)
	 * @param capacity 
	 */ 
	@SuppressWarnings("unchecked")
	public Queue(int capacity) {
		this.q = (T[]) new Object[capacity];
		head=0;
		tail=0;
	}

	
	/** adds element to tail of queue, checks if tail is equal to the
	 * last index if so set tail to 0
	 * O(1)
	 * @param element
	 */
	public void enqueue(T element) {
		if (tail == q.length-1) {
			tail = 0;
		}
		q[tail] = element;
		tail++;
		numEntries++;
	}

	/**
	 * checks if head is equal to the last index if so makes head the first index
	 * O(1)
	 */
	public void dequeue() { 
		if (head == q.length-1) {
			head = 0;
		}
		if(numEntries == 0) {
			throw new NoSuchElementException();
		} else {
			q[head] = null;
			head++;
		}
		numEntries--;
	}

	/**
	 * returns head element from queue
	 * O(1)
	 */
	public T front() {
		return q[head];
	}

	
	/**
	 * returns size of the queue
	 * O(1)
	 */
	public int size() {
		return numEntries;
	}

	@Override
	public String toString() {
		return null;
	}
}
