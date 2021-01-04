import java.util.*;

import java.io.*;
public class RoadTrip {
	HashMap<String, Location> locationEdges = new HashMap<String, Location>();
	
	BufferedReader reader;
	List<String> roads = new ArrayList<String>();
	List<String> attractions = new ArrayList<String>();

	public static void main(String args[]) {
		RoadTrip trip = new RoadTrip();

		trip.read();
		trip.vertices();
		trip.addattractions();
		trip.print();
	}
	
	public void vertices() { 
		for (int i = 0; i < roads.size(); i++) {
			String location1, location2;
			int miles, minutes;
			String [] roadsArray = roads.get(i).split(",");
			location1 = roadsArray[0];
			location2 = roadsArray[1];
			miles = Integer.parseInt(roadsArray[2]);
			minutes = Integer.parseInt(roadsArray[3]);
			
			if(!locationEdges.containsKey(location1)) {
				Location loc = new Location(location1); 
				loc.createEdge(location2, miles, minutes);
				locationEdges.put(loc.getName(), loc);
				if(!locationEdges.containsKey(location2)) {
					Location loc2 = new Location(location2); 
					loc2.createEdge(location1, miles, minutes); 
					locationEdges.put(loc2.getName(), loc2); 
				}
				else if(locationEdges.containsKey(location2)) {
					Location loc3 = locationEdges.get(location2); 
					loc3.createEdge(location1, miles, minutes); 
					locationEdges.replace(loc3.getName(), loc3); 
				}
			}
			else if(locationEdges.containsKey(location1)) { 
				Location loc4 = locationEdges.get(location1); 
				loc4.createEdge(location2, miles, minutes); 
				locationEdges.replace(loc4.getName(), loc4);
				if(!locationEdges.containsKey(location2)) {
					Location loc5 = new Location(location2); 
					loc5.createEdge(location1, miles, minutes); 
					locationEdges.put(loc5.getName(), loc5); 
				}
				else if(locationEdges.containsKey(location2)) {
					Location loc6 = locationEdges.get(location2);
					loc6.createEdge(location1, miles, minutes); 
					locationEdges.replace(loc6.getName(), loc6); 
			}
		}
	}

	
	public void addattractions() {
		for(int i = 1; i < attractions.size(); i++) {
			String attractions2[] = attractions.get(i).split(",");
			Location location = locationEdges.get(attractions2[1]);
			location.attractions.add(attractions2[0]);
			locationEdges.replace(location.getName(), location);
		}
	}

	public void print() {
		for(String location: locationEdges.keySet()) {

			System.out.println("Location: "+ location);
			for(int i = 0; i <locationEdges.get(location).edges.size(); i++) {
				System.out.println(locationEdges.get(location).edges.get(i).name + " miles: " + locationEdges.get(location).edges.get(i).miles);
			}
			for(int i = 0; i < locationEdges.get(location).attractions.size(); i++) {
				System.out.println("Attraction : "+ locationEdges.get(location).attractions.get(i));
			}
		}
	}

	public void read() {
		reader = new BufferedReader(new FileReader("C:/Users/dario/Desktop/RTrip/roads.csv"));
		String line;

		while ((line = reader.readLine()) != null) {
			roads.add(line);
		}

		reader = new BufferedReader(new FileReader("C:/Users/dario/Desktop/RTrip/attractions.csv"));

		while ((line = reader.readLine()) != null) {
			attractions.add(line);
		}

		reader.close();
	}
	
}
