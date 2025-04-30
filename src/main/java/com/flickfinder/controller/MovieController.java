package com.flickfinder.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flickfinder.dao.MovieDAO;
import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;

import io.javalin.http.Context;

/**
 * The controller for the movie endpoints.
 * 
 * The controller acts as an intermediary between the HTTP routes and the DAO.
 * 
 * As you can see each method in the controller class is responsible for
 * handling a specific HTTP request.
 * 
 * Methods a Javalin Context object as a parameter and uses it to send a
 * response back to the client.
 * We also handle business logic in the controller, such as validating input and
 * handling errors.
 *
 * Notice that the methods don't return anything. Instead, they use the Javalin
 * Context object to send a response back to the client.
 */

public class MovieController {

	/**
	 * The movie data access object.
	 */

	private final MovieDAO movieDAO;

	/**
	 * Constructs a MovieController object and initializes the movieDAO.
	 */
	public MovieController(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}

	/**
	 * Returns a list of all movies in the database.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getAllMovies(Context ctx) {
		String query = ctx.queryString();
		if (query != null) {
			if (query.contains("limit") == true) {
				int limit = Integer.parseInt(ctx.queryParam("limit"));
				if (limit <= 0) {
					ctx.status(400);
					ctx.result("Invalid limit");
				} else {
					try {	
						ctx.json(movieDAO.getMoviesLimit(limit));	
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
			
			
		} else {
			
			try {
				ctx.json(movieDAO.getAllMovies());
			} catch (SQLException e) {
				ctx.status(500);
				ctx.result("Database error");
				e.printStackTrace();
			}
		 }
	}

	/**
	 * Returns the movie with the specified id.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getMovieById(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Movie movie = movieDAO.getMovieById(id);
			if (movie == null) {
				ctx.status(404);
				ctx.result("Movie not found");
				return;
			}
			ctx.json(movieDAO.getMovieById(id));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
	
	public void getPeopleByMovieId(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		try {	
			Movie movie = movieDAO.getMovieById(id);
			if (movie == null) {
				ctx.status(404);
				ctx.result("Movie not found");
				return;
			}
			ctx.json(movieDAO.getPeopleByMovieId(movie.getId()));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
		
	
	public void getRatingsByYear(Context ctx) {
		String query = ctx.queryString();
		int year = Integer.parseInt(ctx.pathParam("year"));
		if (query != null) {
			if (query.contains("limit") == true && ctx.queryParam("limit") != null) {
				int limit = Integer.parseInt(ctx.queryParam("limit"));
				if (limit <= 0) {
					ctx.status(400);
					ctx.result("Invalid limit");
				} else {
					try {	
						ctx.json(movieDAO.getMoviesLimitForASpecificYear(year, limit));	
					} catch (SQLException e) {
						ctx.status(500);
						ctx.result("Database error");
						e.printStackTrace();
					}
				}
				
			} else if (query.contains("votes") == true && ctx.queryParam("votes") != null) {
				int votes = Integer.parseInt(ctx.queryParam("votes"));
				if (votes <= 0) {
					ctx.status(400);
					ctx.result("Invalid votes");
				} else {
					try {	
						ctx.json(movieDAO.getMoviesLimitedByVotesForASpecificYear(year, votes));	
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
		} else {
			try {
				ctx.json(movieDAO.getRatingsByYear(year));
			} catch (SQLException e) {
				ctx.status(500);
				ctx.result("Database error");
				e.printStackTrace();
			}
		}
		
	}

}
	
		