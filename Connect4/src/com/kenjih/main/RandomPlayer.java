package com.kenjih.main;

import java.util.Random;

public class RandomPlayer extends Player {
	
	@Override
	public int getNextHand(char[][] board) {
		Random rand = new Random();
		return rand.nextInt(COL);
	}

	@Override
	public String getName() {
		return "Random Player";
	}

}
