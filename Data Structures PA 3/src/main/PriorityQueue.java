
/**
 * <This is priority queue that holds emails. Its oganized as a max
 * priority queue and it maintains the heap proptery. when its about to run out of space
 * the PQ has a method that increases the space it has
 * it also has a hash table of email keys where the value is
 * it position in the heap
 * in O(1) time>
 * Known Bugs: <If spent hours on this one bug. Its the rank emails
 * or essentially "heapsort“ it only does one iteration of heap sort which makes me
 * a sad boy>
 *
 * @author Benajmin Beizer
 * <benjaminbeizer@brandeis.edu>
 * <May, 9, 2020>
 * COSI 21A PA3
 */
package main;
import java.util.NoSuchElementException;

public class PriorityQueue {

	public Email[] heap; // KEEP THIS PUBLIC

	private int heapSize;

	public HashTable table;

	/**
	 * creates the priority queue by giving it an array a hash table and a
	 * heap size
	 */
	public PriorityQueue() {
		heap = new Email[11];
		table = new HashTable();
		heapSize = -1;
	}

	// RECOMMENDED method: you can delete it
	/**
	 * swaps i and j in an array
	 * O(1)
	 * @param i
	 * @param j
	 * @param e
	 */
	private void swap(int i, int j, Email[] e) {
		Email temp = null;
		temp = e[i];
		e[i] = e[j];
		e[j] = temp;
	}

	// RECOMMENDED method: you can delete it
	/**
	 * helps maintain the heap property by sinking down
	 * an email based on its spam score
	 * O(logn)
	 * @param pointer
	 * @param heap
	 */
	private void heapifyDown(int pointer, Email[] heap) {
			int left = pointer*2+1;
			int right = pointer*2+2;
			int largest;
			if (left<heapSize+1 && heap[left].getSpamScore() > heap[pointer].getSpamScore()) {
				largest = left;
			} else {
				largest = pointer;
			}
			if(right <heapSize+1 && heap[right].getSpamScore() > heap[largest].getSpamScore()) {
				largest = right;
			}
			if(largest != pointer) {
				swap(pointer, largest, heap);
				heapifyDown(largest,heap);
		} 
		if(heap.equals(this.heap)) {
			table.put(this.heap[pointer].id, pointer);
		}

	}

	// RECOMMENDED method: you can delete it
	/**
	 * does essentially the same thing as heapify down but floats
	 * and email up
	 * O(logn)
	 * @param index
	 */
	private void heapifyUp(int index) {
		while (index > 0 && heap[index].getSpamScore() > heap[index/2].getSpamScore()) {
			swap(index,index/2,heap);
			index= index/2;
		}
		table.put(heap[index].id, index);
	}

	/**
	 * puts at the last empty spot of the heap and heapifies the email up
	 * if the heap is about to run out of spots it icreases the slots
	 * in method(update heap size)
	 * O(logn) 
	 * @param e
	 */
	public void insert(Email e) {
		if (heapSize == heap.length-1) {
			updateHeapSize();
		}
		heapSize = heapSize + 1;
		heap[heapSize] = e;
		heapifyUp(heapSize);
	}

	/**
	 * increases the amount of slots in the heap
	 * O(n)
	 */
	public void updateHeapSize() {
		Email[] newHeap = new Email[heap.length*2];
		for(int i = 0; i <heap.length; i++) {
			newHeap[i] = heap[i];
		}
		heap = newHeap; 
	}
	/**
	 * modifies the spam score of a given email
	 * will float the email u or down depnding on if it maintains
	 * the heap property
	 * @param eID
	 * @param score
	 */
	public void updateSpamScore(String eID, int score) {
		if (table.get(eID)==null) {
			throw new NoSuchElementException();
		} else {
			heap[(int)table.get(eID)].spamScore = score;
			if(heap[(int)table.get(eID)].spamScore > heap[(int)table.get(eID)/2].spamScore) {
				heapifyUp((int)table.get(eID));
			} else {
				heapifyDown((int)table.get(eID), this.heap);
			}
		}

	}

	/**
	 * returns the max spam score
	 * @return
	 */
	public int getMaximumSpamScore() {
		if(heapSize == -1) {
			throw new NoSuchElementException();
		}
		return heap[0].getSpamScore();
	}

	/**
	 * extract the email with the max spam score, deletes it from
	 * the heap and reuturns it
	 * O(logn)
	 * @return
	 */
	public Email extractMaximum() {
		if (heapSize == -1) {
			throw new NoSuchElementException();
		} 
		Email max = heap[0];
		swap(0, heapSize+1, this.heap);
		heap[heapSize+1] = null;
		heapifyDown(0, this.heap);
		return max;
	}

	/**
	 * returns the email ids in the heap
	 * O(n)
	 * @return
	 */
	public String[] getIDs() {
		String[] ids = new String[heapSize+1];
		int index = 0;
		for(int i = 0; i<heap.length;i++) {
			if(heap[i]!=null) {
				ids[index] = heap[i].getID();
				index++;
			}
		} return ids;
	}

	/**
	 * find the level of a heap given n integer
	 * O(logn)
	 * @param n
	 * @return
	 */
	public int getLevel(int n) {
		int count = 0;
		while (n>0) {
			n= n/2;
			count++;
		} 
		return count;
	}

	/**
	 * this is supposed to sort the heap into a sorted array but stops after 1 iteration
	 * O(nlogn)
	 * with insertion sort its O(n^2)
	 * @return
	 * returns a string array of emails
	 */
	public String[] rankEmails() {
		Email[] copyHeap = new Email[heapSize+1];
		for(int i = 0; i<heapSize+1; i++) {
			copyHeap[i]=heap[i];
		}
//		int elements = copyHeap.length-1;
//		int elementsSize = elements;
//		System.out.println(elementsSize);
//		for(int i = elements; i>0; i--) {
//			swap(0,elementsSize,copyHeap);
//			System.out.println(copyHeap[elementsSize].id + " " + copyHeap[elementsSize].spamScore );
//			elementsSize--;
//			heapifyDown(0,copyHeap);
		String[] result = new String[heapSize+1];
		int i = 0;
		Email[] sortedHeap = selectionSort(copyHeap);
		while(i<sortedHeap.length) {
			result[i] = copyHeap[i].id + " -- " + copyHeap[i].spamScore;
			i++;
		}
	 return result;
	}
	
		



	/**returns a string array of words from an email
	 * O(n^2)
	 * @param id
	 * @return
	 */
	public String[] getWords(String id) {
		for(int i = 0; i<heap.length; i++) {
			if(heap[i].getID()== id) {
				return heap[i].getWords();
			}
		} return null;
	}

	/**
	 * perform insertion sort on a heap
	 * @param heap
	 * @return
	 */
	public Email[] selectionSort(Email[] heap) {
		for(int i = 0; i<heap.length; i++) {
			int k = i;
			for(int j = i+1; j<heap.length; j++) {
				if (heap[j].spamScore > heap[k].spamScore) {
					k=j;
				}
			} 
			if(i!=k) {
				swap(i,k,heap);
			}
		} return heap;
	}
	
	/**
	 * return the amount of elements in the heap
	 * @return
	 */
	public int size() {
		return heapSize + 1;
	}
}
