package cs131.pa4.CarsTunnels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cs131.pa4.Abstract.Scheduler;
import cs131.pa4.Abstract.Tunnel;
import cs131.pa4.Abstract.Vehicle;

/**
 * The priority scheduler assigns vehicles to tunnels based on their priority
 * It extends the Scheduler class.
 * In addition to this, the priority scheduler has a hashmap of tunnel keys and with an arraylist of vehicle
 * so one can keep track of what vehicles are in each tunnel
 * the priority scheduler also contains a lock object and a condition object to ensure mutual exclusion
 * @author cs131a
 *
 */
public class PriorityScheduler extends Scheduler{

	PriorityQueue<Vehicle> vehiclePQ;
	Lock lock;
	Condition condition;
	HashMap<Tunnel, ArrayList<Vehicle>> tunnelMap;
	
	/**
	 * Creates an instance of a priority scheduler with the given name and
	 * @param name the name of the priority scheduler
	 * @param tunnels the tunnels where the vehicles will be scheduled to
	 * in addition to this I add a PriorityQueue to the priority scheduler to hold cars that attempt to enter a tunnel
	 * in addition to this I pass in a VehicleCompator into the priority queue that allows me compare vehicles by
	 * highest priority
	 * I also createlock and condition objects that allow me to maintain mutual exclusion among the vehicle threads
	 * that attempt to call admit and exit.
	 * finally I create a hashmap that has tunnel keys and arraylist values so I can keep track of what vehicles are
	 * are in which tunnels
	 * 
	 */
	public PriorityScheduler(String name, Collection<Tunnel> tunnels) {
		super(name, tunnels);
		this.vehiclePQ = new PriorityQueue<Vehicle>(new VehicleComparator());
		lock = new ReentrantLock();
		condition = lock.newCondition();
		tunnelMap = new HashMap<Tunnel, ArrayList<Vehicle>>();
		for(Tunnel t: tunnels) {
			tunnelMap.put(t, new ArrayList<Vehicle>());
		}

	}

	/**
	 * when a vehicle call admit on a priority scheduler instance, that vehicle attempts to a enter a tunnel
	 * the method starts by calling lock.lock() to ensure no other vehicle call admit or exit at the same time	
	 *  after this the vehicle gets added to the priority queue. a boolean then gets created to check whether a car has entered
	 *  while a vehicle has yet to enter a tunnel, if the current car that is trying to access a tunnel has a lower priority
	 *  then a vehicle a the top of the priority queue, this line of execution will pause on the line "condition.await" 
	 *  while other operations proceed.
	 *  if the vehicle that is being admitted has the highest priority, the code loops through all the tunnels
	 *  in the hashmap and attempt to put the vehicle in one of the tunnels.
	 *  if the a vehicle can be added to a tunnel then that vehicle gets removed from the priority queue, the vehicle gets added
	 *  to the arraylist of that tunnel, we unlock the lock and return the tunnel where the vehicle was added to.
	 *  if the vehicle tries to enter all the tunnels and is not able to, it calls "condition.await()"
	 *  on line 94.
	 *  */
	@Override
	public Tunnel admit(Vehicle vehicle) {
		lock.lock();
		vehiclePQ.add(vehicle);
		boolean entered = false;
		while(entered == false) {
			if(vehicle.getPriority() < vehiclePQ.peek().getPriority()) {
				try {
					condition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for(Tunnel t: tunnelMap.keySet()) {
				entered = t.tryToEnter(vehicle);
				if(entered == true) {
					vehiclePQ.remove(vehicle);
					tunnelMap.get(t).add(vehicle);
					lock.unlock();
					return t;
				}
			}
			try {
				condition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null; 
	}

	/**
	 * when a vehicle calls exit on a priority scheduler instance, the vehicle attempts to exit the tunnel.
	 * at the start of the method the vehicle calls lock.lock() to ensure no other vehicles are calling exit or admit at the same time
	 * after this we loop through the keyset of tunnels in the hashmap of the priority schedulers.
	 * for each tunnel, we check if that tunnel arraylist contains the vehicle that wants to exit the tunnel
	 * if we can find the vehicle, we call "t.exitTunnel(vehicle)" to remove the vehicle from the tunnel.
	 * we then remove that vehicle from the tunnels arraylist and then we signal all the vehicles notifying them that they are allowed to try
	 * to enter tunnels again (code resumes from when we previously called condition.await()) and the lock gets unlocked. 
	 */
	@Override
	public void exit(Vehicle vehicle) {
		lock.lock();
		for(Tunnel t: tunnelMap.keySet()) {
			if(tunnelMap.get(t).contains(vehicle)) {
				t.exitTunnel(vehicle);
				tunnelMap.get(t).remove(vehicle);
				condition.signalAll();
				lock.unlock();
			}
		}
	}

}
