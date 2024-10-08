package userInterface;

import BoardPieces.State;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class NumberCoordinates extends GridPane{
	
	private State state;

	private double width;
	private double height;
	
	public NumberCoordinates(State state, double width, double height) {
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
        	 Label numberLabel = new Label();
        	if(state.getBoardState() == State.BoardState.WHITE_ORIENTED || state.getBoardState() == State.BoardState.BLACK_ORIENTED){
               numberLabel.setText(Integer.toString((state.getBoardState() == State.BoardState.WHITE_ORIENTED? 8-i:i+1))); // ASCII value A, B, C...
        	}
        	else {
        		numberLabel.setText("");
        	}
            numberLabel.setStyle("-fx-font-size: 24;"); // Set font size
            numberLabel.setAlignment(Pos.CENTER); // Center align the letter
            numberLabel.setPrefSize(width, height / 8); // Set cell size
            this.add(numberLabel, 0, i); // Add label to grid cell                   
        }		
	}
	
}
