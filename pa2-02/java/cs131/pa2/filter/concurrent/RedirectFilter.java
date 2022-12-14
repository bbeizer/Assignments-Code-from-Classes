/**
 * <filter for the redirecting output to a file>
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
import java.io.FileWriter;
import java.io.IOException;

import cs131.pa2.filter.Filter;
import cs131.pa2.filter.Message;
/**
 * The filter for redirecting the output to a file 
 * @author cs131a
 *
 */
public class RedirectFilter extends ConcurrentFilter {
	private FileWriter fw;

	/**
	 * The contructor of the redirect filter
	 * @param line the parameters of where to redirect
	 * @throws Exception throws an exception when there is error with the parameters, or when
	 * the specified path is not found (required for the case where we give a path of a directory outside
	 * of the current directory) 
	 */
	public RedirectFilter(String line) throws Exception {
		super();
		String[] param = line.split(">");
		if(param.length > 1) {
			if(param[1].trim().equals("")) {
				System.out.printf(Message.REQUIRES_PARAMETER.toString(), line.trim());
				throw new Exception();
			}
			try {
				fw = new FileWriter(new File(ConcurrentREPL.currentWorkingDirectory + Filter.FILE_SEPARATOR + param[1].trim()));
			} catch (IOException e) {
				System.out.printf(Message.FILE_NOT_FOUND.toString(), line);	//shouldn't really happen but just in case
				throw new Exception();
			}
		} else {
			System.out.printf(Message.REQUIRES_INPUT.toString(), line);
			throw new Exception();
		}
	}

	/**
	 *checks whether the filter is done processing
	 *takes a line from the blocking queue and writes it to a file
	 *if the string it pulls from it is the <END> string, it exits out of the while loop
	 *turns the finished flag to true and closes the next file
	 *if the background command in the main method gets killed
	 *then the process doesn't close the so the file never gets created
	 */
	public void process() {
		while(!isDone()) {
		if(terminate == true) {
			break;
		}
			try {
				String line = input.take();
				if(line.equals("<END>")) {
					finished = true;
					break;
				}
				processLine(line);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		finished = true;
		//this line only skips when user kills a background command
		if(terminate == false) {
		closeFile();
		}
	}

	/**
	 * closes the file
	 */
	public void closeFile() {
		if(isDone()) {
			try {
				fw.flush();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * processes one line from the input and writes it to the output file
	 * @param line the line as got from the input queue
	 * @return not used, always returns null
	 */
	public String processLine(String line) {
		try {
			fw.append(line + "\n");
		} catch (IOException e) {
			System.out.printf(Message.FILE_NOT_FOUND.toString(), line);
		}
		return null;
	}
}
