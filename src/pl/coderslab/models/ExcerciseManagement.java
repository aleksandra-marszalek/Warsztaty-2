package pl.coderslab.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ExcerciseManagement {
	public static void main(String[] args) {
	//	excerciseManagement();
		
	}
	
	public static void excerciseManagement () {
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop_2?useSSL=false",
	            "root", "coderslab")) {
			
		// welcome to the program, show all excercises using simple for loop
			System.out.println("Welcome to the Excercise Management System. Here are all the current Excercises: ");
			Excercise[] allExcercises = Excercise.loadAllExcercises(conn);
				for (int i=0; i<allExcercises.length; i++) {
				Excercise.loadAllExcercises(conn)[i].showExcercise();
				System.out.println("");
				}
		// show the options
			System.out.println("Please choose right command: add/edit/delete/quit");
			Scanner scan = new Scanner(System.in);
			String input1 = scan.nextLine();
			while (!input1.equalsIgnoreCase("add") && !input1.equalsIgnoreCase("edit") && 
				   !input1.equalsIgnoreCase("delete") && !input1.equalsIgnoreCase("quit")) {
				System.out.println("I don't understand. Please insert the right command: add/edit/delete/quit");
				input1 = scan.nextLine();
			}
		// putting everything in the while loop which ends when the input equals quit
			while (!input1.equalsIgnoreCase("quit")) {
				try {
		// if user's input is add - the program is asking for the data needed and uses the savetoDB method
				if (input1.equalsIgnoreCase("add")) {
					Excercise excerciseNew = new Excercise();
					System.out.println("Please insert new title: ");
					String title = scan.nextLine();
					excerciseNew.setTitle(title);
					System.out.println("Please insert new description: ");
					String description = scan.nextLine();
					excerciseNew.setDescription(description);
					excerciseNew.saveToDB(conn);	
					
		// if the user's	 input is edit - the program is asking for the id of the user to edit, then asks for new data and uses saveToDB
				} else if (input1.equalsIgnoreCase("edit")) {
					System.out.println("Please insert the id of the Excercise You want to edit: ");
					int exToEditId = Integer.parseInt(scan.nextLine());
					Excercise excerciseNew = Excercise.loadExcerciseById(conn, exToEditId);
					System.out.println("Please insert new title: ");
					String title = scan.nextLine();
					excerciseNew.setTitle(title);
					System.out.println("Please insert new description: ");
					String description = scan.nextLine();
					excerciseNew.setDescription(description);
					excerciseNew.saveToDB(conn);	
			
			//if the users input is delete - asks for the id nr and saves user data to new user object		
				} else if (input1.equalsIgnoreCase("delete")) {
					System.out.println("Please insert the id of the excercise You want to delete: ");
					int exToEditId = Integer.parseInt(scan.nextLine());
					Excercise excerciseNew = Excercise.loadExcerciseById(conn, exToEditId);
			// extra option for me - asks if the user is sure to delete all the data - with loop - wait for the answer yes/no
			//only after given the confirmation, deletes the user data with delete method
					System.out.println("Are you sure to delete all the data from this user?");
					excerciseNew.showExcercise();
					System.out.println("\nWrite yes to delete / no to abort: ");
					String decision = scan.nextLine();
					while (!decision.equalsIgnoreCase("yes") && !decision.equalsIgnoreCase("no")) {
						System.out.println("\nWrite yes to delete / no to abort: ");
						decision = scan.nextLine();
					}
					if (decision.equalsIgnoreCase("yes")) {
						excerciseNew.delete(conn);
						System.out.println("You have deleted the chosen user.");
					} else {
						System.out.println("The user has not been deleted.");
					}
					
				}
		// After the chosen method, program again shows all the users data 		
		
				System.out.println("Here are all the current Excercises: ");
				allExcercises = Excercise.loadAllExcercises(conn);
					for (int i=0; i<allExcercises.length; i++) {
					Excercise.loadAllExcercises(conn)[i].showExcercise();
					System.out.println("");
					}
					
			// try catch - not to break the program if the input is incorrect --> it sends the user to another command	
					
				} catch (SQLException e) {
					System.out.println("I cannot process the data given. This operation is not possible for the input.");
				} catch (NumberFormatException e) {
					System.out.println("Incorrect input type. Please try again with correct data.");
				} catch (Exception e){
					System.out.println("Error: " + e.getMessage());
				} 
				
			// ask if there is anything else the user would like to do - if quit - the program stops
				System.out.println("Would you like to do anything else? Please choose right command: add/edit/delete/quit: ");
				input1 = scan.nextLine();
				
			
			}
			
			// confirmation that the program has stopped.
			System.out.println("You have quit the excercise manager. See you later!");
			
		
		} catch (Exception e) {
			System.out.println("Error! Please try again later! "+ e.getMessage());
		}
	}

}