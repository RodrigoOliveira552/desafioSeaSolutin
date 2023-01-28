package com.projeto.SeaSolution.repository;


import com.projeto.SeaSolution.models.Cargo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CargoRepository extends CrudRepository<Cargo, String > {
    Cargo findByCodigo(long codigo);
    List<Cargo> findByNome(String nome);

}
