package BOARD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import LOCATION.File;
import LOCATION.Location;
import PIECE.Piece;
import PIECE.PieceColor;
import PIECE.PieceFactory;
import SQUARE.Square;
import SQUARE.SquareColor;

public class Board 
{

	private final Integer Board_length = 8;
	Square[][] boardSquare = new Square[Board_length][Board_length];
	private Map<Location, Square> locationSquareMap;
	private List<Piece> lightPieces = new ArrayList<>();
	private List<Piece> darkPieces = new ArrayList<>();
	private Piece lightKing;
	private Piece darkKing;

	
	public Board()
	{
		locationSquareMap = new HashMap<>();	
		
		Map<Location, Piece> pieces = PieceFactory.getPieces();
		lightKing = pieces.get(new Location(File.E,1));
		darkKing = pieces.get(new Location(File.E,8));
		for(int i = 0; i < boardSquare.length; i++)
		{
			int column = 0;
			SquareColor currentColor = (i%2==0)?SquareColor.Light:SquareColor.Dark;
			for(File file : File.values())
			{
				Square newSquare = new Square(currentColor, new Location(file,Board_length - i));
				if(pieces.containsKey(newSquare.getLocation()))
				{
					Piece piece  = pieces.get(newSquare.getLocation());
					newSquare.setCurrentPiece(piece);
					newSquare.setIsOccupied(true);
					piece.setCurrentSquare(newSquare);
					if(piece.getPieceColor().equals(PieceColor.Light))
						lightPieces.add(piece);
					else
						darkPieces.add(piece);
				}
				boardSquare[i][column] = newSquare;
				this.locationSquareMap.put(newSquare.getLocation(), newSquare);
				currentColor = (currentColor.equals(SquareColor.Light)) ? SquareColor.Dark : SquareColor.Light;
				column++;
			}
		}
	}

	public Map<Location, Square> getLocationSquareMap() 
	{
		return locationSquareMap;
	}
	
	
	
	public Piece getLightKing() {
		return lightKing;
	}

	public Piece getDarkKing() {
		return darkKing;
	}

	public void setLightKing(Piece lightKing) {
		this.lightKing = lightKing;
	}

	public void setDarkKing(Piece darkKing) {
		this.darkKing = darkKing;
	}
	

	public List<Piece> getLightPieces() {
		return lightPieces;
	}

	public List<Piece> getDarkPieces() {
		return darkPieces;
	}

	public void setLightPieces(List<Piece> lightPieces) {
		this.lightPieces = lightPieces;
	}

	public void setDarkPieces(List<Piece> darkPieces) {
		this.darkPieces = darkPieces;
	}

	public void print_board()
	{
		for(int i = 0; i < boardSquare.length; i++)
		{
			System.out.print(Board_length - i + "   ");
			for(int  j = 0; j <  boardSquare[i].length; j++)
			{
				if(boardSquare[i][j].getIsOccupied())
				{
					Piece piece = boardSquare[i][j].getCurrentPiece();
					System.out.print( piece.getName().charAt(0) + (piece.getPieceColor().equals(PieceColor.Light)?"l":"d") + "  ");
				}
				else
					System.out.print("-   ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.print("    ");
		for(File file:File.values())
			System.out.print(file.name() + "   ");
		System.out.println();
	}
	
}
