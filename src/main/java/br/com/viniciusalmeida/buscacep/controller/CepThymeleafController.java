package br.com.viniciusalmeida.buscacep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class CepThymeleafController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/")
    public String home() {
        return "index"; // página inicial com formulário
    }

    @GetMapping("/buscar-cep")
    public String buscarCep(@RequestParam String cep, Model model) {
        try {
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            Map<String, Object> resposta = restTemplate.getForObject(url, Map.class);
            model.addAttribute("resultado", resposta);
        } catch (RestClientException e) {
            model.addAttribute("resultado", Collections.emptyMap());
        }
        return "resultado"; // página que mostra o resultado
    }

    @GetMapping("/buscar-rua")
    public String buscarRua(@RequestParam String uf,
                            @RequestParam String cidade,
                            @RequestParam String rua,
                            Model model) {
        try {
            URI uri = UriComponentsBuilder.fromHttpUrl("https://viacep.com.br/ws")
                    .pathSegment(uf)
                    .pathSegment(cidade)
                    .pathSegment(rua)
                    .pathSegment("json")
                    .build()
                    .encode()
                    .toUri();

            Map<String, Object>[] respostaArray = restTemplate.getForObject(uri, Map[].class);
            List<Map<String, Object>> resultados = respostaArray != null ? Arrays.asList(respostaArray) : Collections.emptyList();
            model.addAttribute("resultados", resultados);

        } catch (RestClientException e) {
            model.addAttribute("resultados", Collections.emptyList());
        }

        return "resultado-rua";
    }

}
