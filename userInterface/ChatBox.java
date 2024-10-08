package userInterface;

import javafx.scene.control.TextArea;

public class ChatBox extends TextArea {
	
	private double width;
	private double height;
	
	public ChatBox(double width, double height){
		 this.setPrefSize(width, height);
		 this.width = width;
		 this.height = height;
		 this.setEditable(false);
	}

}
