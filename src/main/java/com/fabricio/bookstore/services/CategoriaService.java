package com.fabricio.bookstore.services;

import com.fabricio.bookstore.domain.Categoria;
import com.fabricio.bookstore.exceptions.ResourceNotFoundException;
import com.fabricio.bookstore.repositories.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private Validator validator;

    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("A categoria não foi encontrada! ID: " + id + ", TIPO: " + Categoria.class.getName()));
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
    public Categoria update(Integer id, Map<Object, Object> categoriaProperties) {
        Categoria categoria = findById(id);
        updateProperties(categoriaProperties, categoria);
        Set<ConstraintViolation<Categoria>> violations = validator.validate(categoria);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return categoriaRepository.save(categoria);

    }

    private void updateProperties(Map<Object, Object> categoriaProperties, Categoria categoria) {
        categoriaProperties.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Categoria.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, categoria, new ObjectMapper().convertValue(value, field.getType()));
        });
    }

    public void delete(Integer id) {
        try {
            categoriaRepository.delete(findById(id));
        } catch(org.springframework.dao.DataIntegrityViolationException e) {
            throw new com.fabricio.bookstore.exceptions.DataIntegrityViolationException("A categoria não pode ser deletada, possui livros associados!");
        } catch (EmptyResultDataAccessException e) {
        }
    }
}
