package cs131.pa3.CarsTunnels;

import cs131.pa3.Abstract.Direction;
import cs131.pa3.Abstract.Factory;
import cs131.pa3.Abstract.Tunnel;
import cs131.pa3.Abstract.Vehicle;

/**
 * The class implementing the Factory interface for creating instances of classes
 * @author cs131a and Benjamin Beizer Email: benjaminbeizer@brandeis.edu
 *
 */
public class ConcreteFactory implements Factory {

    /**
     * creates a tunnel given a name
     */
    @Override
    public Tunnel createNewBasicTunnel(String name){
    		return new BasicTunnel(name);
    }

    /**
     * creates a vehicle given a name and a direction
     */
    @Override
    public Vehicle createNewCar(String name, Direction direction){
    		return new Car(name, direction);
    }

    /**
     * creates a sled given a name and direction
     */
    @Override
    public Vehicle createNewSled(String name, Direction direction){
    		return new Sled(name, direction);
    }
}
