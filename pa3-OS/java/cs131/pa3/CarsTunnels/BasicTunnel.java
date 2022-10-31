package cs131.pa3.CarsTunnels;

import java.util.ArrayList;

import cs131.pa3.Abstract.Direction;
import cs131.pa3.Abstract.Tunnel;
import cs131.pa3.Abstract.Vehicle;

/**
 * 
 * The class for the Basic Tunnel, extending Tunnel.
 * this class contains an arraylist that represents the tunnel which the vehicles
 * are put into. In this class there are fields that represent the number of sleds in
 * the tunnel and the number of cars in the tunnel and the direction that all the vehicles
 * are going to in the tunnel
 * @author Benjamin Beizer Email: benjaminbeizer@brandeis.edu
 *
 */
public class BasicTunnel extends Tunnel{

	/**
	 * Creates a new instance of a basic tunnel with the given name
	 * @param name the name of the basic tunnel
	 */

	ArrayList<Vehicle> tunnel;
	int sleds;
	int cars;
	Direction dir;

	/**
	 * constructor for the tunnel
	 * @param name
	 */
	public BasicTunnel(String name) {
		super(name);
		tunnel = new ArrayList<Vehicle>();
		cars = 0;
		sleds = 0;
		dir = null;
	}

	/**
	 * method that attempts to insert vehicles into the tunnel. If the vehicle
	 * is able to be added to the tunnel, it gets added to the arraylist true is returned
	 * and the integer associated with the number of cars or sleds gets incremented depending
	 * on the type of vehicle gets added and the direction of the tunnel gets set to whatever direction
	 * that car is going 
	 * 1st if checks if there's 3 cars
	 * 2nd if checks if the vehicle is a car and if it can get added to the tunnel
	 * 3rd if checks if the vehicle is a sled and if it
	 * can be added to the tunnel and the (can only be true if no vehicles are in the tunnel)
	 * The keyword synchronized is used to make sure only one thread is calling this method at a time
	 * 
	 */
	@Override
	protected synchronized boolean tryToEnterInner(Vehicle vehicle) {
		if(tunnel.size()>=3) {
			return false;
		} else {
			if (vehicle instanceof Car) {
				if(dir == null || dir == vehicle.getDirection() && sleds == 0) {
					dir = vehicle.getDirection();
					cars++;
					tunnel.add(vehicle);
					return true;
				} else {
					return false;
				}
			} else {
				if(sleds == 0 && cars == 0) {
					dir = vehicle.getDirection();
					sleds++;
					tunnel.add(vehicle);
					return true;
				} else {
					return false;
				}
			}

		}
	}

	/**
	 *this method removes a specified vehicle from the tunnel.
	 *the vehicle gets removed from the arraylist. if the vehicle 
	 *is a sled, sleds gets decremented and if the vehicle is a car cars gets decremented.
	 *if there's no cars or sleds in the tunnel then the direction gets set to null so that a car going in
	 *any direction can enter the tunnel. this method is synchronized so no two threads
	 * can use this method at the same time
	 *
	 */
	@Override
	protected synchronized void exitTunnelInner(Vehicle vehicle) {
		tunnel.remove(vehicle);
		if(vehicle instanceof Car) {
			cars--;
		} else {
			sleds--;
		}
		if(sleds == 0 && cars == 0) {
			dir = null;
		}
	}

}
