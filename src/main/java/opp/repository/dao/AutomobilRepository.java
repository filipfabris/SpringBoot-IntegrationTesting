package opp.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import opp.model.Automobil;

public interface AutomobilRepository extends JpaRepository<Automobil, Long>{
	
	boolean updateAutomobil(Automobil automobil);

}
