package opp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import opp.model.Automobil;
import opp.repository.dao.AutomobilRepository;

@Service
public class AutomobilService {
	
	@Autowired
	AutomobilRepository repository;
	
    public Long saveAutomobil(Automobil automobil) {
    	automobil = repository.save(automobil);
        return automobil.getId();
    }

	public Automobil getAutomobil(Long id) {
		Optional<Automobil> output = repository.findById(id);
		return output.orElse(null);
	}
	
	public boolean removeAutomobil(Long id) {
		Optional<Automobil> output = repository.findById(id);
		if(output.isPresent()) {
			repository.delete(output.get());	
			return true;
		}
		return false;
	}

	public List<Automobil> getAll() {
		return repository.findAll();
	}
	
    public boolean updateAutomobil(Automobil automobil) {
    	boolean output = repository.updateAutomobil(automobil);
        return output;
    }
	
	

}
