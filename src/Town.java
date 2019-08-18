import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author benth
 *
 */
public class Town implements Comparable<Town> 
{
	private String townName;
	private ArrayList<Road> adjTowns = new ArrayList();
	private Town backPath;
	private int wt;
	
	/**
	 * Consructor
	 */
	public Town() 
	{
		townName = "";
	}
	
	/**
	 * Copy Consructor
	 */
	public Town(Town e) 
	{
		townName = e.getName();
		adjTowns = e.getAdjacentTowns();
		backPath = e.getBackpath();
		wt = e.getWt();
	}
	
	/**
	 * Paramiterized Consructor
	 */
	public Town(String name) 
	{
		townName = name;
	}
	
	/**
	 * Sets the name of the town 
	 * @param name
	 */
	public void setTownName(String name) 
	{
		townName = name;
	}
	
	/**
	 * 
	 * @return returns the name of the town
	 */
	public String getName() 
	{
		return townName;
	}
	
	/**
	 * Sets the BackPath field to later find the path back to the source town
	 * @param e
	 */
	public void setBackPath(Town e) 
	{
		backPath = e;
	}
	
	/**
	 * returns the backpath
	 * @return backTrack
	 */
	public Town getBackpath() 
	{
		return backPath;
	}
	
	/** 
	 * @return reurns the list of adjacent roads
	 */
	public ArrayList<Road> getAdjacentTowns()
	{
		return adjTowns;
	}
	
	/**
	 * sets weight
	 * @param e
	 */
	public void setWt(int e) 
	{
		wt = e; 
	}
	
	/**
	 * @return the weight field
	 */
	public int getWt() 
	{
		return wt;
	}
	
	/**
	 *adds a road to the adjacent towns 
	 * @param v
	 */
	public void addAdjacentTowns(Road v)
	{
		Road flippedRoad;
		if(v.getDestination().getName().equals(townName)) {// check if road is in correct orientation and flips it if not
			flippedRoad = new Road(v.getDestination(),v.getStartPoint(), v.getDistance(), v.getName());
			adjTowns.add(flippedRoad);
		}else
			adjTowns.add(v);
	}
	
	/**
	 * Compares the name of the town towns 
	 * @return 0 if they have the same name;
	 */
	public int compareTo(Town o) 
	{
		// TODO Auto-generated method stub
		return townName.compareToIgnoreCase(o.getName());
	}
	
	/**
	 * compare the names 
	 * @return true if the names are the same
	 */
	public boolean equals(Object o) 
	{
		Town testTown = (Town) o;
		return townName.equalsIgnoreCase(testTown.getName());
	}
	
	/**
	 * To String returns the towns name
	 * @return String
	 */
	public String toString() 
	{
		return townName;
	}

	/**
	 * generates hash code from town name 
	 * @return int 
	 */
	public int hashCode()
	{
		return townName.hashCode();
	}

}
