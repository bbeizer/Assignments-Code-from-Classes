package edu.brandeis.cs12b.pa08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Graph {

	/**
	 * Build the graph from a json file
	 * @param the json filename
	 */
	Set<Vertex> verts = new HashSet<Vertex>();
	int numberOfEdges;

	public Graph(String filename) {
		File file = new File(filename);
		try {
			Scanner reader = new Scanner(file);
			String fileContents = ""; 
			while(reader.hasNextLine()) {
				fileContents += reader.nextLine();
			}
			JSONObject graph = new JSONObject(fileContents);
			JSONArray vertArray = graph.getJSONArray("vertices"); 
			for (int i = 0; i < vertArray.length(); i++) {
				Vertex v = new Vertex(vertArray.get(i).toString());
				verts.add(v);
			} 
			JSONArray edgeArray = graph.getJSONArray("edges");
			numberOfEdges = edgeArray.length();
			for (int i = 0; i <edgeArray.length(); i++) {
				JSONObject edge = edgeArray.getJSONObject(i);
				String vertex1 = edge.getString("v1");
				String vertex2 = edge.getString("v2");
				String weight = edgeArray.getJSONObject(i).getString("weight");
				int result = Integer.parseInt(weight);
				for(Vertex v1 : verts) {
					if (v1.name.equals(vertex1)) {
						for(Vertex v2 : verts) {
							if(v2.name.equals(vertex2)) {
								v1.connections.put(v2, result);	
								v2.connections.put(v1, result);
							}
						}
					}
				}
			}
		}
		catch(FileNotFoundException e){

		}


	}


	/**
	 * Returns a Set of all vertices in the graph. 
	 * @return the Set of vertices.
	 */
	public Set<String> getVertices() {
		Set<String> vertices = new HashSet<String>();
		for (Vertex v : verts) {
			vertices.add(v.name);
		}
		return vertices;
	}

	/**
	 * Return a Set of Vertices that are directly connected to vertex v
	 * @param v the vertex
	 * @return A Set that contains all the vertices that are connected to v
	 */
	public Set<String> getNeighbors(String v) {
		for (Vertex vertex : verts) {
			if(vertex.name.equals(v)) {
				return vertex.getNeighborNames(vertex);
			}
		}
		return null;
	}


	/**
	 * Get the weight of the edge between vertex a and vertex b.
	 * 
	 * @param a one vertex
	 * @param b another vertex 
	 * @return the edge weight or -1 if the edge doesn't exist in the graph.
	 */
	public int getEdgeWeight(String a, String b) {
		for (Vertex vertex : verts) {
			if(vertex.name.equals(a)) {
				for (Vertex v : vertex.connections.keySet()){
					if(v.name.equals(b)) {
						return vertex.connections.get(v);
					}
				}
			}
		}
		return -1;
	}

	/**
	 * Gets the cost of the path (sum of the edge weights)
	 * represented by the passed ArrayList of vertices. 
	 * If the path is invalid, (jumps between non-connected vertices or contains a vertex that doesn't exist)
	 * return -1
	 * @param path a List of vertices
	 * @return the path length
	 */
	public int getPathCost(List<String> path) {
		int sum = 0;
		for(int i = 0; i< path.size()-1; i++) {
			for(Vertex v : verts) {
				if(v.name.equals(path.get(i))) {
					if(getNeighbors(v.name).contains(path.get(i+1))) {
						sum += v.connections.get(v.getVertex(path.get(i+1)));
					}
					if(path.get(i).equals(path.get(i+1))){
						sum += 0;
					}
				} else {
					return -1;
				}
			}
		}
		if(sum == 0) {
			return -1;
		} else {
			return sum;
		}
	}

	/**
	 * @param start the name of the first vertex in the route
	 * @param end the name of the last vertex in the route
	 * @return Any route from start to end. If no route exists, return null
	 */
	public List<String> getRoute(String start, String end){
		HashSet<String> hasVisited = new HashSet<String>();
		Queue<ArrayList<String>> queue = new LinkedList<ArrayList<String>>();
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> b;
		hasVisited.add(start);
		list.add(start);
		queue.add(list);
		while(!queue.isEmpty()) {
			ArrayList<String> temp = queue.remove();
			String s = temp.get(temp.size()-1);
			if(s.equals(end)) {
				return temp;
			}
			Vertex v = getVertex(s);
			Set<String> neighbors = getNeighbors(v.name);
			Iterator<String> i = neighbors.iterator();
			while(i.hasNext()) {		
				String str = i.next();
				if(!hasVisited.contains(str)) {
					b = new ArrayList<String>(temp);
					hasVisited.add(str);
					b.add(str);
					queue.add(b);
				}
			} 
		}
		return null;
	}


	/**
	 * Determines if the graph is a tree or not. 
	 * * @return true if the graph is a tree and false if not.
	 */
	public boolean isTree() {
		if (verts.size() - 1 == numberOfEdges) {
			return true;
		}
		for(Vertex v : verts) {
			if (v.connections.keySet().isEmpty()) {
				return false;
			}
		}
		return false;
	}

	/**
	 * Use Dijkstra's algorithm to find the shortest path from start to end
	 * @param start the name of the first vertex in the route
	 * @param end the name of the last vertex in the route
	 * @return the shortest route from start to end. If no route exists, return null
	 */
	public List<String> getShortestRoute(String start, String end) {
		//TODO Implement me!
		return null;
	}
	public Vertex getVertex(String s) {
		for (Vertex v : verts) {
			if (v.name.equals(s)) {
				return v;
			}
		}
		return null;
	}
}
