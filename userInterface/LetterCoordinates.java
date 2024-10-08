package userInterface;

import BoardPieces.State;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class LetterCoordinates extends GridPane{

	private State state;

	private double width;
	private double height;
	
	public LetterCoordinates(State state, double width, double height) {
		this.state = state;
		this.width = width;
		this.height = height;
		updateState();
	}
	
	public void setState(State state) {
		this.state = state;
		updateState();
	}
	
	private void updateState() {
		this.getChildren().clear();
	
       for (int i = 0; i < 8; i++) {
        	Label letterLabel = new Label();
        	if(state.getBoardState() == State.BoardState.WHITE_ORIENTED || state.getBoardState() == State.BoardState.BLACK_ORIENTED){
        		letterLabel.setText(Character.toString((state.getBoardState() == State.BoardState.WHITE_ORIENTED?(char) (65 + i):(65 + 7 - i)))); // ASCII value A, B, C...
        	}
        	else {
        		letterLabel.setText("");
        	}
        	letterLabel.setStyle("-fx-font-size: 24;"); // Set font size
        	letterLabel.setAlignment(Pos.CENTER); // Center align the letter
        	letterLabel.setPrefSize(width, height / 8); // Set cell size
            this.add(letterLabel, i, 0); // Add label to grid cell                     
        }		
	}
}
