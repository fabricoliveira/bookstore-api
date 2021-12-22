package com.fabricio.bookstore.repositories;

import com.fabricio.bookstore.domain.Categoria;
import com.fabricio.bookstore.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

    //@Query("SELECT l FROM Livro l WHERE l.categoria.id = :categoriaId ORDER BY titulo")
    //List<Livro> findAllByCategoriaOrderByTitulo(@Param(value = "categoriaId") Integer categoriaId);
    List<Livro> findAllByCategoriaOrderByTitulo(Categoria categoria);

}
