package com.flickfinder.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flickfinder.dao.MovieDAO;
import com.flickfinder.dao.PersonDAO;

import io.javalin.http.Context;

/**
 * TODO: Implement this class
 */
class PersonControllerTest {

	private Context ctx;
	private PersonDAO personDAO;
	private PersonController personController;
	
	@BeforeEach
	void setUp() {
		// We create a mock of the MovieDAO class.
		personDAO = mock(PersonDAO.class);
		// We create a mock of the Context class.
		ctx = mock(Context.class);

		// We create an instance of the MovieController class and pass the mock object
		personController = new PersonController(personDAO);
	}
	
	@Test
	void testGetAllPeople() {
		personController.getAllPeople(ctx);
		try {
			verify(personDAO).getAllPeople();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testThrows500ExceptionWhenGetAllDatabaseError() throws SQLException {
		when(personDAO.getAllPeople()).thenThrow(new SQLException());
		personController.getAllPeople(ctx);
		verify(ctx).status(500);
	}
	
	@Test
	void testGetPersonById() {
		when(ctx.pathParam("id")).thenReturn("1");
		personController.getPersonById(ctx);
		try {
			verify(personDAO).getPersonById(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testThrows500ExceptionWhenGetByIdDatabaseError() throws SQLException {
		when(ctx.pathParam("id")).thenReturn("1");
		when(personDAO.getPersonById(1)).thenThrow(new SQLException());
		personController.getPersonById(ctx);
		verify(ctx).status(500);
	}
	
	@Test
	void testThrows404ExceptionWhenNoMovieFound() throws SQLException {
		when(ctx.pathParam("id")).thenReturn("1");
		when(personDAO.getPersonById(1)).thenReturn(null);
		personController.getPersonById(ctx);
		verify(ctx).status(404);
	}
	
	@Test
	void testgetMoviesStarringPerson() {
		when(ctx.pathParam("id")).thenReturn("1");
		personController.getMoviesStarringPerson(ctx);
		try {
			verify(personDAO).getPersonById(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testThrows500ExceptionWhenTestMovieStarringPersonIdDatabaseError() throws SQLException {
		when(ctx.pathParam("id")).thenReturn("1");
		when(personDAO.getPersonById(1)).thenThrow(new SQLException());
		personController.getMoviesStarringPerson(ctx);
		verify(ctx).status(500);
	}
	
	@Test
	void testGetAllPeopleLimit() {
		when(ctx.queryParam("limit")).thenReturn("1");
		when(ctx.queryString()).thenReturn("limit");
		personController.getAllPeople(ctx);
		try {
			verify(personDAO).getPeopleLimit(Integer.parseInt(ctx.queryParam("limit")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testThrows400ExceptionWhenLimitIsNegative() throws SQLException {
		when(ctx.queryParam("limit")).thenReturn("-1");
		when(ctx.queryString()).thenReturn("limit");
		personController.getAllPeople(ctx);
		verify(ctx).status(400);
	}
}
