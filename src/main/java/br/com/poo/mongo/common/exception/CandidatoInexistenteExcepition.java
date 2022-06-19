package br.com.poo.mongo.common.exception;

/**
 * 
 * Exceção criada com o intuito de verificar a existência 
 * de um candidato
 * 
 * @author Nirton Afonso
 */
public class CandidatoInexistenteExcepition extends RuntimeException{

    public CandidatoInexistenteExcepition(String menssage) {
        super(menssage);
    }
    
}
