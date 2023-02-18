package GAME;

import java.util.ArrayList;
import java.util.List;

import PIECE.Piece;

public class Player 
{
	private Integer id;
	private Integer playPoint;
	private List<Piece> listKilledPieces = new ArrayList<>();
	private List<String> historyMoves = new ArrayList<>();
	
	public Player()
	{
		
	}
	
	public Player(Integer id)
	{
		this.id = id;

	}
	
	public Player(Integer id, Integer playPoint)
	{
		this.id = id;
		this.playPoint = playPoint;
	}
	
	public Player(Integer id, List<Piece> listKilledPieces, List<String> historyMoves)
	{
		this.id = id;
		this.listKilledPieces = listKilledPieces;
		this.historyMoves = historyMoves;
	}
	
	public Player(Integer id, Integer playPoint, List<Piece> listKilledPieces, List<String> historyMoves) 
	{
		this.id = id;
		this.playPoint = playPoint;
		this.listKilledPieces = listKilledPieces;
		this.historyMoves = historyMoves;
	}

	public List<String> getHistoryMoves() {
		return historyMoves;
	}

	public void setHistoryMoves(List<String> historyMoves) {
		this.historyMoves = historyMoves;
	}

	public Integer getId() {
		return id;
	}

	public Integer getPlayPoint() {
		return playPoint;
	}

	public List<Piece> getListKilledPieces() {
		return listKilledPieces;
	}

	public void setPlayPoint(Integer playPoint) {
		this.playPoint = playPoint;
	}

	public void setListKilledPieces(List<Piece> listKilledPieces) {
		this.listKilledPieces = listKilledPieces;
	}
	
	public void addHistory(String s)
	{
		this.historyMoves.add(s);
	}
	
	public void addKilledPiece(Piece a)
	{
		this.listKilledPieces.add(a);
	}
	
	
}
