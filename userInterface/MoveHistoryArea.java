package userInterface;
import javafx.scene.control.TextArea;

public class MoveHistoryArea extends TextArea{
	
	private double width;
	private double height;
	
	public MoveHistoryArea(double width, double height) {
		this.setEditable(false);
		this.setPrefSize(width, height);
		this.width = width;
		this.height = height;
	}
	
	public double myGetWidth() {
		return this.width;
	}
	
	public double myGetHeight() {
		return this.height;
	}
}
