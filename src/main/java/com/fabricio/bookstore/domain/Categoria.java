package com.fabricio.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    private Integer id;

    private String name;
    private String descricao;

    private List<Livro> livros = new ArrayList<>();
}
