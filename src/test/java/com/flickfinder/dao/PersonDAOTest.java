package com.flickfinder.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;
import com.flickfinder.util.Seeder;

/**
 * TODO: Implement this class
 */
class PersonDAOTest {

	private PersonDAO personDAO;
	
	Seeder seeder;
	
	@BeforeEach
	void setUp() {
		var url = "jdbc:sqlite::memory:";
		seeder = new Seeder(url);
		Database.getInstance(seeder.getConnection());
		personDAO = new PersonDAO();
	}
	
	@Test
	void testGetAllPeople() {
		try {
			List<Person> people = personDAO.getAllPeople();
			assertEquals(5, people.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetPersonById() {
		Person person;
		try {
			person = personDAO.getPersonById(1);
			assertEquals("Tim Robbins", person.getName());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetPersonByIdInvalidId() {
		// write an assertThrows for a SQLException

		try {
			Person person = personDAO.getPersonById(1000);
			assertEquals(null, person);
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}

	}
	
	@Test
	void testGetMoviesStarringPerson() {
		PersonDAO personDAO = new PersonDAO();
		ArrayList<Movie> returnedList = new ArrayList<Movie>();
		try {
			returnedList = personDAO.getMoviesStarringPerson(4);
			assertEquals(2, returnedList.get(0).getId());
			assertEquals("The Godfather", returnedList.get(0).getTitle());
			assertEquals(3, returnedList.get(1).getId());
			assertEquals("The Godfather: Part II", returnedList.get(1).getTitle());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testGetAllPeopleLimit() {
		try {
			List<Person> people = personDAO.getPeopleLimit(2);
			assertEquals(2, people.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	@AfterEach
	void tearDown() {
		seeder.closeConnection();
	}
	
}