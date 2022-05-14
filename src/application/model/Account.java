package application.model;

import java.util.Date;

public class Account {

	static final long THIRTY_DAYS_IN_MILLISECONDS = 2592000000L;
	
	private String masterUser;
	
	private String serviceName;
	private String username;
	private String email;
	private String password;
	private Date passwordCreationDate;
	private Date passwordExpirationDate;
	
	public Account(String masterUser, String serviceName, String username, String email, String password, Date passwordCreationDate, Date passwordExpirationDate) {
		this.masterUser = masterUser;
		this.serviceName = serviceName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.passwordCreationDate = passwordCreationDate;
		this.passwordExpirationDate = passwordExpirationDate;
	}
	
	@Override
	public String toString() {
		String account = "Service: " + serviceName + ", Username: " + username + ", Email: " + email;
		return account;
	}
	
	public boolean isExpired() {
		Date currentTime = new Date();
		if (currentTime.getTime() >= passwordExpirationDate.getTime()) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object o) {
		Account account = (Account) o;
		if (serviceName.equals(account.serviceName) && username.equals(account.username) && 
				email.equals(account.email) && password.equals(account.password) && 
				passwordCreationDate.equals(account.passwordCreationDate) &&
				passwordExpirationDate.equals(account.passwordExpirationDate)) {
					return true;
				}
		return false;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getPasswordCreationDate() {
		return passwordCreationDate;
	}
	public void setPasswordCreationDate(Date passwordCreationDate) {
		this.passwordCreationDate = passwordCreationDate;
	}
	public Date getPasswordExpirationDate() {
		return passwordExpirationDate;
	}
	public void setPasswordExpirationDate(Date passwordExpirationDate) {
		this.passwordExpirationDate = passwordExpirationDate;
	}

	public String getMasterUser() {
		return masterUser;
	}

	public void setMasterUser(String masterUser) {
		this.masterUser = masterUser;
	}
	

}
