package br.com.masterclass.superpecas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.masterclass.superpecas.exception.ErroException;
import br.com.masterclass.superpecas.exception.RecordNotFoundException;
import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.model.Peca;
import br.com.masterclass.superpecas.repository.PecaRepository;
import jakarta.persistence.Column;
import jakarta.transaction.Transactional;

@Service
public class PecaService {

    private final PecaRepository pecaRepository;

    public PecaService (PecaRepository pecaRepository){  
        this.pecaRepository =  pecaRepository;        
    }

    //Listar
    public List<Peca> list (){
        return pecaRepository.findAll();
    }

    //listaTodosPaginado    
    public Page<Peca> listaTodosPaginado(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return pecaRepository.findAll(pageRequest);
    }

    //listaTodasPaginadoPorTermo
    public Page<Peca> listaTodasPaginadoPorTermo(String termo, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pecaRepository.findByTermo(termo, pageable);
    }

    //Get byId
    public Peca findById(@PathVariable Integer id) {
        return pecaRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id,"Peça"));
    }

    //Salvar
    @Transactional
    public Peca save(Peca peca) {       

        /*//Verifica se o Codigo Unico já existe no banco de dados
        if (pecaRepository.existsByCodigoUnico(carro.getCodigoUnico())) {
            throw new ErroException("Codigo unico " + carro.getCodigoUnico() + " já esta cadastrado");
        } 
        */         
        
        return pecaRepository.save(peca);
    }

    //Delete
    @Transactional
    public String delete(Integer pecaId) {
        // Verificar se o carro existe
        if (!pecaRepository.existsById(pecaId)) {
            throw new ErroException("ID não existe");
        }
         
        /*// Verificar se o carro possui peças relacionadas
        if (pecaRepository.existsByCarro_CarroID(pecaId)) {
            throw new ErroException("Não pode excluir carro com peças relacionadas");
        } 
        */ 

        // Excluir o carro
        pecaRepository.deleteById(pecaId);
        return "Excluído com sucesso";
    }

    //atualizaCarro
    @Transactional
    public Peca atualizaPeca(Integer id, Peca peca) {
        // Verifica se o carro com o ID especificado existe
        Peca existingPeca = pecaRepository.findById(id)
            .orElseThrow(() -> new ErroException("Peça com ID " + id + " não encontrado"));

        // Verifica se o NumeroSerie já existe para outra Peça
        Peca carroComMesmoSerie = pecaRepository.findByNumeroSerie(peca.getNumeroSerie());
        if (carroComMesmoSerie != null && !carroComMesmoSerie.getPecaID().equals(id)) {
            throw new ErroException("Numero serie unico " + peca.getNumeroSerie() + " já está cadastrado");
        }

        // Atualiza os campos do carro existente com os dados do carro recebido
        existingPeca.setNumeroSerie(peca.getNumeroSerie());
        existingPeca.setNome(peca.getNome());
        existingPeca.setDescricao(peca.getDescricao());
        existingPeca.setFabricante(peca.getFabricante());
        existingPeca.setModeloCarro(peca.getModeloCarro());

        // Salva o peça atualizado
        return pecaRepository.save(existingPeca);
    }    


    
}