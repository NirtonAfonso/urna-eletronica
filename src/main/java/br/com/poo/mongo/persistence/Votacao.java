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
    VotosVO votos = new VotosVO();
    List<CandidatosVO> listCandidato = new ArrayList<>();

    public Votacao() {
        iniciarVotacao();
        createCandidatoList();
    }

    public DBCollection conexao() {
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        DB bancoUrna = client.getDB("poo");
        DBCollection candidato = bancoUrna.getCollection("candidatos");
        return candidato;
    }

    public void iniciarVotacao() {
        conexao().updateMulti(new BasicDBObject("votos", new BasicDBObject("$gte", 0)), new BasicDBObject("$set", new BasicDBObject("votos", 0)));
    }

    public void createCandidatoList() {
        try (DBCursor cursor = conexao().find()) {

            while (cursor.hasNext()) {
                listCandidato.add(new CandidatosVO());
            }
            
        }
        
    }

    @Override
    public CandidatosVO getInfoCandidatos(int numero) {

        try (DBCursor cursor = conexao().find(new BasicDBObject("numero", numero))) {
            DBObject infoCandidato = cursor.one();

            if (infoCandidato == null) {
                return null;
            }
            for (CandidatosVO candidato : listCandidato) {
                if (candidato.getNumeroCandidato() == numero) {
                    return candidato;
                }
                candidato.setNome((String) infoCandidato.get("nome"));
                candidato.setNumeroCandidato(((Double) infoCandidato.get("numero")).intValue());
            }

            return null;
        }

    }

    @Override
    public CandidatosVO getInfoPartido(int numero) {

        try (DBCursor cursor = conexao().find(new BasicDBObject("numeroPartido", numero))) {
            DBObject infoCandidato = cursor.one();

            if (infoCandidato == null) {
                return null;
            }
            CandidatosVO vo = new CandidatosVO();

            vo.setPartido((String) infoCandidato.get("nomePartido"));
            vo.setNumPartido(((Double) infoCandidato.get("numeroPartido")).intValue());
            return vo;
        }

    }

    @Override
    public int votar(int numero) {
        try (DBCursor cursor = conexao().find(new BasicDBObject("numero", numero))) {
            DBObject infoCandidato = cursor.one();

            this.votoCandidato = Integer.parseInt(infoCandidato.get("votos").toString());
            this.votoCandidato++;

            conexao().update(new BasicDBObject("numero", numero), new BasicDBObject("$set", new BasicDBObject("votos", votoCandidato)));

            return votoCandidato;
        }

    }

}
