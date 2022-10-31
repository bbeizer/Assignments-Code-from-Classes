/**
 * <main method/ REPL that is used by the user of the program
 * to enter commands>
 *
 *<this class should not have any bugs
 *>
 * @author Benjamin Beizer
 * <benjaminbeizer@brandeis.edu>
 * <March, 8, 2021>
 * COSI 131A PA2
 */
package cs131.pa2.filter.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs131.pa2.filter.Message;

/**
 * The main implementation of the REPL loop (read-eval-print loop).
 * It reads commands from the user, parses them, executes them and displays the result.
 * runs filters concurrently so that all the filters process input at the same time
 * also supports background commands that allow the user to kill unfinished commands or view the unfinished commands
 * @author cs131a
 *
 */
public class ConcurrentREPL {
	/** 
	 * the path of the current working directory
	 */
	static String currentWorkingDirectory;
	/**
	 * The main method that will execute the REPL loop
	 * @param args not used
	 *
	 */

	/**
	 * array list that hols linked list filters that represent the background command threads
	 */
	static ArrayList<ConcurrentFilter> backgroundCommands = new ArrayList<ConcurrentFilter>();

	/**
	 * main method of the REPL Loop
	 * @param args
	 */
	public static void main(String[] args){
		currentWorkingDirectory = System.getProperty("user.dir");
		Scanner s = new Scanner(System.in);
		System.out.print(Message.WELCOME);
		String command;
		// number of background jobs
		int numberOfJobs = 0;
		while(true) {
			//obtaining the command from the user
			System.out.print(Message.NEWCOMMAND);
			command = s.nextLine();
			//check to see if the user command is a background command
			boolean isBackGround = command.endsWith("&");
			if(command.equals("exit")) {
				break;
			}  
			//checks to see if theres a repl_jobs command
			//proceeds to print out the background commands that are still running
			else if(command.trim().equals("repl_jobs")) {
				for(ConcurrentFilter c : backgroundCommands) {
					String commandStr = c.commandName;
					int jobs = c.id;
					while(c.getNext() != null) {
						c = (ConcurrentFilter) c.getNext();
					}
					if(c.finished == false) {
						System.out.println("\t" + jobs + ". " + commandStr);
					}
				}
			//check so see if the kill command is seen, if it is
			// checks to see if the user has made a valid kill command
			//if its valid, the program attempts to kills the specifed backround command th
			// be ending each filters process for the background command
			//user tried to kill
			} else if(command.trim().split(" ")[0].equals("kill")){
				String[] killCommand =command.trim().split(" ");
				//no params
				if(killCommand.length == 1) {
					System.out.printf(Message.REQUIRES_PARAMETER.toString(), command);
				} else {
					try {
						int index = Integer.parseInt(killCommand[1]);
						ConcurrentFilter cf = null;
						for(ConcurrentFilter x : backgroundCommands) {
							if(x.id == index) {
								cf = x;
								break;
							}
						}
						//temp is a reference to the head of the linked list of filters
						ConcurrentFilter temp = cf;
						//this while loop terminates all the filters in a command for the kill
						//cf is a pointer
						while(cf!=null) {
							cf.terminate = true;
							cf = (ConcurrentFilter) cf.getNext();
						} backgroundCommands.remove(temp);
						//if the string cant be converted to an int, it catches the exception and
						//reports the error
					} catch(NumberFormatException ex) {
						System.out.printf(Message.INVALID_PARAMETER.toString(), command);
					}
				}

			} else if(!command.trim().equals("")) {
				//building the filters list from the command
				String tempString = command;
				if(isBackGround) {
					//removes the ampersand from the background command so the command can behave normal
					command = command.substring(0, command.indexOf("&"));
				}
				ConcurrentFilter filterlist = ConcurrentCommandBuilder.createFiltersFromCommand(command);
				if(filterlist!=null) {
					filterlist.commandName = tempString;
				}
				//if the command typed in from the user is a background command
				// the head filter of the linkList of filters receives a number ID to specify which number of background command it is
				if(isBackGround == true) {
					filterlist.id = ++numberOfJobs;
					backgroundCommands.add(filterlist);
				}
				//runs the filter threads concurrently. the last filter of a normal command will finished before the user types in more commands
				//if the command is a background command, it wont join the last filter which allows the user to run multiple threads a once while they could
				//still be processing
				while(filterlist != null) {
					Thread thread = new Thread(filterlist);
					thread.start();
					if(filterlist.getNext()== null) {
						try {
							if(isBackGround == false) {
								thread.join();
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					filterlist = (ConcurrentFilter) filterlist.getNext();
				}
			}
		}
		//clears the background commands when the user is done
		backgroundCommands.clear();
		s.close();
		System.out.print(Message.GOODBYE);
	}

}
