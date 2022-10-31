package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Building;
import main.Person;

class StudentElevatorTest {

	@Test
	void twoPeople() {
		Building b = new Building(3);
		Person p1 = new Person("Steve", "Jobs");
		Person p2 = new Person("James", "Gosling");
		p1.enterBuilding(b, 2);
		p2.enterBuilding(b, 3);
		b.startElevator();
	}

	@Test
	void threePeople() {
		Building b = new Building(8);
		Person p1 = new Person("Steve", "Jobs");
		Person p2 = new Person("James", "Gosling");
		Person p3 = new Person("Eric", "Andre");
		p1.enterBuilding(b, 1);
		p2.enterBuilding(b, 5);
		p3.enterBuilding(b, 3);
		b.startElevator();
	}
	@Test
	void fivePeople() {
		Building b = new Building(5);
		Person p1 = new Person("Steve", "Jobs");
		Person p2 = new Person("James", "Gosling");
		Person p3 = new Person("Eric", "Andre");
		Person p4 = new Person("Franz", "Korman");
		Person p5 = new Person("LeBron", "James");
		p1.enterBuilding(b, 1);
		p2.enterBuilding(b, 5);
		p3.enterBuilding(b, 3);
		p4.enterBuilding(b, 1);
		p5.enterBuilding(b, 4);

		b.startElevator();
	}
	@Test
	void thirtypeople() {
		Building b = new Building(8);
		Person p1 = new Person("Steve", "Jobs");
		Person p2 = new Person("James", "Gosling");
		Person p3 = new Person("Eric", "Andre");
		Person p4 = new Person("Franz", "Korman");
		Person p5 = new Person("LeBron", "James");
		Person p6 = new Person("Steve", "Jobs");
		Person p7 = new Person("James", "Gosling");
		Person p8 = new Person("Eric", "Andre");
		Person p9 = new Person("Franz", "Korman");
		Person p10 = new Person("LeBron", "James");
		Person p11 = new Person("Steve", "Jobs");
		Person p12 = new Person("James", "Gosling");
		Person p13 = new Person("Eric", "Andre");
		Person p14 = new Person("Franz", "Korman");
		Person p15 = new Person("LeBron", "James");
		Person p16 = new Person("Steve", "Jobs");
		Person p17 = new Person("James", "Gosling");
		Person p18 = new Person("Eric", "Andre");
		Person p19 = new Person("Franz", "Korman");
		Person p20 = new Person("LeBron", "James");
		Person p21 = new Person("Steve", "Jobs");
		Person p22 = new Person("James", "Gosling");
		Person p23 = new Person("Eric", "Andre");
		Person p24 = new Person("Franz", "Korman");
		Person p25 = new Person("LeBron", "James");		
		p1.enterBuilding(b, 1);
		p2.enterBuilding(b, 5);
		p3.enterBuilding(b, 3);
		p4.enterBuilding(b, 2);
		p5.enterBuilding(b, 4);
		p6.enterBuilding(b, 1);
		p7.enterBuilding(b, 5);
		p8.enterBuilding(b, 3);
		p9.enterBuilding(b, 2);
		p10.enterBuilding(b, 4);
		p11.enterBuilding(b, 1);
		p12.enterBuilding(b, 5);
		p13.enterBuilding(b, 3);
		p14.enterBuilding(b, 2);
		p15.enterBuilding(b, 4);
		p16.enterBuilding(b, 1);
		p17.enterBuilding(b, 5);
		p18.enterBuilding(b, 3);
		p19.enterBuilding(b, 2);
		p20.enterBuilding(b, 4);
		p21.enterBuilding(b, 4);
		p22.enterBuilding(b, 4);
		p23.enterBuilding(b, 4);
		p24.enterBuilding(b, 4);
		p25.enterBuilding(b, 4);
		b.startElevator();
	}
	@Test
	void testToString() {
		Building b = new Building(8);
		Person p1 = new Person("Steve", "Jobs");
		Person p2 = new Person("James", "Gosling");
		Person p3 = new Person("Eric", "Andre");
		p1.enterBuilding(b, 1);
		p2.enterBuilding(b, 5);
		p3.enterBuilding(b, 3);
		
	}
}

