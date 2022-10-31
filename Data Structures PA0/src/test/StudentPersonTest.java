package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import main.Building;
import main.Person;


class StudentPersonTest {

	@Test
	void getLocation1() {
		Person p = new Person("Antonella", "Dilillo");
		Building volen = new Building(5);
		p.enterBuilding(volen, 6);
		String r = "In Lobby";
		assertEquals(r, p.getLocation());
	}

	@Test
	void getLocation2() {
		Person w = new Person("Ben", "Beizer");
		Building skyline = new Building(6);
		w.enterBuilding(skyline, 6);
		String b = "Waiting to be serviced";
		assertEquals(b, w.getLocation());
	}
	@Test
	void getLocation3() {
		String e = "In Floor 4";
		Person q = new Person("Barbra", "Streisand");
		Building y = new Building(6);
		q.enterBuilding(y, 4);
		y.startElevator();
		assertEquals(e, q.getLocation());
	}

}
