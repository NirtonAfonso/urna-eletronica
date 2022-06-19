package br.com.poo.mongo.common.interfaces;

import br.com.poo.mongo.common.vo.CandidatosVO;

/**
 * 
 * @author Nirton Afonso
 */
public interface IChecagemCandidato {
    
    public CandidatosVO getInfoCandidatos(int numero);
    
    public CandidatosVO getInfoPartido(int numero);
    
   
    
}
