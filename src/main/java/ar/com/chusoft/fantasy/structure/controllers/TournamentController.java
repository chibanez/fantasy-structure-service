package ar.com.chusoft.fantasy.structure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.chusoft.fantasy.structure.application.TournamentApplicationService;
import ar.com.chusoft.fantasy.structure.application.dto.request.TournamentRequest;
import ar.com.chusoft.fantasy.structure.application.dto.response.TournamentResponse;

@RestController
public class TournamentController {
	
	private TournamentApplicationService service; 
	
	@Autowired
	public TournamentController(TournamentApplicationService service) {
		this.service = service;
	}

	@GetMapping("/tournaments")
	public List<TournamentResponse> getTournaments(){
		return service.getTournaments();
	}
	
	@GetMapping("/tournaments/{id}")
	public TournamentResponse getTournamentById(@PathVariable String id){
		return service.getTournamentById(id);
	}
	
	@PostMapping("/tournaments")
	public ResponseEntity<?> createTournament(@RequestBody TournamentRequest tournament) {
		service.createTournament(tournament.getName());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	//PUT 		/Tournaments/{id}/start			Inicia el tournament
	
	//GET 		/Tournaments/{id}/teams			Consulta los teams del tournament
	//POST 		/Tournaments/{id}/teams			Crea un team para el tournament
	//DELETE 	/Tournaments/{id}/teams/{id}		Elimina un team del tournament
	
	//GET 		/Tournaments/{id}/players			Consulta los players del tournament
	//POST 		/Tournaments/{id}/players			Crea un player para el tournament
	//DELETE 	/Tournaments/{id}/players/{id}		Elimina player del tournament
	
}
