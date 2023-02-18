package PIECE;

import java.util.ArrayList;
import java.util.List;

import BOARD.Board;
import LOCATION.Location;
import SQUARE.Square;

public class Queen extends Piece implements Movable 
{
	private Movable bishop;
	private Movable rook;
	
	public Queen() 
	{
		super();
		this.name = "Queen";
		this.valuePoint = 3;
		// TODO Auto-generated constructor stub
	}

	public Queen(PieceColor pieceColor, Square currentSquare, String name) 
	{
		super(pieceColor, currentSquare, name);
		this.name = "Queen";
		this.valuePoint = 3;
		// TODO Auto-generated constructor stub
	}

	public Queen(PieceColor pieceColor, Square currentSquare) 
	{
		super(pieceColor, currentSquare);
		this.name = "Queen";
		this.valuePoint = 3;
		// TODO Auto-generated constructor stub
	}

	public Queen(PieceColor pieceColor) 
	{
		super(pieceColor);
		this.name = "Queen";
		this.valuePoint = 3;
		// TODO Auto-generated constructor stub
	}
	
	

	public Queen(PieceColor pieceColor, Movable bishop, Movable rook) {
		super(pieceColor);
		this.bishop = bishop;
		this.rook = rook;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public List<Location> getValidMoves(Board board)
	{
		this.bishop = new Bishop(this.pieceColor,this.currentSquare);
		this.rook = new Rook(this.pieceColor,this.currentSquare);
		List<Location> moveCandidates = new ArrayList<Location>();
		moveCandidates.addAll(bishop.getValidMoves(board,this.currentSquare));
		moveCandidates.addAll(rook.getValidMoves(board,this.currentSquare));
		return moveCandidates;
	}

	@Override
	public List<Location> getValidMoves(Board board, Square square) 
	{
		// TODO Auto-generated method stub
		return null;
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
