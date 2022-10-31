package cs131.pa4.CarsTunnels;

import java.util.Comparator;

import cs131.pa4.Abstract.Vehicle;

/**
 * @author Ben
 * the class is used by my QriorityQueue object in my PriorityScheduler class that allows me to 
 * compare vehicles by priority. Higher Priority means a vehicle will access the tunnel first
 */
public class VehicleComparator implements Comparator<Vehicle> {

	/**
	 * when vehicles get compared it takes the priority of the second vehicle minus the priority of the first
	 * of the first and returns that integer
	 */
	@Override
	public int compare(Vehicle o1, Vehicle o2) {
		return o2.getPriority()-o1.getPriority();
	}
}
