package application.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.io.FileWriter;

import application.CommonObjects;
import application.model.Account;

public class AccountDataAccessObject {

	public ArrayList<Account> getAllAccounts(String user) throws IOException {
		// file path
		File file = new File("resources/flatFiles/accounts/" + user + ".txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		line = br.readLine();
		ArrayList<Account> accounts = new ArrayList<Account>();
		while (line != null) {
			// update account info with all data on the line
			if (!line.isEmpty()) {
			Account current = new Account(null, null, null, null, null, null, null);
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
			}
			line = br.readLine();
		}
		
		br.close();
		fr.close();
		return accounts;
	}

	public void insertAccount(Account account) throws IOException {
		
		
		
		String user = account.getMasterUser(); // user which this account belongs to
		
		// access file for storing the user's list of accounts
		File file = new File("resources/flatFiles/accounts/" + user + ".txt");
		// append at the end is true
		FileWriter fw = new FileWriter(file, true);
		String appendedAccount = account.getServiceName() + ", " + account.getUsername() + 
				", " + account.getEmail() + ", " + account.getPassword() + ", " + account.getPasswordCreationDate().getTime() + 
				", " + account.getPasswordExpirationDate().getTime() + "\n";
		fw.write(appendedAccount);
		fw.close();
		
	}
	
	public int deleteAccount(Account account) throws IOException {
		
		String user = account.getMasterUser(); // user which this account belongs to
		
		// access file for storing the user's list of accounts
		File file = new File("resources/flatFiles/accounts/" + user + ".txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		
		// add every line of the file to the list
		// find the line where the account is, then store the account in the variable deletedAccount
		boolean found = false;
		ArrayList<String> lines = new ArrayList<String>();
		int index = 0;
		int count = 0;
		while (line != null) {
			// if the account is found, the line will not be added to the ArrayList
			if (!line.isEmpty()) {
				Account current = new Account(null, null, null, null, null, null, null);
				String[] data = line.split(", ");
				current.setMasterUser(user);
				current.setServiceName(data[0]);
				current.setUsername(data[1]);
				current.setEmail(data[2]);
				current.setPassword(data[3]);
				String dateString = data[4];
				//// Dates are represented as the number of milliseconds since January 1st, 1970 00:00:00 UTC.
				Long dateLong = Long.parseLong(dateString);
				Date date = new Date(dateLong);
				current.setPasswordCreationDate(date);
				dateString = data[5];
				dateLong = Long.parseLong(dateString);
				date = new Date(dateLong);
				current.setPasswordExpirationDate(date);
				if (!account.equals(current)) {
					lines.add(line);
					count ++;
				}
				else {
					index = count;
					found = true;
				}
			}
			line = br.readLine();
		}
		
		
		br.close();
		fr.close();
		
		if (found) {
			File accounts = new File("resources/flatFiles/accounts/" + user + ".txt");
			accounts.delete();
			accounts.createNewFile();
			// append every line from the ArrayList
			FileWriter fw = new FileWriter(accounts);
			for (String userLine:lines) {
				fw.write(userLine + "\n");
			}
			fw.close();
			return index;
		}
		return -1;
	}

}
