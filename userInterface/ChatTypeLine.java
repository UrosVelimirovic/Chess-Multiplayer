package userInterface;

import javafx.scene.control.TextField;

public class ChatTypeLine extends TextField{
	private double width;
	private double height;
	
	public ChatTypeLine(double width, double height) {
		this.setPrefSize(width, height);
		this.width = width;
		this.height = height;
		this.setEditable(true);
	}
}

