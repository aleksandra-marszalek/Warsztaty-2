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
	public User(String username, String email,  String password) {
		this.username = username;
		this.email = email;
		this.setPassword(password);
	}
	public User() {
		
	}
	public void saveToDB(Connection conn) throws SQLException { 
		if (this.id == 0) {
			String sql = "INSERT INTO Users(username, email, password) VALUES (?, ?, ?)"; 
			// jakie kolumny baza zwróci po zapisie nowego obiektu - w tym wypadku id
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns); 
			preparedStatement.setString(1, this.username);
			preparedStatement.setString(2, this.email);
			preparedStatement.setString(3, this.password); 
			preparedStatement.executeUpdate();
			
			//wyrzuca w resultsecie generatedColumns
			ResultSet rs = preparedStatement.getGeneratedKeys(); 
			
			//Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu.
			if (rs.next()) {
				this.id = rs.getInt(1); 
				}
			} else {
			 String sql = "UPDATE Users SET username=?, email=?, password=? where id = ?"; 
			 PreparedStatement preparedStatement;
			 preparedStatement = conn.prepareStatement(sql); 
			 preparedStatement.setString(1, this.username);
			 preparedStatement.setString(2, this.email);
			 preparedStatement.setString(3, this.password);
			 preparedStatement.setInt(4, this.id);
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
		 
}
