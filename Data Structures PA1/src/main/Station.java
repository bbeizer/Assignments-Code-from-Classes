package main;
/**
* <a station that holds train and Rider queues depending on their direction>
* Known Bugs: <>
*
* @author Benjamin Beizer
* <Benjaminbeizer@brandeis.edu>
* <2, 27, 2020>
* COSI 21A PA1
*/
public class Station {

	public Queue<Rider> northBoundRiders;
	public Queue<Rider> southBoundRiders;
	public Queue<Train> northBoundTrains;
	public Queue<Train> southBoundTrains;
	String name;

	public Station(String name) {
		this.name = name;
		northBoundRiders = new Queue<Rider>(20);
		southBoundRiders = new Queue<Rider>(20);
		northBoundTrains = new Queue<Train>(20);
		southBoundTrains = new Queue<Train>(20);
	}

	/**adds a rider to appropriate rider q depending on their direction
	 * @param r
	 * @return
	 */
	public boolean addRider(Rider r) { 
		if (r.startingStation == this.name) {
			if(r.goingNorth()== true) {
				northBoundRiders.enqueue(r);
			} else {
				southBoundRiders.enqueue(r);
			}
			return true;
		} else {
			return false;
		}
	}

	/**adds train to appropriate train queue depending on direction
	 * @param t
	 * @return
	 */
	public String addTrain(Train t) {
		if(t.goingNorth()== true) {
			northBoundTrains.enqueue(t);
		} else {
			southBoundTrains.enqueue(t);
		}
		return name + " Disembarking Passengers:" + t.disembarkPassengers();
	}

	public Train southBoardTrain() {
		if (southBoundTrains.front() == null) {
			return null;
		} else {
			Train dequeuedTrain = southBoundTrains.front();
			southBoundTrains.dequeue();
			Rider r = southBoundRiders.front();
			southBoundRiders.dequeue();
			while(dequeuedTrain.addPassenger(r) == true && southBoundRiders.front() !=null) {
				r = southBoundRiders.front();
				southBoundRiders.dequeue();
			}
			return dequeuedTrain;
		}
	}

	public Train northBoardTrain() {
		if (northBoundTrains.front() == null) {
			return null;
		} else {
			Train dequeuedTrain = northBoundTrains.front();
			northBoundTrains.dequeue();
			Rider r = northBoundRiders.front();
			northBoundRiders.dequeue();
			while(dequeuedTrain.addPassenger(r) == true && northBoundRiders.front() !=null) {
				r = northBoundRiders.front();
				northBoundRiders.dequeue();
			}
			return dequeuedTrain;
		}
	}

	public void moveTrainNorthToSouth() {
		Train dequeuedTrain = northBoundTrains.front();
		northBoundTrains.dequeue();
		dequeuedTrain.direction = 1;
		southBoundTrains.enqueue(dequeuedTrain);
	}

	public void moveTrainSouthToNorth() {
		Train dequeuedTrain = southBoundTrains.front();
		southBoundTrains.dequeue();
		dequeuedTrain.direction = 0;
		northBoundTrains.enqueue(dequeuedTrain);
	}

	@Override
	public String toString() {
		return null;
	}

	public String stationName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		Station x = (Station)o;
		if (x.name == this.name) {
			return true;
		} else {
			return false;
		}
	}
}
