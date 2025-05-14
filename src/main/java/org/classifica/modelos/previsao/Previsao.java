package org.classifica.modelos.previsao;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Classe utilitária para realizar previsões de instâncias usando um modelo de classificação.
 * Utiliza a biblioteca Weka para classificar instâncias com base em um modelo treinado.
 */
public class Previsao {

    /**
     * Classifica uma nova instância usando um modelo treinado.
     * 
     * @param modelo   Modelo de classificação treinado (exemplo: J48 ou outro da biblioteca Weka).
     * @param instancia Instância a ser classificada.
     * @param dados     Conjunto de dados usados para o modelo (necessário para obter valores das classes).
     * @return Uma {@link String} representando a classe prevista para a instância fornecida.
     * @throws Exception Caso ocorra algum erro durante o processo de classificação.
     */
    public static String classificaInstancia(Classifier modelo, Instance instancia, Instances dados) throws Exception {
        // Exibe mensagem indicando o início do processo de classificação
        System.out.println("Classificando a nova instância...");

        // Define o índice do atributo alvo se necessário
        if (dados.classIndex() == -1) {
            dados.setClassIndex(dados.numAttributes() - 1); // Define a última coluna como atributo alvo
        }

        // Realiza a classificação e retorna a classe prevista como um índice
        double indiceClasse = modelo.classifyInstance(instancia);

        // Mapeia o índice da classe de volta para o valor nominal associado
        return dados.classAttribute().value((int) indiceClasse);
    }
}