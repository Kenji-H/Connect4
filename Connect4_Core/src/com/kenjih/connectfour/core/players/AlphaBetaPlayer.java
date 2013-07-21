package com.kenjih.connectfour.core.players;

import java.util.Random;

public class AlphaBetaPlayer extends Player {
	
	private final int DEPTH = 10;
	
	private char[][] board;
		
	@Override
	public int getNextHand(char[][] board) {
		this.board = board;
		int[] res = dfs(0, -Integer.MAX_VALUE, Integer.MAX_VALUE);
		System.out.println("評価値: " + res[0]);
		return res[1];
	}
	
	private char opponent(char c) {
		return (c == FIRST_STONE) ? SECOND_STONE : FIRST_STONE;
	}
	
	private int[] dfs(int turn, int alpha, int beta) {
		char cur = (turn % 2 == 0) ? getStone() : opponent(getStone());
				
		// 相手の前の手で自分の負けが確定
		if (winOf(opponent(cur), board))
			return new int[] {-Integer.MAX_VALUE, -1};
		
		// 引き分け判定（※本処理は、勝敗判定の後に行うことに注意）
		boolean tie = true;
		for (int i = 0; i < COL; i++) {
			if (board[0][i] == EMPTY) {
				tie = false;
				break;
			}
		}
		if (tie)
			return new int[] {0, -1};

		// 探索終了
		if (turn >= DEPTH) {
			int val = evaluate(cur, board) - evaluate(opponent(cur), board);
			return new int[] {val, -1};
		}
		
		int res = -Integer.MAX_VALUE;
		int move = -1;
		int j = new Random().nextInt(COL);
		for (int k = 0; k < COL; k++) {
			if (++j >= COL)
				j = 0;
			int i = ROW - 1;
			while (i >= 0 && board[i][j] != EMPTY)
				--i;
			if (i == -1)
				continue;
			
			alpha = Math.max(alpha, res);
			board[i][j] = cur;
			int[] tmp = dfs(turn+1, -beta, -alpha);
			if (-tmp[0] > res || move == -1) {
				res = -tmp[0];
				move = j;
			}
			
			board[i][j] = EMPTY;
			if (res == Integer.MAX_VALUE)
				break;
			if (res >= beta) {
				return new int[] {beta, -1};
			}
		}

		return new int[] {res, move};
	}

	private int evaluate(char c, char[][] board) {
		int res = 0;      // 評価値

		// 縦ライン
		for (int j = 0; j < COL; j++) {
			for (int i = 0; i + CONNECT <= ROW; i++) {
				int tmp = 0;
				for (int k = 0; k < CONNECT; k++) {
					if (board[i + k][j] == c)
						++tmp;
					else if (board[i + k][j] == opponent(c)) {
						tmp = 0;
						break;
					}
				}
				res += tmp * tmp;
			}
		}
		

		// 横ライン
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j + CONNECT <= COL; j++) {
				int tmp = 0;
				for (int k = 0; k < CONNECT; k++) {
					if (board[i][j + k] == c)
						++tmp;
					else if (board[i][j + k] == opponent(c)) {
						tmp = 0;
						break;
					}
				}
				res += tmp * tmp;
			}
		}
		
		// 斜め（右下）ライン
		for (int i = 0; i + CONNECT <= ROW; i++) {
			for (int j = 0; j + CONNECT <= COL; j++) {
				int tmp = 0;
				for (int k = 0; k < CONNECT; k++) {
					if (board[i + k][j + k] == c)
						++tmp;
					else if (board[i + k][j + k] == opponent(c)) {
						tmp = 0;
						break;
					}
				}
				res += tmp * tmp;
			}
		}

		// 斜め（左下）ライン
		for (int i = 0; i + CONNECT <= ROW; i++) {
			for (int j = COL - 1; j - CONNECT + 1 >= 0; j--) {
				int tmp = 0;
				for (int k = 0; k < CONNECT; k++) {
					if (board[i + k][j - k] == c)
						++tmp;
					else if (board[i + k][j - k] == opponent(c)) {
						tmp = 0;
						break;
					}
				}
				res += tmp * tmp;
			}
		}
		
		return res;
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
		return "Alpha Beta Player";
	}

}
