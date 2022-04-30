package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

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
	private boolean capitalLettersEnabled = false;
	private boolean specialCharactersEnabled = false;
	
	@FXML TextField serviceNameTextField;
	@FXML TextField usernameTextField;
	@FXML TextField emailTextField;
	@FXML PasswordField passwordField;
	@FXML CheckBox passwordGeneratorCheckBox;
	@FXML VBox passwordGenerator;
	@FXML TextField minPasswordLengthTextField;
	@FXML TextField maxPasswordLengthTextField;
	@FXML CheckBox specialCharactersCheckBox;
	@FXML CheckBox capitalLettersCheckBox;
	@FXML VBox textFieldsAndButtonsVBox;
	@FXML TextField capitalLettersField;
	@FXML HBox navigationButtonsHBox;
	
	@FXML public void togglePasswordGenerator() {
		if (passwordGeneratorCheckBox.isSelected()) {
			
			textFieldsAndButtonsVBox.getChildren().remove(5);
			textFieldsAndButtonsVBox.getChildren().addAll(passwordGenerator, navigationButtonsHBox);
		}
		else {
			textFieldsAndButtonsVBox.getChildren().remove(5);
		}
	}
	@FXML public void toggleSpecialCharacters() {
		
		specialCharactersEnabled = specialCharactersCheckBox.isSelected();
		
	}
	@FXML public void toggleCapitalLettersField() {
		
		capitalLettersEnabled = capitalLettersCheckBox.isSelected();
		capitalLettersField.setVisible(capitalLettersEnabled);
		
	}
	
	@FXML public void generatePassword() {
		
		// initialize variables and get shared generator
		Random generator = commonObject.getGenerator();
		int minLength = 0;
		int maxLength = 0;
		int numOfCapitalLetters = 0;
		
		
		// check for valid input from all fields
		if (minPasswordLengthTextField.getText().isEmpty()) {
			return;
		}
		else {
			String minLengthInput = minPasswordLengthTextField.getText();
			try {
				minLength = Integer.parseInt(minLengthInput);
				if (minLength <= 0) {
					return;
				}
			}
			catch (NumberFormatException e) {
				e.printStackTrace();
				return;
			}
		}
		
		if (maxPasswordLengthTextField.getText().isEmpty()) {
			return;
		}
		else {
			String maxLengthInput = maxPasswordLengthTextField.getText();
			try {
				maxLength = Integer.parseInt(maxLengthInput);
				if (maxLength <= 0 || maxLength < minLength) {
					return;
				}
			}
			catch (NumberFormatException e) {
				e.printStackTrace();
				return;
			}
		}
		if (capitalLettersEnabled) {
			if (capitalLettersField.getText().isEmpty()) {
				return;
			}
			else {
				String capitalLettersInput = capitalLettersField.getText();
				try {
					numOfCapitalLetters = Integer.parseInt(capitalLettersInput);
					if (numOfCapitalLetters <= 0 || numOfCapitalLetters > minLength) {
						return;
					}
				}
				catch (NumberFormatException e) {
					e.printStackTrace();
					return;
				}
			}
		}
		
		// all input is valid and assigned to variables
		
		//allowed characters is initially only letters
		String letters = "abcdefghijklmnopqrstuvwxyz";
		String specialCharacters = " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
		String allowedCharacters = letters;
		// if special characters are allowed, then they are added to the allowed character string
		if (specialCharactersEnabled) {
			allowedCharacters = allowedCharacters + specialCharacters;
		}
		
		// a random length that is in between the min and max length is generated
		int length = minLength + generator.nextInt(maxLength - minLength + 1);
		String password = "";
		
		// add each random substring to the password string
		for (int i = 0; i < length; i ++) {
			// if the number of capital letters is not fulfilled by the end, then capital letters are forced into the password
			if (numOfCapitalLetters == length - i) {
				int randomIndex = generator.nextInt(26);
				password = password + letters.substring(randomIndex, randomIndex + 1).toUpperCase();
				numOfCapitalLetters --;
			}
			else {
				// a boolean for determining if an added substring is a capital letter is randomly true
				boolean addCapitalLetter = 1 == generator.nextInt(3);
				String addedCharacter = "";
				// if a capital letter is added
				if (addCapitalLetter && numOfCapitalLetters > 0) {
					int randomIndex = generator.nextInt(letters.length());
					addedCharacter = letters.substring(randomIndex, randomIndex + 1);
					addedCharacter = addedCharacter.toUpperCase();
					numOfCapitalLetters --;
				}
				// a capital letter is not added
				else {
					int randomIndex = generator.nextInt(allowedCharacters.length());
					addedCharacter = allowedCharacters.substring(randomIndex, randomIndex + 1);
				}
				// add character to the password
				password = password + addedCharacter;
			}
			
			
		}
		passwordField.setText(password);
		
		
	}
	@FXML public void showHomePage() {
		VBox mainBox = commonObject.getMainBox();
		mainBox.getChildren().clear();
		
		try {
			mainBox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/Home.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void addAccount() {
		if (serviceNameTextField.getText().isEmpty()) {
			return;
		}
		
		if (usernameTextField.getText().isEmpty()) {
			return;
		}
		if (emailTextField.getText().isEmpty()) {
			return;
		}
		if (passwordField.getText().isEmpty()) {
			return;
		}
	}
	
}
