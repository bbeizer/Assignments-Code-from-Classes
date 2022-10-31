/**
 * <This is a hash table the stores entries. entries place by
 * multiplying the ascii value of the letter multiplied by the index.
 * then the hashtable mods this number by the hask table and tries to
 * place the item>
 *
 *<this one does not have many major bug that I know of
 *>
 * @author Benajmin Beizer
 * <benjaminbeizer@brandeis.edu>
 * <May, 9, 2020>
 * COSI 21A PA3
 */

package main;

public class HashTable<V> {


	// KEEP THIS PUBLIC 
	public Entry<V>[] entries;

	// KEEP THIS PUBLIC AND NOT FINAL 
	// However, feel free to change the load factor 
	public static double LOAD_FACTOR = 0.5;

	private int size;

	/**
	 * this creates the hash table. its pretty much and array
	 * O(1)
	 */
	@SuppressWarnings("unchecked")
	public HashTable() {

		// Use this to create generic arrays of type "Entry<V>" 
		// feel free to change the size of this array from 11 
		entries = (Entry<V>[]) new Entry[11];

	}
	
	private Entry<V>[] formerTable;

	/**
	 * this method finds a key in the hash table and return its value
	 * if the slot is full it uses linear probing to place the next item
	 * O(1)
	 * @param key
	 * @return
	 */
	public V get(String key) {
		int hashcode = hashFunction(key);
		int index = hashcode % entries.length;
		while (entries[index]!= null) {
			if (entries[index].getKey() == key) {
				return entries[index].getValue();
			} else {
				index= (index+1) % entries.length;
			}
		} return null;
	}

	/**
	 * this method attempts to put an entry in the hash table
	 * using the hash function. if an entry with the same key is found
	 * as the one you are putting in, the value of that key gets updated
	 * to the new value
	 * if you exceed the load value (elements/table size), the table
	 * gets rehashed
	 * (O)1
	 * @param key
	 * @param value
	 */
	public void put(String key, V value) {
		if(size/entries.length > LOAD_FACTOR) {
			rehash(entries);
		}
		Entry<V> e = new Entry<V>(key, value);
		int hashcode = hashFunction(key);
		int index = hashcode % entries.length;
		Boolean placed = false;
		int i = 0;
		while(placed == false && i < entries.length) {
			index = (index + i) % entries.length;
			if(entries[index]== null) {
				entries[index] = e;
				size++;
				placed = true;
			} else if(entries[index].getKey()== key) {
				entries[index].setValue(value);
				placed = true;
			} else {
				i++;
			}
		}
	
	}
	/**
	 * this increases the size of the hash table
	 * and puts back the existing entries into the hash table that
	 * were in the original
	 * O(n)
	 * @param e
	 */
	@SuppressWarnings("unchecked")
	public void rehash(Entry<V>[] e) {
		formerTable = e;
		Entry<V>[] newTable = (Entry<V>[]) new Entry[formerTable.length*2];
		entries = newTable;
		for(int i = 0; i< formerTable.length; i++) {
			if(formerTable[i]!= null && formerTable[i].getKey() != null) {
				this.put(formerTable[i].getKey(),formerTable[i].getValue());
				
			}
		}
	}

	/**
	 * this tries to deleted a key from the hash table
	 * if it hits a null slot, null is returned,
	 * if they key passed in matches a key in the table
	 * the size of the elements of the hash table decreases and
	 * the element is replaced with a null entry
	 * O(1)
	 * @param key
	 * @return
	 */
	public V delete(String key) {
		Entry<V> e = null;
		int hashCode = hashFunction(key);
		int index = hashCode % entries.length;
		for(int i = 0; i<entries.length; i++) {
			index = (index + i) % entries.length;
			if(entries[index+i] == null){
				return null;
			}
			if (entries[index+i].getKey() == key) {
				size--;
				e = entries[index+i];
				entries[i]= new Entry<V>(null,null);
				return e.getValue();
			}
		} return null;
	}

	/**
	 * returns an array of keys in the hashtable
	 * O(n)
	 * @return
	 */
	public String[] getKeys() {
		String[] keys  = new String[size];
		int keyIndex = 0;
		for(int i = 0; i<entries.length; i++) {
			if(entries[i]!= null && entries[i].getKey() != null) {
				keys[keyIndex] = entries[i].getKey();
				keyIndex++;
			}
		}
		return keys;
	}
	
	/**
	 * this hashfunction is used to assign a value to a entry with  specific key
	 * the better the hash function the quicker it is to access entries
	 * in this one i muliplied the index of the a character in a
	 * string by its ascii value
	 * O(n)
	 * @param s
	 * @return
	 */
	public int hashFunction(String s) {
		int hashNumber = 0;
		for(int i = 0; i < s.length(); i++) {
			hashNumber = hashNumber + s.charAt(i)*i;
		} return hashNumber;
	}

	/**
	 * this return the number of elements in the hash table
	 * O(1)
	 * @return
	 */
	public int size() {
		return size;
	}
}
