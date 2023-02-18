package PIECE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import BOARD.Board;
import LOCATION.Location;
import LOCATION.LocationFactory;
import SQUARE.Square;

public class Knight extends Piece implements Movable 
{

	public Knight() 
	{
		super();
		this.name = "Horse";
		this.valuePoint = 2;
	}

	public Knight(PieceColor pieceColor, Square currentSquare, String name) 
	{
		super(pieceColor, currentSquare, name);
		this.name = "Horse";
		this.valuePoint = 2;
		// TODO Auto-generated constructor stub
	}

	public Knight(PieceColor pieceColor, Square currentSquare) 
	{
		super(pieceColor, currentSquare);
		this.name = "Horse";
		this.valuePoint = 2;
		// TODO Auto-generated constructor stub
	}

	public Knight(PieceColor pieceColor) 
	{
		super(pieceColor);
		this.name = "Horse";
		this.valuePoint = 2;
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Location> getValidMoves(Board board) 
	{
		List<Location> moveCandidates = new ArrayList<Location>();
		Map<Location,Square> squareMap = board.getLocationSquareMap();
		Location current = this.currentSquare.getLocation();
		getMove(moveCandidates, squareMap, current, 1,-2);
		getMove(moveCandidates, squareMap, current, 2,-1);
		getMove(moveCandidates, squareMap, current, 2,1);
		getMove(moveCandidates, squareMap, current, 1,2);
		getMove(moveCandidates, squareMap, current, -1,-2);
		getMove(moveCandidates, squareMap, current, -2,-1);
		getMove(moveCandidates, squareMap, current, -2,1);
		getMove(moveCandidates, squareMap, current, -1,2);
		return moveCandidates;
	}

	@Override
	public List<Location> getValidMoves(Board board, Square square) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	private void getMove(List<Location> moveCandidates, Map<Location, Square> squareMap, Location current, Integer fileOffSet, Integer rankOffSet)
	{
		Location next = LocationFactory.build(current, fileOffSet, rankOffSet);
		if(squareMap.containsKey(next))
		{
			if(squareMap.get(next).getIsOccupied())
			{
				if(this.pieceColor.equals(squareMap.get(next).getCurrentPiece().getPieceColor()))
					return;
			}
			moveCandidates.add(next);
		}
	}
	
	@Override
	public void makeMove(Square nextSquare) 
	{	
		this.currentSquare.setIsOccupied(false);
		this.setCurrentSquare(nextSquare);
		nextSquare.setCurrentPiece(this);
		nextSquare.setIsOccupied(true);
	}

}
