package com.kenjih.connectfour.main.players;

import java.util.Random;

public class MinMaxPlayer extends Player {
	
	private final int DEPTH = 8;
	
	private char[][] board;
		
	@Override
	public int getNextHand(char[][] board) {
		this.board = board;
		int[] res = dfs(0);
		if (res[0] == -1)
			System.out.println("負けました");
		return res[1];
	}
	
	private char opponent(char c) {
		return (c == FIRST_STONE) ? SECOND_STONE : FIRST_STONE;
	}
		
	private int[] dfs(int turn) {
		char cur = (turn % 2 == 0) ? getStone() : opponent(getStone());
		
		// 相手の前の手で自分の負けが確定
		if (winOf(opponent(cur), board))
			return new int[] {-1, -1};
		
		// 探索終了
		if (turn >= DEPTH)
			return new int[] {0, -1};
		
		int res = -2;
		int move = 0;
		int j = new Random().nextInt(COL);
		for (int k = 0; k < COL; k++) {
			if (++j >= COL)
				j = 0;
			int i = ROW - 1;
			while (i >= 0 && board[i][j] != EMPTY)
				--i;
			if (i == -1)
				continue;
			
			board[i][j] = cur;
			int[] tmp = dfs(turn+1);
			if (-tmp[0] > res) {
				res = -tmp[0];
				move = j;
			}			
			board[i][j] = EMPTY;
			if (res == 1)
				break;
		}
		
		return new int[] {res, move};
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
		return "Min Max Player";
	}

}
