package application.controller;

import java.io.IOException;
import java.util.ArrayList;

import application.CommonObjects;
import application.model.Account;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class SignUpController {

	private CommonObjects commonObject = CommonObjects.getInstance();
	
	@FXML TextField usernameTextField;
	@FXML PasswordField passwordField;
	@FXML MenuButton securityQuestionMenuButton;
	@FXML TextField securityQuestionAnswer;	
	
	@FXML public void showLoginPage() {
		VBox mainBox = commonObject.getMainBox();
		mainBox.getChildren().clear();
		
		try {
			mainBox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/Login.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private String question = "";
	@FXML public void addUser() {
		String user = usernameTextField.getText(), 
				pass = passwordField.getText(),  
				answer = securityQuestionAnswer.getText();
		
		boolean added = false;
		
		if(!user.isEmpty() && !pass.isEmpty() && !question.isEmpty() &&	!answer.isEmpty()) {
			try {
				added = commonObject.getUserDAO().insertUser(new User(user, pass, question, answer, new ArrayList<Account>()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (added) {
			showLoginPage();
		}
	}
	
	@FXML public void menuItemHandler(ActionEvent event) { 
		String itemText = ((MenuItem)event.getSource()).getText();
		securityQuestionMenuButton.setText(itemText);
		question = itemText;
	}

}
