package com.kenjih.connectfour.core.games;

import java.util.Arrays;

import com.kenjih.connectfour.core.players.*;

public class Game {
	private static final int ROW = 6;
	private static final int COL = 7;
	private static final int CONNECT = 4;
	private static final char FIRST_STONE = 'O';
	private static final char SECOND_STONE = 'X';
	private static final char EMPTY = '.';

	private char[][] board = new char[ROW][COL];
	private Player p1;
	private Player p2;
	

	public Game(Player player1, Player player2) {		
		this.p1 = player1;
		this.p2 = player2;
		
		p1.setStone(FIRST_STONE);
		p2.setStone(SECOND_STONE);
	}
	
	private void putStoneAt(int column, char stone)
			throws AgainstRuleException {
		if (column < 0 || column >= COL)
			throw new AgainstRuleException("列は、0から6までの数字で指定してください。");
		
		int row = ROW - 1;
		while (row >= 0 && board[row][column] != EMPTY)
			--row;
		if (row == -1)
			throw new AgainstRuleException(column + "列には置けません。");

		board[row][column] = stone;
	}

	public void clearBoard() {
		for (int i = 0; i < ROW; i++)
			for (int j = 0; j < COL; j++)
				board[i][j] = EMPTY;
	}

	private boolean winOf(char stone) {
		// 縦ライン
		for (int j = 0; j < COL; j++) {
			for (int i = 0; i + CONNECT <= ROW; i++) {
				boolean check;
				check = true;
				for (int k = 0; k < CONNECT; k++)
					check &= board[i + k][j] == stone;
				if (check)
					return true;
			}
		}
		
		// 横ライン
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j + CONNECT <= COL; j++) {
				boolean check;
				check = true;
				for (int k = 0; k < CONNECT; k++)
					check &= board[i][j + k] == stone;
				if (check)
					return true;
			}
		}
		
		// 斜め（右下）ライン
		for (int i = 0; i + CONNECT <= ROW; i++) {
			for (int j = 0; j + CONNECT <= COL; j++) {
				boolean check;
				check = true;
				for (int k = 0; k < CONNECT; k++)
					check &= board[i + k][j + k] == stone;
				if (check)
					return true;
			}
		}
		
		// 斜め（左下）ライン
		for (int i = 0; i + CONNECT <= ROW; i++) {
			for (int j = COL - 1; j - CONNECT + 1 >= 0; j--) {
				boolean check;
				check = true;
				for (int k = 0; k < CONNECT; k++)
					check &= board[i + k][j - k] == stone;
				if (check)
					return true;
			}
		}

		return false;
	}

	private void dispBoard(int turn) {
		if (isDispBoardEnabled()) {
			for (int i = 0; i < ROW; i++) {
				for (int j = 0; j < COL; j++)
					System.out.print(board[i][j]);
				System.out.println();
			}
			System.out.println();		
		}
	}
	
	private boolean isDispBoardEnabled() {
		return true;
	}
	
	
	/**
	 * 
	 * @return 0: 引き分け　1: 先手の勝ち -1: 後手の勝ち
	 */
	public int play() {
		for (int i = 0; i < ROW * COL; i++) {
			Player p = (i % 2 == 0) ? p1 : p2;
			char stone = p.getStone();
			try {
				// TODO 10秒でタイムアウトする。（スレッド？）
				char[][] tmpBoard = new char[ROW][COL];
				for (int k = 0; k < ROW; k++)
					tmpBoard[k] = Arrays.copyOf(board[k], board[k].length);
				
				int column = p.getNextHand(tmpBoard);
				if (isDispBoardEnabled())
					System.out.println(p.getName() + "(" + p.getStone() + ") put a stone at colum " + column + ".");
				
				putStoneAt(column, stone);
			} catch (AgainstRuleException e) {
				System.out.println(p.getName() + ": " + e.getMessage());
				return (i % 2 == 0) ? -1 : 1;
			} finally {
				dispBoard(i);
			}
			
			if (winOf(stone))
				return (i % 2 == 0) ? 1 : -1;
		}

		return 0;
	}
}
