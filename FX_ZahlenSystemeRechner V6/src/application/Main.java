package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;	
		mainWindow();
		/*
		 * try { BorderPane root =
		 * (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml")); Scene
		 * scene = new Scene(root,400,400);
		 * scene.getStylesheets().add(getClass().getResource("application.css").
		 * toExternalForm()); primaryStage.setScene(scene); primaryStage.show(); }
		 * catch(Exception e) { e.printStackTrace(); }
		 */
	}
	
	public void mainWindow() {		
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("Sample.fxml"));
			AnchorPane pane = loader.load();
			
			primaryStage.setMinHeight(800.00);
			primaryStage.setMinWidth(600.00);
			
			SampleController sampleController = loader.getController();
			sampleController.setMain(this);
			
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
