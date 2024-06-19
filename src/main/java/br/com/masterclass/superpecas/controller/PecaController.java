package br.com.masterclass.superpecas.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.masterclass.superpecas.model.Peca;
import br.com.masterclass.superpecas.service.CarroService;
import br.com.masterclass.superpecas.service.PecaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/peca")
public class PecaController {

    private final PecaService pecaService;
    private final CarroService carroService;

    public PecaController (PecaService pecaService, CarroService carroService){          
        this.pecaService =  pecaService;
        this.carroService =  carroService;
    }
    
    
    @Tag(name = "get", description = "GET APIs")   
    @Operation(summary = "Lista Peças",description = "A resposta é uma lista de peças")      
    @GetMapping(value = "/listaTodos") 
    public List<Peca> list (){
        return pecaService.list();
    }

    
    @Tag(name = "get", description = "GET APIs")   
    @Operation(summary = "Lista Peças com paginação ?page=1&size=10 ",description = "A resposta é uma lista de peças paginado")      
    @GetMapping(value = "/listaTodosPaginado")    
    public ResponseEntity<Page<Peca>> listaTodosPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Peca> pecas = pecaService.listaTodosPaginado(page, size);
        return ResponseEntity.ok(pecas);
    } 
    
    @Tag(name = "get", description = "GET APIs") 
    @Operation(summary = "Lista Peças com termo /termo de pesquisa ",description = "A resposta é uma lista de peças")        
    @GetMapping(value = "/listaTodosPaginado/{termo}")
    public ResponseEntity<Page<Peca>> listaTodasPaginadoPorTermo(
            @PathVariable String termo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Peca> pecas = pecaService.listaTodasPaginadoPorTermo(termo, page, size);
        return ResponseEntity.ok(pecas);
    }
    

       
    @Tag(name = "get", description = "GET APIs")   
    @GetMapping("/listaTop10CarroComMaisPecas")
    @Operation(summary = "Lista Carros com mais peças ",description = "A resposta é uma lista de carros com mais peças")      
    public ResponseEntity<List<String>> getTop10CarrosComMaisPecas() {
        List<String> carros = carroService.listaTop10CarrosComMaisPecas();
        return ResponseEntity.ok(carros);
    }

    //Procurar registro por id
    @Tag(name = "get", description = "GET APIs")   
    @Operation(summary = "Lista Peça por ID",description = "A resposta é o json de peça")      
    @GetMapping(value = "/{id}") 
    public Peca findById(@PathVariable Integer id) {
        return pecaService.findById(id);            
    }

    //Criar Peça
    @Tag(name = "post", description = "POST APIs")   
    @Operation(summary = "Criar Peça",description = "A resposta é o Json do Peça")      
    @PostMapping("/")
    public ResponseEntity<Peca> createCarro(@RequestBody Peca peca) {        
        Peca createPeca = pecaService.save(peca);
        return new ResponseEntity<>(createPeca, HttpStatus.CREATED);
    }

    //Atualizar
    @Tag(name = "put", description = "PUT APIs") 
    @Operation(summary = "Atualizar Peça",description = "Atualiza carro se existir. A resposta é o Json do Peça atualizado com ID")  
    @PutMapping("/{id}")    
    public ResponseEntity<Peca> atualizaPeca(@PathVariable Integer id, @RequestBody Peca peca) {
        Peca savedPeca = pecaService.atualizaPeca(id, peca);
        return ResponseEntity.ok(savedPeca);
    }

    //Delete
    @Tag(name = "delete", description = "DELETE APIs")   
    @DeleteMapping("/{id}")    
    public ResponseEntity<String> deleteCarro(@PathVariable Integer id) {
        String result = pecaService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }  
}
