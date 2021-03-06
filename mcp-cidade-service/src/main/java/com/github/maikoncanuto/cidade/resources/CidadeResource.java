package com.github.maikoncanuto.cidade.resources;

import com.github.maikoncanuto.cidade.entities.Cidade;
import com.github.maikoncanuto.cidade.entities.dtos.ResponseDTO;
import com.github.maikoncanuto.cidade.entities.enums.UF;
import com.github.maikoncanuto.cidade.services.CidadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CidadeResource.class);

    @Autowired
    private CidadeService cidadeService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final Cidade cidade) {
        LOGGER.info(format("[CidadeResource-create] - request %s", cidade));
        final ResponseDTO<Cidade> response = cidadeService.save(cidade);
        return new ResponseEntity<>(response, CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findByNomeOuUf(@RequestParam(name = "nome", required = false) final String nome,
                                            @RequestParam(name = "uf", required = false) final UF uf) {
        LOGGER.info(format("[CidadeResource-findByNomeOuUf] - request nome[%s], uf[%s]", nome, uf));
        final ResponseDTO<List<Cidade>> response = cidadeService.findByNomeOrUf(nome, uf);
        return new ResponseEntity<>(response, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id) {
        LOGGER.info(format("[CidadeResource-findById] - request id[%s]", id));
        final ResponseDTO<Cidade> response = cidadeService.findById(id);
        return new ResponseEntity<>(response, OK);
    }

}
