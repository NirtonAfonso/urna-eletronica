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
public class VotosVO {
    private int votosBrancos;
    private int votosNumos;
    private int totalVotos;

    public int getVotosBrancos() {
        return votosBrancos;
    }

    public void setVotosBrancos(int votosBrancos) {
        this.votosBrancos = votosBrancos;
    }

    public int getVotosNumos() {
        return votosNumos;
    }

    public void setVotosNumos(int votosNumos) {
        this.votosNumos = votosNumos;
    }

    public int getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(int totalVotos) {
        this.totalVotos = totalVotos;
    }
    
    
    
}