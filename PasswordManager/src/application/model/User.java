package application.model;

import java.util.ArrayList;

public class User {

	private String username;
	private String password;
	private String securityQuestion;
	private String securityQuestionAnswer;
	private ArrayList<Account> listOfAccounts;
	
	public User (String username, String password, String securityQuestion, String securityQuestionAnswer, ArrayList<Account> listOfAccounts) {
		this.setUsername(username);
		this.setPassword(password);
		this.setSecurityQuestion(securityQuestion);
		this.setSecurityQuestionAnswer(securityQuestionAnswer);
		this.setListOfAccounts(listOfAccounts);
	}

	public ArrayList<Account> getExpiredAccounts() {
		ArrayList<Account> expiredAccounts = new ArrayList<Account>();
		for (Account account: listOfAccounts) {
			if (account.isExpired()) {
				expiredAccounts.add(account);
			}
		}
		return expiredAccounts;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityQuestionAnswer() {
		return securityQuestionAnswer;
	}

	public void setSecurityQuestionAnswer(String securityQuestionAnswer) {
		this.securityQuestionAnswer = securityQuestionAnswer;
	}

	public ArrayList<Account> getListOfAccounts() {
		return listOfAccounts;
	}

	public void setListOfAccounts(ArrayList<Account> listOfAccounts) {
		this.listOfAccounts = listOfAccounts;
	}
	
}
