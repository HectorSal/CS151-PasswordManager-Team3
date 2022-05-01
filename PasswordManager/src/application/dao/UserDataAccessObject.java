package application.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

import application.CommonObjects;
import application.model.Account;
import application.model.User;

public class UserDataAccessObject {
	
	private CommonObjects commonObjs= CommonObjects.getInstance();
	private AccountDataAccessObject accountDAO= commonObjs.getAccountDAO();

	public User get(String id) throws IOException {
		// create a buffered reader from file path
		InputStreamReader isr = new InputStreamReader(UserDataAccessObject.class.getClassLoader().getResourceAsStream("flatFiles/Users.txt"));
		BufferedReader br = new BufferedReader(isr);
		String line = "";
		line = br.readLine();
		User user = new User(null, null, null, null, null);
		// search each line, split the elements, and compare the user (first element)
		while (line != null) {
			String[] data = line.split(", ");
			// if the user is found, then update user object variables
			if (data[0].equals(id)) {
				user.setUsername(data[0]); // update username variable
				user.setPassword(data[1]); // update password variable
				user.setSecurityQuestion(data[2]); // update security question variable
				user.setSecurityQuestionAnswer(data[3]); // update security question answer variable
				ArrayList<Account> accounts = commonObjs.getAccountDAO().getAllAccounts(id);
				user.setListOfAccounts(accounts);
				br.close();
				isr.close();
				return user;
			}
			line = br.readLine();
		}
		br.close();
		isr.close();
//		 if not found then return null
		return null;
	}
	
	/*
	 * checks if Users.txt contains the user
	 * 
	 * @param id the username to be searched
	 * @return true if the user is in the file, or false otherwise
	 * 
	 */
	
	public boolean userExists(String id) throws IOException {
		
		// create a buffered reader from file path
			InputStreamReader isr = new InputStreamReader(UserDataAccessObject.class.getClassLoader().getResourceAsStream("flatFiles/Users.txt"));
			BufferedReader br = new BufferedReader(isr);
			String line = "";
			line = br.readLine();
			// search each line, split the elements, and compare the username (first element)
			while (line != null) {
				String[] data = line.split(", ");
				// if the user is found, then return true
				if (data[0].equals(id)) {
					return true;
				}
				line = br.readLine();
			}
			br.close();
			isr.close();
			
			return false;
			
	}

	/*
	 * inserts a new user's information into Users.txt if it does not exits.
	 * 
	 * @param user the user that will be added
	 * @return true if the user is added succesfully, or false if a user is not added because the username is already taken
	 * 
	 */
	
	public boolean insertUser(User user) throws IOException {

		
		if (!userExists(user.getUsername())) {
        // file path
		    URL path = getClass().getResource("/flatFiles/Users.txt");
		    // append at the end is true
		    FileWriter fw = new FileWriter(path.getFile(), true);
		    // format of appended line
		    String appendedUser = user.getUsername() + ", " + user.getPassword() + 
		            ", " + user.getSecurityQuestion() + ", " + user.getSecurityQuestionAnswer() + "\n";
		    fw.write(appendedUser);
		    fw.close();
		
		    // create new file to store the list of accounts of the current user
		    path = new URL(getClass().getResource("/flatFiles/accounts/").toString().concat(user.getUsername() + ".txt"));
		    File userAccounts = new File(path.getPath());
		    userAccounts.createNewFile();
		    return true;
		}
		return false;
		
	}
	
	/*
	 * Updates the user's info in the text file "Users.txt"
	 * 
	 * @param user the user that will have their info updated
	 * @return true if the user exists and their info was updated, and false otherwise
	 * 
	 */
	
	public boolean updateUser(User user) throws IOException {
		
		
		// file path
		InputStreamReader isr = new InputStreamReader(UserDataAccessObject.class.getClassLoader().getResourceAsStream("flatFiles/Users.txt"));
		BufferedReader br = new BufferedReader(isr);
		String line = "";
		// add every line of the file to the list
		ArrayList<String> lines = new ArrayList<String>();
		line = br.readLine();
		while (line != null) {
			if (!line.isEmpty()) {
				lines.add(line);
			}
			line = br.readLine();
		}
		
		br.close();
		isr.close();
		
		// find the line where the user is 
		int index = 0;
		boolean found = false;
		for (String userLine:lines) {
			String[] data = userLine.split(", ");
			if (data[0].equals(user.getUsername())) {
				found = true;
				break;
			}
			index ++;
		}
		// edditedLine replaces the old line in the ArrayList
		if (found) {
		String edittedLine = user.getUsername() + ", " + user.getPassword() + 
				", " + user.getSecurityQuestion() + ", " + user.getSecurityQuestionAnswer();
		lines.set(index, edittedLine);
		// delete the users file and recreate it
		
		URL path = getClass().getResource("/flatFiles/Users.txt");
		File users = new File(path.getFile());
		users.delete();
		users.createNewFile();
		// append every line from the ArrayList
		FileWriter fw = new FileWriter(users);
		for (String userLine:lines) {
			fw.write(userLine + "\n");
		}
		
		fw.close();
		return true;
		}
		return false;
	}

}
