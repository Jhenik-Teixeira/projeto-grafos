/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estruturagrafos;

/**
 *
 * @author pai de hendril
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFS {
    private Grafo grafo;
    private Set<Integer> visitados;
    private List<Integer> ordemVisita;

    public DFS(Grafo grafo) {
        this.grafo = grafo;
        this.visitados = new HashSet<>();
        this.ordemVisita = new ArrayList<>();
    }

    public List<Integer> executar(int verticeInicial) {
        dfsRecursivo(verticeInicial);
        return ordemVisita;
    }

    private void dfsRecursivo(int vertice) {
        visitados.add(vertice);
        ordemVisita.add(vertice);

        // Percorre as arestas para encontrar os vizinhos
        List<Aresta> arestas = grafo.getAdjacenciasDoVertice(vertice);
        for (Aresta aresta : arestas) {
            int vizinho = aresta.getDestino();
            if (!visitados.contains(vizinho)) {
                dfsRecursivo(vizinho);
            }
        }
    }
}
