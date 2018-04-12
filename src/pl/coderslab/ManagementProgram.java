package pl.coderslab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import pl.coderslab.models.Excercise;
import pl.coderslab.models.ExcerciseAssigning;
import pl.coderslab.models.ExcerciseManagement;
import pl.coderslab.models.GroupsManagement;
import pl.coderslab.models.UsersManagement;

public class ManagementProgram {
	public static void main(String[] args) {
		managementProgram();
	}
	public static void managementProgram() {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop_2?useSSL=false",
	            "root", "coderslab")) {
		System.out.println("Welcome to the Management of the Coding School Program!\nPlease insert:\n"
				+ "u - if you want to manage the users.\n"
				+ "e - if you want to manage the excercises.\n"
				+ "g - if yaou want to manage the groups.\n"
				+ "a - for assigning the excercises\n"
				+ "q - if you want to quit.");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		while (!input.equalsIgnoreCase("u") && !input.equalsIgnoreCase("e") && !input.equalsIgnoreCase("g") && 
				!input.equalsIgnoreCase("a") && !input.equalsIgnoreCase("q")) {
			System.out.println("Wrong input. Please choose again: u/e/g/q/a: ");
			input = scan.nextLine();
		}
		while (!input.equalsIgnoreCase("q")) {
			try {
				// if user's input is add - the program is asking for the data needed and uses the savetoDB method
						if (input.equalsIgnoreCase("e")) {
							ExcerciseManagement.excerciseManagement();
							
				// if the user's	 input is edit - the program is asking for the id of the user to edit, then asks for new data and uses saveToDB
						} else if (input.equalsIgnoreCase("g")) {
							GroupsManagement.groupsManagement();
					
					//if the users input is delete - asks for the id nr and saves user data to new user object		
						} else if (input.equalsIgnoreCase("u")) {
							UsersManagement.usersManagement();
						} else if (input.equalsIgnoreCase("a")) {
							ExcerciseAssigning.excerciseAssignment();
						}
							
					// try catch - not to break the program if the input is incorrect --> it sends the user to another command	
							
						} catch (NumberFormatException e) {
							System.out.println("Incorrect input type. Please try again with correct data.");
						} catch (Exception e){
							System.out.println("Error: " + e.getMessage());
						} 
						
					// ask if there is anything else the user would like to do - if quit - the program stops
						System.out.println("Would you like to do anything else? Please choose the right command:\n"
								+ "u - for managing users\n"
								+ "e - for managing excercises\n"
								+ "g - for managing groups\n"
								+ "a - for assigning the excercises\n"
								+ "q - to quit.");
						input = scan.nextLine();
						
					
					}
					
					// confirmation that the program has stopped.
					System.out.println("You have quit the Administrative Panel. See you later!");
		
				
				} catch (Exception e) {
					System.out.println("Error! Please try again later! "+ e.getMessage());
				}
		}
	}

