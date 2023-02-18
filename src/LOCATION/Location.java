package LOCATION;

import java.util.Objects;

public class Location 
{
	private File file;
	private Integer rank;
	
	public Location()
	{
		
	}
	
	public Location(File file, Integer rank)
	{
		this.file = file;
		this.rank = rank;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(file, rank);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return file == other.file && Objects.equals(rank, other.rank);
	}

	public String toString()
	{
		return "file = " + this.file + ", rank = " + this.rank;
	}
}
