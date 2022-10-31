/**
* <This makes creates tree given work entries
* and prints 
* the appropriate commands the appropriated commands 
* given from user
* >
* Known Bugs: <parse isnt implemented>
*
* @author Benjamin Beizer
* <benjaminbeizer@brandeis.edu>
* <4, 12, 2020>
* COSI 21A PA2
*/
package main;

public class WorkTimeAnalysisTool {
	WorkEntry[] entries;

	/**
	 * stores array of entries in entry field
	 *
	 * @param entries
	 */
	public WorkTimeAnalysisTool(WorkEntry[] entries) {
		this.entries = entries;
	}


	/**
	 * creates the tree from the from the array of entries
	 * O(n)
	 */
	public void makeTree() {
		WorkEntrySearchNode temp = new WorkEntrySearchNode("temp");
		WorkEntry firstentry = entries[0];
		WorkEntrySearchNode firstNode = new WorkEntrySearchNode(firstentry.getActivity());
		temp.insert(firstNode);
		for (int i = 1; i < entries.length; i++) {
			WorkEntry e = entries[i];
			WorkEntrySearchNode x = new WorkEntrySearchNode(e.getActivity());
			temp.insert(x).add(e);
		}
	}
	
	/**
	 * returns strings corresponding to the lists
	 * given commands
	 * O(n)
	 * @param cmd
	 * @return
	 */
	public String parse(String cmd) { 
		return"";
	}



}
