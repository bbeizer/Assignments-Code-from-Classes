package edu.brandeis.cs12b.pa04;

import edu.brandeis.cs12b.pa04.provided.City;
import edu.brandeis.cs12b.pa04.provided.Point;
import edu.brandeis.cs12b.pa04.provided.VehicleError;

public class SnowPlow extends Vehicle {

	protected String name = "SnowPlow";

	//I clear snow of where ever the snowplow tries to get place and call the vehicle place method
	@Override
	public boolean place(City city, Point location, String facing) {
		city.clearSnow(location);
		return super.place(city, location, facing);
	}

	//I check to see if next tile is snowy, if it is, i clear it and move the vehicle calling the vehicle move method
	@Override
	public boolean move() {
		if (city.isSnowed(location.translate(facing))) {
			city.clearSnow(location.translate(facing));
		}
		return super.move();
	}


	@Override
	public void reportMoveError() {
		System.out.println(VehicleError.SNOWPLOW_MOVE_ERROR);
	}

	@Override
	public void reportPlaceError() {
		System.out.println(VehicleError.SNOWPLOW_PLACEMENT_ERROR);
	}

	/**
	 * This has been implemented for you.
	 */
	@Override
	public String getName() {
		return this.name;
	}
}
