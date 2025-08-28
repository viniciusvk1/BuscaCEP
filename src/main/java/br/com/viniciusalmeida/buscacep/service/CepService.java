package br.com.viniciusalmeida.buscacep.service;

import br.com.viniciusalmeida.buscacep.model.Cep;
import br.com.viniciusalmeida.buscacep.repository.CepRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CepService {
    private final CepRepository cepRepository;
    private final WebClient webClient = WebClient.create("https://brasilapi.com.br/api/cep/v1/");

    public CepService(CepRepository cepRepository) {
        this.cepRepository = cepRepository;
    }

    public Cep buscarCep(String cep) {
        // Tenta buscar no banco primeiro
        return cepRepository.findById(cep).orElseGet(() -> {
            // Se não existe, consulta BrasilAPI
            Cep resultado = webClient.get()
                    .uri(cep)
                    .retrieve()
                    .bodyToMono(Cep.class)
                    .block(); // síncrono

            if (resultado != null) {
                cepRepository.save(resultado); // salva no histórico
            }

            return resultado;
        });
    }
}