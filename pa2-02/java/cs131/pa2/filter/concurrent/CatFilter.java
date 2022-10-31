/**
 * <filter for a Cat Command>
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
import java.io.FileNotFoundException;
import java.util.Scanner;

import cs131.pa2.filter.Message;

/**
 * @author Ben
 * filter for a Cat Command
 */
public class CatFilter extends ConcurrentFilter {
	private Scanner reader;
	
	/**
	 * The constructor of the cat filter
	 * @param line the parameters for cat
	 * @throws Exception throws exception when there is an error with the given parameters,
	 * 			or when the file is not found
	 */
	public CatFilter(String line) throws Exception {
		super();
		//parsing the cat options
		String[] args = line.split(" ");
		String filename;
		//obviously incorrect number of parameters
		if(args.length == 1) {
			System.out.printf(Message.REQUIRES_PARAMETER.toString(), line);
			throw new Exception();
		}
		filename = args[1];
		try {
			reader = new Scanner(new File(ConcurrentREPL.currentWorkingDirectory+"/"+filename));
		} catch (FileNotFoundException e) {
			System.out.printf(Message.FILE_NOT_FOUND.toString(), line);
			throw new Exception();
		}
	}
	
	/**
	 * Overrides the process() method of ConcurrentFilter to
	 * check whether the file has more lines (through the reader object)
	 * and calls processLine() for each line until the limit (in variable total) is reached
	 * when process ends the end string gets added to the out of the cat filter and the input of the
	 * next filter
	 */
	public void process() {
		while(true) {
			String processedLine = processLine("");
			if(processedLine == null) {
				break;
			}
			output.add(processedLine);
		}
		output.add("<END>");
		finished = true;
		reader.close();
	}
	/**
	 * Processes each line by reading from the reader object and adding the result to the output queue
	 * @param line the line to be processed
	 */
	public String processLine(String line) {
		if(reader.hasNextLine()) {
			return reader.nextLine();
		} else {
			return null;
		}
	}

	/**
	 * Closes the input file reader whenever the filter is created but not executed properly
	 * (for example due to error in linking filters)
	 */
	public void terminate() {
		reader.close();
	}
}
