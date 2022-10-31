/**
 * <filter for a WC Command>
 *
 *<this class should not have any bugs
 *>
 * @author Benjamin Beizer
 * <benjaminbeizer@brandeis.edu>
 * <March, 8, 2021>
 * COSI 131A PA2
 */
package cs131.pa2.filter.concurrent;
/**
 * The filter for wc command
 * @author cs131a
 *
 */
public class WcFilter extends ConcurrentFilter {
	/**
	 * The count of lines found
	 */
	private int linecount;
	/**
	 * The count of words found
	 */
	private int wordcount;
	/**
	 * The count of characters found
	 */
	private int charcount;

	public WcFilter() {
		super();
	}

	/**
	 *checks when the filter is finished processing
	 *polls/takes input from the blocking queue and processes it
	 *if that lines is equals to the special end string, you exit the while loop and mark the filter as finished
	 * and add the end string to the output of this string/input of the next string
	 */
	public void process(){
		while (!isDone()){
			String line;
			try {
				line = input.take();
				if (line.equals("<END>")){
					break;
				}
				String processedLine = processLine(line);
				if (processedLine != null){
					output.add(processedLine);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		finished = true;
		output.add(processLine(null));
		output.add("<END>");
	}
/**
 * Counts the number of lines, words and characters from the input queue
 * @param line the line as got from the input queue
 * @return the number of lines, words, and characters when finished, null otherwise
 */
public String processLine(String line) {
	//prints current result if ever passed a null
	if(line == null) {
		return linecount + " " + wordcount + " " + charcount;
	}

	if(isDone()) {
		String[] wct = line.split(" ");
		wordcount += wct.length;
		String[] cct = line.split("|");
		charcount += cct.length;
		return ++linecount + " " + wordcount + " " + charcount;
	} else {
		linecount++;
		String[] wct = line.split(" ");
		wordcount += wct.length;
		String[] cct = line.split("|");
		charcount += cct.length;
		return null;
	}
}
}
