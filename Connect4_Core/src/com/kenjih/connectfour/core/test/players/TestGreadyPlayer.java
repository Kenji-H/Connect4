package com.kenjih.connectfour.core.test.players;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kenjih.connectfour.core.players.GreadyPlayer;
import com.kenjih.connectfour.core.players.Player;

public class TestGreadyPlayer {
	Player player;
	
	
	@Before
	public void setUp() throws Exception {
		player = new GreadyPlayer();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetNextHand_1() {
		player.setStone('O');
		
		char[][] board = {
			".......".toCharArray(),
			".......".toCharArray(),
			".......".toCharArray(),
			"O......".toCharArray(),
			"O......".toCharArray(),
			"O.X.XX.".toCharArray()
		};
		
		assertEquals(0, player.getNextHand(board));
	}

	@Test
	public void testGetNextHand_2() {
		player.setStone('O');
		
		char[][] board = {
			".......".toCharArray(),
			".......".toCharArray(),
			".......".toCharArray(),
			".......".toCharArray(),
			"O......".toCharArray(),
			"OOX.XX.".toCharArray()
		};
		
		assertEquals(3, player.getNextHand(board));
	}

	@Test
	public void testGetNextHand_3() {
		player.setStone('O');
		
		char[][] board = {
			".......".toCharArray(),
			".......".toCharArray(),
			".......".toCharArray(),
			"O.O....".toCharArray(),
			".O..X..".toCharArray(),
			"O.X.XX.".toCharArray()
		};
		
		assertEquals(3, player.getNextHand(board));
	}

	@Test
	public void testGetNextHand_4() {
		player.setStone('O');
		
		char[][] board = {
			".......".toCharArray(),
			".......".toCharArray(),
			".......".toCharArray(),
			"...X.X.".toCharArray(),
			"O..OXX.".toCharArray(),
			"O.XOXX.".toCharArray()
		};
		
		assertEquals(5, player.getNextHand(board));
	}

	@Test
	public void testGetNextHand_5() {
		player.setStone('O');
		
		char[][] board = {
			".......".toCharArray(),
			".......".toCharArray(),
			"...O...".toCharArray(),
			"....O.X".toCharArray(),
			"OOO..OX".toCharArray(),
			"O.X.XXX".toCharArray()
		};
		
		assertEquals(2, player.getNextHand(board));
	}
}
