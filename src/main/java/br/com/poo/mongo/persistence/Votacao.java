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
    private List<CandidatosVO> listCandidato = new ArrayList<>();

    public Votacao() {
        iniciarVotacao();
        createCandidatoList();
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

    public void createCandidatoList() {
        try (DBCursor cursor = conexao().find()) {

            for (int i = 0; i < cursor.length(); i++) {
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

                return candidato;
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

            for (CandidatosVO candidato : listCandidato) {
                if (candidato.getNumPartido() == numero) {
                    return candidato;
                }
                candidato.setPartido((String) infoCandidato.get("nomePartido"));
                candidato.setNumPartido(((Double) infoCandidato.get("numeroPartido")).intValue());
                return candidato;
            }

            return null;
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
            for (CandidatosVO candidato : listCandidato) {
                if (candidato.getNumeroCandidato() == numero) {
                    candidato.setVotos(votoCandidato);
                    System.out.println("Votos " + candidato.getVotos());
                    break;
                }
            }
            votos.setTotalVotos(votos.getTotalVotos() + 1);
            conexao().update(new BasicDBObject("numero", numero), new BasicDBObject("$set", new BasicDBObject("votos", votoCandidato)));
        }
        return votos;

    }

    @Override
    public VotosVO votarNulo() {

        votos.setVotosNulos(votos.getVotosNulos()+1);
        votos.setTotalVotos(votos.getTotalVotos() + 1);

        return votos;

    }

    @Override
    public VotosVO votarBranco() {

        votos.setVotosBrancos(votos.getVotosBrancos() + 1);
        votos.setTotalVotos(votos.getTotalVotos() + 1);

        return votos;

    }

}
