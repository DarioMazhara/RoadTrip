import java.util.*;

public class Location {
	String city;
	List<Edge> edges = new ArrayList<Edge>();
	List<String> attractions = new ArrayList<String>();

	public class Edge{
		String name;
		int miles;
		int time;

		public Edge(String name, int miles, int time) {
			this.name = name;
			this.miles = miles;
			this.time = time;
		}
	}

	public Location() {
		city = null;
		edges = new ArrayList<Edge>();
	}

	public Location(String name) {
		city = name;
		edges = new ArrayList<Edge>();
	}

	public Location(String name, ArrayList edges) {
		this.city = name;
		this.edges = new ArrayList<Edge>(edges.size());
		for (int i = 0; i < edges.size(); i++) {
			this.edges.addAll(edges);
		}
	}

	public void createEdge(String name, int miles, int time) {
		Edge newEdge = new Edge(name, miles, time);
		edges.add(newEdge);
	}

	public void setName(String name) {
		this.city = name;
	}

	public String getName() {
		return this.city;
	}

}