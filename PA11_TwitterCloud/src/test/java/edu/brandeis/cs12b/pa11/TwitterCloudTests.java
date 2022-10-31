package edu.brandeis.cs12b.pa11;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

import edu.brandeis.cs12b.pa11.TwitterCloud;

public class TwitterCloudTests {

	@Test
	public void test1() throws Exception{
		TwitterCloud tc = new TwitterCloud();
		tc.makeCloud(new String[] {"donald" , "trump" }, "test.png", 4000);
		File file = new File("test.png");
		assertTrue(file.exists());
		List<String> tokenList = tc.getWords();
		assertTrue(!tokenList.isEmpty());
	}
	
}
