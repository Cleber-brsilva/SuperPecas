package br.com.masterclass.superpecas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.masterclass.superpecas.model.Peca;
import br.com.masterclass.superpecas.service.PecaService;

@RestController
@RequestMapping("/peca")
public class PecaController {

    private final PecaService pecaService;

    public PecaController (PecaService pecaService){          
        this.pecaService =  pecaService;
    }

    @GetMapping //listar todos os registros
    public List<Peca> list (){
        return pecaService.list();
    }

    @GetMapping(value = "/{id}") //Procurar registro por id
    public Peca findById(@PathVariable Integer id) {
        return pecaService.findById(id);            
    }
}
