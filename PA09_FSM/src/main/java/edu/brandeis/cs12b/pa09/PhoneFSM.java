package edu.brandeis.cs12b.pa09;


public class PhoneFSM extends FSM {	
	/**
	 * Constructs an FSM for which matchesND(String input) returns true
	 * when input is a valid phone number, and false otherwise.
	 * A valid phone number is defined as a string of any of the following forms:
	 * #-###-###-####
	 * (###)###-####
	 * ###-###-####
	 * ###-####
	 */
	@Override
	public boolean matchesND(String input) {
		String regexps1 = "\\d-\\d{3}-\\d{3}-\\d{4}";
		String regexps2 = "\\(\\d{3}\\)\\d{3}-\\d{4}";
		String regexps3 = "\\d{3}-\\d{3}-\\d{4}";
		String regexps4 = "\\d{3}-\\d{4}";
		if (input.matches(regexps1) ||input.matches(regexps2) || input.matches(regexps3)|| input.matches(regexps4))  {
			return true;
		} else {
			return false;
		}
	}
}

