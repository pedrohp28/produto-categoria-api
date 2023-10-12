package com.example.ac2.controllers;

import com.example.ac2.dtos.CategoriaDto;
import com.example.ac2.dtos.DadosCategoriaDto;
import com.example.ac2.services.CategoriaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categoria")
public class CaategoriaController {
    @Autowired
    private CategoriaServiceImp service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long inserir(@RequestBody CategoriaDto categoriaDto) {
        return service.inserir(categoriaDto).getId();
    }

    @GetMapping
    public List<CategoriaDto> buscarCategorias() {
        return service.obterTodos();
    }

    @GetMapping("{id}")
    public DadosCategoriaDto buscarCategoriaPorId(@PathVariable Long id) {
        return service.obterPorId(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @PutMapping("{id}")
    public void editar(@PathVariable Long id, @RequestBody CategoriaDto dto) {
        service.editar(id, dto);
    }
}
