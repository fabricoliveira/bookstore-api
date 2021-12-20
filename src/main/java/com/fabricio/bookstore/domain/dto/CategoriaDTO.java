package com.fabricio.bookstore.domain.dto;

import com.fabricio.bookstore.domain.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class CategoriaDTO {

    private Integer id;
    private String nome;
    private String descricao;

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.descricao = categoria.getDescricao();
    }
}
