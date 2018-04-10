package pl.coderslab.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Excercise {
	private int id;
	private String title;
	private String description;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	
	public Excercise(String title, String description) {
		this.title = title;
		this.description = description;
	}
	public Excercise () {
		
	}
	
	//additional method to print the actual excercise data
		public void showExcercise() {
			System.out.println("Id: " + getId() + ", title: " + getTitle() + ", description: " + getDescription());
		}
	
	public void saveToDB(Connection conn) throws SQLException { 
		if (this.id == 0) {
			String sql = "INSERT INTO Excercise(title, description) VALUES (?, ?)"; 
			// jakie kolumny baza zwróci po zapisie nowego obiektu - w tym wypadku id
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns); 
			preparedStatement.setString(1, this.title);
			preparedStatement.setString(2, this.description);
			preparedStatement.executeUpdate();
			
			//wyrzuca w resultsecie generatedColumns
			ResultSet rs = preparedStatement.getGeneratedKeys(); 
			
			//Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu.
			if (rs.next()) {
				this.id = rs.getInt(1); 
				}
			} else {
			 String sql = "UPDATE Excercise SET title=?, description=? where id = ?"; 
			 PreparedStatement preparedStatement;
			 preparedStatement = conn.prepareStatement(sql); 
			 preparedStatement.setString(1, this.title);
			 preparedStatement.setString(2, this.description);
			 preparedStatement.setInt(3, this.id);
			 preparedStatement.executeUpdate();
			 }
		}
	static public Excercise loadExcerciseById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM Excercise where id=?";
		PreparedStatement preparedStatement;
		
		//przygotowanie Statementu z podanego stringa sql
		preparedStatement = conn.prepareStatement(sql); 
		preparedStatement.setInt(1, id);
		// wczytanie selecta 
		ResultSet resultSet = preparedStatement.executeQuery(); 
		
		//patrzy czy jest ten uzytkownik
		if (resultSet.next()) {
			
			//pobieram wartosci z resultseta - zwraca nam loadedUsera
			Excercise loadedExcercise = new Excercise();
			loadedExcercise.id = resultSet.getInt("id"); 
			loadedExcercise.title = resultSet.getString("title"); 
			loadedExcercise.description = resultSet.getString("description"); 
			return loadedExcercise;
			}
		
		//jesli nie ma usera o podanym id - zwraca null
	    return null;
	    }
	static public Excercise[] loadAllExcercises(Connection conn) throws SQLException {
		ArrayList<Excercise> excercises = new ArrayList<Excercise>();
		String sql = "SELECT * FROM Excercise"; 
		PreparedStatement preparedStatement; 
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery(); 
		while (resultSet.next()) {
			Excercise loadedExcercise = new Excercise();
			loadedExcercise.id = resultSet.getInt("id"); 
			loadedExcercise.title = resultSet.getString("title"); 
			loadedExcercise.description = resultSet.getString("description"); 
			excercises.add(loadedExcercise);
			}
		// tworzą tablicę o takiej samej wielkosci co lista
		Excercise[] uArray = new Excercise[excercises.size()]; 
		// przekopiowują listę do tablicy
		uArray = excercises.toArray(uArray); 
		return uArray;
		}
	public void delete(Connection conn) throws SQLException { 
		if (this.id != 0) {
			String sql = "DELETE FROM Excercise WHERE id= ?"; 
			PreparedStatement preparedStatement; 
			preparedStatement = conn.prepareStatement(sql); 
			preparedStatement.setInt(1, this.id); 
			preparedStatement.executeUpdate();
			this.id=0; 
		}
	}
	
	
}
