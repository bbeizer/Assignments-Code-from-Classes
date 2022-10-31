package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import main.HashTable;

class StudentTableTest {

	@Test
	void addElement() {
		HashTable h = new HashTable();
		h.put("chicken", 5);
		h.put("gorgleporgle", 4);
		h.put("chive", 3);
		//System.out.println(Arrays.toString(h.entries));
	}
	
	@Test
	void lotsOfElements() {
		HashTable h = new HashTable();
		h.put("Moms", 5);
		h.put("spaghetti", 5);
		h.put("vomit", 5);
		h.put("on", 5);
		h.put("his", 5);
		h.put("sweatter", 5);
		h.put("already", 5);
		h.put("nervous", 5);
		h.put("surface", 5);
		h.put("calm", 5);
		h.put("ready", 5);
		h.put("drop", 5);
		h.put("bombs", 5);
		h.put("but", 5);
		h.put("he", 5);
		h.put("keeps", 5);
		h.put("on", 5);
		h.put("forgetting", 5);
		h.put("shmeeshle", 5);
		h.put("peeshle", 5);
		h.put("chicken", 5);
		//System.out.println(Arrays.toString(h.entries));
	}
	@Test
	void delete() {
		HashTable h = new HashTable();
		h.put("covid", 5);
		//System.out.println(Arrays.toString(h.entries));
		h.delete("covid");
		if(h.get("covid") ==(null)) {
			//System.out.println("yay");
		}
	}
	@Test
	void getKeys() {
		HashTable h = new HashTable();
		h.put("chicken", 5);
		h.put("nuggets", 2);
		System.out.println(Arrays.toString(h.getKeys()));
		}
	
}
