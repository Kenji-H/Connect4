package com.kenjih.connectfour.cui.controller;

import java.util.Scanner;

import com.kenjih.connectfour.core.games.Game;
import com.kenjih.connectfour.core.players.Player;

public class CUIController {
	private static final String PLAYERS_PACKAGE_NAME = "com.kenjih.connectfour.core.players";
	
	int turn;
	Player player1, player2;
	
	public CUIController(int turn, Player p1, Player p2) {
		this.turn = turn;
		this.player1 = p1;
		this.player2 = p2;
	}
	
	public void run() {
		int p1Win = 0;
		int p2Win = 0;
		
		for (int i = 0; i < turn; i++) {
			Game game = null;
			
			if (i % 2 == 0)
				game = new Game(player1, player2, true);
			else
				game = new Game(player2, player1, true);
				
			game.clearBoard();
			int result = game.play();
			if (result == 1) {
				if (i % 2 == 0) {
					++p1Win;
					System.out.println(player1.getName() + " wins!!");
				}
				else {
					++p2Win;
					System.out.println(player2.getName() + " wins!!");
				}
			} else if (result == -1) {
				if (i % 2 == 0) {
					++p2Win;
					System.out.println(player2.getName() + " wins!!");
				}
				else {
					++p1Win;
					System.out.println(player1.getName() + " wins!!");
				}
			} else {
				System.out.println("draw!!");
			}
		}
		
		System.out.println();
		System.out.println("----------------------------------");
		System.out.println(player1.getName() + " won the game " + p1Win + " times.");
		System.out.println(player2.getName() + " won the game " + p2Win + " times.");		
	}
	
	public static void main(String[] args) {
		int turn = -1;
		Player[] players = {null, null};
		
		Scanner in = new Scanner(System.in);
		
		// set the number of games
		System.out.println("Input the number of games to be played:");
		turn = in.nextInt();
				
		// set player objects
		for (int i = 0; i < players.length; i++) {
			while (players[i] == null) {
				String idx = (i == 0) ? "first" : "second";
				System.out.println("Enter the name of " + idx + " player's class:");
				String s = in.next();
				try {
					players[i] = Player.getInstance(PLAYERS_PACKAGE_NAME + "." + s);
				} catch (ReflectiveOperationException e) {
					System.out.println("Player " + s + " doesn't exist.");
				}
			}			
		}
		
		CUIController controller = new CUIController(turn, players[0], players[1]);
		controller.run();
	}
}
