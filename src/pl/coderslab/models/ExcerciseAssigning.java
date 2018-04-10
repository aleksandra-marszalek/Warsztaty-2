package pl.coderslab.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ExcerciseAssigning {
	public static void main(String[] args) {
		excerciseAssignment();
		
		}
		
		public static void excerciseAssignment () {
			
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop_2?useSSL=false",
		            "root", "coderslab")) {
				
				System.out.println("Welcome to the Excercise Assignment System.");
				System.out.println("Please choose right command: add/view/quit");
				Scanner scan = new Scanner(System.in);
				String input1 = scan.nextLine();
				while (!input1.equalsIgnoreCase("add") && !input1.equalsIgnoreCase("view") && 
					   !input1.equalsIgnoreCase("quit")) {
					System.out.println("I don't understand. Please insert the right command: add/view/quit");
					input1 = scan.nextLine();
				}
				
				
			// putting everything in the while loop which ends when the input equals quit
				while (!input1.equalsIgnoreCase("quit")) {
					try {
			// if group's input is add - the program is asking for the data needed and uses the savetoDB method
					if (input1.equalsIgnoreCase("add")) {
						System.out.println("Here are all the current Users: ");
						User[] allUsers = User.loadAllUsers(conn);
						for (int i=0; i<allUsers.length; i++) {
							User.loadAllUsers(conn)[i].showUser();
							System.out.println("");
						}
						System.out.println("Please insert the User Id, for who you would like to assign a Solution: ");
						int userId = Integer.parseInt(scan.nextLine());
						
						
						System.out.println("Here are all the current Excercises: ");
						Excercise[] allExcercises = Excercise.loadAllExcercises(conn);
							for (int i=0; i<allExcercises.length; i++) {
							Excercise.loadAllExcercises(conn)[i].showExcercise();
							System.out.println("");
							}
						System.out.println("Please insert the id of the Excercise you would like to assign: ");
						int excerciseId = Integer.parseInt(scan.nextLine());
						
						
						Solution solutionNew = new Solution();
						LocalDateTime datetime = LocalDateTime.now();
						String dateTimeNow = datetime.toString();
						solutionNew.setCreated(dateTimeNow);
						solutionNew.setUsers_id(userId);
						solutionNew.setExcercise_id(excerciseId);
						solutionNew.saveToDB(conn);		
						System.out.println("Excercise: " + excerciseId + " has been assigned to user: " + userId);
						
			// if the user's input is edit - the program is asking for the id of the group to edit, then asks for new data and uses saveToDB
					} else if (input1.equalsIgnoreCase("view")) {
						System.out.println("Please insert the id of the User, whose Excercises you would like to see: ");
						int excercisesToView = Integer.parseInt(scan.nextLine());
						Solution[] allExcercises = Solution.loadAllByUserId(conn, excercisesToView);
						for (int i=0; i<allExcercises.length; i++) {
							allExcercises[i].showSolution();
						}
				
					}
						
				// try catch - not to break the program if the input is incorrect --> it sends the group to another command	
						
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
				System.out.println("You have quit the Excercise assignment. See you later!");
				
			
			} catch (Exception e) {
				System.out.println("Error! Please try again later! "+ e.getMessage());
			}
		}

	}


