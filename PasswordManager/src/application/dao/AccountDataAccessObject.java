package application.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.io.FileWriter;

import application.model.Account;

public class AccountDataAccessObject {

	public ArrayList<Account> getAllAccounts(String user) throws IOException {
		// file path
		String path = "flatFiles/accounts/" + user + ".txt";
		InputStreamReader isr = new InputStreamReader(AccountDataAccessObject.class.getClassLoader().getResourceAsStream(path));
		BufferedReader br = new BufferedReader(isr);
		String line = "";
		line = br.readLine();
		ArrayList<Account> accounts = new ArrayList<Account>();
		while (line != null) {
			// update account info with all data on the line
			if (!line.isEmpty()) {
			Account current = new Account(null, null, null, null, null, null);
			String[] data = line.split(", ");
			current.setMasterUser(user);
			current.setServiceName(data[0]);
			current.setUsername(data[1]);
			current.setEmail(data[2]);
			current.setPassword(data[3]);
			String dateString = data[4];
			// Dates are represented as the number of milliseconds since January 1st, 1970 00:00:00 UTC.
			Long dateLong = Long.parseLong(dateString);
			Date date = new Date(dateLong);
			current.setPasswordCreationDate(date);
			dateString = data[5];
			dateLong = Long.parseLong(dateString);
			date = new Date(dateLong);
			current.setPasswordExpirationDate(date);
			accounts.add(current);
			line = br.readLine();
			}
		}
		
		br.close();
		isr.close();
		return accounts;
	}

	public void insertAccount(Account account) throws IOException {
		
		String user = account.getMasterUser(); // user which this account belongs to
		
		// access file for storing the user's list of accounts
		String path = "resources/flatFiles/accounts/" + user + ".txt";
		// append at the end is true
		FileWriter fw = new FileWriter(path, true);
		String appendedAccount = account.getServiceName() + ", " + account.getUsername() + 
				", " + account.getEmail() + ", " + account.getPassword() + ", " + account.getPasswordCreationDate().getTime() + 
				", " + account.getPasswordExpirationDate() + "\n";
		fw.write(appendedAccount);
		fw.close();
		
		
		
	}

}
