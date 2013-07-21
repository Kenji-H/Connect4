package com.kenjih.connectfour.core.players;

import java.util.Scanner;

public class HumanPlayer extends Player {
	private Scanner sc = new Scanner(System.in);
	
	@Override
	public int getNextHand(char[][] board) {
		return sc.nextInt();
	}

	@Override
	public String getName() {
		return "Human Player";
	}

}
