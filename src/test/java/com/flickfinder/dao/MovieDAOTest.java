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
import com.flickfinder.model.MovieRating;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;
import com.flickfinder.util.Seeder;

/**
 * Test for the Movie Data Access Object.
 * This uses an in-memory database for testing purposes.
 */

class MovieDAOTest {

	/**
	 * The movie data access object.
	 */

	private MovieDAO movieDAO;

	/**
	 * Seeder
	 */

	Seeder seeder;

	/**
	 * Sets up the database connection and creates the tables.
	 * We are using an in-memory database for testing purposes.
	 * This gets passed to the Database class to get a connection to the database.
	 * As it's a singleton class, the entire application will use the same
	 * connection.
	 */
	@BeforeEach
	void setUp() {
		var url = "jdbc:sqlite::memory:";
		seeder = new Seeder(url);
		Database.getInstance(seeder.getConnection());
		movieDAO = new MovieDAO();

	}

	/**
	 * Tests the getAllMovies method.
	 * We expect to get a list of all movies in the database.
	 * We have seeded the database with 5 movies, so we expect to get 5 movies back.
	 * At this point, we avoid checking the actual content of the list.
	 */
	@Test
	void testGetAllMovies() {
		try {
			List<Movie> movies = movieDAO.getAllMovies();
			assertEquals(7, movies.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getMovieById method.
	 * We expect to get the movie with the specified id.
	 */
	@Test
	void testGetMovieById() {
		Movie movie;
		try {
			movie = movieDAO.getMovieById(1);
			assertEquals("The Shawshank Redemption", movie.getTitle());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getMovieById method with an invalid id. Null should be returned.
	 */
	@Test
	void testGetMovieByIdInvalidId() {
		// write an assertThrows for a SQLException

		try {
			Movie movie = movieDAO.getMovieById(1000);
			assertEquals(null, movie);
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}

	}
	
	@Test
	void testGetPeopleByMovieId() {
		MovieDAO movieDAO = new MovieDAO();
		ArrayList<Person> returnedList = new ArrayList<Person>();
		try {
			returnedList = movieDAO.getPeopleByMovieId(1);
			assertEquals(1, returnedList.get(0).getId());
			assertEquals("Tim Robbins", returnedList.get(0).getName());
			assertEquals(2, returnedList.get(1).getId());
			assertEquals("Morgan Freeman", returnedList.get(1).getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testGetAllMoviesLimit() {
		try {
			List<Movie> movies = movieDAO.getMoviesLimit(2);
			assertEquals(2, movies.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetRatingsByYear() {
		try {
			List<MovieRating> ratings = movieDAO.getRatingsByYear(1994);
			assertEquals(2, ratings.size());
			assertEquals(1, ratings.get(0).getId());
			assertEquals(7, ratings.get(1).getId());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetMoviesLimitForASpecificYear() {
		try {
			List<MovieRating> ratings = movieDAO.getMoviesLimitForASpecificYear(1994, 1);
			assertEquals(1, ratings.size());
			assertEquals(1, ratings.get(0).getId());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	@Test
	void testgetMoviesLimitedByVotesForASpecificYear() {
		try {
			List<MovieRating> ratings = movieDAO.getMoviesLimitedByVotesForASpecificYear(1994, 100);
			assertEquals(3, ratings.size());
			assertEquals(1, ratings.get(0).getId());
			assertEquals(7, ratings.get(1).getId());
			assertEquals(6, ratings.get(2).getId());
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