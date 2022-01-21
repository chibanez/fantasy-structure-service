package ar.com.chusoft.fantasy.structure.domain.model.manager;

import java.util.Objects;

public class Manager {

	private String username;
	private String name;

	private Manager() {
		super();
	}
	
	public Manager(String username, String name) {
		this();
		this.setUsername(username);
		this.setName(name);
	}
	
	public String username() {
		return username;
	}
	public String name() {
		return name;
	}
	
	private void setUsername(String username) {
		if(username == null) throw new IllegalArgumentException("Debe ingresar el username.");
		this.username = username;
	}
	private void setName(String name) {
		if(name == null) throw new IllegalArgumentException("Debe ingresar el name.");
		this.name = name;
	}

	@Override
	public String toString() {
		return "Manager [username=" + username + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manager other = (Manager) obj;
		return Objects.equals(name, other.name) && Objects.equals(username, other.username);
	}
	
}
