package edu.brandeis.cs12b.pa05;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Library {

	public Floor[] arrayoffloors;

	/**
	 * Construct a new library object based on the given parameters
	 * @param floors the number of floors
	 * @param casesPerFloor the number of cases per floor
	 * @param shelvesPerCase the number of shelves per case on each floor
	 * @param shelfCapacity the capacity of each shelf
	 * @return a library object
	 */
	public Library(int floors, int[] casesPerFloor, int[][] shelvesPerCase, int[][][] shelfCapacity) {
		arrayoffloors = new Floor[floors];
		for (int i = 0; i < arrayoffloors.length; i++) {
			arrayoffloors[i] = new Floor(casesPerFloor[i], shelvesPerCase[i], shelfCapacity[i]);

		}
	}

	/**
	 * Returns the number of floors this library has
	 * @return the number of floors
	 */
	public int getNumberOfFloors() {
		return arrayoffloors.length;
	}

	/**
	 * Returns the number of cases on the nth floor (floors start at 0)
	 * @param floor 
	 * @return the number of cases
	 */
	public int getCasesOnFloor(int floor) {
		return arrayoffloors[floor].getNumberOfCases();
	}

	/**
	 * Returns the number of shelves in a given case on a given floor
	 * @param floor
	 * @param bookcase
	 * @return the number of shelves
	 */
	public int getShelvesInCase(int floor, int bookcase) {
		return arrayoffloors[floor].arrayofcases[bookcase].getNumberofShelves();
	}

	/**
	 * Returns the capacity of the nth shelf at the particular floor and case
	 * @param floor
	 * @param bookcase
	 * @param shelf
	 * @return the shelf capacity
	 */
	public int getCapacityOfShelf(int floor, int bookcase, int shelf) {
		return arrayoffloors[floor].arrayofcases[bookcase].arrayofshelves[shelf].getCapacityOfShelf();
	}


	/**
	 * Adds a new book and returns its location. If there is no space for the new book,
	 * return null.
	 * @param title the title of the book to add
	 * @return the location of the book or null
	 */
	public BookLocation addNewBook(String title) {
		for(int floorindex = 0; floorindex < arrayoffloors.length; floorindex++) {
			Floor f = arrayoffloors[floorindex];
			return f.addNewBook(title, floorindex);
		}

		return null;
	}

	/**
	 * Return the location of the book with the given title, or null if
	 * it is not in the library
	 * @param title the title of the book to lookup
	 * @return the book's location
	 */
	public BookLocation getLocationOfBook(String title) {
		for(int floorindex = 0; floorindex < arrayoffloors.length; floorindex++) {
			Floor f = arrayoffloors[floorindex];
			return f.getLocationOfBook(title, floorindex);
	}
		return null;
	}

	/**
	 * Return the set of all books at the given location, or null if the location is invalid
	 * @param loc the location to list the books at (only checked in books should be listed)
	 * @return the set of books, or null
	 */
	public Set<String> getBooksAt(BookLocation loc) {
		HashSet<String> setofbooks = new HashSet();
			for (int i= 0; i < arrayoffloors[loc.getFloor()].arrayofcases[loc.getCase()].arrayofshelves[loc.getShelf()].arrayofbooks.length; i++) {
				if(arrayoffloors[loc.getFloor()].arrayofcases[loc.getCase()].arrayofshelves[loc.getShelf()].arrayofbooks[i] != null) {
					setofbooks.add(arrayoffloors[loc.getFloor()].arrayofcases[loc.getCase()].arrayofshelves[loc.getShelf()].arrayofbooks[i].title);
				}
			}
		return setofbooks;
	}


	/**
	 * Checks out a book
	 * @param title the book to check out
	 * @return true if the book can be checked out
	 */
	public boolean checkOut(String title) {
		for(int floorindex = 0; floorindex < arrayoffloors.length; floorindex++) {
			Floor f = arrayoffloors[floorindex];
			if (f.checkOut(title) == true) {
				return false;
			}
		}	
		return true;
	}

	/**
	 * Checks a book back in
	 * @param title the book
	 */
	public void checkIn(String title) {
		if (checkOut(title) == true) {
			for(int floorindex = 0; floorindex < arrayoffloors.length; floorindex++) {
				Floor f = arrayoffloors[floorindex];		
			}
		}
	}


	/**
	 * Writes the contents of the library to the passed file
	 * in a format compatible with the LibraryFactory's makeLibraryFromFile method.
	 * @param f the file to write the library to
	 */
	public void writeToFile(File f) {
	}


	/**
	 * Makes a new library from the passed file. The file will have been produced by your
	 * Library's writeToFile method.
	 * 
	 * @param f the file to read from
	 * @return the reconstructed library
	 */
	public static Library makeLibraryFromFile(File f) {

		return null;
	}
}
