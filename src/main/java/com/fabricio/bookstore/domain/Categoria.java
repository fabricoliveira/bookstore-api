package com.fabricio.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "Categoria")
@Table(name = "Categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Integer id;

    @NotNull(message = "O campo NOME não pode estar nulo")
    @Size(min = 3, max = 100, message = "O campo NOME deve conter entre {min} e {max} caracteres")
    @Column(name = "nome")
    private String nome;

    @NotNull(message = "O campo DESCRICAO não pode estar nulo")
    @Size(min = 3, max = 200, message = "O campo DESCRICAO deve conter entre {min} e {max} caracteres")
    @Column(name = "descricao")
    private String descricao;

    @JsonManagedReference
    @OneToMany(mappedBy = "categoria")
    private List<Livro> livros = new ArrayList<>();
}
