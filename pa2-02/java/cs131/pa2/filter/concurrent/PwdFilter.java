/**
 * <The filter for the pwd command>
 *
 *<this class should not have any bugs
 *>
 * @author Benajmin Beizer
 * <benjaminbeizer@brandeis.edu>
 * <March, 8, 2021>
 * COSI 131A PA2
 */
package cs131.pa2.filter.concurrent;
/**
 * The filter for pwd command
 * @author cs131a
 *
 */
public class PwdFilter extends ConcurrentFilter {
	public PwdFilter() {
		super();
	}
	
	/**
	 * prints the working directory to output
	 * finished gets turned to true after the above is done
	 * this queue adds the halt string and the next queues input receives the halt string
	 */
	public void process() {
		output.add(processLine(""));
		finished = true;
		output.add("<END>");
	}
	
	/**
	 *prints the current working directory to the output of the filter
	 */
	public String processLine(String line) {
		return ConcurrentREPL.currentWorkingDirectory;
	}
}
