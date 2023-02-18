package PIECE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import BOARD.Board;
import LOCATION.Location;
import LOCATION.LocationFactory;
import SQUARE.Square;

public class Bishop extends Piece implements Movable 
{

	public Bishop() {
		super();
		this.name = "Bishop";
		this.valuePoint = 2;
	}

	public Bishop(PieceColor pieceColor, Square currentSquare, String name) 
	{
		super(pieceColor, currentSquare, name);
		this.name = "Bishop";
		this.valuePoint = 2;
		// TODO Auto-generated constructor stub
	}

	public Bishop(PieceColor pieceColor, Square currentSquare) 
	{
		super(pieceColor, currentSquare);
		this.name = "Bishop";
		this.valuePoint = 2;
		// TODO Auto-generated constructor stub
	}

	public Bishop(PieceColor pieceColor) 
	{
		super(pieceColor);
		this.name = "Bishop";
		this.valuePoint = 2;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public List<Location> getValidMoves(Board board) 
	{
		List<Location> moveCandidates = new ArrayList<Location>();
		Map<Location,Square> squareMap = board.getLocationSquareMap();
		Location current = this.currentSquare.getLocation();
		getMove(moveCandidates, squareMap, current, -1,1);
		getMove(moveCandidates, squareMap, current, 1,1);
		getMove(moveCandidates, squareMap, current, -1,-1);
		getMove(moveCandidates, squareMap, current, 1,-1);
		return moveCandidates;

	}

	@Override
	public List<Location> getValidMoves(Board board, Square square) 
	{
		List<Location> moveCandidates = new ArrayList<Location>();
		Map<Location,Square> squareMap = board.getLocationSquareMap();
		Location current = square.getLocation();
		getMove(moveCandidates, squareMap, current, -1,1);
		getMove(moveCandidates, squareMap, current, 1,1);
		getMove(moveCandidates, squareMap, current, -1,-1);
		getMove(moveCandidates, squareMap, current, 1,-1);
		return moveCandidates;
	}
	
	private void getMove(List<Location> moveCandidates, Map<Location, Square> squareMap, Location current, Integer fileOffSet, Integer rankOffSet)
	{
		Location next = LocationFactory.build(current, fileOffSet, rankOffSet);
		while(squareMap.containsKey(next))
		{
			if(squareMap.get(next).getIsOccupied())
			{
				if(!this.pieceColor.equals(squareMap.get(next).getCurrentPiece().getPieceColor()))
					moveCandidates.add(next);
				break;
			}
			moveCandidates.add(next);
			next = LocationFactory.build(next, fileOffSet, rankOffSet);
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
