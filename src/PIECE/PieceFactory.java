package PIECE;

import java.util.HashMap;
import java.util.Map;

import LOCATION.File;
import LOCATION.Location;

public class PieceFactory 
{
	public static Map<Location, Piece> getPieces()
	{
		Map<Location,Piece> pieces = new HashMap<>();
		
		//rooks
        pieces.put(new Location(File.A, 1), new Rook(PieceColor.Light));
        pieces.put(new Location(File.H, 1), new Rook(PieceColor.Light));
        pieces.put(new Location(File.A, 8), new Rook(PieceColor.Dark));
        pieces.put(new Location(File.H, 8), new Rook(PieceColor.Dark));

        //Knights
        pieces.put(new Location(File.B, 1), new Knight(PieceColor.Light));
        pieces.put(new Location(File.G, 1), new Knight(PieceColor.Light));
        pieces.put(new Location(File.B, 8), new Knight(PieceColor.Dark));
        pieces.put(new Location(File.G, 8), new Knight(PieceColor.Dark));

        //Bishops
        pieces.put(new Location(File.C, 1), new Bishop(PieceColor.Light));
        pieces.put(new Location(File.F, 1), new Bishop(PieceColor.Light));
        pieces.put(new Location(File.C, 8), new Bishop(PieceColor.Dark));
        pieces.put(new Location(File.F, 8), new Bishop (PieceColor.Dark));

        //Queen
        pieces.put(new Location(File.D, 1), new Queen(PieceColor.Light));
        pieces.put(new Location(File.D, 8), new Queen(PieceColor.Dark));

        //King
        pieces.put(new Location(File.E, 1), new King(PieceColor.Light));
        pieces.put(new Location(File.E, 8), new King(PieceColor.Dark));


        for(File file : File.values())
        {
            pieces.put(new Location(file, 2), new Pawn(PieceColor.Light));
            pieces.put(new Location(file, 7), new Pawn(PieceColor.Dark));
        }

        return pieces;
	}
}
