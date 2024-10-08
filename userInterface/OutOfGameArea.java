package userInterface;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class OutOfGameArea extends GridPane{
	// Has 16 fields 2x8
	private double width;
	private double height;
	private ArrayList<OutOfGameAreaField> fields;
	
	public OutOfGameArea (double width, double height) {
		this.width = width;
		this.height = height;
		build();
	}
	
	private void build() {

		// Create and set fields
		fields = new ArrayList<OutOfGameAreaField>();
	    for (int row = 0; row < 2; row++) {
	         for (int col = 0; col < 8; col++) {
	        	 OutOfGameAreaField field = new OutOfGameAreaField(width/8, height/2);
	        	 field.setFill(Color.WHITE);
	             this.add(field, col, row);
	             fields.add(field);
	         }
	    }
	}
}
