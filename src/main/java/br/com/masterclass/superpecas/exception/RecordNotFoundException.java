package br.com.masterclass.superpecas.exception;

public class RecordNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(Integer id, String entidade) {
        super("Registro " + entidade + " não encontrado com o id: " + id);
    }
}