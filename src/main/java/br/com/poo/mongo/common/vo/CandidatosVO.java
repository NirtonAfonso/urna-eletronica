/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.poo.mongo.common.vo;

import br.com.poo.mongo.common.interfaces.IVotacaoCandidato;

/**
 *
 * @author nirto
 */
public class CandidatosVO extends PartidoVO {

    private String nome;
    private int numeroCandidato;
    private int votos;

    public CandidatosVO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroCandidato() {
        return numeroCandidato;
    }

    public void setNumeroCandidato(int numeroCandidato) {
        this.numeroCandidato = numeroCandidato;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public int getNumPartido() {
        return numPartido;
    }

    public void setNumPartido(int numPartido) {
        this.numPartido = numPartido;
    }

    public int getVotos() {
        return votos;
    }

    public void receberVoto() {
        this.votos++;
    }

    
}
