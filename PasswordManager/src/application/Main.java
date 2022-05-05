package application;
	
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import application.dao.AccountDataAccessObject;
import application.dao.UserDataAccessObject;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.Clipboard;
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
			try {
				AnchorPane pagePane = (AnchorPane) FXMLLoader.load(url);
				mainBox.getChildren().add(pagePane);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			primaryStage.setTitle("Password Manager");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			// keep a reference of the main box inside the CommonObjects object
			CommonObjects commonObjects = CommonObjects.getInstance();
			Random generator = new Random();
			UserDataAccessObject userDao = new UserDataAccessObject();
			AccountDataAccessObject accountDao = new AccountDataAccessObject();
			commonObjects.setClipboard(Clipboard.getSystemClipboard());
			commonObjects.setPassUtil(new PassUtil());
			commonObjects.setPrimaryStage(primaryStage);
			commonObjects.setMainBox(mainBox);
			commonObjects.setGenerator(generator);
			commonObjects.setUserDAO(userDao);
			commonObjects.setAccountDAO(accountDao);
			commonObjects.setCurrentUser(null);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
