package main;
/**
* <A DLL railway of station nodes>
* Known Bugs: <>
*
* @author Benjamin Beizer
* <Benjaminbeizer@brandeis.edu>
* <2, 27, 2020>
* COSI 21A PA1
*/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Railway {

	public DoubleLinkedList<Station> railway;
	public String[] stationNames;
	boolean hitDestination;
	boolean hitStart;
	Node pointer;

	public Railway() {
		railway = new DoubleLinkedList();
		stationNames = new String[18];
	}


	public void addStation(Station s) {
		railway.insert(s);
	}

	public void addRider(Rider r) {
		pointer = railway.head;
		int counter = 0;
		hitDestination = false;
		hitStart = false;
		boolean exit = false;
		while(exit == false) {
			if (pointer.data == r.destinationStation) {
				hitDestination = true;
			}
			if(pointer.data == r.startingStation) {
				hitStart = true;
				exit = true;
				setRiderDirection(r);
				((Station) pointer.data).addRider(r);
			}

		}
		pointer = pointer.next;
	}


	public void addTrain(Train t) {
		Node pointer = railway.head;
		boolean exit = false;
		while(exit == false) {
			if (((Station)pointer.data).name == t.currentStation ) {
				((Station)pointer.data).addTrain(t);
				exit = true;
			} else {
				pointer = pointer.next;
			}
		}
	}

	public void setRiderDirection(Rider r) {
		if (hitStart == true && hitDestination == false) {
			r.direction = 0;
			((Station)pointer.data).addRider(r);
		}
		if (hitStart == true && hitDestination == true) {
			r.direction = 1;
			((Station)pointer.data).addRider(r);
		}
	}
	public String simulate() {
		return null;
	}

	@Override
	public String toString() {
		return null;
	}
}
