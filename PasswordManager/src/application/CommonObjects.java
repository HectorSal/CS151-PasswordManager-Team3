package application;

import java.util.Random;

import application.dao.AccountDataAccessObject;
import application.dao.UserDataAccessObject;
import application.model.User;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.scene.input.Clipboard;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CommonObjects {
	
	private static CommonObjects commonObjects = new CommonObjects();
	
	private VBox mainBox;
	private Random generator;
	private AccountDataAccessObject accountDAO;
	private UserDataAccessObject userDAO;
	private User currentUser;
	private Stage primaryStage;
	private boolean userIsLoggedIn;
	private PassUtil passUtil;
	private Clipboard clipboard;
	
	private CommonObjects() {
		
	}
	
	public static CommonObjects getInstance() {
		return commonObjects;
	}

	public VBox getMainBox() {
		return mainBox;
	}

	public void setMainBox(VBox mainBox) {
		this.mainBox = mainBox;
	}

	public Random getGenerator() {
		return generator;
	}

	public void setGenerator(Random generator) {
		this.generator = generator;
	}

	public AccountDataAccessObject getAccountDAO() {
		return accountDAO;
	}

	public void setAccountDAO(AccountDataAccessObject accountDAO) {
		this.accountDAO = accountDAO;
	}

	public UserDataAccessObject getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDataAccessObject userDAO) {
		this.userDAO = userDAO;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public boolean isUserIsLoggedIn() {
		return userIsLoggedIn;
	}

	public void setUserIsLoggedIn(boolean userIsLoggedIn) {
		this.userIsLoggedIn = userIsLoggedIn;
	}

	public PassUtil getPassUtil() {
		return passUtil;
	}

	public void setPassUtil(PassUtil passUtil) {
		this.passUtil = passUtil;
	}

	public Clipboard getClipboard() {
		return clipboard;
	}

	public void setClipboard(Clipboard clipboard) {
		this.clipboard = clipboard;
	}
	
	
	
}
