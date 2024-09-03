package com.mycompany.estruturagrafos;

import java.util.*;

/**
 * Classe que representa um grafo utilizando listas de adjacências.
 */
public class Grafo {
    private Map<Integer, List<Aresta>> adjacencias;
    private int numVertices;

    /**
     * Construtor da classe Grafo.
     * Inicializa o mapa de adjacências e o número de vértices.
     */
    public Grafo() {
        adjacencias = new HashMap<>();
        numVertices = 0;
    }

    /**
     * Insere um novo vértice no grafo.
     *
     * @param id O identificador do vértice.
     */
    public void inserirVertice(int id) {
        if (!adjacencias.containsKey(id)) {
            adjacencias.put(id, new ArrayList<>());
            numVertices++;
        } else {
            System.out.println("Vértice " + id + " já existe.");
        }
    }

    /**
     * Insere uma nova aresta no grafo.
     *
     * @param origem  O vértice de origem da aresta.
     * @param destino O vértice de destino da aresta.
     * @param peso    O peso da aresta.
     */
    public void inserirAresta(int origem, int destino, int peso) {
        if (adjacencias.containsKey(origem) && adjacencias.containsKey(destino)) {
            adjacencias.get(origem).add(new Aresta(destino, peso));
            adjacencias.get(destino).add(new Aresta(origem, peso)); // Como o grafo é não direcionado
        } else {
            System.out.println("Um ou ambos os vértices não existem.");
        }
    }

    /**
     * Remove um vértice do grafo, juntamente com todas as arestas associadas.
     *
     * @param id O identificador do vértice a ser removido.
     */
    public void removerVertice(int id) {
        if (adjacencias.containsKey(id)) {
            adjacencias.values().forEach(list -> list.removeIf(aresta -> aresta.getDestino() == id));
            adjacencias.remove(id);
            numVertices--;
        } else {
            System.out.println("Vértice " + id + " não existe.");
        }
    }

    /**
     * Remove uma aresta específica entre dois vértices.
     *
     * @param origem  O vértice de origem da aresta.
     * @param destino O vértice de destino da aresta.
     */
    public void removerAresta(int origem, int destino) {
        if (adjacencias.containsKey(origem) && adjacencias.containsKey(destino)) {
            adjacencias.get(origem).removeIf(aresta -> aresta.getDestino() == destino);
            adjacencias.get(destino).removeIf(aresta -> aresta.getDestino() == origem);
        } else {
            System.out.println("Um ou ambos os vértices não existem.");
        }
    }

    /**
     * Exibe a representação do grafo, mostrando os vértices e suas respectivas arestas.
     */
    public void visualizarGrafo() {
        for (Map.Entry<Integer, List<Aresta>> entry : adjacencias.entrySet()) {
            System.out.print("Vértice " + entry.getKey() + ": ");
            for (Aresta aresta : entry.getValue()) {
                System.out.print("-> " + aresta.getDestino() + "(peso: " + aresta.getPeso() + ") ");
            }
            System.out.println();
        }
    }

    /**
     * Calcula o grau de um vértice específico.
     *
     * @param id O identificador do vértice.
     * @return O grau do vértice.
     */
    public int informarGrauVertice(int id) {
        if (adjacencias.containsKey(id)) {
            return adjacencias.get(id).size();
        } else {
            System.out.println("Vértice " + id + " não existe.");
            return -1;
        }
    }

    /**
     * Verifica se o grafo é conexo, ou seja, se há um caminho entre todos os pares de vértices.
     *
     * @return true se o grafo for conexo, false caso contrário.
     */
    public boolean verificarGrafoConexo() {
        if (adjacencias.isEmpty()) {
            return false;
        }

        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> fila = new LinkedList<>();
        int verticeInicial = adjacencias.keySet().iterator().next();
        fila.add(verticeInicial);
        visitados.add(verticeInicial);

        while (!fila.isEmpty()) {
            int verticeAtual = fila.poll();
            for (Aresta aresta : adjacencias.get(verticeAtual)) {
                int destino = aresta.getDestino();
                if (!visitados.contains(destino)) {
                    visitados.add(destino);
                    fila.add(destino);
                }
            }
        }

        return visitados.size() == adjacencias.size();
    }

    /**
     * Converte o grafo em uma matriz de adjacência.
     *
     * @return A matriz de adjacência representando o grafo.
     */
    public int[][] converterParaMatrizAdjacencia() {
        // Determina o maior índice de vértice para definir o tamanho da matriz
        int maxVertice = adjacencias.keySet().stream().max(Integer::compare).orElse(0);

        // Cria a matriz de adjacência com o tamanho adequado
        int[][] matrizAdj = new int[maxVertice + 1][maxVertice + 1];

        // Inicializa a matriz com zeros
        for (int i = 0; i <= maxVertice; i++) {
            Arrays.fill(matrizAdj[i], 0);
        }

        // Preenche a matriz com os pesos das arestas
        for (Map.Entry<Integer, List<Aresta>> entry : adjacencias.entrySet()) {
            int origem = entry.getKey();
            for (Aresta aresta : entry.getValue()) {
                int destino = aresta.getDestino();
                matrizAdj[origem][destino] = aresta.getPeso();
                matrizAdj[destino][origem] = aresta.getPeso(); // Grafo não direcionado
            }
        }

        return matrizAdj;
    }
    
    /**
     * Retorna o mapa completo de adjacências.
     *
     * @return O mapa de adjacências do grafo.
     */
    public Map<Integer, List<Aresta>> getMapaAdjacencias() {
        return adjacencias;
    }
    
    /**
     * Retorna a lista de adjacências de um vértice específico.
     *
     * @param vertice O identificador do vértice.
     * @return A lista de adjacências do vértice.
     */
    public List<Aresta> getAdjacenciasDoVertice(int vertice) {
        return adjacencias.get(vertice);
    }
    
    /**
     * Retorna o conjunto de vértices do grafo.
     *
     * @return O conjunto de vértices.
     */
    public Set<Integer> getVertices() {
        return adjacencias.keySet();
    }
}
