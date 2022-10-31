package edu.brandeis.cs12b.pa09;

public class Transition {
	private char[] matchChars;
	private State matchState;

	/**
	 * Constructs a transition that moves to matchState when the input character 
	 * is equal to any of the characters in the matchChars array
	 * @param matchChar
	 * @param matchState
	 */
	public Transition(char[] matchChars, State matchState) {
		this.matchChars = matchChars;
		this.matchState = matchState;
	}

	/**
	 * Attempts to move on the transition, given an input char
	 * @param input
	 * @return matchState if the input character is equal to any of the characters 
	 * in matchChar[]; null otherwise
	 */
	public State moveOn(char input) {
		for(char c : matchChars) {
			if (c == input) {
				return matchState;
			}
		}
		return null;
	}

	public char[] getMatchChars() {
		return matchChars;
	}

	public State getMatchState() {
		return matchState;
	}


}