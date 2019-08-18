import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * TownGraphManager Class
 * @author benth
 */
public class TownGraphManager implements TownGraphManagerInterface 
{

	/**
	 * town graph data structure
	 */
	private Graph townGraph;
	
	/**
	 * constructor
	 */
	public TownGraphManager() 
	{
		townGraph = new Graph();
	}

	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	public boolean addRoad(String town1, String town2, int weight, String roadName) 
	{
		//finds specified vertex objects in the graph and sets the equal to start and destination Town objects
		Town start = null;
		Town destination = null;
		if(townGraph.containsVertex(new Town(town1)) && townGraph.containsVertex(new Town(town2)) && !town1.equalsIgnoreCase(town2)) 
		{
			start = townGraph.getVertex(new Town(town1));
			destination = townGraph.getVertex(new Town(town2));
			townGraph.addEdge(start, destination, weight, roadName);
			return true;
		}
		return false;
	}

	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	public String getRoad(String town1, String town2) 
	{
		if(townGraph.containsEdge(new Town(town1), new Town(town2))) 
		{
			return townGraph.getEdge(new Town(town1), new Town(town2)).getName();
		}
		return null;
	}

	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	public boolean addTown(String v) 
	{
		return townGraph.addVertex(new Town(v));
	}

	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town is in the graph, false if not
	 */
	public boolean containsTown(String v) 
	{
		return townGraph.containsVertex(new Town(v));
	}

	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	public boolean containsRoadConnection(String town1, String town2) 
	{
		return townGraph.containsEdge(new Town(town1), new Town(town2));
	}

	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	public ArrayList<String> allRoads() 
	{
		ArrayList<String> sortedRoadList = new ArrayList();
		for(Road road : townGraph.edgeSet()) 
		{
			sortedRoadList.add(road.getName());
		}
		Collections.sort(sortedRoadList,String.CASE_INSENSITIVE_ORDER);
		
		return sortedRoadList;
	}

	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	public boolean deleteRoadConnection(String town1, String town2, String road) 
	{
		if(townGraph.removeEdge(new Town(town1), new Town(town2), 0, road) != null) 
		{
			return true;
		}
		else
			return false;
	}

	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	public boolean deleteTown(String v) 
	{
		return townGraph.removeVertex(new Town(v));
	}

	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	public ArrayList<String> allTowns() 
	{
		ArrayList<String> sortedTownList = new ArrayList();
		for(Town town : townGraph.vertexSet()) 
		{
			sortedTownList.add(town.getName());
		}
		Collections.sort(sortedTownList, String.CASE_INSENSITIVE_ORDER);
		return sortedTownList;
	}

	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	public ArrayList<String> getPath(String town1, String town2) 
	{
		Town start = null;
		Town destination = null;
		ArrayList<String> path = null;
		if(townGraph.containsVertex(new Town(town1)) && townGraph.containsVertex(new Town(town2)) || !town1.equalsIgnoreCase(town2)) 
		{
			start = townGraph.getVertex(new Town(town1));
			destination = townGraph.getVertex(new Town(town2));
			path  = townGraph.shortestPath(start,destination);
		}else		
			return null;
		
		/*if(path.size() == 0) {
			return null;
		}*/
		
		return path;
	}

}
