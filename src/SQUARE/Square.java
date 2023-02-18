package SQUARE;

import java.util.Objects;

import LOCATION.Location;
import PIECE.Piece;

public class Square 
{
	private SquareColor squareColor;
	private Location location;
	private Boolean isOccupied;
	private Piece currentPiece;
	
	public Square() 
	{
		
	}

	public Square(SquareColor squareColor, Location location, Boolean isOccupied) 
	{
		this.squareColor = squareColor;
		this.location = location;
		this.isOccupied = isOccupied;
	}

	public Square(SquareColor squareColor, Location location)
	{
		this.squareColor = squareColor;
		this.location = location;
		this.isOccupied = false;

	}

	public SquareColor getSquareColor() 
	{
		return squareColor;
	}

	public void setSquareColor(SquareColor squareColor) 
	{
		this.squareColor = squareColor;
	}

	public Location getLocation() 
	{
		return location;
	}

	public void setLocation(Location location) 
	{
		this.location = location;
	}

	public Boolean getIsOccupied() 
	{
		return isOccupied;
	}

	public void setIsOccupied(Boolean isOccupied) 
	{
		this.isOccupied = isOccupied;
	}

	public Piece getCurrentPiece() {
		return currentPiece;
	}

	public void setCurrentPiece(Piece currentPiece) {
		this.currentPiece = currentPiece;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isOccupied, location, squareColor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Square other = (Square) obj;
		return Objects.equals(isOccupied, other.isOccupied) && Objects.equals(location, other.location)
				&& squareColor == other.squareColor;
	}
	
	public void reset()
	{
		this.isOccupied = false;
		this.currentPiece = null;
	}
}
