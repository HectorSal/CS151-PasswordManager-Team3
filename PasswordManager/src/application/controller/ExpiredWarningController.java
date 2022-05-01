package application.controller;

import java.io.IOException;
import java.util.ArrayList;

import application.CommonObjects;
import application.model.Account;
import application.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public class ExpiredWarningController {
	
	private CommonObjects commonObject = CommonObjects.getInstance();
	
	@FXML VBox expiredPasswordsVBox;

	@FXML Button homeButton;

	@FXML public void showHomePage() {
		VBox mainBox = commonObject.getMainBox();
		mainBox.getChildren().clear();
		
		try {
			mainBox.getChildren().add((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/Home.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void addExpiredAccounts() {
		expiredPasswordsVBox.getChildren().remove(1);
		User currentUser = commonObject.getCurrentUser();
		ArrayList<Account> expiredAccounts = currentUser.getExpiredAccounts();
		for (Account account: expiredAccounts) {
			Text accountName = new Text(account.toString());
			accountName.setFont(new Font(15));
			expiredPasswordsVBox.getChildren().add(accountName);
		}
		homeButton.setVisible(true);
		
	}

}
