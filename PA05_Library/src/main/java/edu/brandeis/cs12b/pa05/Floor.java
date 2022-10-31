package edu.brandeis.cs12b.pa05;

public class Floor {
	public Case[] arrayofcases;

	public Floor(int casesPerFloor, int[] shelvesPerCase, int[][] shelfCapacity) {
		arrayofcases = new Case[casesPerFloor]; 
		for (int i = 0; i < arrayofcases.length; i++) {
			arrayofcases[i]= new Case(shelvesPerCase[i], shelfCapacity[i]);
		}
	}

	public int getNumberOfCases() {
		return arrayofcases.length;
	}

	public BookLocation addNewBook(String title, int floorindex) {
		for (int caseindex = 0; caseindex < arrayofcases.length; caseindex++) {
			Case c = arrayofcases[caseindex]; 
			BookLocation b = c.addNewBook(title, floorindex, caseindex);
			return b;
		}
		return null;

	}

	public BookLocation getLocationOfBook(String title, int floorindex) {
		for (int caseindex = 0; caseindex < arrayofcases.length; caseindex++) {
			Case c = arrayofcases[caseindex]; 
			BookLocation b = c.getLocationOfBook(title, floorindex, caseindex);
			return b;
		}
		return null;

		}
	
	public boolean checkOut(String title) {
		for (int caseindex = 0; caseindex < arrayofcases.length; caseindex++) {
			Case c = arrayofcases[caseindex]; 
			if (c.checkOut(title) == true){
				return true;
			}
		}
		return false;
	}

	public void checkIn(String title) {
		for (int caseindex = 0; caseindex < arrayofcases.length; caseindex++) {
			Case c = arrayofcases[caseindex];
		}
	}
}
