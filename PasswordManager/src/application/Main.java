package application;
	
import java.net.URL;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox mainBox = (VBox)FXMLLoader.load(getClass().getClassLoader().getResource("view/Main.fxml"));
			Scene scene = new Scene(mainBox);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("css/application.css").toExternalForm());
			
			URL url = getClass().getClassLoader().getResource("view/Login.fxml");
			AnchorPane pane1 = (AnchorPane) FXMLLoader.load(url);
			mainBox.getChildren().add(pane1);
			
			primaryStage.setTitle("Password Manager");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// keep a reference of the main box inside the CommonObjects object
			CommonObjects commonObjects = CommonObjects.getInstance();
			commonObjects.setMainBox(mainBox);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
