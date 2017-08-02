package com.maggied.rpsgame;

public class Shape {
	public static final int NUMBER_OF_SHAPES = 3;
	private static final String[] SHAPE_NAMES = new String[] { "Rock", "Paper", "Scissors" };
	private static final int[][] RELATIONSHIPS = new int[][] { { 0, -1, 1 }, { 1, 0, -1 }, { -1, 1, 0 } };

	private int shapeValue;

	public Shape() {
		shapeValue = -1;
	}

	public Shape(int shapeValue) {
		this.shapeValue = shapeValue;
	}

	public static int compare(Shape playerShape, Shape computerShape) {
		if (playerShape.getShapeValue() < 0 || playerShape.getShapeValue() > 2 || computerShape.getShapeValue() < 0
				|| computerShape.getShapeValue() > 2) {
			throw new RuntimeException("Invalid input!");
		}
		return RELATIONSHIPS[playerShape.getShapeValue()][computerShape.getShapeValue()];
	}

	public int getShapeValue() {
		return shapeValue;
	}

	public void setShapeValue(int shapeValue) {
		this.shapeValue = shapeValue;
	}

}
