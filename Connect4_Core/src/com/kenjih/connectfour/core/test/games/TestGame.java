package com.kenjih.connectfour.core.test.games;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kenjih.connectfour.core.games.Game;
import com.kenjih.connectfour.core.players.RandomPlayer;

public class TestGame {

	Game game;

	@Before
	public void setUp() throws Exception {
		game = new Game(new RandomPlayer(), new RandomPlayer());
	}

	@After
	public void tearDown() throws Exception {
	}
	
	private void setBoard(char[][] board) throws Exception {
		Field field = game.getClass().getDeclaredField("board");
		field.setAccessible(true);
		field.set(game, board);		
	}
	
	private boolean winOf(char stone) throws Exception {
		Method method = game.getClass().getDeclaredMethod("winOf", char.class);
		method.setAccessible(true);
		return (Boolean) method.invoke(game, stone);
	}

	@Test
	public void testWinOf_1() throws Exception {
		
		char[][] board = { 
			".......".toCharArray(),
			".......".toCharArray(),
			"..XOOO.".toCharArray(),
			"..XXOO.".toCharArray(),
			"..X.X..".toCharArray(), 
			".......".toCharArray()
		};
		
		setBoard(board);
		assertEquals(false, winOf('O'));
		assertEquals(false, winOf('X'));
	}

	@Test
	public void testWinOf_2() throws Exception {
		
		char[][] board = { 
			".......".toCharArray(),
			".......".toCharArray(),
			"..O....".toCharArray(),
			"..O....".toCharArray(),
			"..O....".toCharArray(), 
			"..O....".toCharArray()
		};
		
		setBoard(board);
		assertEquals(true, winOf('O'));
		assertEquals(false, winOf('X'));
	}

	@Test
	public void testWinOf_3() throws Exception {
		
		char[][] board = { 
			".......".toCharArray(),
			".......".toCharArray(),
			"..XXXX.".toCharArray(),
			".......".toCharArray(),
			".......".toCharArray(), 
			".......".toCharArray()
		};
		
		setBoard(board);
		assertEquals(false, winOf('O'));
		assertEquals(true, winOf('X'));
	}

	@Test
	public void testWinOf_4() throws Exception {
		
		char[][] board = { 
			".....O.".toCharArray(),
			"....O..".toCharArray(),
			"...O...".toCharArray(),
			"..O....".toCharArray(),
			".......".toCharArray(), 
			".......".toCharArray()
		};
		
		setBoard(board);
		assertEquals(true, winOf('O'));
		assertEquals(false, winOf('X'));
	}

	@Test
	public void testWinOf_5() throws Exception {
		
		char[][] board = { 
			".......".toCharArray(),
			"..X....".toCharArray(),
			"...X...".toCharArray(),
			"....X..".toCharArray(),
			".....X.".toCharArray(), 
			".......".toCharArray()
		};
		
		setBoard(board);
		assertEquals(false, winOf('O'));
		assertEquals(true, winOf('X'));
	}
}
