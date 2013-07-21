package com.kenjih.connectfour.batch.controller;

import com.kenjih.connectfour.core.games.Game;
import com.kenjih.connectfour.core.players.Player;

public class BatchController {
	private static final int DEFAULT_TURN = 100;
	private static final boolean DEFAULT_VISUALIZE_FLG = false;
	private static final String PLAYERS_PACKAGE_NAME = "com.kenjih.connectfour.core.players";
	
	int turn;
	boolean visualizeFlg;     // TODO 全体的にログを出すとか出さないとかの制御をするためlog4jとか使う？
	Player player1, player2;
	
	public BatchController(int turn, boolean visualizeFlg, Player p1, Player p2) {
		this.turn = turn;
		this.visualizeFlg = visualizeFlg;
		this.player1 = p1;
		this.player2 = p2;
	}
	
	public void run() {
		int p1Win = 0;
		int p2Win = 0;
		
		for (int i = 0; i < turn; i++) {
			Game game = null;
			
			if (i % 2 == 0)
				game = new Game(player1, player2, visualizeFlg);
			else
				game = new Game(player2, player1, visualizeFlg);
				
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
		int turn = DEFAULT_TURN;
		boolean vis = DEFAULT_VISUALIZE_FLG;
		Player p1 = null;
		Player p2 = null;
		
		try {
			String[] playerClass = new String[2];
			int p = 0;
			
			for (int i = 0; i < args.length; i++) {
				if ("-turn".equals(args[i]))
					turn = Integer.valueOf(args[++i]);
				else if ("-vis".equals(args[i]))
					vis = true;
				else
					playerClass[p++] = args[i];
			}
			
			p1 = Player.getInstance(PLAYERS_PACKAGE_NAME + "." + playerClass[0]);
			p2 = Player.getInstance(PLAYERS_PACKAGE_NAME + "." + playerClass[1]);
			
		} catch (Exception e) {
			System.out.println("Syntax error occurred.");
			System.out.println("");
			System.out.println("Required Parameters:");
			System.out.println("  Player1ClassName");
			System.out.println("  Player2ClassName");
			System.out.println("Optional Parameters:");
			System.out.println("  -turn: set the number of games to be played (default to 100)");
			System.out.println("  -vis: turn on the visualizer");
			System.out.println("");
			System.out.println("Ex1) HumanPlayer vs MinMaxPlayer");
			System.out.println("  java -jar connect4.jar HumanPlayer MinMaxPlayer");
			System.out.println("Ex2) AlphaBetaPlayer vs GreadyPlayer with visializer=on and turn=10");
			System.out.println("  java -jar connect4.jar AlphaBetaPlayer GreadyPlayer -vis -turn 10");
			System.out.println("");
			return;
		}
				
		BatchController controller = new BatchController(turn, vis, p1, p2);
		controller.run();
	}
}
