/**
 * <this is a class for an email. defined by its words an id
 * and a spam score. the hash map help me individualize the words>
 * Known Bugs: <none>
 *
 * @author Benajmin Beizer
 * <benjaminbeizer@brandeis.edu>
 * <May, 9, 2020>
 * COSI 21A PA3
 */
package main;

import java.util.Arrays;

public class Email {
	String id;
	String[] words;
	int spamScore;
	public HashTable wordsHash;
	
	/**
	 * creates email
	 * O(n)
	 * @param id
	 * @param contents
	 */
	public Email(String id, String contents) {
		this.id = id;
		words = contents.split("\\s+");
		spamScore = -1;
		wordsHash = new HashTable();
		placeWords(words);
	}
	
	/**
	 * return email id
	 * O(1)
	 * @return
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * gets the words of an email (non unique)
	 * O(1)
	 * @return
	 */
	public String[] getWords() {
		return words;
	}
	
	/**
	 * returns spam score of an email
	 * O(1)
	 * @return
	 */
	public int getSpamScore() {
		return spamScore;
	}
	
	/**
	 * sets spam score of an email
	 * O(1)
	 * @param score
	 */
	public void setSpamScore(int score) {
		spamScore = score;
	}
	
	/**
	 * places the words of an email into a hash map
	 * O(n)
	 * @param s
	 */
	public void placeWords(String[] s) {
		for(int i = 0; i<s.length; i++) {
			wordsHash.put(s[i], 1);
		}
	}
}
