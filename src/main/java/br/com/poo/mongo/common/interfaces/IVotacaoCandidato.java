/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.poo.mongo.common.interfaces;

import br.com.poo.mongo.common.vo.CandidatosVO;
import br.com.poo.mongo.common.vo.VotoVO;

/**
 *
 * @author nirto
 */
public interface IVotacaoCandidato {
    
    public VotoVO votar(int numero);
    
    
}
