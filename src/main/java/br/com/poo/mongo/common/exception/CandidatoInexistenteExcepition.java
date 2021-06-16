/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.poo.mongo.common.exception;

/**
 *
 * @author nirto
 */
public class CandidatoInexistenteExcepition extends RuntimeException{

    public CandidatoInexistenteExcepition(String message) {
        super(message);
    }
    
}
