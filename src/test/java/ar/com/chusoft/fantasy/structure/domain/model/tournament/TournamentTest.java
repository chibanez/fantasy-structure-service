package ar.com.chusoft.fantasy.structure.domain.model.tournament;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.com.chusoft.fantasy.structure.domain.model.tournament.team.Team;
import ar.com.chusoft.fantasy.structure.domain.model.tournament.team.player.Player;

class TournamentTest {

	Tournament tournament;
	
	@BeforeEach
	void setUp() {
		tournament = new Tournament("Torneo de Prueba");
	}
	
	@Test
	void testTournament() {

		assertNotNull(tournament.id());
		assertEquals("Torneo de Prueba", tournament.name());
		assertEquals(Tournament.TournamentStatus.CREATED, tournament.status());
	
	}

	@Test
	void testAddTeamToTournament() {
		
		Team team = new Team("Equipo de Prueba");
		tournament.addTeamToTournament(team.id());
		
		assertEquals(1, tournament.teams().size());
		assertTrue(tournament.teams().contains(team.id()));
		
	}
	
	@Test
	void testAddMultipleTeamsToTournament() {

		Team team = new Team("Equipo de Prueba");
		Team team2 = new Team("Equipo de Prueba 2");
		Team team3 = new Team("Equipo de Prueba 3");
		
		tournament.addTeamToTournament(team.id());
		tournament.addTeamToTournament(team2.id());
		tournament.addTeamToTournament(team3.id());
		
		assertEquals(3, tournament.teams().size());
		//tournament.teams().stream().forEach(a -> System.out.println("EQUIPO ID: " + a.id()));
		
		assertTrue(tournament.teams().contains(team.id()));
		assertTrue(tournament.teams().contains(team2.id()));
		assertTrue(tournament.teams().contains(team3.id()));
		
	}
	
	@Test
	void testRemoveTeamFromTournament() {
		Team team = new Team("Equipo de Prueba");
		Team team2 = new Team("Equipo de Prueba 2");
		Team team3 = new Team("Equipo de Prueba 3");
		
		tournament.addTeamToTournament(team.id());
		tournament.addTeamToTournament(team2.id());
		tournament.addTeamToTournament(team3.id());
		
		assertEquals(3, tournament.teams().size());
		
		tournament.removeTeamFromTournament(team2.id());
		
		assertEquals(2, tournament.teams().size());
		assertFalse(tournament.teams().contains(team2.id()));
		
	}

	/*
	@Test
	void testAddPlayerToTournament() {
		fail("Not yet implemented");
	}

	@Test
	void testRemovePlayerFromTournament() {
		fail("Not yet implemented");
	}

	 */
	@Test
	void testStartTournament() {
		
		//Al iniciarlo directamente tira excepcion
		assertThrows(IllegalArgumentException.class, () -> tournament.startTournament());
		
		for(int i = 0; i < tournament.teamsPerTournament() ; i++) tournament.addTeamToTournament(new Team("Equipo de Prueba " + i).id());
		
		assertThrows(IllegalArgumentException.class, () -> tournament.startTournament());
		
		for(int i = 0; i < tournament.minPlayersPerTournament(); i++) tournament.addPlayerToTournament(new Player("Jugador de Prueba " + i).id());
		
		tournament.startTournament();
		
		assertEquals(Tournament.TournamentStatus.STARTED, tournament.status());
		assertEquals(tournament.teamsPerTournament(), tournament.teams().size());
		assertEquals(tournament.minPlayersPerTournament(), tournament.players().size());
		
	}
}
