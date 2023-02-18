package LOCATION;

public class LocationFactory 
{
	private static final File[] files = File.values();
	
	public static Location build(Location current, Integer fileOffSet, Integer rankOffSet)
	{
		Integer currentFile = current.getFile().ordinal();
		Integer nextFile = currentFile + fileOffSet;
		Integer nextRank = current.getRank() + rankOffSet;
		
		//Check if nextFile is out of bounds
		//rank = 9 is an invalid value, therefore Map.containKeys cannot find it.
		if(nextFile > 7 || nextFile < 0)
			return new Location(files[currentFile],9);
		
		return new Location(files[nextFile],nextRank);
	}
}
