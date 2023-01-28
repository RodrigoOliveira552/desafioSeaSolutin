package com.projeto.SeaSolution.repository;

import com.projeto.SeaSolution.models.Cargo;
import com.projeto.SeaSolution.models.Trabalhador;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrabalhadorRepository extends CrudRepository<Trabalhador, String> {

    Iterable<Trabalhador>findByCargo(Cargo cargo);

    Trabalhador findByRg(String rg);

    Trabalhador findById(long id);

    List<Trabalhador>findByNomeTrabalhador(String nomeTrabalhador);


}
