package br.com.masterclass.superpecas.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.model.Peca;
import br.com.masterclass.superpecas.service.CarroService;
import br.com.masterclass.superpecas.service.PecaService;

@RestController
@RequestMapping("/peca")
public class PecaController {

    private final PecaService pecaService;
    private final CarroService carroService;

    public PecaController (PecaService pecaService, CarroService carroService){          
        this.pecaService =  pecaService;
        this.carroService =  carroService;
    }
    
    //ListaTodos
    @GetMapping(value = "/listaTodos") 
    public List<Peca> list (){
        return pecaService.list();
    }

    //listaTodosPaginado
    @GetMapping(value = "/listaTodosPaginado")    
    public ResponseEntity<Page<Peca>> listaTodosPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Peca> pecas = pecaService.listaTodosPaginado(page, size);
        return ResponseEntity.ok(pecas);
    } 

    //listaTodasPaginadoPorTermo
    @GetMapping(value = "/listaTodosPaginado/{termo}")
    public ResponseEntity<Page<Peca>> listaTodasPaginadoPorTermo(
            @PathVariable String termo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Peca> pecas = pecaService.listaTodasPaginadoPorTermo(termo, page, size);
        return ResponseEntity.ok(pecas);
    }
    

    //listaTop10CarrosComMaisPecas
    @GetMapping("/listaTop10CarroComMaisPecas")
    public ResponseEntity<List<String>> getTop10CarrosComMaisPecas() {
        List<String> carros = carroService.listaTop10CarrosComMaisPecas();
        return ResponseEntity.ok(carros);
    }

    //Procurar registro por id
    @GetMapping(value = "/{id}") 
    public Peca findById(@PathVariable Integer id) {
        return pecaService.findById(id);            
    }

    //Criar Peça
    @PostMapping("/")
    public ResponseEntity<Peca> createCarro(@RequestBody Peca peca) {        
        Peca createPeca = pecaService.save(peca);
        return new ResponseEntity<>(createPeca, HttpStatus.CREATED);
    }

    //Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Peca> atualizaPeca(@PathVariable Integer id, @RequestBody Peca peca) {
        Peca savedPeca = pecaService.atualizaPeca(id, peca);
        return ResponseEntity.ok(savedPeca);
    }

    //Delete
    @DeleteMapping("/{id}")    
    public ResponseEntity<String> deleteCarro(@PathVariable Integer id) {
        String result = pecaService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }  
}
