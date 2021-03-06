package com.kenjih.connectfour.core.players;

public abstract class Player {
	protected static final int ROW = 6;
	protected static final int COL = 7;
	protected static final int CONNECT = 4;
	protected static final char FIRST_STONE = 'O';
	protected static final char SECOND_STONE = 'X';
	protected static final char EMPTY = '.';
	protected char stone;

	@SuppressWarnings("rawtypes")
	public static Player getInstance(String className)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		Player player = null;

		Class clazz = Class.forName(className);
		player = (Player) clazz.newInstance();

		return player;
	}

	public void setStone(char stone) {
		this.stone = stone;
	}

	public char getStone() {
		return stone;
	}
	
	abstract public int getNextHand(char[][] board);
	abstract public String getName();
}
