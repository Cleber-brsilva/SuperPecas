package br.com.masterclass.superpecas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.masterclass.superpecas.model.Peca;

@Repository
public interface PecaRepository extends JpaRepository<Peca,Integer>{    

}
