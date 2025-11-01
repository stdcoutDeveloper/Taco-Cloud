package tacos.repository;

import org.springframework.data.repository.CrudRepository;

import tacos.model.Taco;
import tacos.model.TacoKey;

public interface TacoRepository extends CrudRepository<Taco, TacoKey> {
	
}
