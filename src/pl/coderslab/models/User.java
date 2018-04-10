package pl.coderslab.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//odwołanie do biblioteki BCrypt - wrzucony plik .java do tego samego katalogu, zmiana package'u
import pl.coderslab.BCrypt;

public class User {
	private int id;
	private String username;
	private String email;
	private String password;
	private int user_group_id;
	

	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}
	public String getPassword() {
		return password;
	}
	public int getUser_group_id() {
		return user_group_id;
	}
	public void setUser_group_id(int user_group_id) {
		this.user_group_id = user_group_id;
	}
	
	public User(String username, String email,  String password, int user_group_id) {
		this.username = username;
		this.email = email;
		this.setPassword(password);
		this.user_group_id = user_group_id;
	}
	public User() {
		
	}
	
	//additional method to print the actual user data
	public void showUser() {
		System.out.println("Username: " + getUsername() + ", email: " + getEmail() + ", password: " + getPassword() + ", user_group_id: " + getUser_group_id());
	}
	
	public void saveToDB(Connection conn) throws SQLException { 
		if (this.id == 0) {
			String sql = "INSERT INTO Users(username, email, password, user_group_id) VALUES (?, ?, ?, ?)"; 
			// jakie kolumny baza zwróci po zapisie nowego obiektu - w tym wypadku id
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns); 
			preparedStatement.setString(1, this.username);
			preparedStatement.setString(2, this.email);
			preparedStatement.setString(3, this.password); 
			preparedStatement.setInt(4, this.user_group_id);
			preparedStatement.executeUpdate();
			
			//wyrzuca w resultsecie generatedColumns
			ResultSet rs = preparedStatement.getGeneratedKeys(); 
			
			//Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu.
			if (rs.next()) {
				this.id = rs.getInt(1); 
				}
			} else {
			 String sql = "UPDATE Users SET username=?, email=?, password=?, user_group_id=? where id = ?"; 
			 PreparedStatement preparedStatement;
			 preparedStatement = conn.prepareStatement(sql); 
			 preparedStatement.setString(1, this.username);
			 preparedStatement.setString(2, this.email);
			 preparedStatement.setString(3, this.password);
			 preparedStatement.setInt(4, this.user_group_id);
			 preparedStatement.setInt(5, this.id);
			 preparedStatement.executeUpdate();
			 }
		}
	static public User loadUserById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM Users where id=?";
		PreparedStatement preparedStatement;
		
		//przygotowanie Statementu z podanego stringa sql
		preparedStatement = conn.prepareStatement(sql); 
		preparedStatement.setInt(1, id);
		// wczytanie selecta 
		ResultSet resultSet = preparedStatement.executeQuery(); 
		
		//patrzy czy jest ten uzytkownik
		if (resultSet.next()) {
			
			//pobieram wartosci z resultseta - zwraca nam loadedUsera
			User loadedUser = new User();
			loadedUser.id = resultSet.getInt("id"); 
			loadedUser.username = resultSet.getString("username"); 
			loadedUser.password = resultSet.getString("password"); 
			loadedUser.email = resultSet.getString("email"); 
			loadedUser.user_group_id = resultSet.getInt("user_group_id");
			return loadedUser;
			}
		
		//jesli nie ma usera o podanym id - zwraca null
	    return null;
	    }
	static public User[] loadAllUsers(Connection conn) throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM Users"; 
		PreparedStatement preparedStatement; 
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery(); 
		while (resultSet.next()) {
			User loadedUser = new User();
			loadedUser.id = resultSet.getInt("id"); 
			loadedUser.username = resultSet.getString("username"); 
			loadedUser.password = resultSet.getString("password"); 
			loadedUser.email = resultSet.getString("email"); 
			loadedUser.user_group_id = resultSet.getInt("user_group_id");
			users.add(loadedUser);
			}
		// tworzą tablicę o takiej samej wielkosci co lista
		User[] uArray = new User[users.size()]; 
		// przekopiowują listę do tablicy
		uArray = users.toArray(uArray); 
		return uArray;
		}
	public void delete(Connection conn) throws SQLException { 
		if (this.id != 0) {
			String sql = "DELETE FROM Users WHERE id= ?"; 
			PreparedStatement preparedStatement; 
			preparedStatement = conn.prepareStatement(sql); 
			preparedStatement.setInt(1, this.id); 
			preparedStatement.executeUpdate();
			this.id=0; 
		}
	}
	
	static public User[] loadAllbyGroupId(Connection conn, int id) throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		String sql = "select users.id, username, email, password, user_group_id from Users join user_group on users.user_group_id = user_group.id where user_group_id=?;"; 
		PreparedStatement preparedStatement;
		
		//przygotowanie Statementu z podanego stringa sql
		preparedStatement = conn.prepareStatement(sql); 
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery(); 
		
		
		while (resultSet.next()) {
			User loadedUser = new User();
			loadedUser.id = resultSet.getInt("id"); 
			loadedUser.username = resultSet.getString("username"); 
			loadedUser.email = resultSet.getString("email"); 
			loadedUser.password = resultSet.getString("password");
			loadedUser.user_group_id = resultSet.getInt("user_group_id");
			users.add(loadedUser);
			}
		// tworzą tablicę o takiej samej wielkosci co lista
		User[] uArray = new User[users.size()]; 
		// przekopiowują listę do tablicy
		uArray = users.toArray(uArray); 
		return uArray;
		}
		 
}
