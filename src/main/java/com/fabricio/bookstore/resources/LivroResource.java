package com.fabricio.bookstore.resources;

import com.fabricio.bookstore.domain.Livro;
import com.fabricio.bookstore.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {

    @Autowired
    private LivroService livroService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Livro> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(livroService.findById(id));
    }
}
