package br.com.masterclass.superpecas.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.masterclass.superpecas.exception.ErroException;
import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.service.CarroService;




@RestController
@RequestMapping("/carro")
public class CarroController {

    private final CarroService carroService;

    public CarroController (CarroService carroService){          
        this.carroService =  carroService;
    }

    //Testar
    @GetMapping(value = "/")
    public String buscaCarro() {
        return "Para buscar todos os carros use /listaTodos";
    }

    
    //listar todos os registros
    @GetMapping(value = "/listaTodos")
    public List<Carro> list (){
        return carroService.list();
    }

    //listaTodosPaginado
    @GetMapping(value = "/listaTodosPaginado")    
    public ResponseEntity<Page<Carro>> listaTodosPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Carro> carros = carroService.listaTodosPaginado(page, size);
        return ResponseEntity.ok(carros);
    }  
    
    //listaTodosPaginadoTermo
    @GetMapping(value = "/listaTodosPaginado/{termo}")
    public ResponseEntity<Page<Carro>> listaTodosPaginadoPorTermo(
            @PathVariable String termo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Carro> carros = carroService.listaTodosPaginadoPorTermo(termo, page, size);
        return ResponseEntity.ok(carros);
    }


    //listar por ID
    @GetMapping(value = "/{id}") 
    public Carro findById(@PathVariable Integer id) {
        return carroService.findById(id);            
    }


    //Lista top 10
    @GetMapping("/top10fabricantes")
    public List<String> getTop10Fabricantes() {
        return carroService.listaTop10Fabricantes();
    }

    //listaTodosFabricantes
    @GetMapping("/listaTodosFabricantes")
    public List<String> getTodosFabricantes() {
        return carroService.listaTodosFabricantes();
    }
    
    
    //Criar Carro
    @PostMapping("/")
    public ResponseEntity<Carro> createCarro(@RequestBody Carro carro) {        
        Carro savedCarro = carroService.saveCarro(carro);
        return new ResponseEntity<>(savedCarro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizaCarro(@PathVariable Integer id, @RequestBody Carro carro) {
        Carro atualizado = carroService.atualizaCarro(id, carro);
        return ResponseEntity.ok(atualizado);
    }

    //Delete
    @DeleteMapping("/{id}")    
    public ResponseEntity<String> deleteCarro(@PathVariable Integer id) {
        String result = carroService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }    

    


    
}
