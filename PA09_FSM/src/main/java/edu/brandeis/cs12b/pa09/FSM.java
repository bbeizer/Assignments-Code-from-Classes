package edu.brandeis.cs12b.pa09;

import java.util.Set;

public abstract class FSM {
	protected State initial;

	/**
	 * after processing each character of the input string,
	 * checks if the current state is a terminal/end state
	 * @param input String to process
	 * @return true if processing every character of the input string 
	 * ends up in a terminal/end state
	 */
	public boolean matches(String input) {
		for(int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (initial.isTerminal() == true) {
				return true;
			} else {
				initial = initial.getNextState(c);
				if (initial.isTerminal() == true) {
					return true;
				}	
			}

		}
		return false;
	}

	/**
	 * after processing each character of the input string nondeterministically,
	 * checks if any of the possible current states is a terminal/end state
	 * @param input String to process
	 * @return true if processing every character of the input string
	 * could possibly end up in a terminal/end state
	 */
	public boolean matchesND(String input) {
		for(int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			Set<State> possibleStates = initial.getAllNextStates(c);
			for(State s : possibleStates) {
				if (s == null) {
					return false;
				}
				if(s.isTerminal() == true) {
					return true;	
				} else {
					initial = s;
				}
			}
		}

		throw new UnsupportedOperationException("matchesND not yet implemented");
	}


	/**
	 * EXTRA CREDIT: generates a String representing the FSM in DOT language,
	 * such that it can be read by GraphViz
	 * 
	 * @return the FSM as a DOT string
	 */
	public String toGraphVizString() {
		//TODO EXTRA CREDIT
		return null;
	}
}