package br.com.viniciusalmeida.buscacep.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cep {

    @Id
    private String cep;

    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
}
