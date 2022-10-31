package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.Job;
import main.Person;

class StudentJobTest {

	
	@Test
	void testGetFloor() {
		Job j = new Job(new Person("benjamin", "shmeeshle"), 6);
		assertEquals(6, j.getFloor());
	}
}
