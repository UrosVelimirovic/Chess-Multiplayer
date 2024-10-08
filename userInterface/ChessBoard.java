package userInterface;

import BoardPieces.Bishop;
import BoardPieces.ChessPiece;
import BoardPieces.King;
import BoardPieces.Knight;
import BoardPieces.Pawn;
import BoardPieces.Queen;
import BoardPieces.Rook;
import BoardPieces.State;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;


public class ChessBoard extends GridPane{
	
	private ArrayList<ChessBoardField> fields;
	private Map<String, ChessPiece> pieces;
	private double width;
	private double height;
	private State state;
	private Boolean clicked;
	private String clickedPosition;
	
	public ChessBoard(double width, double height, State state) {
		this.width = width;
		this.height = height;
		this.state = state;
		this.clicked = false;
		this.clickedPosition = "00";
		this.setAlignment(Pos.CENTER);
		build();
	    createPieces();
	    setClickable();
	}
	
	private void build() {
		// Create and set fields
		fields = new ArrayList<ChessBoardField>();
	    for (int row = 0; row < 8; row++) {
	         for (int col = 0; col < 8; col++) {
	             ChessBoardField field = new ChessBoardField(width/8, height/8);
	             field.setFill((row + col) % 2 == 0 ? Color.WHITE : Color.GREEN);
	             this.add(field, col, row);
	             fields.add(field);
	         }
	    }
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	private void setClickable() {
		for(int i = 0; i < fields.size(); i ++) {
			//System.out.println(this.getRowIndex(fields.get(i)));
			//System.out.println(this.getColumnIndex(fields.get(i)));
			String position = makeAddress(this.getRowIndex(fields.get(i)), this.getColumnIndex(fields.get(i)));
			//System.out.println(position);
			//System.out.println(i);
			fields.get(i).setOnMouseClicked(event -> handleFieldClick(position));
		}
	}
	
	// This method is invoked whether the field is clicked
	// Pieces cannot be clicked because they are set to transparent
	public void handleFieldClick(String position) {
		if(clicked == false) {
			if(pieces.containsKey(position) == true ) {
				clicked = true;
				clickedPosition = position;
			}
			return;
		}
		if(clicked == true) {
			if(positionIsLegalToMoveTo(position)) {
				movePiece(pieces.get(clickedPosition), position);
			}
			clicked = false;
		}
	
	}
	
	private Boolean checkForPawnMovementLegality(String targetPosition) {
		ChessPiece piece = pieces.get(clickedPosition);
		if(piece.getType() != ChessPiece.PieceType.PAWN) {
			return true;
		}
		
		Boolean inclineMovement = Math.abs(targetPosition.charAt(0) - clickedPosition.charAt(0)) == 1
									&& Math.abs(targetPosition.charAt(1) - clickedPosition.charAt(1)) == 1;
		
		if(inclineMovement == false) {
			return true;
		}
		
		//	it is incline movement
		if(pieces.containsKey(targetPosition) && pieces.get(targetPosition).getColor() != piece.getColor()) {
			return true;
		}
		
		return false;
	}
	private Boolean positionIsLegalToMoveTo(String position) {
		ChessPiece piece = pieces.get(clickedPosition);
		
		if(piece.canMove(position) == false) {
			
			return false;
		}
		if(checkForPawnMovementLegality(position) == false) {
			
			return false;
		}
		if (pieces.containsKey(position) && ( pieces.get(position).getColor() == piece.getColor()) ){
			
			return false;
		}
		if(hasObstaclesOnTheWay(position)) {
			System.out.println("here");
			return false;
		}
		
		return true;
	}
	
	private Boolean hasObstaclesOnTheWay(String targetPosition) {
		ChessPiece piece = pieces.get(clickedPosition);
		
		if(piece.getType() == ChessPiece.PieceType.KNIGHT) {
			
			return false;
		}
		
		Boolean rookMoveHorizontal =  targetPosition.charAt(0) == clickedPosition.charAt(0);
		Boolean rookMoveVertical = targetPosition.charAt(1) == clickedPosition.charAt(1); 
		
		Boolean bishopMove = !(rookMoveHorizontal || rookMoveVertical);
		
		char tempNum =  targetPosition.charAt(0) > clickedPosition.charAt(0)? clickedPosition.charAt(0): targetPosition.charAt(0);
		char tempLetter = targetPosition.charAt(1) > clickedPosition.charAt(1)? clickedPosition.charAt(1): targetPosition.charAt(1);
		System.out.println(Character.toString(tempNum) + Character.toString(tempLetter));
		char endNum =  targetPosition.charAt(0) < clickedPosition.charAt(0)? clickedPosition.charAt(0): targetPosition.charAt(0);
		char endLetter = targetPosition.charAt(1) < clickedPosition.charAt(1)? clickedPosition.charAt(1): targetPosition.charAt(1);
		System.out.println(Character.toString(endNum) + Character.toString(endLetter));

		if(bishopMove || rookMoveVertical ) {
			tempNum++;
		}

		if(bishopMove || rookMoveHorizontal ) {
			tempLetter++;
			
		}
		
		while( ( (tempNum == endNum) && (tempLetter == endLetter) ) == false ) {
			String result = Character.toString(tempNum) + Character.toString(tempLetter);
			System.out.println(result);
			if(pieces.containsKey(result)) {
				
				return true;
			}
			if(bishopMove || rookMoveVertical ) {
				tempNum++;
			}

			if(bishopMove || rookMoveHorizontal ) {
				tempLetter++;
			}
		}
		
		return false;
	}

	private void movePiece(ChessPiece piece, String position) {
		pieces.remove(piece.getPosition());
		TranslateTransition transition = new TranslateTransition(Duration.seconds(1), piece);
		double deltaX = convertPositionToColumn(position) - convertPositionToColumn(clickedPosition);
		double deltaY = convertPositionToRow(position) - convertPositionToRow(clickedPosition);
		transition.setByX(deltaX * (double)width/8);
		transition.setByY(deltaY * (double)height/8);
		transition.play();
		pieces.put(position, piece);
		piece.setPosition(position);
	}
	
	// Rows and Columns are inverted in GridPane. And they start from top left corner.
	private int convertPositionToRow(String position) {
		int y = position.charAt(0) - '0' - 1;
		if(state.getBoardState() == State.BoardState.WHITE_ORIENTED) 
		{
			y = 7 - y;
		}
		return y;
	} 
	
	private int convertPositionToColumn(String position) {

		int x = position.charAt(1) - 'A';
		if(state.getBoardState() == State.BoardState.BLACK_ORIENTED)
		{
			x = 7 - x;
		}
		return x;
	}
	
	private String makeAddress(int row, int column) {
		StringBuilder sb = new StringBuilder();
		String address = "";
		row++;
		column++;
		int num = state.getBoardState() == State.BoardState.WHITE_ORIENTED? 9 - row: row;
		char letter = (char)(65 + (state.getBoardState() == State.BoardState.WHITE_ORIENTED? column - 1: 8 - column));
		sb.append(Integer.toString(num));
		sb.append(letter);
		address = sb.toString();
		return address;
	}
	
	public void createPieces() {
		
		int row = 0;
		int column = 0;
		ChessPiece.PieceType pieceType;
		ChessPiece.Color color; 
		ChessPiece piece;
		pieces = new HashMap<>();
		
		for(int i = 1; i <= 4; i ++) {
			
			if(i<=2) {
				color = state.getBoardState() == State.BoardState.WHITE_ORIENTED? ChessPiece.Color.WHITE:ChessPiece.Color.BLACK;
			}
			else {
				color = state.getBoardState() == State.BoardState.WHITE_ORIENTED? ChessPiece.Color.BLACK:ChessPiece.Color.WHITE;
			}
			
			// Rooks
			pieceType = ChessPiece.PieceType.ROOK;
			piece = new Rook(pieceType, color, i, state);
			this.pieces.put(piece.getPosition(), piece); // put the piece in the map with key that corresponds to its position
			
			// Knights
			pieceType = ChessPiece.PieceType.KNIGHT;
			piece = new Knight(pieceType, color, i, state);
			this.pieces.put(piece.getPosition(), piece); // put the piece in the map with key that corresponds to its position
			
			// Bishops
			pieceType = ChessPiece.PieceType.BISHOP;
			piece = new Bishop(pieceType, color, i, state);
			this.pieces.put(piece.getPosition(), piece); // put the piece in the map with key that corresponds to its position
			
		}
		
		// Queens and kings
		for(int i = 1; i <= 2; i++) {
		
			if(i==1) {
				color = state.getBoardState() == State.BoardState.WHITE_ORIENTED? ChessPiece.Color.WHITE:ChessPiece.Color.BLACK;
			}
			else {
				color = state.getBoardState() == State.BoardState.WHITE_ORIENTED? ChessPiece.Color.BLACK:ChessPiece.Color.WHITE;
			}
			// Queens
			pieceType = ChessPiece.PieceType.QUEEN;
			piece = new Queen(pieceType, color, i, state);
			this.pieces.put(piece.getPosition(), piece); // put the piece in the map with key that corresponds to its position
			
			// King
			pieceType = ChessPiece.PieceType.KING;
			piece = new King(pieceType, color, i, state);
			this.pieces.put(piece.getPosition(), piece); // put the piece in the map with key that corresponds to its position
						
		}
		
		// Pawns
		for(int i = 1; i <= 16; i ++) {
			if(i<=8) {
				color = state.getBoardState() == State.BoardState.WHITE_ORIENTED? ChessPiece.Color.WHITE:ChessPiece.Color.BLACK;
			}
			else {
				color = state.getBoardState() == State.BoardState.WHITE_ORIENTED? ChessPiece.Color.BLACK:ChessPiece.Color.WHITE;
			}
			
			// Pawn
			pieceType = ChessPiece.PieceType.PAWN;
			piece = new Pawn(pieceType, color, i, state);
			this.pieces.put(piece.getPosition(), piece); // put the piece in the map with key that corresponds to its position
		}
		
		for (String key : pieces.keySet()) {
			
		    piece = pieces.get(key);
		    row = convertPositionToRow(piece.getPosition());
			column = convertPositionToColumn(piece.getPosition());
			this.add(piece, column, row); // it takes parameters in such way
			GridPane.setHalignment(piece, HPos.CENTER); // Center horizontally
			GridPane.setValignment(piece, VPos.CENTER); // Center 
			piece.setMouseTransparent(true);
			
		}
	}
	
}
	
