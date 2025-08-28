package br.com.viniciusalmeida.buscacep.repository;

import br.com.viniciusalmeida.buscacep.model.Cep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CepRepository extends JpaRepository<Cep, String> {

    // Buscar por logradouro (rua/avenida etc.)
    List<Cep> findByLogradouroContainingIgnoreCase(String logradouro);

    // Buscar por cidade
    List<Cep> findByCidadeIgnoreCase(String cidade);

    // Buscar por estado (UF)
    List<Cep> findByEstadoIgnoreCase(String estado);

    // Buscar por bairro
    List<Cep> findByBairroContainingIgnoreCase(String bairro);

    // Buscar por CEP exato
    Optional<Cep> findByCep(String cep);

    // Buscar por cidade e estado juntos
    List<Cep> findByCidadeIgnoreCaseAndEstadoIgnoreCase(String cidade, String estado);
}
