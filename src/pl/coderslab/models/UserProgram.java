package pl.coderslab.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class UserProgram { 
	public static void main(String[] args) {
		userView();
		
		}
	
	/*
	 * This is the user Program - enables user to add solutions to the excercises assigned for the user.
	 * 
	 * 
	 */
	
		
		public static void userView () {

	try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop_2?useSSL=false",
            "root", "coderslab")) {
		
		System.out.println("Welcome to the User Program!");
		System.out.println("Please insert Your User Id: ");
		Scanner scan = new Scanner(System.in);
		while (!scan.hasNextInt()) {
			System.out.println("Please insert right User Id!");
			scan.next();
		}
		int userId = scan.nextInt();
		User[] usersIdList = User.loadAllUsers(conn);
		boolean isUserValid = false;
		for (int i=0; i<usersIdList.length; i++) {
			if (usersIdList[i].getId() == userId) {
				System.out.println("Welcome, " + usersIdList[i].getUsername());
				isUserValid = true;
			}
		}
//  if there is no user in database with this id - it will switch the program off
		if (isUserValid) {
		
			System.out.println("Please choose right command: \n"
					+ "add - to add new solutions to the chosen excercise\n"
					+ "view - to view all your solutions\n"
					+ "quit - to quit program");
			
			scan.nextLine();
			String input1 = scan.nextLine();
			
			while (!input1.equalsIgnoreCase("add") && !input1.equalsIgnoreCase("view") && 
				   !input1.equalsIgnoreCase("quit")) {
				System.out.println("I don't understand. Please insert the right command: add/view/quit");
				input1 = scan.nextLine();
			}
			
			
		// putting everything in the while loop which ends when the input equals quit
			while (!input1.equalsIgnoreCase("quit")) {
				try {
		
	// for add input 				
				if (input1.equalsIgnoreCase("add")) {
					Solution[] allSolutions = Solution.loadAllNotUpdatedByUserId(conn, userId);
					
					
	// if user has no excercises to add solution to - nothing to do
					if (allSolutions.length == 0) {
						System.out.println("There are no excercises which you can add solution to.");
						
	// else there is the list of the excercises					
					} else {
						System.out.println("Here are all the excercises you have not given the solutions to: ");
						for (int i=0; i<allSolutions.length; i++) {
							allSolutions[i].showSolution();
							System.out.println("");
						}
						
	// ask user for the excercise id to add solution to
						System.out.println("Please insert the Excercise Id, for which you would like to add a Solution: ");
						
	// waits for number to be inserted
						while (!scan.hasNextInt()) {
							System.out.println("Please insert right Excercise id!");
							scan.next();
						}
	// if number - proceeds to inserting solution
						int excerciseId = scan.nextInt();
						for  (int i=0; i<allSolutions.length; i++) {
							

	// if valid id of excercise --> inserts solution
							if (allSolutions[i].getExcercise_id() == excerciseId) {
								System.out.println("Insert your solution: ");
								scan.nextLine();
								String descriptionNew = scan.nextLine();		
								String updatedNew = LocalDateTime.now().toString();
								String set = "UPDATE solution SET updated=?, description=? where excercise_id = ? and users_id = ?";
								PreparedStatement preparedStatement;
								preparedStatement = conn.prepareStatement(set);
								preparedStatement.setString(1, updatedNew);
								preparedStatement.setString(2, descriptionNew);
								preparedStatement.setInt(3, excerciseId);
								preparedStatement.setInt(4, userId);
								preparedStatement.executeUpdate();
								
								
	// if not valid id of excercise - no possibility to add one
							} else {
								System.out.println("No such excercise / already given solution for this excercise.");
							}
						}
					}
					
	// if users input inupt is view:
	
				} else if (input1.equalsIgnoreCase("view")) {
					Solution[] allSolutions = Solution.loadAllByUserId(conn, userId);
					
	// if user has no excercises assigned				
					if (allSolutions.length == 0) {
						System.out.println("No excercises for this user.");
						
	// if user has excercises - enables to see all
					} else {
						System.out.println("Here are all of your solutions: ");
						for (int i=0; i<allSolutions.length; i++) {
							Solution.loadAllByUserId(conn, userId)[i].showSolution();;
							System.out.println("");
					}
					}
			
				}
					
				} catch (SQLException e) {
					System.out.println("I cannot process the data given. This operation is not possible for the input.");
				} catch (NumberFormatException e) {
					System.out.println("Incorrect input type. Please try again with correct data.");
				} catch (Exception e){
					System.out.println("Error: " + e.getMessage());
				} 
				
			// ask if there is anything else the user would like to do - if quit - the program stops
				System.out.println("Would you like to do anything else? Please choose right command: add/view/quit: ");
				input1 = scan.nextLine();
				
			
			}
			
			// confirmation that the program has stopped.
			System.out.println("You have quit the User Panel. See you later!");
		} else {
			System.out.println("There is no user with such Id. Please try again later.");
		}
	
	} catch (Exception e) {
		System.out.println("Error! Please try again later! "+ e.getMessage());
	}
}
}


