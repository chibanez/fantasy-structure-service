package ar.com.chusoft.fantasy.structure.domain.model.tournament;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import ar.com.chusoft.fantasy.structure.domain.model.tournament.team.TeamId;
import ar.com.chusoft.fantasy.structure.domain.model.tournament.team.player.PlayerId;

public class Tournament implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final int TEAMS_PER_TOURNAMENT = 2;
	private final int MIN_PLAYERS_PER_TOURNAMENT = 3;
	
	public enum TournamentStatus{ CREATED, STARTED, FINISHED, CANCELLED }; 
	
	//Invariants
	//1 - Un Tournament siempre debe tener un nombre
	//2 - Un Tournament siempre debe tener un estado
	//3 - Un Tournament siempre debe tener 20 Teams para estar en estado STARTED
	//4 - Un Tournament siempre debe tener al menos 320 Players para pasar a estado STARTED
	
	private TournamentId tournamentId;
	private String name;
	private TournamentStatus status;
	
	private Set<TeamId> teams;
	private Set<PlayerId> players;
		
	//CONSTRUCTORS
	public Tournament(String name) {
		this.tournamentId = new TournamentId(UUID.randomUUID().toString());
		this.setStatus(TournamentStatus.CREATED);
		this.setName(name);
		this.setTeams(new HashSet<TeamId>());
		this.setPlayers(new HashSet<PlayerId>());
	}

	//GETTERS
	public TournamentId id() {
		return this.tournamentId;
	}
	
	public String name() {
		return this.name;
	}
	
	public TournamentStatus status() {
		return this.status;
	}
	
	public Set<TeamId> teams() {
		return this.teams;
	}

	public Set<PlayerId> players() {
		return this.players;
	}
	
	public int teamsPerTournament() {
		return TEAMS_PER_TOURNAMENT;
	}
	
	public int minPlayersPerTournament() {
		return MIN_PLAYERS_PER_TOURNAMENT;
	}
	
	//SETTERS
	private void setName(String name) {
		if(name == null || name.trim().length() == 0) throw new IllegalArgumentException("Debe ingresar un nombre para el torneo.");
		this.name = name;
	}
	
	private void setStatus(TournamentStatus status) {
		
		switch(status) {
			case CREATED:
				if(this.status != null) throw new IllegalArgumentException("El estado CREATED solo puede asignarse durante la inicializacion del Torneo.");
				break;
				
			case STARTED:
				
				if(!this.status.equals(TournamentStatus.CREATED)) throw new IllegalArgumentException("Solo pueden iniciarse torneos en estado CREATED.");
				if(teams == null) throw new IllegalArgumentException("Debe ingresar equipos para iniciar el torneo.");
				if(teams.size() != TEAMS_PER_TOURNAMENT) throw new IllegalArgumentException("El torneo debe tener " + TEAMS_PER_TOURNAMENT + " equipos para poder iniciarse.");
				if(players == null) throw new IllegalArgumentException("Debe ingresar jugadores para iniciar el torneo.");
				if(players.size() < MIN_PLAYERS_PER_TOURNAMENT) throw new IllegalArgumentException("El torneo debe tener como mínimo " + MIN_PLAYERS_PER_TOURNAMENT + " jugadores para poder iniciarse.");
				break;
				
			case FINISHED:
				if(!this.status.equals(TournamentStatus.STARTED)) throw new IllegalArgumentException("Solo pueden finalizarse torneos en estado STARTED.");
				break;
				
			case CANCELLED:
				break;
				
			default:
			
		}
		
		this.status = status;
	}
	
	private void setTeams(Set<TeamId> teams) {
		this.teams = teams;
	}
	
	private void setPlayers(Set<PlayerId> players) {
		this.players = players;
	}
	
	//BEHAVIOUR
	public void addTeamToTournament(TeamId teamId) {
		//TODO Ver si puedo hacer que Tournament funcione como factory de Team
		//Ver la respuesta de: https://stackoverflow.com/questions/68407906/ddd-how-to-check-invariant-with-multiple-aggregate
		//Tournament crearia una nueva instancia de Team y la devolveria para que la persista el Repository afuera. Si falla el proceso de persistencia debo eliminar el team del tournament
		//Al mismo tiempo tomaria el ID y lo agregaria a su Set de Teams. 
		//Podria controlar entonces que no se creen mas de 20 equipos en este momento tambien.
		//En vez de guardar solo el TeamId podria guardar ValueObject(TeamId + Manager) ya que un team id que se crea para un manager siempre va a pertenecer a el.
		//Y asi puedo controlar cuantos equipos tengo para cada tipo de manager y validar eso tambien.
		if(!this.status.equals(TournamentStatus.CREATED)) throw new IllegalArgumentException("Ya no puede agregarse equipos a este torneo.");
		this.teams.add(teamId);
	}
	
	public void removeTeamFromTournament(TeamId teamId) {
		if(!this.status.equals(TournamentStatus.CREATED)) throw new IllegalArgumentException("Ya no puede eliminarse equipos de este torneo.");
		this.teams.remove(teamId);
	}
	
	public void replaceTeamFromTournament(TeamId oldTeam, TeamId newTeam) {
		//TODO Validaciones replaceTeamFromTournament
		// 1 - Puedo reemplazar cuando el torneo esta CREATED o STARTED
		// 2 - No puedo reemplazar equipo humano por otro humano
		
		
		this.teams.remove(oldTeam);
		this.teams.add(newTeam);
	}
	
	public void addPlayerToTournament(PlayerId playerId) {
		this.players.add(playerId);
	}
	
	public void removePlayerFromTournament(PlayerId playerId) {
		// TODO Validaciones removePlayerFromTournament
		// No deberia poder remover un player que ya esta asignado a un equipo
		this.players.remove(playerId);
	}
	
	public void startTournament() {
		this.setStatus(TournamentStatus.STARTED);
	}
	
	//TODO Ver Tournament
	//El torneo se crea vacio, pero solo se podria iniciar si tiene 20 equipos y al menos 320 jugadores asignados
	
	//TODO Ver inicializacion de Tournament
	//Domain service TournamentService metodo inicializarTournament. Ahi llamo a PlayerService que conecta con el BoundedContext de RealPlayers, los crea en este context usando PlayerRepository y devuelve un Set<PlayerId>
	//Tambien se llama a TeamService que crea 20 equipos administrados por el System, los graba en TeamRepository y devuelve un Set<TeamId>
	//Luego pasa esos Sets al metodo de creacion del Tournament que es donde se validan las invariant que obliga a tener al menos 320 jugadores y exactamente 20 equipos.
	
	

	
}
