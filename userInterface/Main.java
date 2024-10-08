package userInterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.geometry.Pos;

public class Main extends Application{
	
	Stage window;
	MyScene scene;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	 	primaryStage.setTitle("Chess Game");
	 	window = primaryStage;
	 	scene = new MyScene();
        window.setScene(scene);
        window.setWidth(scene.myGetWidth());
        window.setHeight(scene.myGetHeight());
        window.show();
	}

	
}
