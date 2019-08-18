import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Town Graph dataStructure
 * 
 * The root interface in the graph hierarchy. A mathematical graph-theory graph
 * object G(V,E) contains a set V of vertices and a set
 * E of edges. Each edge e=(v1,v2) in E connects vertex v1 to vertex v2.
 *
 * Through generics, a graph can be typed to specific classes for vertices
 * V and edges E<T>. Such a graph can contain
 * vertices of type V and all sub-types and Edges of type
 * E and all sub-types.
 *
 * @author benth
 */
public class Graph implements GraphInterface<Town,Road>
{
	Set<Town> towns;
	Set<Road> roads;
	
	Set<Town> closed;
	Set<Town> open;
	ArrayList<String> shortestPath = new ArrayList<String>();
	
	/** Constructor
	 */
	Graph()
	{
		towns = new HashSet();
		roads = new HashSet();
		closed = new HashSet();
	}

	/**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	public Road getEdge(Town sourceVertex, Town destinationVertex) 
	{
		if(!containsEdge(sourceVertex,destinationVertex) || sourceVertex == null || destinationVertex == null) 
		{
			return null;
		}
		else if(containsEdge(sourceVertex,destinationVertex)) 
		{
			for (Road road : roads)
			{
				Road testRoad = new Road(sourceVertex, destinationVertex,0,"");
				if(road.equals(testRoad)) 
				{
					return road;
				}
			}
		}

		return null;
	}
	
	/**
     * Returns an Vertex in the Graph if such a vertex exist in this graph. Otherwise returns
     * null.
     * @param Vertex object of type Town.
     * @return an Vertex in the graph.
     */
	public Town getVertex(Town vertex) throws IllegalArgumentException{
		for(Town town: towns) 
		{
			if(town.equals(vertex)) 
			{
				return town;
			}
		}
		return null;
	}

	
	/**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) throws IllegalArgumentException 
	{
		Road newRoad = new Road(sourceVertex,destinationVertex,weight, description);

			if(towns.contains(sourceVertex) && towns.contains(destinationVertex)) 
			{
				//adds edge to sourceVertex and destinationVertex adjacent list 
				getVertex(sourceVertex).addAdjacentTowns(newRoad);
				getVertex(destinationVertex).addAdjacentTowns(newRoad);
				//adds edge to road set
				roads.add(newRoad);
				return newRoad;
			}else if(sourceVertex == null || destinationVertex == null || description ==null) 
			{
				return null;
			}
			else
				throw new IllegalArgumentException();
	}


	/**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
	public boolean addVertex(Town v) throws NullPointerException
	{
		if(v == null) {
			throw new NullPointerException();
		}
		if(towns.contains(v)) 
		{
			return false;
		}
		else
			towns.add(v);
		
		return true;
	}


	 /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) 
	{
		Road testRoad = new Road(sourceVertex, destinationVertex,0,"");
		
		if(containsVertex(sourceVertex) && containsVertex(destinationVertex)) 
		{
			for (Road road : roads)
			{
				if(road.equals(testRoad)) 
				{
					return true;
				}
			}
			return false;
		}
		else
			return false;
	}

    /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
	public boolean containsVertex(Town v)
	{
		Town testTown = v;
		if(towns.contains(testTown)) 
		{
			return true;
		}
		else
			return false;
	}

	 /**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	public Set<Road> edgeSet() 
	{
		return roads;
	}

	 /**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	public Set<Road> edgesOf(Town vertex) throws NullPointerException, IllegalArgumentException
	{
		Set<Road> adjVertex = new HashSet<Road>();
		if(containsVertex(vertex)) 
		{
			adjVertex = new HashSet<Road>(getVertex(vertex).getAdjacentTowns());
			return adjVertex;
		}else if(!containsVertex(vertex)) {
			throw new IllegalArgumentException();
		}else if(vertex == null) {
			throw new NullPointerException();
		}
		return adjVertex;
	}

	 /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) 
	{
		Road testRoad = new Road(sourceVertex, destinationVertex, weight, description);
		if(roads.contains(testRoad)) 
		{
			//removes edge from sourceVertex and destinationVertex adjacent list
			getVertex(sourceVertex).getAdjacentTowns().remove(testRoad);
			getVertex(destinationVertex).getAdjacentTowns().remove(testRoad);
			//removes edge from roads set
			roads.remove(testRoad);
			return testRoad;
		}
		else
			return null;
		
	}

	  /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	public boolean removeVertex(Town v) 
	{
		if(towns.contains(v)) 
		{
			for(Road road : roads) 
			{
				if(road.getStartPoint().equals(v) || road.getDestination().equals(v))//removes all roads in the roads set that contain specified town as a start or end point 
				{
					roads.remove(new Road(road.getStartPoint(), road.getDestination(),0, ""));
				}
			}
			towns.remove(v);//removes specified town for towns set
			return true;
		}
		else
			return false;

	}

	/**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
	public Set<Town> vertexSet() 
	{
		Set<Town> town = towns;
		return town;
	}
	
	 /**
	  * Shallow Copys a set<Town>
	  * @param e
	  * @return HashSet<Town>
	  */
	public HashSet<Town> copy(Set<Town> e)
	{
		HashSet<Town> copy = new HashSet();
		
		for(Town iter : e) 
		{
			Town newTown = new Town(iter);
			copy.add(newTown);
		}
		return copy;
	}
	
