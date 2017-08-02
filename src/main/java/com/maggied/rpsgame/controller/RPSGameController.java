package com.maggied.rpsgame.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maggied.rpsgame.GameManager;
import com.maggied.rpsgame.RPSGame;

@RestController
public class RPSGameController {
	
	// Creates a RPS game by a given playerId and returns json data of the game.
	@RequestMapping(value = "/rpsgame/{playerId}", method = RequestMethod.POST)
	public RPSGame newGame(@PathVariable("playerId") String playerId) {
		GameManager gameManager = GameManager.getInstance();
		return gameManager.createNewGame(playerId);
	}
	
	// Gets a list of games owned by a given player
	@RequestMapping(value = "/rpsgame/{playerId}", method = RequestMethod.GET)
	public List<RPSGame> getGameList(@PathVariable("playerId") String playerId) {
		GameManager gameManager = GameManager.getInstance();
		return gameManager.getGamesByPlayer(playerId);
	}

	// Plays a round of RPS game and return the json data of the game
	@RequestMapping(path = "/rpsgame/{playerId}/{gameId}", method = RequestMethod.POST)
	public RPSGame combat(@PathVariable("playerId") String playerId, @PathVariable("gameId") long gameId,
			@RequestParam(value = "playerShape") int playerShape) {
		GameManager gameManager = GameManager.getInstance();
		return gameManager.getGameByGameId(playerId, gameId).combat(playerShape, RPSGame.generateComputerShape());
	}
	
	// Gets the game by a given game ID and player ID and returns json data of the game  
	@RequestMapping(path = "/rpsgame/{playerId}/{gameId}", method = RequestMethod.GET)
	public RPSGame getGameByGameId(@PathVariable("playerId") String playerId, @PathVariable("gameId") long gameId) {
		GameManager gameManager = GameManager.getInstance();
		return gameManager.getGameByGameId(playerId, gameId);
	}

	// Resets a Game by the given game ID and player ID and returns json data of the game 
	@RequestMapping(path = "/rpsgame/{playerId}/{gameId}", method = RequestMethod.PUT)
	public RPSGame reset(@PathVariable("playerId") String playerId, @PathVariable("gameId") long gameId) {
		GameManager gameManager = GameManager.getInstance();
		return gameManager.getGameByGameId(playerId, gameId).reset();
	}
}
