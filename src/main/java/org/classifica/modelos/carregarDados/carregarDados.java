package org.classifica.modelos.carregarDados;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Classe utilitária para carregar conjuntos de dados no formato ARFF.
 * Fornece métodos para ler e retornar os dados que serão utilizados no treinamento do modelo.
 */
public class carregarDados {

    /**
     * Carrega o conjunto de dados a partir do arquivo fornecido.
     * 
     * @param caminhoArquivo Caminho para o arquivo ARFF contendo o conjunto de dados.
     * @return Instância do objeto {@link Instances} representando os dados carregados.
     * @throws Exception Caso ocorra algum erro durante a leitura do arquivo, como arquivo inexistente
     *                   ou formato inválido.
     */
    public static Instances carregaDados(String caminhoArquivo) throws Exception {
        // Exibe mensagem indicando o início do carregamento
        System.out.println("Carregando dados do arquivo: " + caminhoArquivo);
        
        // Usa a classe DataSource do Weka para abrir o arquivo
        DataSource fonte = new DataSource(caminhoArquivo);
        
        // Retorna o conjunto de dados como objeto Instances
        return fonte.getDataSet();
    }
}