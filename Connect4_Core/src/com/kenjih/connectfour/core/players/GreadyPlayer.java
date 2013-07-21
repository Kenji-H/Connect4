package com.kenjih.connectfour.core.players;

import java.util.Random;

public class GreadyPlayer extends Player {
	
	@Override
	public int getNextHand(char[][] board) {
		char opponent = (getStone() == FIRST_STONE) ? SECOND_STONE : FIRST_STONE;
		
		// 次の一手で勝てる場合は、勝ち手を指す
		for (int j = 0; j < COL; j++) {
			int i = ROW - 1;
			while (i >= 0 && board[i][j] != EMPTY)
				--i;
			if (i == -1)
				continue;
			
			board[i][j] = getStone();
			if (winOf(getStone(), board))
				return j;
			board[i][j] = EMPTY;			
		}
		
		// 相手が次の一手で勝てる場合は、それを止める
		for (int j = 0; j < COL; j++) {
			int i = ROW - 1;
			while (i >= 0 && board[i][j] != EMPTY)
				--i;
			if (i == -1)
				continue;
			
			board[i][j] = opponent;
			if (winOf(opponent, board))
				return j;
			board[i][j] = EMPTY;			
		}
		
		Random rand = new Random();		
		int column = -1;
		for (;;) {
			column = rand.nextInt(COL);
			if (board[0][column] == EMPTY)
				break;
		}
		return column;
	}

	private boolean winOf(char c, char[][] board) {
		// 縦ライン
		for (int j = 0; j < COL; j++) {
			for (int i = 0; i + CONNECT <= ROW; i++) {
				boolean check;
				check = true;
				for (int k = 0; k < CONNECT; k++)
					check &= board[i + k][j] == c;
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
					check &= board[i][j + k] == c;
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
					check &= board[i + k][j + k] == c;
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
					check &= board[i + k][j - k] == c;
				if (check)
					return true;
			}
		}
		
		return false;
	}
	
	@Override
	public String getName() {
		return "Gready Player";
	}

}
