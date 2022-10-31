package cs131.pa2.filter.concurrent;

/**
 * The filter for printing in the console
 * @author cs131a
 *
 */
public class PrintFilter extends ConcurrentFilter {
	public PrintFilter() {
		super();
	}

	/**
	 *while the filter is not done processing the input,
	 *lines get removed from the input queue
	 *if that line is the end string, you break out of the while loop and mark the filter
	 *as done processing
	 */
	public void process() {
		while(!isDone()) {
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
	}

	/**
	 *prints the lines from the input of the blocking queue to the console
	 */
	public String processLine(String line) {
		if(line!= null) {
			System.out.println(line);
		}
		return null;
	}
}
