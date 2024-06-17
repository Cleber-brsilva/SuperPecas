package br.com.masterclass.superpecas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping(value = "/")
    public String buscaCarro() {
        return "Para buscar todos os carros use /listaTodos";
    }

    @GetMapping(value = "/listaTodos")
    public List<Carro> list (){
        return carroService.list();
    }

    @GetMapping(value = "/{id}") //Procurar registro por id
    public Carro findById(@PathVariable Integer id) {
        return carroService.findById(id);            
    }

    @GetMapping("/top10fabricantes")
    public List<String> getTop10Fabricantes() {
        return carroService.listaTop10Fabricantes();
    }

}
