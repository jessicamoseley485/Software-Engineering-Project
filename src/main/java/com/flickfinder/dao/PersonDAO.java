package com.flickfinder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;


public class PersonDAO {

	private final Connection connection;


	public PersonDAO() {
		Database database = Database.getInstance();
		connection = database.getConnection();
	}
	
	public ArrayList<Person> getAllPeople() throws SQLException {
		ArrayList<Person> people = new ArrayList<>();
		
		Statement statement = connection.createStatement();
		
		ResultSet rs = statement.executeQuery("select * from people LIMIT 50");
		
		while (rs.next()) {
			people.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth")));
		}

		return people;
	}
	
	public Person getPersonById(int id) throws SQLException {
		
		String statement = "select * from people where id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth"));
		}
		
		// return null if the id does not return a movie.

		return null;
	}
	
	public ArrayList<Movie> getMoviesStarringPerson(int id) throws SQLException {
		ArrayList<Movie> movies = new ArrayList<>();
		
		String statement = "select movie_id from stars where person_id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			MovieDAO current = new MovieDAO();
			Movie currentMovie = current.getMovieById(rs.getInt("movie_id"));
			
			movies.add(new Movie(currentMovie.getId(), currentMovie.getTitle(), currentMovie.getYear()));

		}

		return movies;
	}
	
	public List<Person> getPeopleLimit(int limit) throws SQLException {
		List<Person> people = new ArrayList<>();

		String statement = "select * from people LIMIT ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, limit);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			people.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth")));
		}

		return people;
	}

}
