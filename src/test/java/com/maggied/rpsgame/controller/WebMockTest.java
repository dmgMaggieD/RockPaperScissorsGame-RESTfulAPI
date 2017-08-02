package com.maggied.rpsgame.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.maggied.rpsgame.GameManager;

@RunWith(SpringRunner.class)
@WebMvcTest(RPSGameController.class)
public class WebMockTest {

	@Autowired
	private MockMvc mockMvc;

	@Before()
	public void setup() {
		// Reset the Game Manager before test.
		GameManager.getInstance().reset();
	}

	@Test
	public void newGameTest() throws Exception {
		String playerId = "player001";
		int gameId = 0;

		// Create a new game, check the initial values.
		this.mockMvc.perform(post("/rpsgame/" + playerId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.playerId", is(playerId))).andExpect(jsonPath("$.gameId", is(gameId)));
	}

	@Test
	public void combatTest() throws Exception {
		String playerId = "player001";
		int gameId = 0;
		String shapeJson = "{\"shapeValue\":2}";

		// Create a new game.
		this.mockMvc.perform(post("/rpsgame/" + playerId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.playerId", is(playerId))).andExpect(jsonPath("$.scoreBoard[0][1]", is(0)))
				.andExpect(jsonPath("$.currentRound", is(0))).andExpect(jsonPath("$.gameId", is(gameId)));

		// Combat the first round with Scissors.
		this.mockMvc
				.perform(post("/rpsgame/" + playerId + "/" + gameId).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(shapeJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$.playerId", is(playerId)))
				.andExpect(jsonPath("$.scoreBoard[0][1]", is(2))).andExpect(jsonPath("$.currentRound", is(1)));
	}

	@Test
	public void getGameListTest() throws Exception {
		String playerId = "player001";
		int gameId = 0;

		// No games created for the player.
		this.mockMvc.perform(get("/rpsgame/" + playerId)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));

		// Create 3 new games for player.
		for (int i = 0; i < 3; i++) {
			this.mockMvc.perform(post("/rpsgame/" + playerId)).andExpect(status().isOk())
					.andExpect(jsonPath("$.playerId", is(playerId))).andExpect(jsonPath("$.currentRound", is(0)))
					.andExpect(jsonPath("$.gameId", is(gameId++)));
		}

		// There are 3 games now.
		this.mockMvc.perform(get("/rpsgame/" + playerId)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)));
		;
	}

	@Test
	public void getGameByGameIdTest() throws Exception {
		String playerId = "player001";
		int gameId = 0;

		// Create a new game.
		this.mockMvc.perform(post("/rpsgame/" + playerId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.playerId", is(playerId))).andExpect(jsonPath("$.currentRound", is(0)))
				.andExpect(jsonPath("$.gameId", is(gameId)));

		// Get the game by game id.
		this.mockMvc.perform(get("/rpsgame/" + playerId + "/" + gameId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.playerId", is(playerId))).andExpect(jsonPath("$.currentRound", is(0)))
				.andExpect(jsonPath("$.gameId", is(gameId)));

	}

	@Test
	public void resetTest() throws Exception {
		String playerId = "player001";
		int gameId = 0;
		String shapeJson = "{\"shapeValue\":2}";

		// Create a new game.
		this.mockMvc.perform(post("/rpsgame/" + playerId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.playerId", is(playerId))).andExpect(jsonPath("$.scoreBoard[0][1]", is(0)))
				.andExpect(jsonPath("$.currentRound", is(0))).andExpect(jsonPath("$.gameId", is(gameId)));

		// Combat the first round with Scissors.
		this.mockMvc
				.perform(post("/rpsgame/" + playerId + "/" + gameId).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(shapeJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$.playerId", is(playerId)))
				.andExpect(jsonPath("$.scoreBoard[0][1]", is(2))).andExpect(jsonPath("$.currentRound", is(1)));

		// Reset the game.
		this.mockMvc.perform(put("/rpsgame/" + playerId + "/" + gameId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.playerId", is(playerId))).andExpect(jsonPath("$.scoreBoard[0][1]", is(0)))
				.andExpect(jsonPath("$.currentRound", is(0)));
	}

}
