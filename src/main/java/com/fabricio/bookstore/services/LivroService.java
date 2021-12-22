package com.fabricio.bookstore.services;

import com.fabricio.bookstore.domain.Categoria;
import com.fabricio.bookstore.domain.Livro;
import com.fabricio.bookstore.exceptions.ResourceNotFoundException;
import com.fabricio.bookstore.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Livro findById(Integer id) {
        return livroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("O recurso não foi encontrado! ID: " + id + ", TIPO: " + Livro.class.getName()));
    }

    public List<Livro> findAllByCategoria(Integer categoriaId) {
        Categoria categoria = categoriaService.findById(categoriaId);// throws ResourceNotFoundException if Categoria cannot be found
        return livroRepository.findAllByCategoriaOrderByTitulo(categoria);
    }
}
