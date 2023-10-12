package com.example.ac2.services;

import com.example.ac2.dtos.DadosProdutoDto;
import com.example.ac2.dtos.ProdutoDto;
import com.example.ac2.models.Produto;

import java.util.List;

public interface ProdutoService {
    Produto inserir(ProdutoDto produtoDto);
    void editar(Long id, ProdutoDto produtoDto);
    void excluir(Long id);
    List<ProdutoDto> obterTodos();
    DadosProdutoDto obterPorId(Long id);
}
