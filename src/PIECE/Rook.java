package PIECE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import BOARD.Board;
import LOCATION.Location;
import LOCATION.LocationFactory;
import SQUARE.Square;

public class Rook extends Piece implements Movable 
{
	

	public Rook() 
	{
		super();
		this.name = "Rook";
		this.valuePoint = 2;
	}

	public Rook(PieceColor pieceColor, Square currentSquare, String name) 
	{
		super(pieceColor, currentSquare, name);
		this.name = "Rook";
		this.valuePoint = 2;
	}

	public Rook(PieceColor pieceColor, Square currentSquare) 
	{
		super(pieceColor, currentSquare);
		this.name = "Rook";
		this.valuePoint = 2;
	}

	public Rook(PieceColor pieceColor) 
	{
		super(pieceColor);
		this.name = "Rook";
		this.valuePoint = 2;
	}

	@Override
	public String toString() 
	{
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public List<Location> getValidMoves(Board board) 
	{
		List<Location> moveCandidates = new ArrayList<Location>();
		Map<Location,Square> squareMap = board.getLocationSquareMap();
		Location current = this.currentSquare.getLocation();
		getFileCandidates(moveCandidates,squareMap,current,1);
		getFileCandidates(moveCandidates,squareMap,current,-1);
		getRankCandidates(moveCandidates,squareMap,current,1);
		getRankCandidates(moveCandidates,squareMap,current,-1);

		return moveCandidates;
		
	}

	@Override
	public List<Location> getValidMoves(Board board, Square square) 
	{
		List<Location> moveCandidates = new ArrayList<Location>();
		Map<Location,Square> squareMap = board.getLocationSquareMap();
		Location current = square.getLocation();
		getFileCandidates(moveCandidates,squareMap,current,1);
		getFileCandidates(moveCandidates,squareMap,current,-1);
		getRankCandidates(moveCandidates,squareMap,current,1);
		getRankCandidates(moveCandidates,squareMap,current,-1);

		return moveCandidates;
	}
	
	private void getFileCandidates(List<Location> moveCandidates, Map<Location, Square> squareMap, Location current, Integer fileOffSet)
	{
		Location next = LocationFactory.build(current, fileOffSet, 0);
		while(squareMap.containsKey(next))
		{
			if(squareMap.get(next).getIsOccupied())
			{
				if(!this.pieceColor.equals(squareMap.get(next).getCurrentPiece().getPieceColor()))
					moveCandidates.add(next);
				break;
			}
			moveCandidates.add(next);
			next = LocationFactory.build(next, fileOffSet, 0);
		}
	}
	
	private void getRankCandidates(List<Location> moveCandidates, Map<Location, Square> squareMap, Location current, Integer rankOffSet)
	{
		Location next = LocationFactory.build(current, 0, rankOffSet);
		while(squareMap.containsKey(next))
		{
			if(squareMap.get(next).getIsOccupied())
			{
				if(!this.pieceColor.equals(squareMap.get(next).getCurrentPiece().getPieceColor()))
					moveCandidates.add(next);
				break;
			}
			moveCandidates.add(next);
			next=LocationFactory.build(next, 0, rankOffSet);
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
