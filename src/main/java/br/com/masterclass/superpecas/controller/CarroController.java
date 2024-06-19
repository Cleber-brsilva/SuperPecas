package br.com.masterclass.superpecas.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;




@RestController
@RequestMapping("/carro")
public class CarroController {

    private final CarroService carroService;

    public CarroController (CarroService carroService){          
        this.carroService =  carroService;
    }

    //Testar
    @Tag(name = "get", description = "GET APIs")
    @GetMapping(value = "/")
    public String buscaCarro() {
        return "Para buscar todos os carros use /listaTodos";
    }

    
    //listar todos os registros
    @Tag(name = "get", description = "GET APIs")
    @Operation(summary = "Lista Carros",description = "A resposta é uma lista de carros")      
    @GetMapping(value = "/listaTodos")
    public List<Carro> list (){
        return carroService.list();
    }

    //listaTodosPaginado
    @Tag(name = "get", description = "GET APIs")   
    @Operation(summary = "Lista Carros com paginação ?page=1&size=10 ",description = "A resposta é uma lista de carros paginado")      
    @GetMapping(value = "/listaTodosPaginado")     
    public ResponseEntity<Page<Carro>> listaTodosPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Carro> carros = carroService.listaTodosPaginado(page, size);
        return ResponseEntity.ok(carros);
    }  
    
    //listaTodosPaginadoTermo
    @Tag(name = "get", description = "GET APIs")
    @Operation(summary = "Lista Carros com termo /termo de pesquisa ",description = "A resposta é uma lista de carros paginado")      
    @GetMapping(value = "/listaTodosPaginado/{termo}")    
    public ResponseEntity<Page<Carro>> listaTodosPaginadoPorTermo(
            @PathVariable String termo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Carro> carros = carroService.listaTodosPaginadoPorTermo(termo, page, size);
        return ResponseEntity.ok(carros);
    }


    //listar por ID
    @Tag(name = "get", description = "GET Carro APIs") 
    @Operation(summary = "Lista Carro por ID",description = "A resposta é o json de carro")        
    @GetMapping(value = "/{id}") 
    public Carro findById(@PathVariable Integer id) {
        return carroService.findById(id);            
    }


    //Lista top 10
    @Tag(name = "get", description = "GET Carro APIs") 
    @Operation(summary = "Lista Carros com top 10 fabricantes",description = "A resposta é uma lista de fabricantes")  
    @GetMapping("/top10fabricantes")
    public List<String> getTop10Fabricantes() {
        return carroService.listaTop10Fabricantes();
    }

    //listaTodosFabricantes
    @Tag(name = "get", description = "GET Carro APIs") 
    @Operation(summary = "Lista fabricantes de carros",description = "A resposta é uma lista de fabricantes")    
    @GetMapping("/listaTodosFabricantes")
    public List<String> getTodosFabricantes() {
        return carroService.listaTodosFabricantes();
    }
    
    
    //Criar Carro
    @Tag(name = "post", description = "POST APIs")
    @Operation(summary = "Criar Carro",description = "A resposta é o Json do Carro")      
    @PostMapping("/")
    public ResponseEntity<Carro> createCarro(@RequestBody Carro carro) {        
        Carro savedCarro = carroService.saveCarro(carro);
        return new ResponseEntity<>(savedCarro, HttpStatus.CREATED);
    }

    //Atualizar
    @Tag(name = "put", description = "PUT APIs")   
    @Operation(summary = "Atualizar Carro",description = "Atualiza carro se existir. A resposta é o Json do Carro atualizado com ID")
    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizaCarro(@PathVariable Integer id, @RequestBody Carro carro) {
        Carro atualizado = carroService.atualizaCarro(id, carro);
        return ResponseEntity.ok(atualizado);
    }

    //Delete
    @Tag(name = "delete", description = "DELETE APIs")   
    @Operation(summary = "Delete Carro",description = "Deletar carro se existir. A resposta tras a informação se conseguiu deletar o registro")
    @DeleteMapping("/{id}")    
    public ResponseEntity<String> deleteCarro(@PathVariable Integer id) {
        String result = carroService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }    

    


    
}
