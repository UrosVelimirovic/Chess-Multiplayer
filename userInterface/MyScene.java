package userInterface;

import BoardPieces.State;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class MyScene extends Scene{
	
	private static final double moveHistoryAreaWidth = 200;
	private static final double moveHistoryAreaHeight = 640;
	private static final double chessPieceFieldWidth = 80;
	private static final double chessPieceFieldHeight = chessPieceFieldWidth;
	private static final double outOfGameAreaWidth = chessPieceFieldWidth * 8;
	private static final double outOfGameAreaHeight = chessPieceFieldHeight * 2;
	private static final double letterCoordinatesWidth = chessPieceFieldHeight * 8;
	private static final double letterCoordinatesHeight = 40;
	private static final double chessBoardWidth = chessPieceFieldWidth * 8;
	private static final double chessBoardHeight = chessPieceFieldHeight * 8;
	private static final double numberCoordinatesWidth = 40;
	private static final double numberCoordinatesHeight = chessPieceFieldHeight * 8;
	private static final double chatBoxWidth = 200;
	private static final double chatBoxHeight = 640;
	private static final double chatTypeLineWidth = chatBoxWidth;
	private static final double chatTypeLineHeight = 50;
	
	private BorderPane root;
	private double width;
	private double height;
	private VBox left;
	private HBox center;
	private VBox right;
	private State state;
	
	public MyScene() {
		super(new BorderPane());
		this.root = (BorderPane) super.getRoot();
		state = new State(State.BoardState.WHITE_ORIENTED);
		initialize();
		
	}
	
	private void initialize() {
		left = new VBox();
		center = new HBox();
		right = new VBox();
		
		// Initialize left
        Region spacer1 = new Region();
        spacer1.setPrefHeight(outOfGameAreaHeight);
        
        MoveHistoryArea moveHistoryArea = new MoveHistoryArea(moveHistoryAreaWidth, moveHistoryAreaHeight);
 
        HBox exitSaveButtons = new HBox();
        Button exit = new Button("Exit");
        Region spacer2 = new Region();
        spacer2.setPrefWidth(130);
        Button save = new Button("Save");
        exitSaveButtons.getChildren().addAll(exit, spacer2, save);
        
        Button newGame = new Button("New Game");
        
        left.getChildren().addAll(spacer1, moveHistoryArea, exitSaveButtons, newGame);
        left.setPrefSize(moveHistoryArea.myGetWidth(), outOfGameAreaHeight + moveHistoryArea.myGetHeight() + numberCoordinatesHeight + outOfGameAreaHeight);
        
        //Initialize center
        VBox centerLeft = new VBox();
        VBox centerRight = new VBox();
        //center left
        Region spacer3 = new Region();
        spacer3.setPrefSize(chessPieceFieldWidth * 8, chessPieceFieldHeight * 2);
        NumberCoordinates numberCoordinates = new NumberCoordinates(state, numberCoordinatesWidth, numberCoordinatesHeight);
        centerLeft.getChildren().addAll(spacer3,numberCoordinates);
        //center right
        OutOfGameArea outOfGameAreaUp = new OutOfGameArea(outOfGameAreaWidth, outOfGameAreaHeight);
        ChessBoard chessBoard = new ChessBoard(chessBoardWidth, chessBoardHeight, state);
        LetterCoordinates letterCoordinates = new LetterCoordinates(state, letterCoordinatesWidth, letterCoordinatesHeight);
        OutOfGameArea outOfGameAreaDown = new OutOfGameArea(outOfGameAreaWidth, outOfGameAreaHeight);
        centerRight.getChildren().addAll(outOfGameAreaUp, chessBoard, letterCoordinates, outOfGameAreaDown);
        
        center.getChildren().addAll(centerLeft, centerRight);
        
        //Initialize right
        Region spacer4 = new Region();
        spacer4.setPrefSize(chessPieceFieldWidth * 8, chessPieceFieldHeight * 2);
        
        ChatBox chatBox = new ChatBox(chatBoxWidth, chatBoxHeight);
        ChatTypeLine chatTypeLine = new ChatTypeLine(chatTypeLineWidth, chatTypeLineHeight);
        Button send = new Button("Send");
        right.getChildren().addAll(spacer4, chatBox, chatTypeLine, send);
        
        root.setRight(right);
        root.setCenter(center); 
        root.setLeft(left); 
        
        this.width = moveHistoryAreaWidth + numberCoordinatesWidth + chessBoardWidth + chatBoxWidth;
		this.height = outOfGameAreaHeight + chessBoardHeight + letterCoordinatesHeight + outOfGameAreaHeight;

	}
	
	public double myGetWidth() {
		return this.width;
	}
	
	public double myGetHeight() {
		return this.height;
	}
	
}
