package com.kenjih.connectfour.core.test.players;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kenjih.connectfour.core.players.Player;
import com.kenjih.connectfour.core.players.SmartMinMaxPlayer;

public class TestSmartMinMaxPlayer {
	Player player;
	
	@Before
	public void setUp() throws Exception {
		player = new SmartMinMaxPlayer();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetNextHand_1() {
		player.setStone('O');
		
		char[][] board = {
			"XX.XO.O".toCharArray(),
			"OOXOOXX".toCharArray(),
			"XOOOXXX".toCharArray(),
			"OXXXOOO".toCharArray(),
			"XXOOOXX".toCharArray(),
			"XOOOXOX".toCharArray()
		};
		
		assertEquals(2, player.getNextHand(board));
	}
}
