/** Interface gráfica com o intuito de criar um grafico de barras, com o JFreeChart,
 * para melhor visualição da apuração dos votos
 */
package br.com.poo.mongo.presentation;

import br.com.poo.mongo.common.vo.CandidatosVO;
import br.com.poo.mongo.common.vo.VotoVO;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author Nirton Afonso
 *
 */
public class GraficoVotos extends JFrame {

    //Criação da nova tela que irá mostrar o grafico
    public GraficoVotos() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Gráfico de Contabilização dos Votos");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("" + new File("").getAbsoluteFile() + 
                                                "/Arquivos/images/urna/justiçaEleitoralLogoMini.png"));
        setSize(950, 700);              //Definindo o tamanho do JFrame
        setLocationRelativeTo(null);    //Toda vez que é criado, o Frame aparece  no centro da tela
        setVisible(false);              //False para que ele não inicie junto com a Urna
    }

    /**
     * Método para fazer a criação do gráfico, tendo como referência a lista de
     * candidatos ordenada por quantidade decrescente de votos recebidos por
     * candidato e a classe VotosVO onde se tem a quantidade total de votos,
     * votos brancos e nulos.
     *
     * @param listCandidatos
     * @param votos
     */
    public void imprimirGrafico(ArrayList<CandidatosVO> listCandidatos, VotoVO votos) {
        if (votos.getTotalVotos() > 0) {
            
             
            DefaultCategoryDataset barra = new DefaultCategoryDataset(); //Para se obter as barras do gráfico, precisamos criar um Dataset
            for (CandidatosVO listCandidato : listCandidatos) {         //Looping para poder percorrer toda a lista instanciada e adicionar no Dataset
                barra.setValue(listCandidato.getVotos(), listCandidato.getNome(), listCandidato.getPartido());
            }
           
            barra.setValue(votos.getVotosBrancos(), "Votos Brancos", "BRANCOS");
            barra.setValue(votos.getVotosNulos(), "Votos Nulos", "NULOS");
            JFreeChart grafico = ChartFactory.createBarChart3D("Apuração dos Votos", //Colocando os Títulos do gráfico junto com o tipo de Orientação das barras
                                                              "Candidatos", "Quantidade de Votos",
                                                               barra, PlotOrientation.VERTICAL, true, true, true);

            CategoryPlot barraItem = grafico.getCategoryPlot();
            barraItem.getRenderer().setSeriesPaint(0, Color.RED);       //Alteração de cor da primeira (Candidato Eleito)
            barraItem.getRenderer().setSeriesPaint(15, Color.WHITE);    //e das duas ultimas barras (votos Brancos e Nulos)
            barraItem.getRenderer().setSeriesPaint(16, Color.BLACK);    //para poderem se destacar das demais.

            ChartPanel painel = new ChartPanel(grafico);
            add(painel);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UrnaSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UrnaSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UrnaSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UrnaSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraficoVotos().setVisible(true);
            }
        });

    }

}
