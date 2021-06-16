/** Classe criada para receber os candidatos do Banco de dados e armazenar na memoria
 * através de um ArrayList para pooder fazer a manipulação dos votos
 *
 */
package br.com.poo.mongo.common.vo;

/**
 * @author Nirton Afonso
 *
 */
public class CandidatosVO extends PartidoVO {

    private String nome;
    private int numeroCandidato;
    private int votos;

    public CandidatosVO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroCandidato() {
        return numeroCandidato;
    }

    public void setNumeroCandidato(int numeroCandidato) {
        this.numeroCandidato = numeroCandidato;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public int getNumPartido() {
        return numPartido;
    }

    public void setNumPartido(int numPartido) {
        this.numPartido = numPartido;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    @Override
    public String toString() {
        StringBuilder imprimirCandidatos = new StringBuilder();

        imprimirCandidatos.append("Candidato: ");
        imprimirCandidatos.append(getNome());
        imprimirCandidatos.append("\n");
        imprimirCandidatos.append("Votos: ");
        imprimirCandidatos.append(getVotos());
        imprimirCandidatos.append("");

        return imprimirCandidatos.toString();
    }

}
