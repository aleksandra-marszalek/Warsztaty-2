package pl.coderslab.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class GroupsManagement {

	public static void main(String[] args) {
		groupsManagement();
		
		}
		
		public static void groupsManagement () {
			
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop_2?useSSL=false",
		            "root", "coderslab")) {
				
			// welcome to the program, show all groups using simple for loop
				System.out.println("Welcome to the Groups Management System. Here are all the current Groups: ");
				Group[] allGroups = Group.loadAllGroups(conn);
					for (int i=0; i<allGroups.length; i++) {
					Group.loadAllGroups(conn)[i].showGroup();
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
			// if group's input is add - the program is asking for the data needed and uses the savetoDB method
					if (input1.equalsIgnoreCase("add")) {
						Group groupNew = new Group();
						System.out.println("Please insert new groupname: ");
						String groupname = scan.nextLine();
						groupNew.setName(groupname);
						groupNew.saveToDB(conn);	
						
			// if the user's input is edit - the program is asking for the id of the group to edit, then asks for new data and uses saveToDB
					} else if (input1.equalsIgnoreCase("edit")) {
						System.out.println("Please insert the id of the group You want to edit: ");
						int groupToEditId = Integer.parseInt(scan.nextLine());
						Group groupNew = Group.loadGroupById(conn, groupToEditId);
						System.out.println("Please insert new group Name: ");
						String groupname = scan.nextLine();
						groupNew.setName(groupname);
						groupNew.saveToDB(conn);	
				
				//if the user's input is delete - asks for the id nr and saves group data to new group object		
					} else if (input1.equalsIgnoreCase("delete")) {
						System.out.println("Please insert the id of the group You want to delete: ");
						int groupToEditId = Integer.parseInt(scan.nextLine());
						Group groupNew = Group.loadGroupById(conn, groupToEditId);
				// extra option for me - asks if the group is sure to delete all the data - with loop - wait for the answer yes/no
				//only after given the confirmation, deletes the group data with delete method
						System.out.println("Are you sure to delete all the data from this group?");
						groupNew.showGroup();
						System.out.println("\nWrite yes to delete / no to abort: ");
						String decision = scan.nextLine();
						while (!decision.equalsIgnoreCase("yes") && !decision.equalsIgnoreCase("no")) {
							System.out.println("\nWrite yes to delete / no to abort: ");
							decision = scan.nextLine();
						}
						if (decision.equalsIgnoreCase("yes")) {
							groupNew.delete(conn);
							System.out.println("You have deleted the chosen group.");
						} else {
							System.out.println("The group has not been deleted.");
						}
						
					}
			// After the chosen method, program again shows all the groups data 		
			
					System.out.println("Here are all the current Groups: ");
					allGroups = Group.loadAllGroups(conn);
						for (int i=0; i<allGroups.length; i++) {
						Group.loadAllGroups(conn)[i].showGroup();
						System.out.println("");
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
					System.out.println("Would you like do anything else? Please choose right command: add/edit/delete/quit: ");
					input1 = scan.nextLine();
					
				
				}
				
				// confirmation that the program has stopped.
				System.out.println("You have quit the program. See you later!");
				scan.close();
			
			} catch (Exception e) {
				System.out.println("Error! Please try again later! "+ e.getMessage());
			}
		}

	}
