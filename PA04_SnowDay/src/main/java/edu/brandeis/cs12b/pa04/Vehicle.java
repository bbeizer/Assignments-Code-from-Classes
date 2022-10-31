package edu.brandeis.cs12b.pa04;

import edu.brandeis.cs12b.pa04.provided.City;
import edu.brandeis.cs12b.pa04.provided.Point;

public abstract class Vehicle {

	protected Point location;
	protected City city;
	protected String facing;

	//method that checks to see if a point is out of bounds
	public boolean outOfBounds(Point area) {
		if (area.x > city.maxX || area.y > city.maxY) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This places your vehicle into a city. If invalid, ensure that 
	 * somewhere in your code the proper VehicleError is printed.
	 * 
	 * @param city the city to be placed into
	 * @param location the location in the city to be placed
	 * @param facing this direction
	 * @return true if this happens successfully, false if not
	 */
	//places vehicle in city initializing city, loc, and facing in vehicle fields
	public boolean place(City city, Point location, String facing){
		this.city = city;
		this.location = location;
		this.facing = facing;
		if (city.isOffRoad(location)||outOfBounds(location) == true) {
			reportPlaceError();
			return false;
		} else {
			return true;
		}	
	}

	/**
	 * This method should move your vehicle one cell in the direction it was facing.
	 * If your vehicle can't move because there's a wall in the way, it should stay in
	 * same place and call the reportError(), then return false so it can be taken
	 * off the list of active vehicles
	 * @return true if the move was successfully made, false if not
	 */
	//basic move patern for a vehicle, check to see if next tile is off road if not moves vehicle one tile forward
	public boolean move(){
		if (city.isOffRoad(location.translate(facing)) || outOfBounds(location.translate(facing))) {
			reportMoveError();
			return false;
		} 
		location = location.translate(facing);
		return true;
	}

	/**
	 * This method reports if a vehicle can't move. This should be different for each vehicle.
	 * Use the static fields in the VehicleError class to get the text to print.
	 */
	//calls move error method to subclass
	public abstract void reportMoveError();
	/**
	 * Likewise for placing a vehicle.
	 */
	//calls place error to sub class
	public abstract void reportPlaceError();

	/**
	 * This method turns the vehicle in question to the right. Not all vehicles
	 * have this capability, however. So make sure only certain of your vehicles
	 * can turn right.
	 */
	//method returns nothing. I dont want car or snowplow to turn right
	protected void turnRight() {
		return;
	}

	/**
	 * This method turns the vehicle in question to the left. Not all vehicles
	 * have this capability, however. So make sure only certain of your vehicles
	 * can turn left.
	 */
	//method returns nothing. I dont want car or snowplow to turn left
	protected void turnLeft(){
		return;
	}


	/**
	 * Methods for getting the name of the vehicle and converting to a string have been implemented
	 * for you. However, you should study how this mechanism works to get a sense of how you might
	 * implement inheritance among the various vehicles.
	 */
	//gets the name of the vehicle
	public abstract String getName();

	public String toString(){
		return this.getName() + " " + this.location.toString();
	}
}
