package com.maggied.rpsgame;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShapeTest {

	@Test
	public void compareTest() {
		assertEquals(-1, Shape.compare(new Shape(0), new Shape(1)));
		assertEquals(-1, Shape.compare(new Shape(1), new Shape(2)));
		assertEquals(1, Shape.compare(new Shape(0), new Shape(2)));
		assertEquals(0, Shape.compare(new Shape(0), new Shape(0)));
		assertEquals(0, Shape.compare(new Shape(1), new Shape(1)));
		assertEquals(0, Shape.compare(new Shape(2), new Shape(2)));
	}

	@Test(expected = RuntimeException.class)
	public void compareTest_InbalidInput_1() {
		Shape.compare(new Shape(3), new Shape(1));
	}

	@Test(expected = RuntimeException.class)
	public void compareTest_InbalidInput_2() {
		Shape.compare(new Shape(-1), new Shape(1));
	}

}
