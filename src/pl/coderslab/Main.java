package pl.coderslab;

import java.sql.Connection;
import java.sql.DriverManager;


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
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
	
	}
	
	

}
