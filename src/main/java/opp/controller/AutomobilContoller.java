package opp.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import opp.model.Automobil;
import opp.service.AutomobilService;

@RestController
public class AutomobilContoller {
	
	@Autowired
	AutomobilService service;
	
	@PostMapping("/add")
	ResponseEntity<Automobil> addAutomobil(@RequestBody Automobil automobil) throws URISyntaxException{
		Long output = this.service.saveAutomobil(automobil);
		if(output == null) {
			return new ResponseEntity<Automobil>(HttpStatus.BAD_REQUEST);
		}
//		return new ResponseEntity<Automobil>(HttpStatus.CREATED);
		return ResponseEntity.created(new URI("/user/" + output)).build();
	}
	
	
	@GetMapping("/car/{id}")
	public ResponseEntity<Automobil> getAutomobil(@PathVariable("id") Long id){
		Automobil output = service.getAutomobil(id);
		if(output == null) {
			return new ResponseEntity<Automobil>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(output);		
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Boolean> deleteAutomobil(@PathVariable("id") Long id){
		boolean output = service.removeAutomobil(id);
		if(output == false) {
			return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.accepted().body(output);
	}
	
	@PutMapping("/modify")
	public ResponseEntity<Automobil> sceneEdit(@RequestBody Automobil automobil) throws URISyntaxException {
		Long output = this.service.saveAutomobil(automobil);
		if(output == null) {
			return new ResponseEntity<Automobil>(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.created(new URI("/user/" + output)).build();
	}
	
	@GetMapping("/cars")
	public ResponseEntity<List<Automobil>> getAll(){
		List<Automobil> output = service.getAll();
		return ResponseEntity.ok(output);		
	}
	
}
