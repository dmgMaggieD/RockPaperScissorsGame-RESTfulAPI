package com.maggied.rpsgame;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GameManagerTest {

	@Test
	public void createNewGameTest() {
		String playerId = "playerTest";
		GameManager testGameManager = GameManager.getInstance();
		assertEquals(0, testGameManager.getGamesByPlayer(playerId).size());
		testGameManager.createNewGame(playerId);
		assertEquals(1, testGameManager.getGamesByPlayer(playerId).size());
	}
	
	@Test(expected = RuntimeException.class)
	public void createNewGameTest_PlayerIdIsNull() {
		String playerId = null;
		GameManager testGameManager = GameManager.getInstance();
		testGameManager.createNewGame(playerId);
	}

	@Test
	public void getGameByGameIdTest() {
		String playerId = "player01";
		GameManager testGameManager = GameManager.getInstance();
		RPSGame expectedGame = testGameManager.createNewGame(playerId);
		assertEquals(expectedGame,
				testGameManager.getGameByGameId(expectedGame.getPlayerId(), expectedGame.getGameId()));

	}

	@Test(expected = RuntimeException.class)
	public void getGameByGameIdTest_GameIdNotExist() {
		
		//Assign gameId a big number, doesn't exist
		long gameId = 100;
		String playerId = "player01";
		GameManager testGameManager = GameManager.getInstance();
		testGameManager.getGameByGameId(playerId, gameId);
	}
	
	@Test(expected = RuntimeException.class)
	public void getGameByGameIdTest_Unauthorized() {
		
		//Create game using playerId, then get this game by playerIdOther
		String playerId = "player01";
		String playerIdOther = "player02";
		GameManager testGameManager = GameManager.getInstance();
		RPSGame expectedGame = testGameManager.createNewGame(playerId);
		testGameManager.getGameByGameId(playerIdOther, expectedGame.getGameId());
	}

	@Test
	public void getGamesByPlayerTest() {
		String playerId = "player03";
		GameManager testGameManager = GameManager.getInstance();
		List<RPSGame> expectedGameList = new ArrayList<RPSGame>();
		int count = 3;
		
		// Create 3 games
		for(int i = 0; i < count; i ++) {
			expectedGameList.add(testGameManager.createNewGame(playerId));
		}
		
		// Get gameList by playerId
		List<RPSGame> gameList = testGameManager.getGamesByPlayer(playerId);
		for(int i = 0; i < count; i ++) {
			assertEquals(expectedGameList.get(i), gameList.get(i));
		}
	}
	
	@Test(expected = RuntimeException.class)
	public void getGamesByPlayerTest_PlayerIdIsNull() {
		String playerId = null;
		GameManager testGameManager = GameManager.getInstance();
		testGameManager.getGamesByPlayer(playerId);
	}
}
