package main;
/**
* <A train that holds rider objects current station and direction>
* Known Bugs: <none>
*
* @author Benjamin Beizer
* <Benjaminbeizer@brandeis.edu>
* <2, 27, 2020>
* COSI 21A PA1
*/
public class Train {

	public static final int TOTAL_PASSENGERS = 10;
	public Rider[] passengers;
	public int passengerIndex;
	String currentStation;
	int direction;

	/** constructs the train given given a current station and direction
	 * O(1)
	 * @param currentStation
	 * @param direction
	 */
	public Train(String currentStation, int direction) {
		this.currentStation = currentStation;
		this.direction = direction;
		passengers = new Rider[TOTAL_PASSENGERS];
	}

	/**
	 * checks whether the train is going north. true if north, false if south
	 */
	public boolean goingNorth() {
		if (direction == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * switches the diretion of the train
	 * O(n) 
	 */
	public void swapDirection() {
		if (direction == 1) {
			direction = 0;
		} else {
			direction = 1;
		}
	}

	/**
	 * returns a string of the current passengers
	 * O(n)
	 */
	public String currentPassengers() {
		String result = "";
		if (passengers[0] == null){
			return result;
		} else {
			for(int i = 0; i<passengers.length; i++) {
				result += passengers[i].getRiderID() + ", "  + this.currentStation + "\n";

			}
			return result;
		}
	}

	/**if the rider is at the same station as the train, the train has space, and is going in the same direction of the train
	 * they get added to the train and true is returned, else false
	 * O(n)
	 * @param r
	 * @return
	 */
	public boolean addPassenger(Rider r) {
		if(r.getStarting() == currentStation && r.direction == this.direction && hasSpaceForPassengers() == true) {
			passengers[passengerIndex] = r;
			passengerIndex++;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return true or false if the passenger can get added to the train
	 * O(1)
	 */
	public boolean hasSpaceForPassengers() {
		if (passengerIndex<9) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * if passengers destination is not the current station they get added to another array 
	 * if passengers destination is the station the get disembarked
	 * the other array becomes the new passengers array 
	 * O(n)
	 * @return String of disembarked passengers
	 */
	public String disembarkPassengers() {
		int updatedTrainIndex = 0;
		Rider[] updatedTrain = new Rider[10];
		String result = "";
		if (passengers[0] == null) {
			return result;
		} else {
			for(int i = 0; i<passengers.length; i++) {
				if (passengers[i].getDestination()!=currentStation) {
					updatedTrain[updatedTrainIndex]= passengers[i];
					updatedTrainIndex++;
				} else {
					result = passengers[i].getRiderID() + ", "  + this.currentStation + "\n";
				}
			}
			passengers= updatedTrain;
			return result;
		}
	}

	/**
	 * this changes the current station of train based on what gets passed in
	 * O(1)
	 * @param String s
	 */
	public void updateStation(String s) {
		currentStation = s;
	}

	/**
	 * @return the current station
	 * O(1)
	 */
	public String getStation() {
		return currentStation;
	}

	@Override
	public String toString() {
		return null;
	}
}
