package PIECE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import BOARD.Board;
import LOCATION.Location;
import LOCATION.LocationFactory;
import SQUARE.Square;

public class Pawn extends Piece implements Movable 
{
	private boolean isFirstMove = true;

	public Pawn() 
	{
		super();
		this.name = "Pawn";
		this.valuePoint = 1;
	}

	public Pawn(PieceColor pieceColor, Square currentSquare, String name) 
	{
		super(pieceColor, currentSquare, name);
		this.name = "Pawn";
		this.valuePoint = 1;
	}

	public Pawn(PieceColor pieceColor, Square currentSquare) 
	{
		super(pieceColor, currentSquare);
		this.name = "Pawn";
		this.valuePoint = 1;
	}
	

	public Pawn(PieceColor pieceColor) 
	{
		super(pieceColor);
		this.name = "Pawn";
		this.valuePoint = 1;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public List<Location> getValidMoves(Board board) 
	{
		List<Location> moveCandidates = new ArrayList<>();
		Map<Location, Square> squareMap = board.getLocationSquareMap();
		Location current = this.currentSquare.getLocation();
		Integer  move = (this.pieceColor.equals(PieceColor.Light)) ? 1:-1;
		moveCandidates.add(LocationFactory.build(current, 0, move));
		if(isFirstMove)
			moveCandidates.add(LocationFactory.build(current,0, 2* move));
		moveCandidates.add(LocationFactory.build(current, move, move));
		moveCandidates.add(LocationFactory.build(current, -move, move));
		
		//filter valid moves in moveCandidates
		List<Location> validMoves = new ArrayList<>();
		for(Location key : moveCandidates)
		{
			if(squareMap.containsKey(key))
			{
				Square temp = squareMap.get(key);
				if(temp.getLocation().getFile().equals(this.currentSquare.getLocation().getFile()))
				{
					if(temp.getIsOccupied())
						continue;
					Location front = LocationFactory.build(current, 0, move);
					if(squareMap.get(front).getIsOccupied())
						continue;
				}
				else
				{
					if(temp.getIsOccupied())
					{
						if(temp.getCurrentPiece().pieceColor.equals(this.pieceColor))
							continue;
					}
					else
						continue;
				}
				validMoves.add(key);
			}
		}
		return validMoves;
	}

	@Override
	public List<Location> getValidMoves(Board board, Square square) 
	{
		return null;
	}

	@Override
	public void makeMove(Square nextSquare)
	{
		if(isFirstMove)
			isFirstMove = false;
		this.currentSquare.setIsOccupied(false);
		this.setCurrentSquare(nextSquare);
		nextSquare.setCurrentPiece(this);
		nextSquare.setIsOccupied(true);
		
		//Promoting when the pawn is at the opposite edge.
		if(this.pieceColor.equals(PieceColor.Light) && this.currentSquare.getLocation().getRank() == 8)
			Promoting(this.pieceColor,nextSquare);
		else if(this.pieceColor.equals(PieceColor.Dark) && this.currentSquare.getLocation().getRank() == 1)
			Promoting(this.pieceColor,nextSquare);
	}
	
	private Piece Promoting(PieceColor color, Square square)
	{
		System.out.println("Promote to: ");
		System.out.println("1. Queen: ");
		System.out.println("2. Rook: ");
		System.out.println("3. Bishop: ");
		System.out.println("4. Knight: ");
		System.out.println("5. Not promote: ");
		try (Scanner sc = new Scanner(System.in)) {
			Integer prom = sc.nextInt();
			if(prom == 1)
				return null;
			else if(prom == 2)
				return null;
			else if(prom == 3)
				return null;
			else if(prom == 4)
				return null;
			else 
				return this;
		}
					
	}
	

}
