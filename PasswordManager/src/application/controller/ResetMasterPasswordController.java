package application.controller;

import java.io.IOException;

import application.CommonObjects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.PasswordField;

public class ResetMasterPasswordController {

	private CommonObjects commonObject = CommonObjects.getInstance();
	
	@FXML Text securityQuestion;
	@FXML TextField securityQuestionAnswerTextField;
	@FXML PasswordField masterPasswordField1;
	@FXML PasswordField masterPasswordField2;
	@FXML public void showLoginPage() {
		VBox mainBox = commonObject.getMainBox();
		mainBox.getChildren().clear();
		
		try {
			mainBox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/Login.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML public void setMasterPassword() {}

}
