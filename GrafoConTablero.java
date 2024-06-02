/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.grafocontablero;

/**
 *
 * @author pinto
 */
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GrafoConTablero extends JPanel {
    private char[][] matrizNodos;
    private int numVertices;
    private final int ladoCelda = 50; // Tamaño de cada celda en píxeles
    private Random random = new Random();

    public GrafoConTablero(int numVertices) {
        this.numVertices = numVertices;
        this.matrizNodos = new char[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                matrizNodos[i][j] = obtenerLetraAleatoria();
            }
        }
    }

    private char obtenerLetraAleatoria() {
        return (char) ('A' + random.nextInt(26)); // Letras aleatorias
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                g.setColor(Color.BLACK);
                g.drawRect(j * ladoCelda, i * ladoCelda, ladoCelda, ladoCelda);
                g.drawString(String.valueOf(matrizNodos[i][j]), j * ladoCelda + 20, i * ladoCelda + 30);
            }
        }
    }

    public static void main(String[] args) {
        int numVertices = 4;
        GrafoConTablero grafo = new GrafoConTablero(numVertices);

        // Crear la ventana gráfica
        JFrame frame = new JFrame("Grafo con Tablero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(grafo);
        frame.setSize(numVertices * grafo.ladoCelda, numVertices * grafo.ladoCelda);
        frame.setVisible(true);
    }
}
