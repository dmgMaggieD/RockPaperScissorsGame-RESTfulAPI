package com.maggied.rpsgame;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShapesTest {

	@Test
	public void compareTest() {
		 assertEquals(-1, Shapes.compare(0, 1));
		 assertEquals(-1, Shapes.compare(1, 2));
		 assertEquals(1, Shapes.compare(0, 2));
		 assertEquals(0, Shapes.compare(0, 0));
		 assertEquals(0, Shapes.compare(1, 1));
		 assertEquals(0, Shapes.compare(2, 2));
	}
	
	@Test(expected = RuntimeException.class)
	public void compareTest_InbalidInput_1() {
		 Shapes.compare(3, 1);
	}
	
	@Test(expected = RuntimeException.class)
	public void compareTest_InbalidInput_2() {
		 Shapes.compare(-1, 1);
	}

}
