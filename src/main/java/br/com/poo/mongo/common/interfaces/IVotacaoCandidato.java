package br.com.poo.mongo.common.interfaces;

import br.com.poo.mongo.common.vo.VotoVO;

/**
 *
 * @author Nirton Afonso
 */
public interface IVotacaoCandidato {
    
    public VotoVO votar(int numero);
    
    
}
