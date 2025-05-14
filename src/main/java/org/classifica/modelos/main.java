package org.classifica.modelos;

import org.classifica.modelos.carregarDados.carregarDados;
import org.classifica.modelos.previsao.Previsao;
import org.classifica.modelos.treinarModelo.TreinarModelo;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import java.util.Scanner;

/**
 * Classe principal que executa o fluxo de classificação de bolas.
 * Este programa permite que o usuário insira informações de uma bola (tamanho e cor)
 * e utiliza um modelo treinado para classificar o tipo da bola.
 */
public class main {
    /**
     * Método principal do programa.
     * Ele executa os seguintes passos:
     * - Carrega um conjunto de dados de um arquivo ARFF
     * - Treina o modelo de árvore de decisão J48 com os dados carregados
     * - Permite que o usuário insira os atributos de uma nova bola (tamanho e cor) em um loop
     * - Classifica a nova bola com base no modelo treinado
     * 
     * @param args Argumentos passados ao programa (não utilizados aqui)
     */
    public static void main(String[] args) {
        try {
            // Objeto para leitura de entradas do usuário
            Scanner scanner = new Scanner(System.in);

            // Carregar Base de Dados
            System.out.println("Carregando a base de dados...");
            Instances data = carregarDados.carregaDados("src/main/resources/bolas_gigante.arff");

            // Treinar modelo
            System.out.println("Treinando o modelo...");
            J48 model = TreinarModelo.treinarModelo(data);

            // Loop para entrada de dados do usuário e previsão do tipo de bola
            while (true) {
                System.out.println("\nDigite o tamanho da bola (ou -1 para sair):");
                int tamanho = scanner.nextInt();
                if (tamanho == -1) break; // Sai do loop se o usuário digitar -1

                // Valida o índice da cor fornecido pelo usuário
                int corIndex;
                do {
                    System.out.println("Escolha a cor (0: branca, 1: laranja, 2: amarela):");
                    corIndex = scanner.nextInt();

                    // Verifica se o índice é válido
                    if (corIndex < 0 || corIndex >= data.attribute("cor").numValues()) {
                        System.out.println("Entrada inválida! Escolha um número entre 0 e " + (data.attribute("cor").numValues() - 1));
                    }
                } while (corIndex < 0 || corIndex >= data.attribute("cor").numValues());

                // Mapeia o índice para o valor correspondente no atributo 'cor'
                String cor = data.attribute("cor").value(corIndex);

                // Criação de uma nova instância da bola a ser classificada
                Instance novaBola = (Instance) data.instance(0).copy();
                novaBola.setValue(data.attribute("tamanho"), tamanho);
                novaBola.setValue(data.attribute("cor"), cor);

                // Classifica a nova instância
                String resultado = Previsao.classificaInstancia(model, novaBola, data);
                System.out.println("Tipo de bola prevista: " + resultado);
            }

            // Fecha o Scanner após o fim do processo
            scanner.close();
        } catch (Exception e) {
            // Captura e exibe eventuais erros durante a execução
            e.printStackTrace();
        }
    }
}