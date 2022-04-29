package application.controller;

import java.io.IOException;

import application.CommonObjects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class LoginController {

	private CommonObjects commonObject = CommonObjects.getInstance();
	
	@FXML PasswordField passwordField;
	@FXML TextField usernameTextField;

	@FXML public void verifyLogin() {}

	@FXML public void showSignUpPage() {
		VBox mainBox = commonObject.getMainBox();
		mainBox.getChildren().clear();
		
		try {
			mainBox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/SignUp.fxml")));
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

}
