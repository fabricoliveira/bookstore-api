package com.fabricio.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "Livro")
@Table(name = "Livro")
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "livro_id")
    private Integer id;

    @NotNull(message = "O campo TITULO não pode estar nulo")
    @Size(min = 3, max = 50, message = "O campo TITULO deve conter entre {min} e {max} caracteres")
    @Column(name = "titulo")
    private String titulo;

    @NotNull(message = "O campo NOME DO AUTOR não pode estar nulo")
    @Size(min = 3, max = 50, message = "O campo NOME DO AUTOR deve conter entre {min} e {max} caracteres")
    @Column(name = "nome_autor")
    private String nomeAutor;

    @NotNull(message = "O campo TEXTO não pode estar nulo")
    @Size(min = 3, max = 2000000, message = "O campo TEXTO deve conter entre {min} e {max} caracteres")
    @Column(name = "texto")
    private String texto;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
