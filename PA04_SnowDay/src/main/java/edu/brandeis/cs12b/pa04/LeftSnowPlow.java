package edu.brandeis.cs12b.pa04;

import edu.brandeis.cs12b.pa04.provided.City;
import edu.brandeis.cs12b.pa04.provided.Point;
import edu.brandeis.cs12b.pa04.provided.VehicleError;

public class LeftSnowPlow extends SnowPlow {

	protected String name = "LeftSnowPlow";

	//if left snowplow is about to run into an offroad it attempts to turn left, then refers back to snowplow method
	//else it just moves straight and calls snowplow move method
	@Override 
	public boolean move() {
		if (city.isOffRoad(location.translate(facing))) {
			turnLeft();
			return super.move();
		} else {
			return super.move();
		}
	}

	@Override
	public void reportMoveError() {
		System.out.println(VehicleError.LEFTSNOWPLOW_MOVE_ERROR);
	}

	@Override
	public void reportPlaceError() {
		System.out.println(VehicleError.LEFTSNOWPLOW_PLACEMENT_ERROR);
	}

	//method for snowplow to turn left
	@Override
	public void turnLeft(){
		if (facing.equals("EAST")) {
			facing = "NORTH";
		} else if (facing.equals("SOUTH")){
			facing = "EAST";
		} else if (facing.equals("WEST")){
			facing = "SOUTH";
		} else {
			facing = "WEST";
		}
	}
	/**
	 * This has been implemented for you.
	 */
	@Override
	public String getName() {
		return this.name;
	}
}
