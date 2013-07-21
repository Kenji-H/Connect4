package com.kenjih.connectfour.batch.controller;

import com.kenjih.connectfour.core.games.Game;
import com.kenjih.connectfour.core.players.Player;

public class BatchController {
	public static void main(String[] args) {
		// TODO コマンド引数からplayerクラスの名前を取得してインスタンス化する
		// TODO コマンド引数からゲーム数、visualizerオン/オフを切り替える
		// TODO Swingでvisualizer作る
		
		int p1Win = 0;
		int p2Win = 0;
		
		Player player1 = Player.getInstance("com.kenjih.connectfour.core.players.AlphaBetaPlayer");
		Player player2 = Player.getInstance("com.kenjih.connectfour.core.players.MinMaxPlayer");
		
		for (int i = 0; i < 100; i++) {
			Game game = null;
			
			if (i % 2 == 0)
				game = new Game(player1, player2);
			else
				game = new Game(player2, player1);
				
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
}
