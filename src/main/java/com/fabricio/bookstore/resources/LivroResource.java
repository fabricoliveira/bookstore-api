package com.fabricio.bookstore.resources;

import com.fabricio.bookstore.domain.Livro;
import com.fabricio.bookstore.domain.dto.LivroDTO;
import com.fabricio.bookstore.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {

    @Autowired
    private LivroService livroService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Livro> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(livroService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAllByCategoria(@RequestParam(value = "categoria", defaultValue = "0") Integer categoria) {
        List<Livro> livros = livroService.findAllByCategoria(categoria);
        List<LivroDTO> livroDTOS = livros.stream().map(livro -> new LivroDTO(livro)).collect(Collectors.toList());
        return ResponseEntity.ok().body(livroDTOS);
    }
}
