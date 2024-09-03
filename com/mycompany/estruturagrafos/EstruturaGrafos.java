/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * projeto-grafos/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/seuprojeto/grafos/
│   │   │       ├── Grafo.java
│   │   │       ├── Aresta.java
│   │   │       ├── BFS.java
│   │   │       ├── DFS.java
│   │   │       ├── Dijkstra.java
│   │   │       ├── Prim.java
│   │   │       ├── MatrizAdjacencia.java
│   │   │       ├── Main.java
│   │   │       └── UI.java
│   │   └── resources/
│   └── test/
│       ├── java/
│       │   └── com/seuprojeto/grafos/
│       │       ├── GrafoTest.java
│       │       ├── BFSTest.java
│       │       ├── DFSTest.java
│       │       ├── DijkstraTest.java
│       │       └── PrimTest.java
└── pom.xml

 */
package com.mycompany.estruturagrafos;

import java.util.List;
import java.util.Scanner;
import org.jfree.ui.RefineryUtilities;

public class EstruturaGrafos {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        Scanner scanner = new Scanner(System.in);
        BFS bfs = new BFS();
        GraphPlot plotter = new GraphPlot("Visualização do Grafo");

        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Inserir Vértice");
            System.out.println("2. Inserir Aresta");
            System.out.println("3. Remover Vértice");
            System.out.println("4. Remover Aresta");
            System.out.println("5. Visualizar Grafo");
            System.out.println("6. Informar Grau do Vértice");
            System.out.println("7. Verificar se o Grafo é Conexo");
            System.out.println("8. Converter para Matriz de Adjacência");
            System.out.println("9. Executar busca em largura (BFS)");
            System.out.println("10. Executar busca em profundidade (DFS)");
            System.out.println("11. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\nINSERIR VÉRTICE:");
                    System.out.print("Digite o ID do vértice: ");
                    int idVertice = scanner.nextInt();
                    grafo.inserirVertice(idVertice);
                    break;
                case 2:
                    System.out.println("\nINSERIR ARESTA::");
                    System.out.print("Digite o ID do vértice de origem: ");
                    int origem = scanner.nextInt();
                    System.out.print("Digite o ID do vértice de destino: ");
                    int destino = scanner.nextInt();
                    System.out.print("Digite o peso da aresta: ");
                    int peso = scanner.nextInt();
                    grafo.inserirAresta(origem, destino, peso);
                    break;
                case 3:
                    System.out.println("\nREMOVER VÉRTICE:");
                    System.out.print("Digite o ID do vértice a ser removido: ");
                    int idRemoverVertice = scanner.nextInt();
                    grafo.removerVertice(idRemoverVertice);
                    break;
                case 4:
                    System.out.println("\nREMOVER ARESTA:");
                    System.out.print("Digite o ID do vértice de origem da aresta: ");
                    int origemRemover = scanner.nextInt();
                    System.out.print("Digite o ID do vértice de destino da aresta: ");
                    int destinoRemover = scanner.nextInt();
                    grafo.removerAresta(origemRemover, destinoRemover);
                    break;
                case 5:
                    System.out.println("\nVISUALIZAR GRAFO:");
                    grafo.visualizarGrafo();
                    break;
                case 6:
                    System.out.println("\nINFORMAR GRAU DO VÉRTICE");
                    System.out.print("Digite o ID do vértice: ");
                    int idGrau = scanner.nextInt();
                    int grau = grafo.informarGrauVertice(idGrau);
                    if (grau != -1) {
                        System.out.println("Grau do vértice " + idGrau + ": " + grau);
                    }
                    break;
                case 7:
                    System.out.println("\nVERIFICAR SE O GRAFO É CONEXO");
                    boolean conexo = grafo.verificarGrafoConexo();
                    if (conexo) {
                        System.out.println("O grafo é conexo.");
                    } else {
                        System.out.println("O grafo não é conexo.");
                    }
                    break;
                case 8:
                    System.out.println("\nCONVERTER PARA MATRIZ DE ADJACÊNCIA:");
                    int[][] matrizAdjacencia = grafo.converterParaMatrizAdjacencia();
                    System.out.println("Matriz de Adjacência:");
                    for (int i = 0; i < matrizAdjacencia.length; i++) {
                        for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                            System.out.print(matrizAdjacencia[i][j] + " ");
                        }
                        System.out.println();
                    }
                    break;
                case 9:
                    System.out.println("\nEXECUTAR BUSCA EM LARGURA (BFS)");
                    System.out.print("Digite o ID do vértice inicial para BFS: ");
                    int inicial = scanner.nextInt();
                    List<Integer> ordemVisita = bfs.buscar(grafo, inicial);
                    System.out.println("Ordem de visita no BFS: " + ordemVisita);
                    
                    // Chama a plotagem do grafo com BFS
                    plotter.plotarGrafo(grafo, ordemVisita);
                    plotter.pack();
                    plotter.setVisible(true);
                    break;
                case 10: // Executar busca em profundidade (DFS)
                    System.out.println("\nEXECUTAR BUSCA EM PROFUNDIDADE (DFS)");
                    System.out.print("Digite o ID do vértice inicial para DFS: ");
                    int verticeInicial = scanner.nextInt();

                    // Verificar se o vértice inicial é válido
                    if (grafo.getMapaAdjacencias().containsKey(verticeInicial)) {
                        DFS dfs = new DFS(grafo);
                        List<Integer> ordemVisitaDFS = dfs.executar(verticeInicial);
                        System.out.println("Ordem de visita no DFS: " + ordemVisitaDFS);
                        
                        // Chama a plotagem do grafo com DFS
                        plotter.plotarGrafo(grafo, ordemVisitaDFS);
                        plotter.pack();
                        plotter.setVisible(true);
                    } else {
                        System.out.println("Vértice inválido! Tente novamente.");
                    }
                    break;
                case 11:    
                    System.out.println("Saindo...");
                    break;
                    
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 11);

        scanner.close();
    }
}
