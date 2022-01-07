package com.fabricio.bookstore.services;

import com.fabricio.bookstore.domain.Categoria;
import com.fabricio.bookstore.domain.Livro;
import com.fabricio.bookstore.exceptions.MethodArgumentNotValidException;
import com.fabricio.bookstore.exceptions.ResourceNotFoundException;
import com.fabricio.bookstore.repositories.LivroRepository;
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
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Livro findById(Integer id) {
        return livroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("O livro não foi encontrado! ID: " + id + ", TIPO: " + Livro.class.getName()));
    }

    public List<Livro> findAllByCategoria(Integer categoriaId) {
        Categoria categoria = categoriaService.findById(categoriaId);// throws ResourceNotFoundException if Categoria cannot be found
        return livroRepository.findAllByCategoriaOrderByTitulo(categoria);
    }

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    public Livro update(Integer id, Map<Object, Object> livroProperties) throws MethodArgumentNotValidException {
        Livro livro = findById(id);
        updateProperties(livroProperties, livro);
        if (livro.getCategoria() == null) {
            throw new MethodArgumentNotValidException("O livro ID: " + id + " não pode ser atualizado! A categoria não pode ser nula.");
        }
        return livroRepository.save(livro);
    }

    private void updateProperties(Map<Object, Object> livroProperties, Livro livro) {
        livroProperties.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Livro.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, livro, new ObjectMapper().convertValue(value, field.getType()));
        });
    }

    public Livro create(Integer categoriaId, Livro livro) {
        livro.setId(null);
        livro.setCategoria(categoriaService.findById(categoriaId));
        return livroRepository.save(livro);
    }

    public void delete(Integer id) {
        livroRepository.delete(findById(id));
    }
}
