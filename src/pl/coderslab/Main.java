package pl.coderslab;

import java.sql.Connection;
import java.sql.DriverManager;

import pl.coderslab.models.Excercise;
import pl.coderslab.models.Group;
import pl.coderslab.models.Solution;
import pl.coderslab.models.User;

/*zakomentowane są wywoływane metody*/


public class Main {
		
	
	public static void main(String[] args) {
		
	
		User user = new User();
		user.setUsername("Jan Kowalsky");
		user.setEmail("jankowalsky@gmail.com");
		user.setPassword("jan1234");
		
		User user2 = new User();
		user2.setUsername("Janina Nowak");
		user2.setPassword("janina123");
		
		User user3 = new User();
		user3.setUsername("Katarzyna Wojciechowska");
		user3.setEmail("kwoj@buziaczek.pl");
		user3.setPassword("Kasia123");
		user3.setUser_group_id(1);
		
		Group group1 = new Group();
		group1.setName("AMELCO");
		
		Group group2 = new Group();
		group2.setName("Python_07");
		
		Excercise excercise1 = new Excercise();
		excercise1.setTitle("Ex 1");
		excercise1.setDescription("Description of Ex1");
		
		Excercise excercise2 = new Excercise();
		excercise2.setTitle("Ex2");
		excercise2.setDescription("Description of Ex2");
		
		Solution solution2 = new Solution();
		solution2.setDescription("Description of solution2 by user5.");
		solution2.setExcercise_id(2);
		solution2.setUsers_id(5);
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop_2?useSSL=false",
	            "root", "coderslab")) {
	
		//user2.saveToDB(conn);
		//System.out.println(User.loadUserById(conn, 3).getUsername());
		//System.out.println((User.loadAllUsers(conn))[0].getUsername());
		//System.out.println((User.loadAllUsers(conn)).length);
			
		/*
		 * tutaj modyfikacja istniejącego obiektu --> 
		 * wyciągam usera z bazy;
		 * zmieniamy maila;
		 * zapisujemy do bazy;
		 */
		//User userNew = User.loadUserById(conn, 3);
		//userNew.setEmail("null@gmail.com");
		//userNew.saveToDB(conn);
			
		//metoda usuwająca danego użytkownika
		//userNew.delete(conn);
			
		//Solution[] newSolution = Solution.loadAllByUserId(conn, 5);
		//newSolution[0].showSolution();
		Solution[] newSolution = Solution.loadAllByExcerciseId(conn, 2);
		newSolution[0].showSolution();
		//User[] newUser = User.loadAllbyGroupId(conn, 1);
		//newUser[1].showUser();
		
		
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
	
	}
	
	

}
