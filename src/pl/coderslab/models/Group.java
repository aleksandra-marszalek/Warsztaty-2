package pl.coderslab.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Group {
	private int id;
	private String name;
	public Group(String name) {
		this.name = name;
	}
	
	public Group () {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	
	public void saveToDB(Connection conn) throws SQLException { 
		if (this.id == 0) {
			String sql = "INSERT INTO User_Group (name) VALUES (?)"; 
			// jakie kolumny baza zwróci po zapisie nowego obiektu - w tym wypadku id
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns); 
			preparedStatement.setString(1, this.name);
			preparedStatement.executeUpdate();
			
			//wyrzuca w resultsecie generatedColumns
			ResultSet rs = preparedStatement.getGeneratedKeys(); 
			
			//Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu.
			if (rs.next()) {
				this.id = rs.getInt(1); 
				}
			} else {
			 String sql = "UPDATE User_Group SET name=? where id = ?"; 
			 PreparedStatement preparedStatement;
			 preparedStatement = conn.prepareStatement(sql); 
			 preparedStatement.setString(1, this.name);
			 preparedStatement.setInt(2, this.id);
			 preparedStatement.executeUpdate();
			 }
		}
	static public Group loadGroupById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM user_group where id=?";
		PreparedStatement preparedStatement;
		
		//przygotowanie Statementu z podanego stringa sql
		preparedStatement = conn.prepareStatement(sql); 
		preparedStatement.setInt(1, id);
		// wczytanie selecta 
		ResultSet resultSet = preparedStatement.executeQuery(); 
		
		//patrzy czy jest ta grupa
		if (resultSet.next()) {
			
			//pobieram wartosci z resultseta - zwraca nam loadedGroup
			Group loadedGroup = new Group();
			loadedGroup.id = resultSet.getInt("id"); 
			loadedGroup.name = resultSet.getString("name"); 
			return loadedGroup;
			}
		
		//jesli nie ma grupy o podanym id - zwraca null
	    return null;
	    }
	static public Group[] loadAllGroups(Connection conn) throws SQLException {
		ArrayList<Group> groups = new ArrayList<Group>();
		String sql = "SELECT * FROM User_group"; 
		PreparedStatement preparedStatement; 
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery(); 
		while (resultSet.next()) {
			Group loadedGroup = new Group();
			loadedGroup.id = resultSet.getInt("id"); 
			loadedGroup.name = resultSet.getString("name"); 
			groups.add(loadedGroup);
			}
		// tworzą tablicę o takiej samej wielkosci co lista
		Group[] uArray = new Group[groups.size()]; 
		// przekopiowują listę do tablicy
		uArray = groups.toArray(uArray); 
		return uArray;
		}
	public void delete(Connection conn) throws SQLException { 
		if (this.id != 0) {
			String sql = "DELETE FROM User_Group WHERE id= ?"; 
			PreparedStatement preparedStatement; 
			preparedStatement = conn.prepareStatement(sql); 
			preparedStatement.setInt(1, this.id); 
			preparedStatement.executeUpdate();
			this.id=0; 
		}
	}
	

}
