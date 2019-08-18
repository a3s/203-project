/**
 * 
 * @author benth
 *
 */
public class Road implements Comparable<Road> 
{
	private int distance;
	private String streetName;
	private Town startPoint, destination;
	
	/**
	 * Constructor
	 */
	public Road() 
	{
		distance = 0;
		streetName = "";
		startPoint = null;
		destination = null;
	}
	
	/**
	 * paramaterized constructor
	 * @param start, starting town
	 * @param end, destination town
	 * @param dist, distance from the two towns
	 * @param name, name of the road
	 */
	public Road(Town start, Town end, int dist, String name)
	{
		streetName = name;
		distance = dist;
		startPoint = start;
		destination = end;
	}
	
	/**
	 * sets the name of the road
	 * @param name
	 */
	public void setName(String name) 
	{
		streetName = name;
	}
	
	/**
	 * sets the distance of the road
	 * @param dist
	 */
	public void setDistance(int dist) 
	{
		distance = dist;
	}
	
	/**
	 * sets the starting town
	 * @param start
	 */
	public void setStartPoint(Town start) 
	{
		startPoint = start;
	}
	
	/**
	 * set the destination town
	 * @param end
	 */
	public void setdestination(Town end) 
	{
		destination = end;
	}
	
	/**
	 * set the name of the road
	 * @return name of road
	 */
	public String getName() 
	{
		return streetName;
	}
	
	/**
	 * gets the distance of the road
	 * @return int distance
	 */
	public int getDistance() 
	{
		return distance;
	}
	
	/**
	 * gets the starting town
	 * @return Town object
	 */
	public Town getStartPoint() 
	{
		return startPoint;
	}
	
	/**
	 * get the destination town
	 * @return town objext
	 */
	public Town getDestination() 
	{
		return destination;
	}
	
	/**
	 * compares two raods by name, 
	 * @returns 0 is they are the same
	 */
	public int compareTo(Road o) 
	{
		return streetName.compareToIgnoreCase(o.getName());
	}
	
	/**
	 * compares two roads by comparing the two endpoints
	 * @return boolean
	 */
	public boolean equals(Object o) 
	{
		Road testRoad = (Road) o;
		if(startPoint.equals(testRoad.getStartPoint()) && destination.equals(testRoad.getDestination()) ||
				startPoint.equals(testRoad.getDestination()) && destination.equals(testRoad.getStartPoint())) 
		{
			return true;
		}
		else
			return false;
	}
	
	/**
	 * generates Hashcode from road name 
	 * @return int
	 */
	public int hashCode()
	{
		return streetName.hashCode();
	}
	
	/**
	 * toString returns the street name
	 * @return string
	 */
	public String toString() 
	{
		return streetName;
	}
}
