package edu.brandeis.cs12b.pa11;

/* 
 * We're including a lot of import statements for you because several of the classes
 * You'll be using in this assignment have the same names as classes in other
 * packages, and we don't want you to get confused and use the wrong one. 
 * You may not use all of these imports, and you might use some that aren't included
 * here. That's ok!
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.json.JSONObject;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import wordcloud.CollisionMode;
import wordcloud.WordCloud;
import wordcloud.WordFrequency;
import wordcloud.bg.RectangleBackground;
import wordcloud.font.scale.LinearFontScalar;

public class TwitterCloud {

	/**
	 * Create a word cloud! Remember to use all the tools available in your libraries,
	 * make good decisions on which collections to use to store your data,
	 * and create additional methods as necessary. Use the PA PDF as a guide on how 
	 * to use the various libraries to solve the problem.
	 * 
	 * You can use the code included in Provided_PA11.txt, located in the
	 * root directory of this project, to start things off.
	 * However, you will need to break it up into different methods and classes.
	 * 
	 * Make sure you understand how each part works, and be prepared to explain
	 * your work to your grading TA.
	 * 
	 * Remember: The tests for this PA are very basic, so don't worry about edge cases
	 * or handling bad inputs, etc. Just make your code work, keep it organized,
	 * and be creative!
	 * 
	 * @param args an array of strings to use as a filter for incoming Tweets
	 * @param filename the filename of the image file you should create for your word cloud
	 * @param numberTokens the number of tokens you should extract from the tweets
	 */
	Hosts hosebirdHosts;
	StatusesFilterEndpoint hosebirdEndpoint; 
	Authentication hosebirdAuth; 
	BlockingQueue<String> msgQueue;
	ClientBuilder builder; 
	Client hosebirdClient; 
	List<String> tokens;
	Map<String, Integer> map;
	List<WordFrequency> wflist;
	WordCloud cloud;


	public void makeCloud(String[] args, String filename, int numberTokens) {
		negateDebugInfo();
		directHosebird();
		List<String> terms = Arrays.asList(args);
		hosebirdEndpoint.trackTerms(terms);
		addAuthentication();
		createBlockingQueue();
		createClient();
		addTokens(numberTokens);
		createMap();
		createWFList();
		createCloud();
	}

	public void negateDebugInfo() {
		BasicConfigurator.configure();
		List<Logger> loggers = Collections.<Logger>list(LogManager.getCurrentLoggers());

		loggers.add(LogManager.getRootLogger());
		for (Logger logger : loggers ) {
			logger.setLevel(Level.OFF);
		}
	}

	public void directHosebird() {
		hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
		hosebirdEndpoint = new StatusesFilterEndpoint();
	}

	public void addAuthentication(){
		hosebirdAuth = new OAuth1(System.getenv("CONSUMER_KEY"),
				System.getenv("CONSUMER_SECRET"),
				System.getenv("TOKEN"),
				System.getenv("TOKEN_SECRET"));
	}

	public void createBlockingQueue() {
		msgQueue = new LinkedBlockingQueue<String>(100000);
	}

	public void addTokens(int num) {
		tokens = new LinkedList<String>();
		System.out.println(msgQueue);
		while(tokens.size() < num) {
			System.out.println("ENTERING");
			String msg = null;
			try {
				msg = msgQueue.take();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			JSONObject msg1 = new JSONObject(msg);
			String s = msg1.getString("text");
			try (EnglishAnalyzer an = new EnglishAnalyzer()) {
				TokenStream sf = an.tokenStream(null, s);
				try {
					sf.reset();
					while (sf.incrementToken()) {
						CharTermAttribute cta = sf.getAttribute(CharTermAttribute.class);
						tokens.add(cta.toString());
					}
				} catch (Exception e) {
					System.err.println("Could not tokenize string: " + e);
				} 
			}
		}
		hosebirdClient.stop();
	}

	public void createClient() {
		builder = new ClientBuilder()
				.hosts(hosebirdHosts)
				.authentication(hosebirdAuth)
				.endpoint(hosebirdEndpoint)
				.processor(new StringDelimitedProcessor(msgQueue));
		hosebirdClient = builder.build();
		hosebirdClient.connect();
	}

	public void createMap() {
		map = new HashMap<String, Integer>();
		for(String s : tokens) {
			if(!map.containsKey(s)) {
				map.put(s, 1);
			} else {
				map.replace(s, map.get(s)+1);
			}
		}
	}
	public void createWFList() {
		wflist = new ArrayList<WordFrequency>();
		for(String s : map.keySet()) {
			wflist.add(new WordFrequency(s, map.get(s)));
		}
	}

	public void createCloud() {
		cloud = new WordCloud(400, 400, CollisionMode.RECTANGLE);
		cloud.setPadding(0);
		cloud.setBackground(new RectangleBackground(400, 400));
		cloud.setFontScalar(new LinearFontScalar(14, 40));
		cloud.build(wflist);
		cloud.writeToFile("wf.png");
	}
	/**
	 * This method will only be called after makeCloud
	 * @return a list of all tokenized words from your word cloud
	 */
	public List<String> getWords(){
		return tokens;
	}
}
