package ar.com.chusoft.fantasy.structure.domain.model.tournament;

import java.util.Objects;

public class TournamentId {

	private String id;
	
	private TournamentId() {
		super();
	}
	
	public TournamentId(String id) {
		this();
		this.setId(id);
	}
	
	public TournamentId(TournamentId tournamentId) {
		this(tournamentId.id);
	}
	
	public String id() {
        return this.id;
    }
	
	private void setId(String id) {
		if(id == null) throw new IllegalArgumentException("Debe ingresar el ID.");
		if(id.length() > 36) throw new IllegalArgumentException("El ID no puede tener mas de 36 caracteres.");
		this.id = id;
	}

	@Override
	public String toString() {
		return "TournamentId [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TournamentId other = (TournamentId) obj;
		return Objects.equals(id, other.id);
	}

}
