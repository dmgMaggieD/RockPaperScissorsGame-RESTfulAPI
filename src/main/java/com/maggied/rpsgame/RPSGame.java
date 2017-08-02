package com.maggied.rpsgame;

public class RPSGame implements Game {
	public static final int NUMBER_OF_ROUNDS = 3;

	private int currentRound;
	private long gameId;
	private String playerId;

	/*
	 * ScoreBoard: store information of every round. Row: one round of game.
	 * Column0: result, 1 player lose, 0 Tie, 1 player win. Column1: playerShape.
	 * Column2: computerShape.
	 */
	private int[][] scoreBoard;
	private String status;

	// Generate computerShape randomly
	public static Shape generateComputerShape() {
		return new Shape((int) (Math.random() * Shape.NUMBER_OF_SHAPES));
	}

	public RPSGame(long gameId, String playerId) {
		this.gameId = gameId;
		this.playerId = playerId;
		this.scoreBoard = new int[NUMBER_OF_ROUNDS][3];
		this.status = Game.PLALYING;
		this.currentRound = 0;
	}

	@Override
	public RPSGame reset() {
		this.scoreBoard = new int[NUMBER_OF_ROUNDS][3];
		this.status = Game.PLALYING;
		this.currentRound = 0;
		return this;
	}

	public RPSGame combat(Shape playerShape, Shape computerShape) {

		// If Game finished
		if (!status.equals(Game.PLALYING)) {
			String errMsg = String.format("Game '%d' has already finished!", gameId);
			throw new RuntimeException(errMsg);
		}

		// Compare and update scoreBoard
		int result = Shape.compare(playerShape, computerShape);
		
		currentRound++;
		scoreBoard[currentRound - 1][0] = result;
		scoreBoard[currentRound - 1][1] = playerShape.getShapeValue();
		scoreBoard[currentRound - 1][2] = computerShape.getShapeValue();

		int scoreSum = 0;
		for (int i = 0; i < currentRound; i++) {
			scoreSum += scoreBoard[i][0];
		}

		// Update game status
		if (scoreSum == 2) {
			status = Game.PLAYER_WIN;
		} else if (scoreSum == -2) {
			status = Game.COMPUTER_WIN;
		} else if (currentRound == 3) {
			if (scoreSum > 0) {
				status = Game.PLAYER_WIN;
			} else if (scoreSum < 0) {
				status = Game.COMPUTER_WIN;
			} else {
				status = Game.TIE;
			}
		}

		return this;
	}

	public boolean isPlayerGame(String playerId) {
		return this.playerId.equals(playerId);
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public long getGameId() {
		return gameId;
	}

	public String getPlayerId() {
		return playerId;
	}

	public int[][] getScoreBoard() {
		return scoreBoard;
	}

	public String getStatus() {
		return status;
	}
}
