/**
 * <this is a simple entry class that is defined 
 * by a string key and a generic value>
 * Known Bugs: <im happy to say this one has 0 bugs>
 *
 * @author Benajmin Beizer
 * <benjaminbeizer@brandeis.edu>
 * <May, 9, 2020>
 * COSI 21A PA3
 */
package main;

public class Entry<V> {

	private String key; 
	private V value;
	
	/**
	 * creates entries
	 * O(1)
	 * @param key
	 * @param value
	 */
	public Entry(String key, V value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * return entry key
	 * O(1)
	 * @return
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * returns entry value
	 * O(1)
	 * @return
	 */
	public V getValue() {
		return value;
	}
	
	/**
	 * sets an entries value
	 * O(1)
	 * @param value
	 */
	public void setValue(V value) {
		this.value = value;
	}
}
