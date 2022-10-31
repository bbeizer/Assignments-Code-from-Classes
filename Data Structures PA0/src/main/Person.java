/**
 * <This a a person object. People have a last name firstname building they belong to, a desired floor, and a current location>
 * Known Bugs: <“People could create person objects that dont fit the parameters”>
 *
 * @author Benjamin Beizer
 * <benjaminbeizer@brandeis.edu>
 * <1, 22, 2020>
 * COSI 21A PA0
 */
package main;

public class Person {

	private String firstName;
	private String lastName;
	private Building building;
	private int desiredFloor;
	private int location;

	/**
	 * creates person object
	 * @param firstName
	 * @param lastName
	 */
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	/**
	 * stores the persons building and desired floor
	 * when they enter the building this triggers the create job method to add their job request to the elevator jobs array if its a valid request
	 * returns true or false if their elevator request is valid
	 * @param building
	 * @param floor
	 * @return
	 */
	public boolean enterBuilding(Building building, int floor) {
		this.building = building;
		desiredFloor = floor;
		this.building.getElevator().createJob(this, floor);
		return building.enterElevatorRequest(this, floor);
	}
	public Building getBuilding() {
		return building;
	}
	/**
	 * sets a persons location
	 * @param floor
	 */
	public void setLocation(int floor) {
		location = floor;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	/**
	 * if the person location is zero, if the can be serviced by the elevator they'll get the string waiting to be serviced
	 * if their request is invalid, the string "In Lobby" will be produced
	 * if they are not in the lobby the method will return the location of the person
	 * @return String based on location and if the can be serviced
	 */
	public String getLocation() {
		if (location == 0) {
			if (building.enterElevatorRequest(this, desiredFloor)== true) {
				return "Waiting to be serviced";
			} else {
				return "In Lobby";
			}
		} else {
			return "In Floor " + location;
		}
	}
	public String toString() {
		return firstName + " " + lastName;
	}
}
