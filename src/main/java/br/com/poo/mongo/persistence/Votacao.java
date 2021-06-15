/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.poo.mongo.persistence;

import br.com.poo.mongo.common.interfaces.IChecagemCandidato;
import br.com.poo.mongo.common.interfaces.IVotacaoCandidato;
import br.com.poo.mongo.common.vo.CandidatosVO;
import br.com.poo.mongo.common.vo.VotosVO;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nirto
 */
public class Votacao implements IChecagemCandidato, IVotacaoCandidato {

    private int votoCandidato;
    private VotosVO votos = new VotosVO();
    private CandidatosVO candidato = new CandidatosVO();

    public Votacao() {
        iniciarVotacao();

    }

    public DBCollection conexao() {
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        DB bancoUrna = client.getDB("poo");
        DBCollection bdCandidato = bancoUrna.getCollection("candidatos");
        return bdCandidato;
    }

    public void iniciarVotacao() {
        conexao().updateMulti(new BasicDBObject("votos", new BasicDBObject("$gte", 0)), new BasicDBObject("$set", new BasicDBObject("votos", 0)));
    }

    @Override
    public CandidatosVO getInfoCandidatos(int numero) {
        
        try (DBCursor cursor = conexao().find(new BasicDBObject("numero", numero))) {
            DBObject infoCandidato = cursor.one();

            if (infoCandidato == null) {
                return null;
            }
            candidato.setNome((String) infoCandidato.get("nome"));
            candidato.setNumeroCandidato(((Double) infoCandidato.get("numero")).intValue());
            return candidato;
        }

    }

    @Override
    public CandidatosVO getInfoPartido(int numero) {
        //CandidatosVO candidato = new CandidatosVO();
        try (DBCursor cursor = conexao().find(new BasicDBObject("numeroPartido", numero))) {
            DBObject infoCandidato = cursor.one();

            if (infoCandidato == null) {
                return null;
            }

            candidato.setPartido((String) infoCandidato.get("nomePartido"));
            candidato.setNumPartido(((Double) infoCandidato.get("numeroPartido")).intValue());
            return candidato;

        }

    }

    @Override
    public VotosVO votar(int numero) {
        try (DBCursor cursor = conexao().find(new BasicDBObject("numero", numero))) {
            DBObject infoCandidato = cursor.one();

            if (infoCandidato == null) {
                return null;
            }
            this.votoCandidato = Integer.parseInt(infoCandidato.get("votos").toString());
            this.votoCandidato++;

            conexao().update(new BasicDBObject("numero", numero), new BasicDBObject("$set", new BasicDBObject("votos", votoCandidato)));
            return votos;
        }
    }
}
