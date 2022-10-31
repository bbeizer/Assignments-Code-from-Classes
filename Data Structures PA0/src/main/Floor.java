/**
 * <This a a floor object that holds an array of Person objects>
 * Known Bugs: <“Someone could create a lot of floors. also the expandFloor method could be inefficient”>
 *
 * @author Benjamin Beizer
 * <benjaminbeizer@brandeis.edu>
 * <1, 22, 2020>
 * COSI 21A PA0
 */
package main;

public class Floor {
	private Person[] floorList;
	private int numOnFloor;

	public Floor() {
		floorList = new Person[5];

	}
	/**
	 * adds a person to the floorList array and increases the index
	 * whenever someone gets added to the floor, the floor list increases by 2
	 * @param person
	 */
	public void enterFloor(Person person) {
		floorList[numOnFloor++] = person;
		expandFloor(floorList, 2);
	}
	/**
	 * increases the length of the floor array and copies the info on the original aray onto the expanded array
	 * array field gets set to the new expanded array
	 * @param floorList
	 * @param increaser
	 */
	public void expandFloor(Person[] floorList, int increaser) {
		Person[] expandedFloor = new Person[floorList.length + increaser];
		for(int i=0; i<floorList.length; i++) {
			expandedFloor[i] = floorList[i];
		}
		this.floorList = expandedFloor;
	}

	public Person[] getFloorList() {
		return floorList;
	}
	public String toString() {
		int i = 0;
		String people = "People on Floor: ";
		while(floorList[i]!=null) {
			people = people + floorList[i].getFirstName() + " " + floorList[i].getLastName() + ". ";
			i++;
		}
		return people;
	}
}
