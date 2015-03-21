package controller;


import java.util.Collection;

import game.Game;
import game.GameBuilder;
import game.GameBuilderImpl;
import player.InvalidPlayerException;
import player.Player;
import player.PlayerImpl;
import player.PlayerValidatorImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(HierarchicalContextRunner.class)
public class TableTest {
	
	Table table;
	
	@Before
	public void setup(){
		table = new Table();
		table.setValidator(new PlayerValidatorImpl());
		table.setGameBuilder(mock(GameBuilder.class));
	}
	
	@Test
	public void initiallyTableHasNoOwner() throws Exception {
		assertFalse(table.isOwner(mock(Player.class)));
	}
	
	@Test
	public void afterRegisteringTheFirstPlayerBecomesOwner() throws Exception {
		Player player = mock(Player.class);
		
		table.registerPlayer(player);
		
		assertTrue(table.isOwner(player));
	}
	
	@Test
	public void afterRegisteringTheSecondPlayerDoesNotBecomeOwner() throws Exception {
		Player first = mock(Player.class);
		when(first.getName()).thenReturn("Player1");
		Player second = mock(Player.class);
		when(first.getName()).thenReturn("Player2");
		
		table.registerPlayer(first);
		table.registerPlayer(second);
		
		assertFalse(table.isOwner(second));
	}
	
	@Test
	public void numberOfPlayersIsZeroAtStart() throws Exception {
		int numberOfPlayers = table.getNumberOfPlayers();
		
		assertEquals(0,numberOfPlayers);
	}
	
	@Test
	public void whenPlayerFoldsPlayerIsRemovedFromGame() throws Exception {
		Player first = mock(Player.class);
		when(first.getName()).thenReturn("Player1");
		Player second = mock(Player.class);
		when(first.getName()).thenReturn("Player2");
		Game game = mock(Game.class);
		when(table.getGameBuilder().withPlayers(any(Collection.class))).thenReturn(table.getGameBuilder());
		when(table.getGameBuilder().build()).thenReturn(game);
		
		table.registerPlayer(first);
		table.registerPlayer(second);
		table.startGame();
		table.fold(first);
		
		verify(game, times(1)).removePlayer(first);
	}
	
	public class TableWithOnePlayer{
		
		Player player;
		
		@Before
		public void setup() throws InvalidPlayerException{
			player = new PlayerImpl("player");
			table.registerPlayer(player);
		}
		
		@Test
		public void numberOfPlayersIsOne() throws Exception {
			int numberOfPlayers = table.getNumberOfPlayers();
			
			assertEquals(1,numberOfPlayers);
		}
	}
	
	
}
