package br.com.poo.mongo.common.vo;

/**
 * Classe responsável por guardar na memória os votos nulos,
 * brancos e fazer a contabilização de todos os votos
 * 
 * @author Nirton Afonso
 *
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
