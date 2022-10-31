package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import main.Email;
import main.HashTable;
import main.PriorityQueue;

class StudentQueueTest {

	@Test
	void insert() {
		PriorityQueue pq = new PriorityQueue();
		Email a = new Email("ebviefonfe", "hello TAs its me Ben");
		Email b = new Email("ebfreebvfe", "I sure love my pizzapie");
		Email c = new Email("gtewcwvfe", "I love writing tests");
		Email r = new Email("ewbbbbbbgehe", "he he heehehe hehe");
		Email t = new Email("euuuuuuuhehehe", "he he heehehe hehe");
		Email l = new Email("errrrrrhehehe", "he he heehehe hehe");
		Email to = new Email("emmmmmhehehe", "he he heehehe hehe");



		pq.insert(a);
		pq.insert(b);
		pq.insert(c);
		pq.insert(r);
		pq.insert(t);
		pq.insert(l);
		pq.insert(to);
	}

	@Test
	void heapTests() {
		

		PriorityQueue pq = new PriorityQueue();
		HashTable tabe = pq.table;
		Email a = new Email("chami", "hello TAs its me Ben");
		a.setSpamScore(5);
		Email b = new Email("chami1", "I sure love my pizzapie");
		b.setSpamScore(2);
		Email c = new Email("chami2", "I love writing tests");
		c.setSpamScore(5);
		Email r = new Email("chami3", "he he heehehe hehe");
		r.setSpamScore(15);
		Email t = new Email("antonella", "he he heehehe hehe");
		t.setSpamScore(10);
		Email l = new Email("chami5", "he he heehehe hehe");
		l.setSpamScore(1);
		Email to = new Email("chami4", "he he heehehe hehe");
		to.setSpamScore(9);

		pq.insert(l);
		pq.insert(b);
		pq.insert(a);
		pq.insert(c);
		pq.insert(to);
		pq.insert(t);
		pq.insert(r);
		//System.out.println(tabe.get("chami3"));
	//	System.out.println(pq.heap[6].getID());
	//	System.out.println(Arrays.toString(pq.heap));
	//	System.out.println(pq.getMaximumSpamScore());
		String[] ranks = pq.rankEmails();
		for(int i = 0; i<ranks.length; i++) {
			System.out.println(ranks[i]);
		}

	}



}
