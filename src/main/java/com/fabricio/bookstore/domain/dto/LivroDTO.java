package com.fabricio.bookstore.domain.dto;

import com.fabricio.bookstore.domain.Livro;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class LivroDTO {

    private Integer id;
    private String titulo;

    public LivroDTO(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
    }
}