	/**
     *returns a set of adjVertices of a specified Vertex in a specified set
     * @param Set of adjacent Vertices of town
     * 
     */
	public Set<Town> getAdjVerticesInSet(Town town, Set<Town> open)
	{
		Set<Town> adjList = new HashSet();
	
		if(containsVertex(town)) 
		{
			Set<Road> set = edgesOf(town);//gets list of vertexs adjacent from specified town
			for(Road adj : set) 
			{
				Town newTown = null;

				if(open.contains(adj.getDestination()) && !closed.contains(adj.getDestination())) //if adjacent town is in open set and not in closed
				{
					newTown = adj.getDestination();
					newTown.setWt(adj.getDistance());
					adjList.add(newTown);
				}
			}
		}
		
		return adjList;
	}
	
	/**
	 * Recursive method that adds to "path" each town name form a  destination 
	 * back thru the unique shortest path to the source town name.
	 * assumes each town's backpath is already set
	 * @param source a town object, the start point 
	 * @param town a town object, a destination point
	 */
	public void getStrBackPath(Town source, Town town)
	{
		Town start = source;
		Town end = town;
		
		if(end != null && start != null && !end.equals(start)) 
		{
			Road road = getEdge(end,end.getBackpath());
			getStrBackPath(start,end.getBackpath());//recursively goes through the backpath filed in Town object to get back to sourceVertex and prints the path  
			if(road!=null) 
			{
				//System.out.println(town.getBackpath().getName() + " via " + road.getName() + " to " + town.getName() + " " + road.getDistance() + " mi");
				//adds string of the path back to the sorceVertex to the shortestPath ArrayList field
				shortestPath.add(town.getBackpath().getName() + " via " + road.getName() + " to " + town.getName() + " " + road.getDistance() + " mi");
			}
		}
		
	}

	/**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     */
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) 
	{
		if(sourceVertex.getAdjacentTowns().size() == 0) {
			return new ArrayList();
		}
		open = copy(towns);//Shallow copys towns set to open set
		closed = new HashSet();
		open.remove(sourceVertex);
		sourceVertex.setWt(0);
		closed.add(sourceVertex);
		sourceVertex.setWt(0);
		sourceVertex.setBackPath(null);
		shortestPath = new ArrayList<String>();
		dijkstraShortestPath(sourceVertex);//sets the path for a minimum spanning tree through the backpath field in town object leading back to sourceVertex
		getStrBackPath(sourceVertex, destinationVertex);//Recursively foes through the backPath field in town object
		open = copy(towns);
		
		
		return shortestPath;
	}
	
	/**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	public void dijkstraShortestPath(Town sourceVertex) 
	{
		boolean isDisjoint = false;

		while(!open.isEmpty() && !isDisjoint) 
		{
			isDisjoint = true;
			int minWt = Integer.MAX_VALUE;
			Town minAdjTown = null;
			for(Town town : closed)// for every town in closed set
			{
				for(Town adjTown : getAdjVerticesInSet(town,open)) // find the town's adjacent vertexes that are in open set 
				{
					int wt = getWtToSource(adjTown,town,sourceVertex);//finds the total distance from adjacent vertex back to source
					if(wt < minWt)//adjacent vertex with smaller path
					{
						minWt = wt;
						minAdjTown = adjTown;
						adjTown.setBackPath(town);
					}
				}
			}
			
			if (minAdjTown!=null) //adjacent vertex with the shorest path out of all 
			{
				minAdjTown.setWt(minWt);
				open.remove(minAdjTown);
				closed.add(minAdjTown);
				isDisjoint = false;
			}
		}
	}
	
	/**
	 * adds the wt of the path to get to specified adjacent town from the source Vertex
	 * @param adjTown
	 * @param town
	 * @param sourceVertex
	 * @return distance to specified adjacent town from source vertex
	 */
	public int getWtToSource(Town adjTown,Town town,Town sourceVertex) 
	{
		//System.out.println("adj town: " + adjTown + " Town: " + town + " source:  " + sourceVertex);
		int adjToTown =0;
		int toSource = 0;
		int wt= 0;
		Town iter = new Town(town);

		while(iter.getBackpath() != null) // Iterates through the backpath field in town and adds the road distance to get back to the sourceTown
		{
			toSource += getEdge(iter, iter.getBackpath()).getDistance();
			iter = iter.getBackpath();
		}

		if(containsEdge(adjTown,town))//gets the distance from town and its adjacent town
		{
			adjToTown = getEdge(adjTown,town).getDistance();
			
		}
		//System.out.println(adjToTown + toSource);
		return adjToTown + toSource;
	}
	

}
