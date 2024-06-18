package br.com.masterclass.superpecas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.masterclass.superpecas.exception.ErroException;
import br.com.masterclass.superpecas.exception.RecordNotFoundException;
import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.repository.CarroRepository;
import br.com.masterclass.superpecas.repository.PecaRepository;
import jakarta.transaction.Transactional;


@Service
public class CarroService {

    private final CarroRepository carroRepository;
    private final PecaRepository pecaRepository;

    public CarroService (CarroRepository carroRepository , PecaRepository pecaRepository){  
        this.carroRepository =  carroRepository;        
        this.pecaRepository =  pecaRepository;        
    }

    //ListaTodos
    public List<Carro> list (){
        return carroRepository.findAll();
    }

    //listaTodosPaginado    
    public Page<Carro> listaTodosPaginado(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return carroRepository.findAll(pageRequest);
    }

    //listaTodosPaginadoPorTermo
    public Page<Carro> listaTodosPaginadoPorTermo(String termo, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return carroRepository.findByTermo(termo, pageable);
    }
    

    //findById
    public Carro findById(@PathVariable Integer id) {
        return carroRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id,"Carro"));
    }

    //listaTop10Fabricantes
    public List<String> listaTop10Fabricantes() {
        List<Object[]> results = carroRepository.findTop10Fabricantes();
        return results.stream()
                      .map(result -> (String) result[0])
                      .limit(10)
                      .collect(Collectors.toList());
    }

    //listaTodosFabricantes
    public List<String> listaTodosFabricantes() {
        List<Object[]> results = carroRepository.findTop10Fabricantes();
        return results.stream()
                      .map(result -> (String) result[0])                      
                      .collect(Collectors.toList());
    }

    public List<String> listaTop10CarrosComMaisPecas() {
        return carroRepository.findTop10CarrosComMaisPecas();
    }


    
    //Salvar
    @Transactional
    public Carro saveCarro(Carro carro) {
        if (carro.getCodigoUnico() == null || carro.getCodigoUnico().isEmpty()) {
            throw new ErroException("O campo codigoUnico é obrigatório ");
        }

        //Verifica se o Codigo Unico já existe no banco de dados
        if (carroRepository.existsByCodigoUnico(carro.getCodigoUnico())) {
            throw new ErroException("Codigo unico " + carro.getCodigoUnico() + " já esta cadastrado");
        }          
        
        return carroRepository.save(carro);
    }

    @Transactional
    public Carro atualizaCarro(Integer id, Carro carro) {
        // Verifica se o carro com o ID especificado existe
        Carro existingCarro = carroRepository.findById(id)
            .orElseThrow(() -> new ErroException("Carro com ID " + id + " não encontrado"));

        // Verifica se o campo codigoUnico não está vazio
        if (carro.getCodigoUnico() == null || carro.getCodigoUnico().isEmpty()) {
            throw new ErroException("O campo codigoUnico é obrigatório");
        }

        // Verifica se o codigoUnico já existe para outro carro
        Carro carroComMesmoCodigo = carroRepository.findByCodigoUnico(carro.getCodigoUnico());
        if (carroComMesmoCodigo != null && !carroComMesmoCodigo.getCarroID().equals(id)) {
            throw new ErroException("Codigo unico " + carro.getCodigoUnico() + " já está cadastrado");
        }

        // Atualiza os campos do carro existente com os dados do carro recebido
        existingCarro.setNomeModelo(carro.getNomeModelo());
        existingCarro.setFabricante(carro.getFabricante());
        existingCarro.setCodigoUnico(carro.getCodigoUnico());

        // Salva o carro atualizado
        return carroRepository.save(existingCarro);
    }    
    

    //Delete
    @Transactional
    public String delete(Integer carroId) {
        // Verificar se o carro existe
        if (!carroRepository.existsById(carroId)) {
            throw new ErroException("ID não existe");
        }
         
        // Verificar se o carro possui peças relacionadas
        if (pecaRepository.existsByCarro_CarroID(carroId)) {
            throw new ErroException("Não pode excluir carro com peças relacionadas");
        }  

        // Excluir o carro
        carroRepository.deleteById(carroId);

        return "Excluído com sucesso";
    }
}
