package com.flickfinder.controller;

import java.sql.SQLException;

import com.flickfinder.dao.PersonDAO;
import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;

import io.javalin.http.Context;

public class PersonController {

	private final PersonDAO personDAO;


	public PersonController(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	public void getAllPeople(Context ctx) {
		String query = ctx.queryString();
		if (query != null) {
			if (query.contains("limit") == true) {
				int limit = Integer.parseInt(ctx.queryParam("limit"));
				if (limit <= 0) {
					ctx.status(400);
					ctx.result("Invalid limit");
				} else {
					try {	
						ctx.json(personDAO.getPeopleLimit(limit));	
					} catch (SQLException e) {
						ctx.status(500);
						ctx.result("Database error");
						e.printStackTrace();
					}
				}
			} else {
				ctx.status(404);
				ctx.result("invalid url");
			}
		}
		
		else {
			try {
				ctx.json(personDAO.getAllPeople());
			} catch (SQLException e) {
				ctx.status(500);
				ctx.result("Database error");
				e.printStackTrace();
			}
		 }
	}
	
	public void getPersonById(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Person person = personDAO.getPersonById(id);
			if (person == null) {
				ctx.status(404);
				ctx.result("Person not found");
				return;
			}
			ctx.json(personDAO.getPersonById(id));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
	
	public void getMoviesStarringPerson(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		try {	
			Person person = personDAO.getPersonById(id);
			if (person == null) {
				ctx.status(404);
				ctx.result("Person not found");
				return;
			}
			ctx.json(personDAO.getMoviesStarringPerson(person.getId()));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
	


}