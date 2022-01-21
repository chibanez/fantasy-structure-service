package ar.com.chusoft.fantasy.structure.application.dto.response;

import java.io.Serializable;

import ar.com.chusoft.fantasy.structure.domain.model.tournament.Tournament;

public class TournamentResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;

	public TournamentResponse(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public TournamentResponse(Tournament tournament) {
		super();
		this.id = tournament.id().id();
		this.name = tournament.name();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
