package ar.com.chusoft.fantasy.structure.application;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ar.com.chusoft.fantasy.structure.application.dto.response.TournamentResponse;
import ar.com.chusoft.fantasy.structure.domain.model.tournament.Tournament;
import ar.com.chusoft.fantasy.structure.domain.model.tournament.TournamentId;

@Service
public class TournamentApplicationService {
	//Aca llamo a los objetos del modelo (Aggregate Roots y Domain Services), a los repositorios, controlo transacciones (@Transactional) y seguridad (@PreAuthorize)
	
	private static HashMap<TournamentId, Tournament> tournaments = new HashMap<TournamentId, Tournament>(); 
	
	public List<TournamentResponse> getTournaments() {
		return tournaments.values().stream().map(t -> new TournamentResponse(t)).collect(Collectors.toList());
	}
	
	public TournamentResponse getTournamentById(String id) {
		return new TournamentResponse(tournaments.get(new TournamentId(id)));
	}
	
	public void createTournament(String name) {
		Tournament tournament = new Tournament(name);
		tournaments.put(tournament.id(), tournament);
	}
	
		
		//PUT 		/Tournaments/{id}/start			Inicia el tournament
		
		//GET 		/Tournaments/{id}/teams			Consulta los teams del tournament
		//POST 		/Tournaments/{id}/teams			Crea un team para el tournament
		//DELETE 	/Tournaments/{id}/teams/{id}		Elimina un team del tournament
		
		//GET 		/Tournaments/{id}/players			Consulta los players del tournament
		//POST 		/Tournaments/{id}/players			Crea un player para el tournament
		//DELETE 	/Tournaments/{id}/players/{id}		Elimina player del tournament
}
