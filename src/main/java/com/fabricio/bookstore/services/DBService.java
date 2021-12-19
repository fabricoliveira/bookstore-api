package com.fabricio.bookstore.services;

import com.fabricio.bookstore.domain.Categoria;
import com.fabricio.bookstore.domain.Livro;
import com.fabricio.bookstore.repositories.CategoriaRepository;
import com.fabricio.bookstore.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroRepository livroRepository;

    public void inicializaBaseDeDados() {
        Categoria cat1 = Categoria.builder().nome("Informática").descricao("Livros de TI").livros(new ArrayList<>()).build();
        Categoria cat2 = Categoria.builder().nome("Ficção Científica").descricao("Livros de Ficção Científica").livros(new ArrayList<>()).build();
        Categoria cat3 = Categoria.builder().nome("Biografia").descricao("Livros de Biografias").livros(new ArrayList<>()).build();

        Livro l1 = Livro.builder().titulo("Clean Code").nomeAutor("Robert Martin").texto("Lorem Ipsum").categoria(cat1).build();
        Livro l2 = Livro.builder().titulo("Engenharia de Software").nomeAutor("Luis V. Gerstner").texto("Lorem Ipsum").categoria(cat1).build();
        Livro l3 = Livro.builder().titulo("The Time Machine").nomeAutor("H.G Wells").texto("Lorem Ipsum").categoria(cat2).build();
        Livro l4 = Livro.builder().titulo("The War of the Worlds").nomeAutor("H.G Wells").texto("Lorem Ipsum").categoria(cat2).build();
        Livro l5 = Livro.builder().titulo("I, Robot").nomeAutor("Isaac Asimov").texto("Lorem Ipsum").categoria(cat2).build();

        cat1.getLivros().addAll(Arrays.asList(l1, l2));
        cat2.getLivros().addAll(Arrays.asList(l3, l4, l5));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        livroRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));
    }
}
