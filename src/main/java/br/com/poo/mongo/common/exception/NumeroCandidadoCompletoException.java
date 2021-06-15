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
public class NumeroCandidadoCompletoException extends RuntimeException {

    public NumeroCandidadoCompletoException() {
        super("Teclas numéricas indisponiveis no momento\n"
                    + "O numero do candidato já está completo.");
    }
    
    
}
