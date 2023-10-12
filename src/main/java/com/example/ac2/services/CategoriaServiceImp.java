package com.example.ac2.services;

import com.example.ac2.dtos.CategoriaDto;
import com.example.ac2.dtos.DadosCategoriaDto;
import com.example.ac2.dtos.ProdutoDto;
import com.example.ac2.exceptions.RegraNegocioException;
import com.example.ac2.models.Categoria;
import com.example.ac2.models.Produto;
import com.example.ac2.repositories.CategoriaRepository;
import com.example.ac2.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImp implements CategoriaService{

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Override
    public Categoria inserir(CategoriaDto categoriaDto) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDto.getNome());

        return categoriaRepository.save(categoria);
    }

    @Override
    public void editar(Long id, CategoriaDto categoriaDto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Código da categoria não encontrada."));
        categoria.setNome(categoriaDto.getNome());
        categoriaRepository.save(categoria);
    }

    @Override
    public void excluir(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Código da categoria não encontrada."));
        categoriaRepository.delete(categoria);
    }

    @Override
    public List<CategoriaDto> obterTodos() {
        List<CategoriaDto> categorias = categoriaRepository.findAll()
                .stream().map((Categoria c) -> {
                    List<Long> produtos = c.getProdutos().stream().map((Produto p) -> p.getId()).collect(Collectors.toList());
                    return CategoriaDto.builder()
                            .id(c.getId())
                            .nome(c.getNome())
                            .produtosId(produtos)
                            .build();
                }).collect(Collectors.toList());
        return categorias;
    }

    @Override
    public DadosCategoriaDto obterPorId(Long id) {
        return categoriaRepository.findById(id).map((Categoria c) -> {
            return DadosCategoriaDto.builder()
                    .id(c.getId())
                    .nome(c.getNome())
                    .produtos(c.getProdutos() != null ?
                            c.getProdutos().stream().map((Produto p) -> {
                                return ProdutoDto.builder()
                                        .id(p.getId())
                                        .nome(p.getNome())
                                        .preco(p.getPreco())
                                        .categoriaId(p.getCategoria().getId())
                                        .build();
                            }).collect(Collectors.toList()) : null
                    ).build();
        }).orElseThrow(() -> new RegraNegocioException("Código da categoria não encontrada."));
    }
}
