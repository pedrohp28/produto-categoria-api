package com.example.ac2.repositories;

import com.example.ac2.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByPrecoGreaterThan(Double preco);

    List<Produto> findByPrecoIsLessThanEqual(Double preco);

    List<Produto> findByNomeLike(String nome);

}
