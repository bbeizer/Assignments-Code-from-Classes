package edu.brandeis.cs12b.pa04;

import java.util.Iterator;

import java.util.ArrayList;

import edu.brandeis.cs12b.pa04.provided.City;
import edu.brandeis.cs12b.pa04.provided.Point;

public class Simulator {

	protected ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

	protected City city;

	/**
	 * Creates a new simulation for a city
	 * @param city to be simulated
	 */
	public Simulator(City city){
		this.city = city;
	}

	/** 
	 * You do not need to alter this constructor.
	 */
	protected Simulator(){}

	/**
	 * This should move each vehicle in the city the given number of steps
	 * on the city map in the appropriate String.
	 * If a vehicle makes an invalid move, ensure the correct error is
	 * printed and remove it from the state.
	 */
	//moves each vehicle in the array list the number of steps indicated, if vehicle's move method is false, it is removed 
	// from the vehicles array list
	public void step(int numberOfSteps){
		for (int j = 0; j<numberOfSteps; j++) {
			Iterator<Vehicle> iterator = vehicles.iterator();
			while(iterator.hasNext()) {
				Vehicle v = iterator.next();
				if (v.move() == false) {
					iterator.remove();
				}
			}

		}
	}


	/**
	 * Places a Vehicle in the city
	 * 
	 * @param vehicle to place in the city
	 * @param location to place the vehicle in the city
	 * @return true if vehicle is successfully placed, false if not
	 */
	//places the vehicle in the city in the simulator. if vehicle.pace is true, the vehicle gets added to the array list
	// else, false is returned
	public boolean placeVehicle(Vehicle vehicle, Point location, String facing){
		if (vehicle == null || location == null) {
			return false;
		}
		if (vehicle.place(this.city, location, facing) == true) {
			vehicles.add(vehicle);
			return true;
		} else {
			return false;
		}	
	}

	/**
	 * Check to see if the Simulation's city is clear
	 * @return true if the city is clear, false if not
	 */
	//checks if sim city is clear
	public boolean isClear(){
		return this.city.isClear();
	}

	public String toString(){
		return this.city.toString();
	}
}
