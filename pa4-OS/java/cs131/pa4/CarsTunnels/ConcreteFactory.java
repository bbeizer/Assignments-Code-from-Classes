package cs131.pa4.CarsTunnels;

import java.util.Collection;

import cs131.pa4.Abstract.Direction;
import cs131.pa4.Abstract.Factory;
import cs131.pa4.Abstract.Scheduler;
import cs131.pa4.Abstract.Tunnel;
import cs131.pa4.Abstract.Vehicle;

/**
 * The class implementing the Factory interface for creating instances of classes
 * @author cs131a
 *
 */
public class ConcreteFactory implements Factory {

    /**
     * Creates a new instance of a tunnel with a string name
     */
    @Override
    public Tunnel createNewBasicTunnel(String name){
    		return new BasicTunnel(name);   
    }

    /**
     * creates a new instance of a car with a string name and a direction the car is going in
     */
    @Override
    public Vehicle createNewCar(String name, Direction direction){
    		return new Car(name, direction);  
    }

    /**
     * creates a new instance of a sled with a string name and a direction
     */
    @Override
    public Vehicle createNewSled(String name, Direction direction){
    		return new Sled(name, direction);   
    }

    /**
     * creates an instance of a priority scheduler that takes in a name and a collection of tunnels
     */
    @Override
    public Scheduler createNewPriorityScheduler(String name, Collection<Tunnel> tunnels){
    		return new PriorityScheduler(name, tunnels);
    }
}
