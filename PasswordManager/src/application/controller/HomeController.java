package application.controller;

import java.io.IOException;
import java.net.URL;

import application.CommonObjects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class HomeController {

	private CommonObjects commonObject = CommonObjects.getInstance();
	
	@FXML TableView accountTable;

	@FXML public void showAddAccountPage() {
		
		URL url = getClass().getClassLoader().getResource("view/AddAccount.fxml");
		try {
			AnchorPane pagePane = (AnchorPane) FXMLLoader.load(url);
			
			VBox mainBox = commonObject.getMainBox();
			
			mainBox.getChildren().clear();
			mainBox.getChildren().add(pagePane);
			VBox vbox = (VBox) pagePane.getChildren().get(1);
			vbox.getChildren().remove(5);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	@FXML public void showResetMasterPasswordPage() {
		VBox mainBox = commonObject.getMainBox();
		mainBox.getChildren().clear();
		
		try {
			mainBox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/ResetMasterPassword.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void showLoginPage() {
		VBox mainBox = commonObject.getMainBox();
		mainBox.getChildren().clear();
		
		try {
			mainBox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/Login.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
