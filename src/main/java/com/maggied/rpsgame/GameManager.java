package com.maggied.rpsgame;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class GameManager {
	
	//Design pattern: singleton 
	private static final GameManager GAME_MANAGERE = new GameManager();

	private HashMap<Long, RPSGame> games;
	private AtomicLong currentGameId;

	private GameManager() {
		games = new HashMap<Long, RPSGame>();
		currentGameId = new AtomicLong();
	}

	public static GameManager getInstance() {
		return GAME_MANAGERE;
	}

	public RPSGame getGameByGameId(String playerId, long gameId) {
		
		// If gameId doesn't exist
		if (!games.containsKey(gameId)) {
			String errMsg = String.format("Game '%d' doesn't exist!", gameId);
			throw new RuntimeException(errMsg);
		} else {
			RPSGame game = games.get(gameId);
			
			// If player doesn't own this game
			if (!game.getPlayerId().equals(playerId)) {
				String errMsg = String.format("Player '%s' can't access Game '%d'", playerId, gameId);
				throw new RuntimeException(errMsg);
			} else {
				return game;
			}
		}
	}

	public RPSGame createNewGame(String playerId) {
		
		// If playerId is null
		if (playerId == null) {
			throw new RuntimeException("Player ID can't be null!");
		}
		long gameId = currentGameId.getAndIncrement();
		RPSGame newGame = new RPSGame(gameId, playerId);
		games.put(gameId, newGame);
		return newGame;
	}

	public List<RPSGame> getGamesByPlayer(String playerId) {
		
		// If playerId is null
		if (playerId == null) {
			throw new RuntimeException("Player ID can't be null!");
		}
		return games.entrySet().stream().map(entry -> entry.getValue()).filter(game -> game.isPlayerGame(playerId))
				.collect(Collectors.toList());

	}

	public void reset() {
		games.clear();
		currentGameId.set(0);
	}

	public HashMap<Long, RPSGame> getGames() {
		return games;
	}

	public AtomicLong getCurrentGameId() {
		return currentGameId;
	}
}
