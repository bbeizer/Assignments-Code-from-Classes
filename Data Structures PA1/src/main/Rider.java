package main;
/**
* <A rider that has has a starting and ending destination and a direction>
* Known Bugs: <>
*
* @author Benjamin Beizer
* <Benjaminbeizer@brandeis.edu>
* <2, 27, 2020>
* COSI 21A PA1
*/
public class Rider {
	String riderID;
	String startingStation;
	String destinationStation;
	int direction;
	public Rider(String riderID, String startingStation, String destinationStation) {
		this.riderID = riderID;
		this.startingStation = startingStation;
		this.destinationStation =  destinationStation;
		direction = 1;
	}

	public String getStarting() {
		return startingStation;
	}

	public String getDestination() {
		return destinationStation;
	}

	public String getRiderID() {
		return riderID;
	}

	public boolean goingNorth() {	
		if(direction == 1) {
			return false;
		} else {
			return true;
		}
	}

	public void swapDirection() {
		if (direction == 1) {
			direction = 0;
		} else {
			direction = 1;
		}
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public boolean equals(Object o) {
		Rider r = (Rider)o;
		
		if (this.riderID == r.riderID) {
			return true;
		} else {
			return false;
		}
	}
}
