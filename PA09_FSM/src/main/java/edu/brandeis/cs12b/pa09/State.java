package edu.brandeis.cs12b.pa09;
import java.util.*;

public class State {
	private String name;
	private Set<Transition> transitions;
	private boolean isTerminal;

	/**
	 * constructs a new State object, with an empty set of transitions
	 * @param name of the state
	 * @param isTerminal whether or not it is a terminal/end state
	 */
	public State(String name, boolean isTerminal) {
		this.name = name;
		this.transitions = new HashSet<Transition>();
		if (isTerminal == true) {
			this.isTerminal = true;
		} else {
			this.isTerminal = false;
		}
	}

	public State(String name, boolean isTerminal, Set<Transition> transitions) {
		this.name = name;
		this.transitions = new HashSet<Transition>();
		if (isTerminal == true) {
			this.isTerminal = true;
		} else {
			this.isTerminal = false;
		}
		this.transitions = transitions;
	}

	/**
	 * add a transition to this state's set of transitions
	 * @param t transition to add
	 */
	public void addTransition(Transition t) {
		this.transitions.add(t);
	}

	/**
	 * find a transition in this state's set of transitions that matches
	 * the given character, and return the State object that this
	 * transition moves to
	 * @param inputChar character to match
	 * @return the State that the transition that matches inputChar moves to, 
	 * or null if none exists
	 */
	public State getNextState(char inputChar) {
		for(Transition t : this.transitions) {
			for(char c : t.getMatchChars()) {
				if (c == inputChar) {
					return t.getMatchState();
				}

			}

		}
		return null;
	}

	/**
	 * find all possible States you could end up at by transitioning
	 * on the given char
	 * @param c character to match
	 * @return set of possible States after moving on char c
	 */
	public Set<State> getAllNextStates(char inputChar) {
		Set<State> stateSet = new HashSet<State>();
		for(Transition t : this.transitions) {
			for(char c : t.getMatchChars()) {
				if (c == inputChar) {
					stateSet.add(t.getMatchState());
				}
			}
		} 
		if(stateSet.size() == 0) {	
			return null;
		} else {
			return stateSet;
		}
	}

	/**
	 * @return the name of this state
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return true if is a terminal state, false if not
	 */
	public boolean isTerminal() {
		return isTerminal;
	}

	public void setTransition(Set<Transition> t) {
		this.transitions = t;
	}

}