package com.example.ac2.services;

import com.example.ac2.dtos.CategoriaDto;
import com.example.ac2.dtos.DadosProdutoDto;
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
public class ProdutoServiceImp implements ProdutoService{

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Produto inserir(ProdutoDto produtoDto) {
        Categoria categoria = categoriaRepository.findById(
                produtoDto.getCategoriaId()).orElseThrow(
                        () -> new RegraNegocioException("Código da categoria não encontrada"));
        Produto produto = new Produto();
        produto.setNome(produtoDto.getNome());
        produto.setPreco(produtoDto.getPreco());
        produto.setCategoria(categoria);

        return produtoRepository.save(produto);
    }

    @Override
    public void editar(Long id, ProdutoDto produtoDto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Código de produto não encontrado."));
        Categoria categoria = categoriaRepository.findById(produtoDto.getCategoriaId())
                .orElseThrow(() -> new RegraNegocioException("Código da categoria não encontrada."));
        produto.setNome(produtoDto.getNome());
        produto.setPreco(produtoDto.getPreco());
        produto.setCategoria(categoria);
        produtoRepository.save(produto);
    }

    @Override
    public void excluir(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Código de produto não encontrado."));
        produtoRepository.delete(produto);
    }

    @Override
    public List<ProdutoDto> obterTodos() {
        List<ProdutoDto> produtos = produtoRepository.findAll()
                .stream().map((Produto p) -> {
                    return ProdutoDto.builder()
                            .id(p.getId())
                            .nome(p.getNome())
                            .preco(p.getPreco())
                            .categoriaId(p.getCategoria() == null ? null : p.getCategoria().getId())
                            .build();
                }).collect(Collectors.toList());
        return produtos;
    }

    @Override
    public DadosProdutoDto obterPorId(Long id) {
        return produtoRepository.findById(id).map((Produto p) -> {
            return DadosProdutoDto.builder()
                    .id(p.getId())
                    .nome(p.getNome())
                    .preco(p.getPreco())
                    .categoria(p.getCategoria() != null ?
                            CategoriaDto.builder()
                                    .id(p.getCategoria().getId())
                                    .nome(p.getCategoria().getNome())
                                    .build() : null
                    ).build();
                }).orElseThrow(() -> new RegraNegocioException("Código de produto não encontrado."));
    }
}
