package application.controller;

import java.io.IOException;

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
		VBox mainBox = commonObject.getMainBox();
		mainBox.getChildren().clear();
		
		try {
			mainBox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/AddAccount.fxml")));
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
