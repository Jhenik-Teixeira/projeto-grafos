package com.mycompany.estruturagrafos;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.ui.ApplicationFrame;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Classe responsável por plotar o grafo utilizando JFreeChart.
 */
public class GraphPlot extends ApplicationFrame {

    public GraphPlot(String title) {
        super(title);
    }

    /**
     * Método que plota o grafo utilizando a ordem de visita do BFS.
     *
     * @param grafo       O grafo a ser plotado.
     * @param ordemVisita A ordem de visita do BFS.
     */
    public void plotarGrafo(Grafo grafo, List<Integer> ordemVisita) {
        DefaultXYDataset dataset = new DefaultXYDataset();
        Map<Integer, List<Aresta>> adjacencias = grafo.getMapaAdjacencias();

        // Mapear as coordenadas dos vértices aleatoriamente para simplificar a visualização
        Map<Integer, double[]> coordenadasVertice = gerarCoordenadasVertice(grafo);

        // Adicionar arestas ao dataset
        for (int vertice : adjacencias.keySet()) {
            List<Aresta> arestas = adjacencias.get(vertice);
            double[] coordOrigem = coordenadasVertice.get(vertice);

            for (Aresta aresta : arestas) {
                int destino = aresta.getDestino();
                double[] coordDestino = coordenadasVertice.get(destino);

                double[][] data = {
                    { coordOrigem[0], coordDestino[0] },
                    { coordOrigem[1], coordDestino[1] }
                };
                dataset.addSeries("V" + vertice + " -> V" + destino, data);
            }
        }

        JFreeChart chart = ChartFactory.createScatterPlot(
                "Visualização do Grafo",    // Título do gráfico
                "X",                         // Rótulo do eixo X
                "Y",                         // Rótulo do eixo Y
                dataset,                     // Dataset
                PlotOrientation.VERTICAL,
                true,                        // Mostrar legenda
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);
        plot.setBackgroundPaint(Color.white);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    /**
     * Gera coordenadas aleatórias para cada vértice.
     *
     * @param grafo O grafo com os vértices a serem mapeados.
     * @return Um mapa com as coordenadas de cada vértice.
     */
    private Map<Integer, double[]> gerarCoordenadasVertice(Grafo grafo) {
        Map<Integer, double[]> coordenadas = new HashMap<>();
        Random random = new Random();

        for (int vertice : grafo.getVertices()) {
            double x = random.nextDouble() * 10;
            double y = random.nextDouble() * 10;
            coordenadas.put(vertice, new double[]{x, y});
        }

        return coordenadas;
    }
}
