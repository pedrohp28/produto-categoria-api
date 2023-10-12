package com.example.ac2.controllers;

import com.example.ac2.dtos.DadosProdutoDto;
import com.example.ac2.dtos.ProdutoDto;
import com.example.ac2.services.ProdutoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoServiceImp service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long inserir(@RequestBody ProdutoDto produtoDto) {
        return service.inserir(produtoDto).getId();
    }

    @GetMapping
    public List<ProdutoDto> buscarProdutos() {
        return service.obterTodos();
    }

    @GetMapping("{id}")
    public DadosProdutoDto buscarProdutoPorId(@PathVariable Long id) {
        return service.obterPorId(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @PutMapping("{id}")
    public void editar(@PathVariable Long id, @RequestBody ProdutoDto dto) {
        service.editar(id, dto);
    }
}
