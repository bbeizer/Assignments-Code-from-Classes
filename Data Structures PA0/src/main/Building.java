/**
 * <Building object holds an array of floor objects and an elevator object>
 * Known Bugs: <“Someone could create too many floors. also the expandFloor method could be inefficient”>
 *
 * @author Benjamin Beizer
 * <benjaminbeizer@brandeis.edu>
 * <1, 22, 2020>
 * COSI 21A PA0
 */
package main;

public class Building {
	private final Floor[] floors;
	private final Elevator elevator;
	/**
	 * creates a building given number of floors
	 * @param numFloors
	 */
	public Building(int numFloors) {
		floors =new Floor[numFloors+1];
		for(int i=0; i<numFloors+1; i++) {
			floors[i]= new Floor();
		}
		elevator = new Elevator();
	}
	/**
	 * 
	 * @param person Person object
	 * @param floor number floor they go to
	 * @return true or false
	 * if a person enters the building theres a true or false test to determine if they can use the elevator
	 */
	public boolean enterElevatorRequest(Person person, int floor) {
		if (floors.length <= floor || floor < 1 ) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * starts
	 */
	public void startElevator() {
		elevator.processAllJobs();
	}
	//returns the elevator object
	public Elevator getElevator() {
		return elevator;

	}
	/**
	 * puts a person in the floor array
	 * @param person
	 * @param floor
	 */
	public void enterFloor(Person person, int floor) {
		floors[floor].enterFloor(person);
	}
	/**
	 * returns a given floor
	 * @param floor
	 * @return floor
	 */
	public Floor getFloor(int floor) {
		return floors[floor];
	}
	public String toString() {
		return "Building," + " Floors: " + (floors.length-1);
	}
}
