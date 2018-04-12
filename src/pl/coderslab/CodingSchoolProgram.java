package pl.coderslab;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

import pl.coderslab.ManagementProgram;

public class CodingSchoolProgram {
	public static void main(String[] args) {
		MainProgram();
		
	}
	
	/*
	 * This is main program - 
	 * it uses user panel prepared in UserProgram class and administrative panel prepared in ManagementProgram class.
	 * 
	 */
	
	public static void MainProgram () {
		System.out.println("Welcome to the Coding School Program.");
		System.out.println("Please choose:\nU - if you want to continue as a User\nA - if you want to use Administrative Options "
				+ "\nQ - if you want to quit.");
		Scanner scan = new Scanner(System.in);
			String input1 = scan.nextLine();
			while (!input1.equalsIgnoreCase("u") && !input1.equalsIgnoreCase("a") && 
					   !input1.equalsIgnoreCase("q")) {
					System.out.println("I don't understand. Please insert the right command: U/A/Q");
					input1 = scan.nextLine();
				}
				
				
		
				while (!input1.equalsIgnoreCase("q")) {
					try {
	
						if (input1.equalsIgnoreCase("u")) {
							UserProgram.userView();
						} else if (input1.equalsIgnoreCase("a")) {
							ManagementProgram.managementProgram();
						}
					} catch (NumberFormatException e) {
						System.out.println("Incorrect input type. Please try again with correct data.");
					} catch (Exception e){
						System.out.println("Error: " + e.getMessage());
					} 
					
				
					System.out.println("Would you like to do anything else? Please choose right command:\n"
							+ "U - User Panel\n"
							+ "A - Administrative Panel \n"
							+ "Q - Quit Program.");
					input1 = scan.nextLine();
				}
				
				// confirmation that the program has stopped.
				System.out.println("You have quit the Coding School Program. See you later!");
	}

}
