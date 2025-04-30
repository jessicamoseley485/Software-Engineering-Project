package com.flickfinder.model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * TODO: Implement this class
 * 
 */

class PersonTest {

	private Person person;
	
	@BeforeEach
	public void setUp() {
		person = new Person(1, "John Doe", 1980);
	}
	
	@Test
	public void testPersonCreated() {
		assertEquals(1, person.getId());
		assertEquals("John Doe", person.getName());
		assertEquals(1980, person.getBirth());
	}
	
	@Test
	public void testPersonSetters() {
		person.setId(2);
		person.setName("Jonathan Sims");
		person.setBirth(1988);
		assertEquals(2, person.getId());
		assertEquals("Jonathan Sims", person.getName());
		assertEquals(1988, person.getBirth());
	}

}