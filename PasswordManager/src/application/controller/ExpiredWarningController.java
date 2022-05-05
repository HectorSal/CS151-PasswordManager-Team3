package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.CommonObjects;
import application.dao.AccountDataAccessObject;
import application.model.Account;
import application.model.User;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class ExpiredWarningController {

	private CommonObjects commonObject = CommonObjects.getInstance();
	
	@FXML TableView<Account> accountTable;
    @FXML private TableColumn<Account, String> serviceCol;
    @FXML private TableColumn<Account, String> userCol;
    @FXML private TableColumn<Account, String> emailCol;
    @FXML private TableColumn<Account, Date> createdCol;
    @FXML private TableColumn<Account, Date> expiresCol;
	@FXML TextField filterField;
	
	private ObservableList<Account> getExpiredAccounts() {
		List<Account> accountList = commonObject.getCurrentUser().getExpiredAccounts();
		return FXCollections.observableList(accountList);
	}
	
	@FXML public void initialize() {
		
		serviceCol.setCellValueFactory(new PropertyValueFactory<Account, String>("serviceName"));
		userCol.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
		emailCol.setCellValueFactory(new PropertyValueFactory<Account, String>("email"));
		createdCol.setCellValueFactory(new PropertyValueFactory<Account, Date>("passwordCreationDate"));
		expiresCol.setCellValueFactory(new PropertyValueFactory<Account, Date>("passwordExpirationDate"));
		
		ObservableList<Account> expiredUserAccounts = getExpiredAccounts();
		
		FilteredList<Account> filteredData = new FilteredList<>(expiredUserAccounts, b -> true);
		
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(account -> {
				if(newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if(account.getServiceName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}
				else if(account.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}
				else {
					return false;
				}
			});
		});
		
		SortedList<Account> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(accountTable.comparatorProperty());
		
		accountTable.setItems(sortedData);
	}

	@FXML public void showAddAccountPage() {
		Stage primaryStage = commonObject.getPrimaryStage();
		URL url = getClass().getClassLoader().getResource("view/AddAccount.fxml");
		try {
			AnchorPane pagePane = (AnchorPane) FXMLLoader.load(url);
			
			VBox mainBox = commonObject.getMainBox();
			
			mainBox.getChildren().clear();
			mainBox.getChildren().add(pagePane);
			primaryStage.setWidth(pagePane.getPrefWidth());
			primaryStage.setHeight(pagePane.getPrefHeight());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@FXML public void deleteAccount() {
		Account account = accountTable.getSelectionModel().getSelectedItem();
		if (account != null) {
			AccountDataAccessObject accountDAO = commonObject.getAccountDAO();
			User user = commonObject.getCurrentUser();
			try {
				int index = accountDAO.deleteAccount(account);
				if (index != -1) {
					user.getListOfAccounts().remove(index);
					initialize();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@FXML public void copyPassword() {
		Account account = accountTable.getSelectionModel().getSelectedItem();
		if (account != null) {
			Clipboard clipboard = commonObject.getClipboard();
			ClipboardContent content = new ClipboardContent();
			PassUtil passUtil = commonObject.getPassUtil();
			String decryptedPassword = passUtil.decrypt(account.getPassword());
			content.putString(decryptedPassword);
			clipboard.setContent(content);
		}
	}

	@FXML public void showResetMasterPasswordPage() {
		VBox mainBox = commonObject.getMainBox();
		mainBox.getChildren().clear();
		Stage primaryStage = commonObject.getPrimaryStage();
		
		try {
			AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/ResetMasterPassword.fxml"));
			mainBox.getChildren().add(page);
			primaryStage.setWidth(page.getPrefWidth());
			primaryStage.setHeight(page.getPrefHeight());
		} catch (IOException e) {
			e.printStackTrace();
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

}
