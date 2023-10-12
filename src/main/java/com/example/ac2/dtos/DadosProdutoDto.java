package com.example.ac2.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DadosProdutoDto {
    private Long id;
    private String nome;
    private Double preco;
    private CategoriaDto categoria;
}
