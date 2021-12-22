package com.fabricio.bookstore.services;

import com.fabricio.bookstore.domain.Livro;
import com.fabricio.bookstore.exceptions.ResourceNotFoundException;
import com.fabricio.bookstore.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro findById(Integer id) {
        return livroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("O recurso não foi encontrado! ID: " + id + ", TIPO: " + Livro.class.getName()));
    }
}
