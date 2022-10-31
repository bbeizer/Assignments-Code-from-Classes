/**
 * <This is an elevator object. it holds jobs it has to do. it will service the a max of three people in the building lobby before handling more jobs>
 * Known Bugs: <“being asked to hold too many jobs could be an issue”>
 *
 * @author Benjamin Beizer
 * <benjaminbeizer@brandeis.edu>
 * <1, 22, 2020>
 * COSI 21A PA0
 */
package main;

public class Elevator {

	/**
	 * This specifies the number of people that can be brought to their floors by an Elevator 
	 * instance at any given time. 
	 * <p>DO NOT REMOVE THIS FIELD</p>
	 * <p>You may edit it. But keep it public.</p>
	 */
	Job[] tasks;
	int taskLength = 0;
	int level;
	public static int maxOccupants = 3;
	//creates an elevator with an array of jobs
	public Elevator() {
		tasks = new Job[5];
	}
	/**
	 * this method creates and adds a job for the elevator to complete if the person has a valid elevator request
	 * this method also expands the job array every time a person get added by two (so its essentially an array list
	 * 
	 * @param person
	 * @param floor
	 */
	public void createJob(Person person, int floor) {
		if (person.getBuilding().enterElevatorRequest(person, floor) == true) {
			Job job = new Job(person, floor);
			tasks[taskLength++] = job;
			expandTasks(tasks, 2);
		} 
	}
	/**
	 * this processes all the jobs that the elevator needs to do. The method will try to take at most three people to be processed
	 * after this it creates a null person job which queues processJob to send the elevator to the lobby and will jump to the next possible group 
	 * of three or less to be serviced by the elevator. Jobs get processed first come first serve
	 */
	public void processAllJobs() {
		Job[] jobqueue = new Job[maxOccupants];
		int i = 0;
		while(tasks[i]!= null) {
			for(int j = 0; j< maxOccupants; j++) {
				if(tasks[j+i]!=null) {
					jobqueue[j]= tasks[i+j];
				}
			} 
			for(int k = 0; k < jobqueue.length; k++) {
				if(jobqueue[k]!=null) {
					processJob(jobqueue[k]);
					jobqueue[k]=null;
				} 
			}
			processJob(new Job(null, 0));
			i = i + maxOccupants;
		}
	}
	/**
	 * this increases the size of the task array by the specified increased and sets the task field to the expanded array
	 * @param tasks
	 * @param increaser
	 */
	public void expandTasks(Job[] tasks, int increaser) {
		Job[] expandedTasks = new Job[tasks.length + increaser];
		for(int i=0; i<tasks.length; i++) {
			expandedTasks[i] = tasks[i];
		}
		this.tasks = expandedTasks;
	}
	/**
	 * if the job has a person associated with it, the elevator will move up or down depending on where it is in relation to where it needs to go. 
	 * when the person reached the desired floor the will exit the elevator to their floor
	 * if job object has a null person it returns the elevator to the lobby  
	 * @param job
	 */
	public void processJob(Job job) {
		if(job.getPerson()!=null) {
			while(job.getFloor()!=level) {
				if(level == 0) {
					System.out.println("Elevator at Lobby");
					level++;
				} else if (level != 0 && level < job.getFloor()) {
					System.out.println("Elevator at floor "+ level++);
				} else {
					System.out.println("Elevator at floor " +level--);
				}
			}
			exit(job.getPerson(), job.getFloor());
			System.out.println("Elevator at floor " + level);
		} else {
			while(level>0) {
				System.out.println("Elevator at floor " +level--);
			}
			System.out.println("Elevator at Lobby");
		}
	}
	/**
	 * checks if the person being passed is not null. their location gets set to their floor and they enter that floor array
	 * @param person
	 * @param floor
	 */
	public void exit(Person person, int floor) {
		if (person!=null) {
			person.setLocation(floor);
			person.getBuilding().enterFloor(person, floor);
		}
	}
	public String toString() {
		int i = 0;
		String result = "Elevator Jobs: ";
		while(tasks[i]!=null) {
			result = result + tasks[i].toString() + ". ";
			i++;
		}
		return result;
	}
}