package br.com.masterclass.superpecas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.service.CarroService;

@RestController
@RequestMapping("/carro")
public class CarroController {

    private final CarroService carroService;

    public CarroController (CarroService carroService){          
        this.carroService =  carroService;
    }

    @GetMapping //listar todos os registros
    public List<Carro> list (){
        return carroService.list();
    }

    @GetMapping(value = "/{id}") //Procurar registro por id
    public Carro findById(@PathVariable Integer id) {
        return carroService.findById(id);            
    }

}
