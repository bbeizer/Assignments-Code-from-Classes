package edu.brandeis.cs12b.pa08;

import java.util.*;

public class Vertex {
	String name;
	HashMap<Vertex, Integer> connections = new HashMap<Vertex, Integer>();
	public Vertex(String name) {
		this.name = name;
	}
	public Set<String> getNeighborNames(Vertex v){
		Set<String> neighborNames = new HashSet<String>();
		for(Vertex vertex : v.connections.keySet()) {
			neighborNames.add(vertex.name);
		}
		return neighborNames;
	}
	
	public Vertex getVertex(String s) {
		for (Vertex v : connections.keySet()) {
			if(v.name.equals(s)) {
				return v;
			}
		}
		return null;
	}
}
