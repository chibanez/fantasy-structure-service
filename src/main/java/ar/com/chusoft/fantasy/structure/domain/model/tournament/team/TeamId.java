package ar.com.chusoft.fantasy.structure.domain.model.tournament.team;

import java.util.Objects;

public class TeamId {

	private String id;
	
	private TeamId() {
		super();
	}
	
	public TeamId(String id) {
		this();
		this.setId(id);
	}
	
	public TeamId(TeamId teamId) {
		this(teamId.id);
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
		return "TeamId [id=" + id + "]";
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
		TeamId other = (TeamId) obj;
		return Objects.equals(id, other.id);
	}
	
}
