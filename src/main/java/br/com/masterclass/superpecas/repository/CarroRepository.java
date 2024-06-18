package br.com.masterclass.superpecas.repository;

import org.springframework.stereotype.Repository;

import br.com.masterclass.superpecas.model.Carro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro,Integer>{

    // Método para verificar se um carro já existe    
    boolean existsByCodigoUnico(String codigoUnico);  
    
    Carro findByCodigoUnico(String codigoUnico);

    /*selecionar os fabricantes e contar o número de carros de cada fabricante, 
    agrupando pelo fabricante e ordenando pelo número de carros de forma decrescente. 
    O resultado é uma lista de objetos, onde cada objeto é um array de dois elementos: 
    o fabricante e o número de carros. */ 
    @Query("SELECT c.fabricante, COUNT(c) as numCarros " +
           "FROM Carro c " +
           "GROUP BY c.fabricante " +
           "ORDER BY numCarros DESC")
    List<Object[]> findTop10Fabricantes();  
    
    
    @Query(value =  "SELECT c.CarroID, c.NomeModelo, c.Fabricante, c.CodigoUnico, COUNT(p.PecaID) AS NumeroDePecas " +
                    "FROM Carros c " +
                    "JOIN Pecas p ON c.CarroID = p.CarroID " +
                    "GROUP BY c.CarroID, c.NomeModelo, c.Fabricante, c.CodigoUnico " +
                    "ORDER BY NumeroDePecas DESC " +
                    "LIMIT 10", nativeQuery = true)
    List<Object[]> findTop10CarrosComMaisPecas2();

    @Query(value =  "SELECT c.NomeModelo " +
                    "FROM Carros c " +
                    "JOIN Pecas p ON c.CarroID = p.CarroID " +
                    "GROUP BY c.CarroID, c.NomeModelo " +
                    "ORDER BY COUNT(p.PecaID) DESC " +
                    "LIMIT 10", nativeQuery = true)
    List<String> findTop10CarrosComMaisPecas();

    @Query("SELECT c FROM Carro c WHERE c.nomeModelo LIKE %:termo% OR c.fabricante LIKE %:termo% OR c.codigoUnico LIKE %:termo%")
    Page<Carro> findByTermo(@Param("termo") String termo, Pageable pageable);

}


