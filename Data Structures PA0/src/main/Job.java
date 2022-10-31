/**
 * <A Job Object holds a >
 * Known Bugs: <“None”>
 *
 * @author Benjamin Beizer
 * <benjaminbeizer@brandeis.edu>
 * <1, 22, 2020>
 * COSI 21A PA0
 */
package main;
public class Job {
	Person person;
	int floor;
	/**
	 * creates a job object with person and floornum fields
	 * @param person
	 * @param floor
	 */
	public Job(Person person, int floor){
		this.person = person;
		this.floor = floor;
	}

	public int getFloor() {
		return floor;
	}
	public Person getPerson() {
		return person;
	}
	public String toString() {
		return person.getFirstName() + " "+ person.getLastName() +", " +floor;
	}
}