package br.com.masterclass.superpecas.repository;

import org.springframework.stereotype.Repository;

import br.com.masterclass.superpecas.model.Carro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro,Integer>{

    /*selecionar os fabricantes e contar o número de carros de cada fabricante, 
    agrupando pelo fabricante e ordenando pelo número de carros de forma decrescente. 
    O resultado é uma lista de objetos, onde cada objeto é um array de dois elementos: 
    o fabricante e o número de carros. */ 
    @Query("SELECT c.fabricante, COUNT(c) as numCarros " +
           "FROM Carro c " +
           "GROUP BY c.fabricante " +
           "ORDER BY numCarros DESC")
    List<Object[]> findTop10Fabricantes();

}


