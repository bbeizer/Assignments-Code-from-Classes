package edu.brandeis.cs12b.pa05;

public class Shelf {
	public Book[] arrayofbooks;
	public int capacity;
	public boolean canAdd;


	public Shelf(int shelfCapacity) {
		arrayofbooks = new Book[shelfCapacity];

	}

	public BookLocation addNewBook(String title, int floorindex, int caseindex, int shelfindex) {
		for (int i = 0; i< arrayofbooks.length; i++) {
			if(arrayofbooks[i]== null ) {
				arrayofbooks[i] = new Book(title);
				arrayofbooks[i].checkedoutstatus = true;
				arrayofbooks[i].location = new BookLocation(floorindex, caseindex, shelfindex);
				return arrayofbooks[i].location;
			}
		}
		return null;
	}

	public boolean getLocationOfBook(String title, int floorindex, int caseindex, int shelfindex) {
		for (int i = 0; i< arrayofbooks.length; i++) {
			if(arrayofbooks[i].title == title) {
				return true;
			}
		}
		return false;
	}

	public boolean checkOut(String title) { 
		for (int i = 0; i< arrayofbooks.length; i++) {
			if(arrayofbooks[i].title.equals(title)) {
				arrayofbooks[i].checkedoutstatus = false;
				return true;
			}
		}
		return false;
	}
	
	public void checkIn(String title) { 
		for (int i = 0; i< arrayofbooks.length; i++) {
			if(arrayofbooks[i].checkedoutstatus == true) {
				arrayofbooks[i].checkedoutstatus = false;
			}
		}
	}
				
	

	public int getCapacityOfShelf() {
		return arrayofbooks.length;
	}
}
