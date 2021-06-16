/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.poo.mongo.common.vo;

/**
 *
 * @author nirto
 */
public class VotoVO {
    private int votosBrancos;
    private int votosNulos;
    private int totalVotos;

    public int getVotosBrancos() {
        return votosBrancos;
    }

    public void votarBranco() {
        this.votosBrancos++;
    }

    public int getVotosNulos() {
        return votosNulos;
    }

    public void votarNulo() {
        this.votosNulos++;
    }

    public int getTotalVotos() {
        return totalVotos;
    }

    public void somaTotalVotos() {
        this.totalVotos++;
    }
    
    
    
}
