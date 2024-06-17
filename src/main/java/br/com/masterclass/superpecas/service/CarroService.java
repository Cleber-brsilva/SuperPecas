package br.com.masterclass.superpecas.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.repository.CarroRepository;

@Service
public class CarroService {

    private final CarroRepository carroRepository;

    public CarroService (CarroRepository carroRepository){  
        this.carroRepository =  carroRepository;        
    }

    public List<Carro> list (){
        return carroRepository.findAll();
    }

    public Carro findById(@PathVariable Integer id) {
        return carroRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Carro não encontrado com o id: " + id));
        
        //.orElseThrow(() -> new 
        //new 
        //RecordNotFoundException(id,"Aluno"));
    }
}
