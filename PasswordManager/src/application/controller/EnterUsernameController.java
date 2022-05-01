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

public class EnterUsernameController {

	private CommonObjects commonObject = CommonObjects.getInstance();
	
	@FXML TextField usernameTextField;

	@FXML public void showLoginPage() {
		VBox mainBox = commonObject.getMainBox();
		mainBox.getChildren().clear();
		commonObject.setCurrentUser(null);
		
		try {
			mainBox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/Login.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showResetMasterPasswordPage() {
		VBox mainBox = commonObject.getMainBox();
		mainBox.getChildren().clear();
		
		try {
			mainBox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/ResetMasterPassword.fxml")));
			// modify the security question
			AnchorPane ap = (AnchorPane) mainBox.getChildren().get(0);
			VBox vbox = (VBox) ap.getChildren().get(0);
			Text securityQuestion = (Text) vbox.getChildren().get(0);
			User currentUser = commonObject.getCurrentUser();
			securityQuestion.setText(currentUser.getSecurityQuestion());
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
