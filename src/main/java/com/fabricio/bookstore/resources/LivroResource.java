package com.fabricio.bookstore.resources;

import com.fabricio.bookstore.domain.Livro;
import com.fabricio.bookstore.domain.dto.LivroDTO;
import com.fabricio.bookstore.exceptions.MethodArgumentNotValidException;
import com.fabricio.bookstore.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
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

    @PutMapping("/{id}")
    public ResponseEntity<Livro> update(@PathVariable("id") Integer id, @RequestBody Map<Object, Object> livroProperties) throws MethodArgumentNotValidException {
        return ResponseEntity.ok().body(livroService.update(id, livroProperties));
    }

    @PostMapping
    public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0") Integer categoriaId, @RequestBody @Valid Livro livro) {
        livro = livroService.create(categoriaId, livro);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(livro.getId())
                .toUri();
        return ResponseEntity.created(uri).body(livro);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        livroService.delete(id);
    }
}
