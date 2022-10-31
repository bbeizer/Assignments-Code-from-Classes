package main;
/**
* <A main method class that runs a simulation of the railways of train and rider and station given by text files>
* Known Bugs: <>
*
* @author Benjamin Beizer
* <Benjaminbeizer@brandeis.edu>
* <2, 27, 2020>
* COSI 21A PA1
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MBTA {

	public static final int SOUTHBOUND = 1;
	public static final int NORTHBOUND = 0;

	static final int TIMES = 6;
	static Railway r;

	public static void main(String[] args) {
		r = new Railway();
		initRiders("riders.txt");
		initStations("redLine.txt");
		initTrains("trains.txt");
		for (int i = 0; i<TIMES; i++) {
			runSimulation();
		}
	}

	public static void runSimulation() {
		r.simulate();
	}

	public static void initTrains(String trainsFile) {
		try {
			Scanner s = new Scanner(new File(trainsFile));
			while(s.hasNext()) {
				Train t = new Train(s.nextLine(), Integer.parseInt(s.nextLine()));
				r.addTrain(t);
			}
		} catch (FileNotFoundException e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}

	public static void initRiders(String ridersFile) {
		try {
			Scanner s = new Scanner(new File(ridersFile));
			while(s.hasNext()) {
				Rider x = new Rider(s.nextLine(), s.nextLine(), s.nextLine());
				r.addRider(x);
			}
		} catch (FileNotFoundException e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}

	public static void initStations(String stationsFile) {
		try {
			Scanner s = new Scanner(new File(stationsFile));
			while(s.hasNext()) {
				String str = s.nextLine();
				Station sta = new Station(str);
				r.addStation(sta);
			}
		} catch (FileNotFoundException e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}
}
