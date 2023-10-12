package com.example.ac2;

import com.example.ac2.models.Categoria;
import com.example.ac2.models.Produto;
import com.example.ac2.repositories.CategoriaRepository;
import com.example.ac2.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Ac1Application {

	public static void main(String[] args) {
		SpringApplication.run(Ac1Application.class, args);
	}

	@Bean
	public CommandLineRunner init(@Autowired ProdutoRepository produtoRepository,
								  @Autowired CategoriaRepository categoriaRepository) {
		return args -> {

			//Testes categoriaRepository
			Categoria c1 = new Categoria("Eletronicos");
			Categoria c2 = new Categoria("Construção");
			Categoria c3 = new Categoria("Jardinagem");
			System.out.println("TESTE CATEGORIA");

			System.out.println("INSERINDO CATEGORIA:");
			System.out.println(categoriaRepository.save(c1));
			System.out.println(categoriaRepository.save(c2));
			System.out.println(categoriaRepository.save(c3));
			System.out.println("-".repeat(30));

			System.out.println("OBTENDO TODAS AS CATEGORIAS:");
			System.out.println(categoriaRepository.findAll());
			System.out.println("-".repeat(30));

			System.out.println("EDITANDO CATEGORIA:");
			c2 = categoriaRepository.findById(2L).orElseThrow();
			c2.setNome("Cozinha");
			System.out.println(categoriaRepository.save(c2));
			System.out.println("-".repeat(30));

			System.out.println("OBTENDO CATEGORIA PELO NOME:");
			System.out.println(categoriaRepository.findByNomeLike("%le%"));
			System.out.println("-".repeat(30));

			System.out.println("EXCLUINDO CATEGORIA:");
			categoriaRepository.findById(2L).orElseThrow();
			System.out.println("Excluindo " + c2);
			categoriaRepository.delete(c2);
			System.out.println(categoriaRepository.findAll());

			//Testes produtoRepository
			c1 = categoriaRepository.findById(1L).orElseThrow();

			List<Produto> produtos = new ArrayList<>();
			Produto p1 = new Produto("Celular", 2000.0, c1);
			produtos.add(p1);
			Produto p2 = new Produto("TV", 3500.0, c1);
			produtos.add(p2);
			Produto p3 = new Produto("Notebook", 6000.0, c1);
			produtos.add(p3);
			c1.setProdutos(produtos);
			categoriaRepository.save(c1);

			System.out.println();
			System.out.println("TESTE PRODUTO");

			System.out.println("INSERINDO PRODUTO:");
			System.out.println(produtoRepository.save(p1));
			System.out.println(produtoRepository.save(p2));
			System.out.println(produtoRepository.save(p3));
			System.out.println("-".repeat(30));

			System.out.println(produtoRepository.findAll());

			System.out.println("EDITANDO PRODUTO:");
			p2 = produtoRepository.findById(2L).orElseThrow();
			p2.setPreco(3000.0);
			produtoRepository.save(p2);
			System.out.println(p2);
			System.out.println("-".repeat(30));

			System.out.println("OBTENDO PRODUTO COM VALOR MAIOR QUE:");
			System.out.println(produtoRepository.findByPrecoGreaterThan(2999.0));
			System.out.println("-".repeat(30));

			System.out.println("OBTENDO PRODUTO COM VALOR MENOR OU IGUAL QUE:");
			System.out.println(produtoRepository.findByPrecoIsLessThanEqual(2000.0));
			System.out.println("-".repeat(30));

			System.out.println("OBTENDO PRODUTO PELO NOME:");
			System.out.println(produtoRepository.findByNomeLike("%e%"));
			System.out.println("-".repeat(30));

			System.out.println("EXCLUINDO PRODUTO:");
			p2 = produtoRepository.findById(2L).orElseThrow();
			System.out.println("Excluindo " + p2);
			produtoRepository.delete(p2);
			c1.getProdutos().remove(1);
			categoriaRepository.save(c1);
			System.out.println(produtoRepository.findAll());
			System.out.println();

			System.out.println("CHAMANDO A CATEGORIA COM SEUS PRODUTOS:");
			System.out.println(categoriaRepository.findCategoriaCursoFetchCursos(1L));
		};
	}
}
