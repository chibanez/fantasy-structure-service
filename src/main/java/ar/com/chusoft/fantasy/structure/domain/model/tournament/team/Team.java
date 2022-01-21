package ar.com.chusoft.fantasy.structure.domain.model.tournament.team;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import ar.com.chusoft.fantasy.structure.domain.model.manager.Manager;
import ar.com.chusoft.fantasy.structure.domain.model.tournament.team.player.PlayerId;

public class Team {
	
	private TeamId teamId;
	private String name;
	
	private Set<PlayerId> players;
	private Manager manager;
	
	public Team(String name) {
		this.teamId = new TeamId(UUID.randomUUID().toString());
		this.setName(name);
		this.setPlayers(new HashSet<PlayerId>());
	}
	
	//GETTERS
	public TeamId id() {
		return this.teamId;
	}
	
	public String name() {
		return this.name;
	}
	
	public Set<PlayerId> players() {
		return this.players;
	}
	
	//SETTERS
	private void setName(String name) {
		if(name == null || name.trim().length() == 0) throw new IllegalArgumentException("Debe ingresar un nombre para el equipo.");
		this.name = name;
	}
	
	private void setPlayers(Set<PlayerId> players) {
		this.players = players;
	}
	
	//BEHAVIOUR
	//TODO Ver comportamiento de Teams al agregar Players
	public void assignPlayerToTeam(PlayerId playerId) {
		if(playerId == null) throw new IllegalArgumentException("Debe ingresar el id del jugador que quiere asignar al equipo.");
		//TODO Deberia validar que no supere la cantidad maxima de jugadores en el equipo
		this.players.add(playerId);
	}
	
	public void removePlayerFromTeam(PlayerId playerId) {
		if(playerId == null) throw new IllegalArgumentException("Debe ingresar el id del jugador que quiere sacar del equipo.");
		//TODO Deberia controlar que no queden menos que la cantidad minima de jugadores en el equipo
		this.players.remove(playerId);
	}
	
}
