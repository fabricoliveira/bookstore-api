package com.fabricio.bookstore.services;

import com.fabricio.bookstore.domain.Categoria;
import com.fabricio.bookstore.exceptions.ResourceNotFoundException;
import com.fabricio.bookstore.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("O recurso não foi encontrado! ID: " + id + ", TIPO: " + Categoria.class.getName()));
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }
}
