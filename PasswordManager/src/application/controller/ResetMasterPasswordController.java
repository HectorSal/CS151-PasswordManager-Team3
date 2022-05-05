package application.controller;

import java.io.IOException;

import application.CommonObjects;
import application.dao.UserDataAccessObject;
import application.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;

public class ResetMasterPasswordController {

	private CommonObjects commonObject = CommonObjects.getInstance();
	
	@FXML Text securityQuestion;
	@FXML TextField securityQuestionAnswerTextField;
	@FXML PasswordField masterPasswordField1;
	@FXML PasswordField masterPasswordField2;

	@FXML Button backToLoginButton;

	@FXML Button homeButton;
	
	@FXML public void initialize() {
		// modify the security question
		User currentUser = commonObject.getCurrentUser();
		securityQuestion.setText(currentUser.getSecurityQuestion());
		if (!commonObject.isUserIsLoggedIn()) {
			homeButton.setVisible(false);
		}
		else {
			backToLoginButton.setText("Logout");
		}
		
	}
	
	@FXML public void showLoginPage() {
		VBox mainBox = commonObject.getMainBox();
		mainBox.getChildren().clear();
		Stage primaryStage = commonObject.getPrimaryStage();
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
	@FXML public void setMasterPassword() {
		User currentUser = commonObject.getCurrentUser();
		if (!securityQuestionAnswerTextField.getText().isEmpty() && !masterPasswordField1.getText().isEmpty() && !masterPasswordField2.getText().isEmpty()) {
			// if the text fields are not empty, the two password fields are the same, and the security question answer is correct
			// then the master password is changed
			String newPass = masterPasswordField1.getText();
			if(newPass.equals(masterPasswordField2.getText())) {
				if (securityQuestionAnswerTextField.getText().equals(currentUser.getSecurityQuestionAnswer())) {
					currentUser.setPassword(newPass);
					UserDataAccessObject userDAO = commonObject.getUserDAO();
					try {
						userDAO.updateUser(currentUser);
						showLoginPage();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@FXML public void showHomePage() {
		VBox mainBox = commonObject.getMainBox();
		Stage primaryStage = commonObject.getPrimaryStage();
		mainBox.getChildren().clear();
		
		try {
			AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/Home.fxml"));
			mainBox.getChildren().add(page);
			primaryStage.setWidth(page.getPrefWidth());
			primaryStage.setHeight(page.getPrefHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
