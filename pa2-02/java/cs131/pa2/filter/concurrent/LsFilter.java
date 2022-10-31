/**
 * <filter for LS>
 *
 *<this class should not have any bugs
 *>
 * @author Benjamin Beizer
 * <benjaminbeizer@brandeis.edu>
 * <March, 8, 2021>
 * COSI 131A PA2
 */
package cs131.pa2.filter.concurrent;
import java.io.File;
/**
 * The filter for ls command
 * @author cs131a
 *
 */
public class LsFilter extends ConcurrentFilter{
	/**
	 * The counter of how many contents are in the directory
	 */
	int counter;
	/**
	 * The folder of the current working directory 
	 */
	File folder;
	/**
	 * The list of files within the current working directory
	 */
	File[] flist;
	/**
	 * The constructor of the ls filter, no parameters.
	 */
	public LsFilter() {
		super();
		counter = 0;
		folder = new File(ConcurrentREPL.currentWorkingDirectory);
		flist = folder.listFiles();
	}
	
	/**
	 *added the boolean to signal that this filter was done processing and the <END> STRING
	 *to the output of this filter and input of the next filter
	 */
	@Override
	public void process() {
		while(counter < flist.length) {
			output.add(processLine(""));
		}
		finished = true;
		output.add("<END>");
		
	}
	
	@Override
	public String processLine(String line) {
		return flist[counter++].getName();
	}
}
