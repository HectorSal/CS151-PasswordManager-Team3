package application.controller;

import java.io.IOException;

import application.CommonObjects;
import application.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EnterUsernameController {

	private CommonObjects commonObject = CommonObjects.getInstance();
	
	@FXML TextField usernameTextField;

	@FXML public void showLoginPage() {
		VBox mainBox = commonObject.getMainBox();
		Stage primaryStage = commonObject.getPrimaryStage();
		mainBox.getChildren().clear();
		commonObject.setCurrentUser(null);
		commonObject.setUserIsLoggedIn(false);
		
		try {
			AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/Login.fxml"));
			mainBox.getChildren().add(page);
			primaryStage.setWidth(page.getPrefWidth());
			primaryStage.setHeight(page.getPrefHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showResetMasterPasswordPage() {
		VBox mainBox = commonObject.getMainBox();
		Stage primaryStage = commonObject.getPrimaryStage();
		mainBox.getChildren().clear();
		
		try {
			AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/ResetMasterPassword.fxml"));
			mainBox.getChildren().add(page);
			primaryStage.setWidth(page.getPrefWidth());
			primaryStage.setHeight(page.getPrefHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void verifyUser() {
		try {
			User user = commonObject.getUserDAO().get(usernameTextField.getText());
			if(user != null) {
				commonObject.setCurrentUser(user);
				showResetMasterPasswordPage();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
}
