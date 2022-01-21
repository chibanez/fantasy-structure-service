package ar.com.chusoft.fantasy.structure.domain.model.tournament.team.player;

import java.util.UUID;

public class Player {

	private PlayerId playerId;
	
	private String name;
	
	public Player(String name) {
		this.playerId = new PlayerId(UUID.randomUUID().toString());
		this.setName(name);
	}
	
	//GETTERS
	public PlayerId id() {
		return this.playerId;
	}
	
	public String name() {
		return this.name;
	}
	
	//SETTERS
	private void setName(String name) {
		if(name == null || name.trim().length() == 0) throw new IllegalArgumentException("Debe ingresar un nombre para el jugador.");
		this.name = name;
	}
	
}
