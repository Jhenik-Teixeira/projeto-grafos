/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estruturagrafos;

/**
 *
 * @author pai de hendril
 */

/**
 * Classe que representa uma aresta em um grafo.
 */
public class Aresta {
    private int destino;
    private int peso;

    /**
     * Construtor da classe Aresta.
     *
     * @param destino O vértice de destino desta aresta.
     * @param peso    O peso da aresta.
     */
    public Aresta(int destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }

    /**
     * Obtém o vértice de destino desta aresta.
     *
     * @return O vértice de destino.
     */
    public int getDestino() {
        return destino;
    }

    /**
     * Obtém o peso desta aresta.
     *
     * @return O peso da aresta.
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Define o vértice de destino desta aresta.
     *
     * @param destino O novo vértice de destino.
     */
    public void setDestino(int destino) {
        this.destino = destino;
    }

    /**
     * Define o peso desta aresta.
     *
     * @param peso O novo peso da aresta.
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Aresta{" +
                "destino=" + destino +
                ", peso=" + peso +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Aresta)) return false;
        Aresta aresta = (Aresta) obj;
        return destino == aresta.destino && peso == aresta.peso;
    }

    @Override
    public int hashCode() {
        int resultado = Integer.hashCode(destino);
        resultado = 31 * resultado + Integer.hashCode(peso);
        return resultado;
    }
}

