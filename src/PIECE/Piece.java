package PIECE;

import java.util.List;
import java.util.Objects;

import BOARD.Board;
import LOCATION.Location;
import SQUARE.Square;

public abstract class Piece implements Movable
{
	protected PieceColor pieceColor;
	protected Square currentSquare;
	protected String name;
	protected Integer valuePoint;
	
	public Piece()
	{
		
	}
	
	public Piece(PieceColor pieceColor) 
	{
		super();
		this.pieceColor = pieceColor;
	}

	public Piece(PieceColor pieceColor, Square currentSquare) 
	{
		this.pieceColor = pieceColor;
		this.currentSquare = currentSquare;
	}
	
	public Piece(PieceColor pieceColor, Square currentSquare, String name) 
	{
		this.pieceColor = pieceColor;
		this.currentSquare = currentSquare;
		this.name = name;
	}
	

	public PieceColor getPieceColor() 
	{
		return pieceColor;
	}

	public String getName() 
	{
		return name;
	}

	public Square getCurrentSquare()
	{
		return currentSquare;
	}

	public void setCurrentSquare(Square currentSquare) 
	{
		this.currentSquare = currentSquare;
	}
	
	public Integer getValuePoint() {
		return valuePoint;
	}

	public void setValuePoint(Integer valuePoint) 
	{
		this.valuePoint = valuePoint;
	}

	@Override
	public int hashCode() {
		return Objects.hash(currentSquare, name, pieceColor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		return Objects.equals(currentSquare, other.currentSquare) && Objects.equals(name, other.name)
				&& pieceColor == other.pieceColor;
	}

	@Override
	public String toString() {
		return "Piece [pieceColor=" + pieceColor + ", currentSquare=" + currentSquare + ", name=" + name + "]";
	}
	
	public boolean nameEqual( Piece b)
	{
		if(this.name.equals(b.name))
			return true;
		return false;
	}

	public abstract List<Location> getValidMoves(Board board);

	public abstract List<Location> getValidMoves(Board board, Square square);

	public abstract void makeMove(Square nextSquare);
	
	public boolean isInDanger(Board board)
	{
		return false;
	}
}
