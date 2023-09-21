/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Conflict Exception class
 */
class ConflictExceptionTest {

	/**
	 * Test method for parameterized constructor for Conflict Exception
	 */
	@Test
	public void testConflictExceptionString() {
		 ConflictException ce = new ConflictException("Custom exception message");
		    assertEquals("Custom exception message", ce.getMessage());
	} 

	/**
	 * Test method for parameterless constructor for Conflict Exception
	 */
	@Test
	public void testConflictException() {
		 ConflictException ce = new ConflictException("Schedule conflict.");
		    assertEquals("Schedule conflict.", ce.getMessage());
	}

}
