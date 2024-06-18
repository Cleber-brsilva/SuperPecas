package br.com.masterclass.superpecas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import br.com.masterclass.superpecas.model.Peca;

@Repository
public interface PecaRepository extends JpaRepository<Peca,Integer>{    

    //Verifica se peça relacionada ao carro
    boolean existsByCarro_CarroID(Integer carroId);

    Peca findByNumeroSerie(String numeroSerie);

    @Query("SELECT p FROM Peca p WHERE p.nome LIKE %:termo% OR p.descricao LIKE %:termo% OR p.numeroSerie LIKE %:termo% OR p.fabricante LIKE %:termo% OR p.modeloCarro LIKE %:termo%")
    Page<Peca> findByTermo(@Param("termo") String termo, Pageable pageable);

}
