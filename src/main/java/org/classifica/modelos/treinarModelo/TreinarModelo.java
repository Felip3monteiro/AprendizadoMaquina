package org.classifica.modelos.treinarModelo;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;

/**
 * Classe utilitária para treinar modelos de classificação utilizando dados fornecidos.
 * Utiliza a biblioteca Weka para configurar e treinar um modelo, retornando-o pronto
 * para uso em classificações.
 */
public class TreinarModelo {

    /**
     * Treina um modelo de classificação do tipo árvore de decisão J48.
     * 
     * @param dados Conjunto de dados no formato {@link Instances} utilizado para treinar o modelo.
     *              Deve conter atributos relevantes e um atributo alvo (classe).
     * @return Um modelo de classificação do tipo {@link J48}, já treinado.
     * @throws Exception Caso ocorra algum erro durante o treinamento do modelo, como ausência do índice 
     *                   da classe alvo ou inconsistência nos dados fornecidos.
     */
    public static J48 treinarModelo(Instances dados) throws Exception {
        // Verifica se o índice da classe (atributo alvo) está configurado
        if (dados.classIndex() == -1) {
            // Define o último atributo como atributo alvo (classe)
            dados.setClassIndex(dados.numAttributes() - 1);
            System.out.println("Índice da classe definido como: " + dados.classAttribute().name());
        }

        // Cria uma nova instância do modelo de classificação J48 (árvore de decisão)
        J48 modelo = new J48();

        // Configura parâmetros do modelo, se necessário (opcional)
        modelo.setOptions(new String[]{"-U"}); // Exemplo: define a opção "-U" para árvore não podada

        // Treina o modelo com base no conjunto de dados fornecido
        System.out.println("Treinando o modelo com os dados fornecidos...");
        modelo.buildClassifier(dados);

        // Retorna o modelo treinado
        System.out.println("Modelo treinado com sucesso!");
        return modelo;
    }
}