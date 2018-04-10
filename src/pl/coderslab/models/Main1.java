package pl.coderslab.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main1 {
	
	public static void main(String[] args) {
		usersManagement();
	}
	
	public static void usersManagement () {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop_2?useSSL=false",
	            "root", "coderslab")) {
			
		// welcome to the program, show all users
			System.out.println("Welcome to the Users Management System. Here are all the current Users: ");
			User[] allUsers = User.loadAllUsers(conn);
				for (int i=0; i<allUsers.length; i++) {
				User.loadAllUsers(conn)[i].showUser();
				System.out.println("");
				}
		// show the options
			System.out.println("Please choose right command: add/edit/delete/quit");
			Scanner scan = new Scanner(System.in);
			String input1 = scan.nextLine();
			while (!input1.equals("add") && !input1.equals("edit") && !input1.equals("delete") && !input1.equals("quit")) {
				System.out.println("I don't understand. Please insert the right command: add/edit/delete/quit");
				input1 = scan.nextLine();
			}
		// putting everything in the while loop which ends when the input equals quit
			while (!input1.equals("quit")) {
				if (input1.equals("add")) {
					User userNew = new User();
					System.out.println("Please insert new username: ");
					String username = scan.nextLine();
					userNew.setUsername(username);
					System.out.println("Please insert new email: ");
					String email = scan.nextLine();
					userNew.setEmail(email);
					System.out.println("Please insert new password: ");
					String password = scan.nextLine();
					userNew.setPassword(password);
					System.out.println("Please insert right user group id: ");
					int userGroupId = Integer.parseInt(scan.nextLine());
					userNew.setUser_group_id(userGroupId);
					userNew.saveToDB(conn);
					
					
				}
				System.out.println("Here are all the current Users: ");
				allUsers = User.loadAllUsers(conn);
					for (int i=0; i<allUsers.length; i++) {
					User.loadAllUsers(conn)[i].showUser();
					System.out.println("");
					}
			System.out.println("Would you like do anything else?");
			input1 = scan.nextLine();
			}
			System.out.println("You have quit the program. See you later!");
			
		} catch (Exception e) {
			System.out.println("Error "+ e.getMessage());
		}
	}

}
