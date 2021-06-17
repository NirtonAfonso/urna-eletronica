/**
 * Classe principal onde temos a comunicação realizada entre
 * o java e o Banco de Dados (MongoDB).
 */
package br.com.poo.mongo.persistence;

import br.com.poo.mongo.common.interfaces.IChecagemCandidato;
import br.com.poo.mongo.common.interfaces.IVotacaoCandidato;
import br.com.poo.mongo.common.vo.CandidatosVO;
import br.com.poo.mongo.common.vo.VotoVO;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * @author Nirton Afonso
 *
 */
public class Votacao implements IChecagemCandidato, IVotacaoCandidato {

    private int votoCandidato;

    public Votacao() {
        iniciarVotacao();

    }

    /**
     * Método responsável para iniciar a conexão do MongoDB retornando a
     * Collection onde está armazenada os candidatos
     *
     * @return Collection do banco
     */
    public DBCollection conexao() {
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        DB bancoUrna = client.getDB("poo");
        DBCollection bdCandidato = bancoUrna.getCollection("candidatos");
        return bdCandidato;
    }

    /**
     * Toda vez que se inicia uma nova coneção com o Banco, no nosso caso toda
     * vez que execultarmos o programa, ele irá atualizar todos os votos para 0,
     * onde assim podemos realizar uma votação do inicio sem algum candidato
     * sair na vantagem.
     */
    public void iniciarVotacao() {
        conexao().updateMulti(new BasicDBObject("votos", new BasicDBObject("$gte", 0)), new BasicDBObject("$set", new BasicDBObject("votos", 0)));
    }

    /**
     * Método para fazer a busca especifica dentro do banco de dados, tendo como
     * parâmentro o numero do Candidato e retornando as informações encontradas
     * do mesmo.
     *
     * @param numero
     * @return
     */
    @Override
    public CandidatosVO getInfoCandidatos(int numero) {

        /**
         * Try usado dentro da pesquisa do candidato para realizar o fechamento
         * da conexão do banco de dados.
         */
        try (DBCursor cursor = conexao().find(new BasicDBObject("numero", numero))) {
            DBObject infoCandidato = cursor.one();
            CandidatosVO candidato = new CandidatosVO();

            if (infoCandidato == null) {
                return null;
            }
            candidato.setNome((String) infoCandidato.get("nome"));
            candidato.setNumeroCandidato(((Double) infoCandidato.get("numero")).intValue());
            candidato.setVotos((int) (infoCandidato.get("votos")));
            candidato.setPartido((String) infoCandidato.get("nomePartido"));
            candidato.setNumPartido(((Double) infoCandidato.get("numeroPartido")).intValue());
            return candidato;
        }

    }

    /**
     * Método para fazer a busca especifica dentro do banco de dados, tendo como
     * parâmentro o numero do Partido e retornando as informações encontradas do
     * mesmo.
     *
     * @param numero
     * @return
     */
    @Override
    public CandidatosVO getInfoPartido(int numero) {
        /**
         * Try utilizado também para realizar o fechamento da conexão do banco.
         */
        try (DBCursor cursor = conexao().find(new BasicDBObject("numeroPartido", numero))) {
            DBObject infoCandidato = cursor.one();
            CandidatosVO candidato = new CandidatosVO();

            if (infoCandidato == null) {
                return null;
            }

            candidato.setPartido((String) infoCandidato.get("nomePartido"));
            candidato.setNumPartido(((Double) infoCandidato.get("numeroPartido")).intValue());
            return candidato;

        }

    }

    /**
     * Método para fazer a contabilização de voto do candidato em expecifico,
     * tendo como parâmentro o numero do Candidato e atualizando sua quantidade
     * de votos dentro do Banco.
     *
     * @param numero
     * @return
     */
    @Override
    public VotoVO votar(int numero) {
        /**
         * Try utilizado também para realizar o fechamento da conexão do banco.
         */
        try (DBCursor cursor = conexao().find(new BasicDBObject("numero", numero))) {
            DBObject infoCandidato = cursor.one();
            CandidatosVO candidato = new CandidatosVO();
            VotoVO votos = new VotoVO();

            if (infoCandidato == null) {
                return null;
            }
            this.votoCandidato = Integer.parseInt(infoCandidato.get("votos").toString());
            this.votoCandidato++;

            candidato.setVotos((int) (infoCandidato.get("votos")));
            conexao().update(new BasicDBObject("numero", numero), new BasicDBObject("$set", new BasicDBObject("votos", votoCandidato)));
            return votos;
        }
    }
}
