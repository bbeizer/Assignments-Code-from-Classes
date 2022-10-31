package test;

import org.junit.jupiter.api.Test;

import main.Building;
import main.Person;

class StudentFloorTest {

	@Test
	void testFloor() {
		Building b = new Building(8);
		Person p1 = new Person("Steve", "Jobs");
		Person p2 = new Person("James", "Gosling");
		Person p3 = new Person("Eric", "Andre");
		p1.enterBuilding(b, 1);
		p2.enterBuilding(b, 1);
		p3.enterBuilding(b, 1);
		b.startElevator();
	
	}
}

