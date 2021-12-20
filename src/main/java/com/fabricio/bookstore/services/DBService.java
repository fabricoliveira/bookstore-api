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
        Categoria cat1 = new Categoria(null, "Informática", "Livros de TI", new ArrayList<>());
        Categoria cat2 = new Categoria(null, "Ficção Científica", "Livros de Ficção Científica", new ArrayList<>());
        Categoria cat3 = new Categoria(null, "Biografia", "Livros de Biografias", new ArrayList<>());

        Livro l1 = new Livro(null, "Clean Code", "Robert Martin", "Lorem Ipsum", cat1);
        Livro l2 = new Livro(null, "Engenharia de Software", "Luis V. Gerstner", "Lorem Ipsum", cat1);
        Livro l3 = new Livro(null, "The Time Machine", "H.G Wells", "Lorem Ipsum", cat2);
        Livro l4 = new Livro(null, "The War of the Worlds", "H.G Wells", "Lorem Ipsum", cat2);
        Livro l5 = new Livro(null, "I, Robot", "Isaac Asimov", "Lorem Ipsum", cat2);

        cat1.getLivros().addAll(Arrays.asList(l1, l2));
        cat2.getLivros().addAll(Arrays.asList(l3, l4, l5));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        livroRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));
    }
}
