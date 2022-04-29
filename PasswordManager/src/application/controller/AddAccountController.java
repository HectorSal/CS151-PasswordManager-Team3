package application.controller;

import java.io.IOException;

import application.CommonObjects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;

public class AddAccountController {

	private CommonObjects commonObject = CommonObjects.getInstance();
	
	@FXML TextField serviceNameTextField;
	@FXML TextField usernameTextField;
	@FXML TextField emailTextField;
	@FXML PasswordField passwordField;
	@FXML CheckBox passwordGeneratorCheckBox;
	@FXML VBox passwordGenerator;
	@FXML TextField minPasswordLengthTextField;
	@FXML TextField maxPasswordLengthTextField;
	@FXML CheckBox specialCharactersCheckBox;
	@FXML HBox capitalLettersHBox;
	@FXML CheckBox capitalLettersCheckBox;
	@FXML public void togglePasswordGenerator() {}
	@FXML public void toggleSpecialCharacters() {}
	@FXML public void toggleCapitalLettersField() {}
	@FXML public void generatePassword() {}
	@FXML public void showHomePage() {
		VBox mainBox = commonObject.getMainBox();
		mainBox.getChildren().clear();
		
		try {
			mainBox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/Home.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML public void addAccount() {}
	
}
