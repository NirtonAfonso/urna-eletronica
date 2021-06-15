/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.poo.mongo.service;

import br.com.poo.mongo.common.exception.CandidatoInexistenteExcepition;
import br.com.poo.mongo.common.exception.NumeroErradoException;
import br.com.poo.mongo.common.exception.VotarCandidatoInexistenteException;
import br.com.poo.mongo.common.vo.CandidatosVO;
import br.com.poo.mongo.common.vo.VotosVO;
import br.com.poo.mongo.persistence.Votacao;

/**
 *
 * @author nirto
 */
public class ServiceCandidato {

    Votacao votacao = new Votacao();
    private VotosVO votos = new VotosVO();
    

    public CandidatosVO getInfoCandidatos(int numero) throws CandidatoInexistenteExcepition {

        if (votacao.getInfoCandidatos(numero) == null) {
            throw new CandidatoInexistenteExcepition("CANDIDATO INEXISTENTE");
        }
        
        return votacao.getInfoCandidatos(numero);
    }

    public CandidatosVO getInfoPartido(int numero) throws NumeroErradoException {

        if (votacao.getInfoPartido(numero) == null) {
            throw new NumeroErradoException("NÚMERO ERRADO");
        }

        return votacao.getInfoPartido(numero);
    }

    public VotosVO votar(int numero) throws VotarCandidatoInexistenteException {
        if (votacao.votar(numero)==null) {
            throw new VotarCandidatoInexistenteException();
        }
        return votos;
    }

    /*public VotosVO votarNulo() {

        votos.setVotosNulos(votos.getVotosNulos() + 1);
        votos.setTotalVotos(votos.getTotalVotos() + 1);

        return votos;

    }

    public VotosVO votarBranco() {

        votos.setVotosBrancos(votos.getVotosBrancos() + 1);
        votos.setTotalVotos(votos.getTotalVotos() + 1);

        return votos;

    }*/

}
