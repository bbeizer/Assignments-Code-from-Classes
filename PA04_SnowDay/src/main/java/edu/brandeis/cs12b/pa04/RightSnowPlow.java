package edu.brandeis.cs12b.pa04;

import edu.brandeis.cs12b.pa04.provided.City;
import edu.brandeis.cs12b.pa04.provided.Point;
import edu.brandeis.cs12b.pa04.provided.VehicleError;

public class RightSnowPlow extends SnowPlow {

	protected String name = "RightSnowPlow";

	//left and right snow plow follow the same model
	@Override
	public boolean move() {
		if (city.isOffRoad(location.translate(facing))) {
			turnRight();
			return super.move();
		} else {
			return super.move();
		}
	}

	@Override
	public void reportMoveError() {
		System.out.println(VehicleError.RIGHTSNOWPLOW_MOVE_ERROR);
	}

	@Override
	public void reportPlaceError() {
		System.out.println(VehicleError.RIGHTSNOWPLOW_PLACEMENT_ERROR);
	}


	@Override
	public void turnRight() {
		if (facing.equals("NORTH")) {
			facing = "EAST";
		} else if (facing.equals("EAST")){
			facing = "SOUTH";
		} else if (facing.equals("SOUTH")){
			facing = "WEST";
		} else {
			facing = "NORTH";
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
