package com.fabricio.bookstore.services;

import com.fabricio.bookstore.domain.Categoria;
import com.fabricio.bookstore.domain.dto.CategoriaDTO;
import com.fabricio.bookstore.exceptions.ResourceNotFoundException;
import com.fabricio.bookstore.repositories.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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

    public Categoria create(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    @Modifying
    @Transactional
    public Categoria update(Integer id, CategoriaDTO categoriaDTO) {
        Categoria categoria = findById(id);
        categoria.setNome((categoriaDTO.getNome()));
        categoria.setDescricao(categoriaDTO.getDescricao());
        return categoriaRepository.save(categoria);
    }

    @Modifying
    @Transactional
    public Categoria update(Integer id, Map<Object, Object> categoriaDTOProperties) {
        Categoria categoria = findById(id);
        categoriaDTOProperties.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Categoria.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, categoria, new ObjectMapper().convertValue(value, field.getType()));
        });
        return categoriaRepository.save(categoria);
    }

    public void delete(Integer id) {
        Categoria categoria = findById(id);
        categoriaRepository.delete(categoria);
    }
}
