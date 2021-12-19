package com.fabricio.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "livro")
@Table(name = "livro")
public class Livro {

    private Integer id;

    private String titulo;
    private String nomeAutor;
    private String texto;

    private Categoria categoria;
}
