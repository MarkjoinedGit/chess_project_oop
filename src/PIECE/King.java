package PIECE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import BOARD.Board;
import LOCATION.Location;
import LOCATION.LocationFactory;
import SQUARE.Square;

public class King extends Piece implements Movable 
{
	private Movable bishop;
	private Movable rook;
	
	
	
	public King() {
		super();
		this.name = "King";
		this.valuePoint = 5;
		// TODO Auto-generated constructor stub
	}

	public King(PieceColor pieceColor, Square currentSquare, String name) 
	{
		super(pieceColor, currentSquare, name);
		this.name = "King";
		this.valuePoint = 5;
		// TODO Auto-generated constructor stub
	}

	public King(PieceColor pieceColor, Square currentSquare) 
	{
		super(pieceColor, currentSquare);
		this.name = "King";
		this.valuePoint = 5;
		// TODO Auto-generated constructor stub
	}

	public King(PieceColor pieceColor) 
	{
		super(pieceColor);
		this.name = "King";
		this.valuePoint = 5;
		// TODO Auto-generated constructor stub
	}

	public King(PieceColor pieceColor, Movable bishop, Movable rook) {
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
		
		List<Location> validMoves = new ArrayList<Location>();
		for(Location key : moveCandidates)
		{
			if(isValidMoveOfKing(key))
				validMoves.add(key);
		}
		
		return validMoves;
	}

	@Override
	public List<Location> getValidMoves(Board board, Square square) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean isValidMoveOfKing(Location key)
	{
		 if ( (Math.abs(key.getFile().ordinal() - this.currentSquare.getLocation().getFile().ordinal()) == 1) && Math.abs(key.getRank() - this.currentSquare.getLocation().getRank()) == 1 )
             return true;
         if ( (Math.abs(key.getFile().ordinal() - this.currentSquare.getLocation().getFile().ordinal()) == 0) && Math.abs(key.getRank() - this.currentSquare.getLocation().getRank()) == 1 )
             return true;
         if ((Math.abs(key.getFile().ordinal() - this.currentSquare.getLocation().getFile().ordinal()) == 1) && Math.abs(key.getRank() - this.currentSquare.getLocation().getRank()) == 0 )
                 return true;
         return false;
	}

	@Override
	public void makeMove(Square nextSquare) 
	{
		this.currentSquare.setIsOccupied(false);
		this.setCurrentSquare(nextSquare);
		nextSquare.setCurrentPiece(this);
		nextSquare.setIsOccupied(true);
	}
	
	@Override
	 public boolean isInDanger(Board board)
      {
          //check whether king is in targeted by opponet rook or queen
          Map<Location, Square> squareMap = board.getLocationSquareMap();
          if (isInDangerByFile(squareMap))
              return true;
          if (isInDangerByRank(squareMap))
              return true;

          //check whether king is in targeted by opponent bishop or queen
          if (isInDangerByDiagonalSquares(squareMap))
              return true;

          //check if king is in targeted by opponent Knight
          if (isInDangerByKnight(squareMap))
              return true;

          //check if king is in targeted by opponent pawn
          if (isInDangerByPawn(squareMap))
              return true;

          return false;
      }

