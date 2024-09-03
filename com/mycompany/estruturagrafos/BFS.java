/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estruturagrafos;

/**
 *
 * @author pai de hendril
 */

import java.util.*;

/**
 * Classe que representa a busca em largura (BFS) em um grafo.
 */
public class BFS {

    /**
     * Realiza a busca em largura a partir do vértice inicial.
     *
     * @param grafo O grafo no qual a busca será realizada.
     * @param inicial O vértice de início da busca.
     * @return Uma lista contendo a ordem de visita dos vértices.
     */
    public List<Integer> buscar(Grafo grafo, int inicial) {
        List<Integer> ordemVisita = new ArrayList<>();
        if (grafo == null || !grafo.getMapaAdjacencias().containsKey(inicial)) {
            System.out.println("Grafo ou vértice inicial inválido.");
            return ordemVisita;
        }

        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> fila = new LinkedList<>();
        fila.add(inicial);
        visitados.add(inicial);

        while (!fila.isEmpty()) {
            int verticeAtual = fila.poll();
            ordemVisita.add(verticeAtual);

            for (Aresta aresta : grafo.getMapaAdjacencias().get(verticeAtual)) {
                int destino = aresta.getDestino();
                if (!visitados.contains(destino)) {
                    visitados.add(destino);
                    fila.add(destino);
                }
            }
        }

        return ordemVisita;
    }
}
