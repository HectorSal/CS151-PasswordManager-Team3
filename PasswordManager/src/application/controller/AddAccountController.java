package application.controller;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import application.CommonObjects;
import application.dao.AccountDataAccessObject;
import application.model.Account;
import application.model.User;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.DatePicker;

public class AddAccountController {

	static final long THIRTY_DAYS_IN_MILLISECONDS = 2592000000L;
	
	private CommonObjects commonObject = CommonObjects.getInstance();
	private boolean capitalLettersEnabled = false;
	private boolean specialCharactersEnabled = false;
	
	@FXML TextField serviceNameTextField;
	@FXML TextField usernameTextField;
	@FXML TextField emailTextField;
	@FXML TextField passwordField;
	@FXML CheckBox passwordGeneratorCheckBox;
	@FXML VBox passwordGenerator;
	@FXML TextField minPasswordLengthTextField;
	@FXML TextField maxPasswordLengthTextField;
	@FXML CheckBox specialCharactersCheckBox;
	@FXML CheckBox capitalLettersCheckBox;
	@FXML VBox textFieldsAndButtonsVBox;
	@FXML TextField capitalLettersField;
	@FXML HBox navigationButtonsHBox;

	@FXML DatePicker datePicker;

	@FXML TextField specialCharactersField;
	
	@FXML public void initialize(){
		textFieldsAndButtonsVBox.getChildren().remove(6);
	}
	
	@FXML public void togglePasswordGenerator() {
		if (passwordGeneratorCheckBox.isSelected()) {
			
			textFieldsAndButtonsVBox.getChildren().remove(6);
			textFieldsAndButtonsVBox.getChildren().addAll(passwordGenerator, navigationButtonsHBox);
		}
		else {
			textFieldsAndButtonsVBox.getChildren().remove(6);
		}
	}
	@FXML public void toggleSpecialCharacters() {
		
		specialCharactersEnabled = specialCharactersCheckBox.isSelected();
		specialCharactersField.setVisible(specialCharactersEnabled);
		
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
		int numOfSpecialCharacters = 0;
		
		
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
		
		if (specialCharactersEnabled) {
			if (specialCharactersField.getText().isEmpty()) {
				return;
			}
			else {
				String specialCharactersInput = specialCharactersField.getText();
				try {
					numOfSpecialCharacters = Integer.parseInt(specialCharactersInput);
					if (numOfSpecialCharacters <= 0 || numOfSpecialCharacters > minLength) {
						return;
					}
					else if (numOfSpecialCharacters + numOfCapitalLetters > minLength) {
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
		
		String letters = "abcdefghijklmnopqrstuvwxyz";
		String specialCharacters = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
		
		// a random length that is in between the min and max length is generated
		int length = minLength + generator.nextInt(maxLength - minLength + 1);
		String password = "";
		
		// add each random substring to the password string
		for (int i = 0; i < length; i ++) {
			// if the number of capital letters and special characters is not fulfilled by the end, then characters are forced into the password
			if (numOfCapitalLetters + numOfSpecialCharacters == length - i) {
				// 0 if a capital letter is added
				// 1 if a special character is added
				int capsOrSpecial = 0;
				if (numOfCapitalLetters == 0) {
					capsOrSpecial = 1;
				}
				else if (numOfSpecialCharacters == 0) {
					capsOrSpecial = 0;
				}
				else {
					capsOrSpecial = generator.nextInt(2);
				}
				if (capsOrSpecial == 0) {
					int randomIndex = generator.nextInt(26);
					password = password + letters.substring(randomIndex, randomIndex + 1).toUpperCase();
					numOfCapitalLetters --;
				}
				else {
					int randomIndex = generator.nextInt(specialCharacters.length());
					password = password + specialCharacters.substring(randomIndex, randomIndex + 1);
					numOfSpecialCharacters --;
				}
			}
			// else randomly pick a character based on the previous criteria
			else {
				// 2 booleans for determining what type of character is added
				int randomNumber = generator.nextInt(3);
				boolean addCapitalLetter = 1 == randomNumber;
				boolean addSpecialCharacter = 2 == randomNumber;
				String addedCharacter = "";
				// if a capital letter is added
				if (addCapitalLetter && numOfCapitalLetters > 0) {
					int randomIndex = generator.nextInt(letters.length());
					addedCharacter = letters.substring(randomIndex, randomIndex + 1);
					addedCharacter = addedCharacter.toUpperCase();
					numOfCapitalLetters --;
				}
				else if (addSpecialCharacter && numOfSpecialCharacters > 0) {
					int randomIndex = generator.nextInt(specialCharacters.length());
					addedCharacter = specialCharacters.substring(randomIndex, randomIndex + 1);
					numOfSpecialCharacters --;
				}
				// a capital letter or special character is not added
				else {
					int randomIndex = generator.nextInt(letters.length());
					addedCharacter = letters.substring(randomIndex, randomIndex + 1);
				}
				// add character to the password
				password = password + addedCharacter;
			}
			
			
		}
		passwordField.setText(password);
		
		
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
	
	@FXML public void addAccount() {
		if (!serviceNameTextField.getText().isEmpty() && !usernameTextField.getText().isEmpty() && !emailTextField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
			User currentUser = commonObject.getCurrentUser(); // get the current user
			// creation and expiration dates
			Date creationTime = new Date();
			Date expirationTime;
			if (datePicker.getValue() == null) {
				long expirationTimeLong = creationTime.getTime() + THIRTY_DAYS_IN_MILLISECONDS;
				expirationTime = new Date(expirationTimeLong);
			}
			else {
				long expirationTimeLong = datePicker.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toEpochSecond() * 1000L;
				expirationTime = new Date(expirationTimeLong);
			}
			// create account object from text fields;
			PassUtil passUtil = commonObject.getPassUtil();
			String encryptedPassword = passUtil.encrypt(passwordField.getText());
			Account account = new Account(currentUser.getUsername(), serviceNameTextField.getText(), usernameTextField.getText(), emailTextField.getText(), encryptedPassword, creationTime, expirationTime);
			AccountDataAccessObject accountDAO = commonObject.getAccountDAO();
			try {
				accountDAO.insertAccount(account);
				currentUser.getListOfAccounts().add(account);				
				showHomePage();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
