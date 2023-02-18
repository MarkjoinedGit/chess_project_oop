package PIECE;

import java.util.List;

import BOARD.Board;
import LOCATION.Location;
import SQUARE.Square;

public interface Movable 
{
	public List<Location> getValidMoves(Board board);
	public List<Location> getValidMoves(Board board, Square square);
	public void makeMove(Square nextSquare);
}
