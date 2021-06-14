/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.poo.mongo.common.interfaces;

import br.com.poo.mongo.common.vo.CandidatosVO;

/**
 *
 * @author nirto
 */
public interface IChecagemCandidato {
    
    public CandidatosVO getInfoCandidatos(int numero);
    
    public CandidatosVO getInfoPartido(int numero);
    
   
    
}
