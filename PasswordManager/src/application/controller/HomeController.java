package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.CommonObjects;
import application.model.Account;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

public class HomeController {

	private CommonObjects commonObject = CommonObjects.getInstance();
	
	@FXML TableView accountTable;
    @FXML private TableColumn<Account, String> serviceCol;
    @FXML private TableColumn<Account, String> userCol;
    @FXML private TableColumn<Account, String> emailCol;
    @FXML private TableColumn<Account, String> passCol;
    @FXML private TableColumn<Account, Date> createdCol;
    @FXML private TableColumn<Account, Date> expiresCol;
	@FXML TextField filterField;
	
	private ObservableList<Account> getAccounts() {
		List<Account> accountList = commonObject.getCurrentUser().getListOfAccounts();
		return FXCollections.observableList(accountList);
	}
	
	@FXML public void initialize() {
		serviceCol.setCellValueFactory(new PropertyValueFactory<Account, String>("serviceName"));
		userCol.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
		emailCol.setCellValueFactory(new PropertyValueFactory<Account, String>("email"));
		passCol.setCellValueFactory(new PropertyValueFactory<Account, String>("password"));
		createdCol.setCellValueFactory(new PropertyValueFactory<Account, Date>("passwordCreationDate"));
		expiresCol.setCellValueFactory(new PropertyValueFactory<Account, Date>("passwordExpirationDate"));
		
		ObservableList<Account> userAccounts = getAccounts();
		
		FilteredList<Account> filteredData = new FilteredList<>(userAccounts, b -> true);
		
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
		
		URL url = getClass().getClassLoader().getResource("view/AddAccount.fxml");
		try {
			AnchorPane pagePane = (AnchorPane) FXMLLoader.load(url);
			
			VBox mainBox = commonObject.getMainBox();
			
			mainBox.getChildren().clear();
			mainBox.getChildren().add(pagePane);
			VBox vbox = (VBox) pagePane.getChildren().get(1);
			vbox.getChildren().remove(5);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	@FXML public void showResetMasterPasswordPage() {
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

}
