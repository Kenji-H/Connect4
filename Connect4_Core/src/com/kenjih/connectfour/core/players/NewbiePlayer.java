package com.kenjih.connectfour.core.players;

import java.util.Random;

public class NewbiePlayer extends Player {
	
	@Override
	public int getNextHand(char[][] board) {
		Random rand = new Random();		
		int column = -1;
		for (;;) {
			column = rand.nextInt(COL);
			if (board[0][column] == EMPTY)
				break;
		}
		return column;
	}

	@Override
	public String getName() {
		return "Newbie Player";
	}

}
