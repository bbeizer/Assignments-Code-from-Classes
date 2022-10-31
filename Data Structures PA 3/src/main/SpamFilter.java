/**
 * <this is my filter class. it has as spamicity threshold a
 * hashtable of good words a hashtable of bad words
 * a number of a good email, a number of abad emails
 * and priority queue inbox of emails>
 *
 *<there may be bugs>
 * @author Benajmin Beizer
 * <benjaminbeizer@brandeis.edu>
 * <May, 9, 2020>
 * COSI 21A PA3
 * 
 */
package main;

public class SpamFilter {
	int threshold;
	PriorityQueue inbox;
	public HashTable goodWords;
	public HashTable badWords;
	int badEmails;
	int goodEmails;

	/**
	 * creates the spam filter
	 * O(1)
	 * @param threshold
	 */
	public SpamFilter(int threshold) {
		this.threshold = threshold;
		goodWords = new HashTable();
		badWords = new HashTable();
		badEmails = 0;
		goodEmails = 0;
		inbox = new PriorityQueue();
	}

	/**
	 * sets the spamicity threshold of the filters
	 * O(1)
	 * @param threshold
	 */
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	/**
	 * calculates the spamicity of a word by diving the probability
	 * that the word is bad over the probablity that the word is bad + good
	 * 
	 * @param word
	 * @return
	 */
	public double getSpamicity(String word) {
		double numOfBadWords = (double)badWords.get(word);
		double numOfGoodWords = (double)goodWords.get(word);
		double spamicity = (numOfBadWords/badEmails)/((numOfBadWords/badEmails)+(numOfGoodWords/goodEmails));
		return spamicity;
	}

	/**
	 * gives the spam score of an array of words
	 * O(n)
	 * @param words
	 * @return
	 */
	public int calculateSpamScore(String[] words) {
		int spamScore = 0;
		for (int i = 0; i<words.length;i++) {
			if(words[i]!=null) {
				double spamicity = getSpamicity(words[i]);
				if (spamicity >= .9) {
					spamScore = spamScore + 4;
				}else if (spamicity >= 0.75) {
					spamScore = spamScore + 2;
				}
				else if (spamicity >= 0.5) {
					spamScore = spamScore + 1;
				} else {
					spamScore = spamScore + 0;
				}
			}

		}return spamScore;
	}

	/**
	 * puts emails in the inbox of the filter
	 * O(n)
	 * @param emails
	 */
	public void receive(Email[] emails) {
		for(int i = 0; i<emails.length; i++) {
			inbox.insert(emails[i]);
		}
	}

	/**
	 * prints emails ids + if they are blow the spam threshold
	 * O(n)
	 * @return
	 */
	public String filter() {
		String result = "";
		for(int i = 0; i< inbox.heap.length; i++) {
			if(calculateSpamScore(inbox.heap[i].getWords())< threshold) {
				result = result + inbox.heap[i].getID() + "\n";
			}
		} return result;
	}

	/**
	 * calculates the likelihood that a word word is spam or not spam and puts
	 * the number of times a word is seen in seen in a spam email or a safe email into the hash table
	 * O(n^2)
	 * @param emails
	 * @param isSpam
	 */
	public void train(Email[] emails, boolean[] isSpam) {
		for(int i = 0; i<emails.length; i++) {
			if(isSpam[i] == true) {
				badEmails++;
				for(int j = 0; j< emails[i].getWords().length; j++) {
					String[] keySet = badWords.getKeys();
					for(int k = 0; k< keySet.length; k++) {
						if (keySet[k] == emails[i].getWords()[j]) {
							int val = (int)badWords.get(keySet[k]);
							badWords.put(keySet[k], val);
						} else {
							badWords.put(emails[i].getWords()[j], 1);
						}
					}
				}

			} else {
				goodEmails++;
				for(int j = 0; j< emails[i].wordsHash.getKeys().length; j++) {
					String[] keySetGood = goodWords.getKeys();
					for(int k = 0; k<keySetGood.length; k++) {
						if (keySetGood[k]== emails[i].getWords()[j]) {
							int val = (int)goodWords.get(keySetGood[k]);
							badWords.put(goodWords.entries[j].getKey(), val);
						} else {
							goodWords.put(emails[i].getWords()[j], 1);
						}
					}
				}
			}
		}
	}

	/**
	 * return an ordering of the spamicity of the email in the inbox
	 * O(n)
	 * @return
	 */
	public String getEmailRanking() {
		String[] rankedEmails = inbox.rankEmails();
		String result = "";
		for(int i = 0; i<rankedEmails.length; i++) {
			result = result + rankedEmails[i] + "\n";
		} return result;
	}
}
