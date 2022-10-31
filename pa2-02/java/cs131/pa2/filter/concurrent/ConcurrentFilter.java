/**
 * <An abstract class that extends the Filter and implements the basic functionality of all filters. Each filter should
 * extend this class and implement functionality that is specific for that filter.
 * this class also implements runnable and represents a thread.
 * when a thread starts in the main loop, it calls run in this class which calls process 
 * @author cs131a
 *>
 * @author Benjamin Beizer
 * <benjaminbeizer@brandeis.edu>
 * <March, 8, 2021>
 * COSI 131A PA2
 */
package cs131.pa2.filter.concurrent;
import java.util.concurrent.LinkedBlockingQueue;

import cs131.pa2.filter.Filter;


public abstract class ConcurrentFilter extends Filter implements Runnable {
	
	public String commandName = "";
	
	/**
	 * The input is a blocking queue. blocking queues are thread safe and blocks you from trying to take or pol
	 * when the queue is empty
	 */
	protected LinkedBlockingQueue<String> input;
	/**
	 * read the above description for the input queue
	 */
	protected LinkedBlockingQueue<String> output;

	/**
	 * boolean flag for a filter that checks if the filter has finished processing
	 */
	protected boolean finished = false;
	
	/**
	 * boolean flag that is triggered when someone tries to kill a background command
	 * if its true it stops process/run for a filter
	 */
	protected boolean terminate = false;

	/**
	 * id number for the background command
	 */
	protected int id;
	
	/**
	 *sets the previous filter
	 */
	@Override
	public void setPrevFilter(Filter prevFilter) {
		prevFilter.setNextFilter(this);
	}

	/**
	 * the next filter for this filter. the output of this filter is the input for the next filter
	 */
	@Override
	public void setNextFilter(Filter nextFilter) {
		if (nextFilter instanceof ConcurrentFilter){
			ConcurrentFilter concurrentNext = (ConcurrentFilter) nextFilter;
			this.next = concurrentNext;
			concurrentNext.prev = this;
			if (this.output == null){
				this.output = new LinkedBlockingQueue<String>();
			}
			concurrentNext.input = this.output;
		} else {
			throw new RuntimeException("Should not attempt to link dissimilar filter types.");
		}
	}
	/**
	 * Gets the next filter
	 * @return the next filter
	 */
	public Filter getNext() {
		return next;
	}
	/**
	 * processes the input queue and writes the result to the output queue
	 * if the terminate flag gets turned to true from kill, the processstop
	 * if process sees the string "<END>" is removes it from the queue exits out of the while loop
	 * and send <END> to the output queue which is the input of the next filter
	 * the finished boolean flag also gets turned to true
	 */
	public void process(){
		while (!isDone()){
			if(terminate == true) {
				break;
			}
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
		}	output.add("<END>");
		finished = true;
	}

	/**
	 *gets called when a thread starts in the main method
	 */
	public void run() {
		process();
	}

	/**
	 *checks whether the filter is done processing
	 */
	@Override
	public boolean isDone() {
		return finished;
	}

/**
 * @param line
 * @return
 * method for all filters to process lines however they process
 */
protected abstract String processLine(String line);

}
