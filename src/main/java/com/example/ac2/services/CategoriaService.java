package com.example.ac2.services;

import com.example.ac2.dtos.CategoriaDto;
import com.example.ac2.dtos.DadosCategoriaDto;
import com.example.ac2.models.Categoria;

import java.util.List;

public interface CategoriaService {
    Categoria inserir(CategoriaDto categoriaDto);
    void editar(Long id, CategoriaDto categoriaDto);
    void excluir(Long id);
    List<CategoriaDto> obterTodos();
    DadosCategoriaDto obterPorId(Long id);
}