      private boolean isInDangerByFile(Map<Location, Square> squareMap)
      {
          Location nextDown = LocationFactory.build(this.currentSquare.getLocation(), 0, -1);
          Location nextUp = LocationFactory.build(this.currentSquare.getLocation(), 0, 1);

          while (squareMap.containsKey(nextDown))
          {
              if (squareMap.get(nextDown).getIsOccupied())
              {
                  if ( squareMap.get(nextDown).getCurrentPiece().nameEqual(new Rook())|| squareMap.get(nextDown).getCurrentPiece().nameEqual(new Queen()))
                  {
                      if (!squareMap.get(nextDown).getCurrentPiece().getPieceColor().equals(this.pieceColor))
                          return true;
                  }
                  break;
              }
              nextDown = LocationFactory.build(nextDown, -1, 0);
          }

          while (squareMap.containsKey(nextUp))
          {
              if (squareMap.get(nextUp).getIsOccupied())
              {
                  if ( squareMap.get(nextUp).getCurrentPiece().nameEqual(new Rook())|| squareMap.get(nextUp).getCurrentPiece().nameEqual(new Queen()))
                  {
                      if (!squareMap.get(nextUp).getCurrentPiece().getPieceColor().equals(this.pieceColor))
                          return true;
                  }
                  break;
              }
              nextUp = LocationFactory.build(nextUp, 1, 0);
          }

          return false;
      }
      private boolean isInDangerByRank(Map<Location, Square> squareMap)
      {
          Location nextLeft = LocationFactory.build(this.currentSquare.getLocation(), -1, 0);
          Location nextRight = LocationFactory.build(this.currentSquare.getLocation(), 1, 0);

          while (squareMap.containsKey(nextLeft))
          {
              if (squareMap.get(nextLeft).getIsOccupied())
              {
                  if ( squareMap.get(nextLeft).getCurrentPiece().nameEqual(new Rook())|| squareMap.get(nextLeft).getCurrentPiece().nameEqual(new Queen()))
                  {
                      if (!squareMap.get(nextLeft).getCurrentPiece().getPieceColor().equals(this.pieceColor))
                          return true;
                  }
                  break;
              }
              nextLeft = LocationFactory.build(nextLeft, -1, 0);
          }

          while (squareMap.containsKey(nextRight))
          {
              if (squareMap.get(nextRight).getIsOccupied())
              {
                  if ( squareMap.get(nextRight).getCurrentPiece().nameEqual(new Rook())|| squareMap.get(nextRight).getCurrentPiece().nameEqual(new Queen()))
                  {
                      if (!squareMap.get(nextRight).getCurrentPiece().getPieceColor().equals(this.pieceColor))
                          return true;
                  }
                  break;
              }
              nextRight = LocationFactory.build(nextRight, 1, 0);
          }

          return false;
      }

      private boolean isInDangerByDiagonalSquares(Map<Location,Square> squareMap)
      {
          if (checkByDirection(squareMap, -1, 1))
              return true;
          if (checkByDirection(squareMap, 1, 1))
              return true; 
          if (checkByDirection(squareMap, -1, -1))
              return true;
          if (checkByDirection(squareMap, 1, -1))
              return true;
          return false;
      }

      private boolean checkByDirection(Map<Location, Square> squareMap, int fileOffSet, int rankOffSet)
      {
          Location next = LocationFactory.build(this.currentSquare.getLocation(), fileOffSet, rankOffSet);
         
          while (squareMap.containsKey(next))
          {
              if (squareMap.get(next).getIsOccupied())
              {
                  if ( squareMap.get(next).getCurrentPiece().nameEqual(new Bishop()) || squareMap.get(next).getCurrentPiece().nameEqual(new Queen()))
                  {
                      if (!squareMap.get(next).getCurrentPiece().getPieceColor().equals(this.pieceColor))
                          return true;
                  }
                  break;
              }
              next = LocationFactory.build(next, fileOffSet, rankOffSet);
          }
          return false;
      }

      private boolean isInDangerByKnight(Map<Location, Square> squareMap)
      {
          int[] I = { 1, 2, 2, 1, -1, -2, -2, -1 };
          int[] J = { -2, -1, 1, 2, -2, -1, 1, 2 };
          for(int k = 0; k<I.length; k++)
          {
              Location temp = LocationFactory.build(this.currentSquare.getLocation(), I[k], J[k]);
              if(squareMap.containsKey(temp))
              {
                  if (squareMap.get(temp).getIsOccupied())
                  {
                      if (squareMap.get(temp).getCurrentPiece().nameEqual(new Knight()))
                      {
                          if (!squareMap.get(temp).getCurrentPiece().getPieceColor().equals(this.pieceColor))
                              return true;
                      }
                  }
              }
              
          }

          return false;
      }

      private boolean isInDangerByPawn(Map<Location, Square> squareMap)
      {
          int move = (this.pieceColor.equals(PieceColor.Light)) ? 1 : -1;
          Location leftCheck = LocationFactory.build(this.currentSquare.getLocation(), -move, move);
          Location rightCheck = LocationFactory.build(this.currentSquare.getLocation(), move, move);
          if(squareMap.containsKey(leftCheck))
          {
              if (squareMap.get(leftCheck).getIsOccupied())
              {
                  if (squareMap.get(leftCheck).getCurrentPiece().nameEqual(new Pawn()))
                  {
                      if (!squareMap.get(leftCheck).getCurrentPiece().getPieceColor().equals(this.pieceColor)) 
                          return true;
                  }
              }

          }

          if (squareMap.containsKey(rightCheck))
          {
              if (squareMap.get(rightCheck).getIsOccupied())
              {
                  if (squareMap.get(rightCheck).getCurrentPiece().nameEqual(new Pawn()))
                  {
                      if (!squareMap.get(rightCheck).getCurrentPiece().getPieceColor().equals(this.pieceColor))
                          return true;
                  }
              }
          }

          return false;
          
      	}
}



