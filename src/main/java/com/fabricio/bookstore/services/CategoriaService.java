package com.fabricio.bookstore.services;

import com.fabricio.bookstore.domain.Categoria;
import com.fabricio.bookstore.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }
}
