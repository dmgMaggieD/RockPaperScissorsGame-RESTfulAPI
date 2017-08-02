package com.maggied.rpsgame;

import static org.junit.Assert.*;

import org.junit.Test;

public class RPSGameTest {

	@Test
	public void isPlayerGameTest() {
		RPSGame testGame = new RPSGame(1, "playerTest");
		assertTrue(testGame.isPlayerGame(testGame.getPlayerId()));
	}

	@Test
	public void resetTest() {
		RPSGame game = new RPSGame(1, "playerTest");
		game.combat(0, 0);
		game.combat(1, 1);
		assertEquals(2, game.getCurrentRound());

		game.reset();

		assertEquals(0, game.getCurrentRound());
		assertEquals(1, game.getGameId());
		assertEquals("playerTest", game.getPlayerId());
		for (int i = 0; i < 3; i++) {
			assertArrayEquals(new int[3], game.getScoreBoard()[i]);
		}
	}

	@Test
	public void combatTest() {
		RPSGame game = new RPSGame(1, "playerTest");

		assertEquals(0, game.getCurrentRound());
		assertEquals(1, game.getGameId());
		assertArrayEquals(new int[3], game.getScoreBoard()[0]);

		// First round, Paper beats Rock, computer wins the round.
		game.combat(0, 1);

		assertEquals(1, game.getCurrentRound());
		assertEquals(1, game.getGameId());
		assertEquals(-1, game.getScoreBoard()[0][0]);
		assertEquals(0, game.getScoreBoard()[0][1]);
		assertEquals(1, game.getScoreBoard()[0][2]);
		assertEquals(RPSGame.PLALYING, game.getStatus());

		// Second round, Scissors beats Paper, player wins the round.
		game.combat(2, 1);

		assertEquals(2, game.getCurrentRound());
		assertEquals(1, game.getScoreBoard()[1][0]);
		assertEquals(2, game.getScoreBoard()[1][1]);
		assertEquals(1, game.getScoreBoard()[1][2]);
		assertEquals(RPSGame.PLALYING, game.getStatus());

		// Third round, Rock beats Scissors, player wins the game.
		game.combat(0, 2);

		assertEquals(3, game.getCurrentRound());
		assertEquals(1, game.getScoreBoard()[2][0]);
		assertEquals(0, game.getScoreBoard()[2][1]);
		assertEquals(2, game.getScoreBoard()[2][2]);
		assertEquals(RPSGame.PLAYER_WIN, game.getStatus());
	}

	@Test(expected = RuntimeException.class)
	public void combatTest_WinInSecondRound() {
		RPSGame game = new RPSGame(1, "playerTest");

		assertEquals(0, game.getCurrentRound());
		assertEquals(1, game.getGameId());
		assertArrayEquals(new int[3], game.getScoreBoard()[0]);

		// First round, Scissors beats Paper, player wins the round.
		game.combat(2, 1);

		assertEquals(1, game.getCurrentRound());
		assertEquals(1, game.getScoreBoard()[0][0]);
		assertEquals(2, game.getScoreBoard()[0][1]);
		assertEquals(1, game.getScoreBoard()[0][2]);
		assertEquals(RPSGame.PLALYING, game.getStatus());

		// Second round, Rock beats Scissors, player wins the game.
		game.combat(0, 2);

		assertEquals(2, game.getCurrentRound());
		assertEquals(1, game.getScoreBoard()[1][0]);
		assertEquals(0, game.getScoreBoard()[1][1]);
		assertEquals(2, game.getScoreBoard()[1][2]);
		assertEquals(RPSGame.PLAYER_WIN, game.getStatus());

		// Throw exception when try to combat in a finished game.
		game.combat(0, 2);
	}

	@Test
	public void combatTest_Tie() {
		RPSGame game = new RPSGame(1, "playerTest");

		assertEquals(0, game.getCurrentRound());
		assertEquals(1, game.getGameId());
		assertArrayEquals(new int[3], game.getScoreBoard()[0]);

		// First round, Paper beats Rock, computer wins the round.
		game.combat(0, 1);

		assertEquals(1, game.getCurrentRound());
		assertEquals(1, game.getGameId());
		assertEquals(-1, game.getScoreBoard()[0][0]);
		assertEquals(0, game.getScoreBoard()[0][1]);
		assertEquals(1, game.getScoreBoard()[0][2]);
		assertEquals(RPSGame.PLALYING, game.getStatus());

		// Second round, Scissors beats Paper, player wins the round.
		game.combat(2, 1);

		assertEquals(2, game.getCurrentRound());
		assertEquals(1, game.getScoreBoard()[1][0]);
		assertEquals(2, game.getScoreBoard()[1][1]);
		assertEquals(1, game.getScoreBoard()[1][2]);
		assertEquals(RPSGame.PLALYING, game.getStatus());

		// Third round, Rock vs Rock, it's a tied game.
		game.combat(0, 0);

		assertEquals(3, game.getCurrentRound());
		assertEquals(0, game.getScoreBoard()[2][0]);
		assertEquals(0, game.getScoreBoard()[2][1]);
		assertEquals(0, game.getScoreBoard()[2][2]);
		assertEquals(RPSGame.TIE, game.getStatus());
	}
}
