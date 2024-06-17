package br.com.masterclass.superpecas.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.masterclass.superpecas.exception.RecordNotFoundException;
import br.com.masterclass.superpecas.model.Peca;
import br.com.masterclass.superpecas.repository.PecaRepository;

@Service
public class PecaService {

    private final PecaRepository pecaRepository;

    public PecaService (PecaRepository pecaRepository){  
        this.pecaRepository =  pecaRepository;        
    }

    public List<Peca> list (){
        return pecaRepository.findAll();
    }

    public Peca findById(@PathVariable Integer id) {
        return pecaRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id,"Carro"));
    }

    
}