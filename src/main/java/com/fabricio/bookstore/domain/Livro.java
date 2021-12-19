package com.fabricio.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "livro")
@Table(name = "livro")
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "livro_id")
    private Integer id;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "titulo", length = 100)
    private String titulo;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "nome_autor", length = 100)
    private String nomeAutor;

    @NotNull
    @Size(min = 3, max = 4000)
    @Column(name = "texto", length = 4000)
    private String texto;

    @JsonIgnore
    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
