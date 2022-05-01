package application.controller;

import java.io.IOException;

import application.CommonObjects;
import application.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.PasswordField;

public class LoginController {

	private CommonObjects commonObject = CommonObjects.getInstance();
	
	@FXML TextField usernameTextField;
	@FXML PasswordField passwordField;
	@FXML public void verifyLogin() {
		try {
			User user = commonObject.getUserDAO().get(usernameTextField.getText());
			if(user != null && passwordField.getText().equals(user.getPassword())) {
				commonObject.setCurrentUser(user);
				if (user.getExpiredAccounts().isEmpty()) {
					showHomePage();
				}
				else {
					showExpiredWarningPage();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void showExpiredWarningPage() {
		VBox mainBox = commonObject.getMainBox();
		mainBox.getChildren().clear();
		
		try {
			mainBox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/ExpiredWarning.fxml")));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void showHomePage() {
		VBox mainBox = commonObject.getMainBox();
		mainBox.getChildren().clear();
		
		try {
			mainBox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/Home.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML public void showSignUpPage() {
		VBox mainBox = commonObject.getMainBox();
		mainBox.getChildren().clear();
		
		try {
			mainBox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/SignUp.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void showEnterUsernamePage() {
		VBox mainBox = commonObject.getMainBox();
		mainBox.getChildren().clear();
		
		try {
			mainBox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/EnterUsername.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
