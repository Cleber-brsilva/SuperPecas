package br.com.masterclass.superpecas.repository;

import org.springframework.stereotype.Repository;

import br.com.masterclass.superpecas.model.Carro;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CarroRepository extends JpaRepository<Carro,Integer>{

}


