package com.maggied.rpsgame;

public interface Game {
	
	//Game status
	public static final String PLALYING = "Playing";
	public static final String PLAYER_WIN = "Player Win";
	public static final String COMPUTER_WIN = "Computer Win";
	public static final String TIE = "Tie";

	Game reset();
}
