package com.maggied.rpsgame;

public class Shapes {
	public static final int NUMBER_OF_SHAPES = 3;
	private static final String[] SHAPE_NAMES = new String[] { "Rock", "Paper", "Scissors" };
	private static final int[][] RELATIONSHIPS = new int[][] { { 0, -1, 1 }, { 1, 0, -1 }, { -1, 1, 0 } };

	public static int compare(int playerShape, int computerShape) {
		if (playerShape < 0 || playerShape > 2 || computerShape < 0 || computerShape > 2) {
			throw new RuntimeException("Invalid input!");
		}
		return RELATIONSHIPS[playerShape][computerShape];
	}

}
