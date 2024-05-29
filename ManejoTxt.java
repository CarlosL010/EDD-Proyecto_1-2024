/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.manejotxt;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 *
 * 
 * @author pinto
 */


 /**
 * La clase ManejoTxt proporciona una interfaz gráfica de usuario para leer, escribir y cargar archivos de texto.
 * También permite guardar cambios en el archivo de texto.
 */
 
public class ManejoTxt {
    JFrame frame;
    JButton leerBtn, escribirBtn, cargarBtn, guardarBtn, crearBtn;
    JTextArea textArea;
    File archivoActual;

    public ManejoTxt() {
        frame = new JFrame("Manejo de Archivos TXT");
        leerBtn = new JButton("Leer Archivo");
        escribirBtn = new JButton("Escribir Archivo");
        cargarBtn = new JButton("Cargar Archivo");
        guardarBtn = new JButton("Guardar Cambios");
        crearBtn = new JButton("Crear Archivo");
        textArea = new JTextArea(15, 30); // Ajusta el tamaño según tus necesidades
        JScrollPane scrollPane = new JScrollPane(textArea);

        leerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                leerArchivo();
            }
        });

        escribirBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                escribirArchivo();
            }
        });

        cargarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarArchivo();
            }
        });

        guardarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });

        crearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearArchivo();
            }
        });

        frame.setLayout(new FlowLayout());
        frame.add(leerBtn);
        frame.add(escribirBtn);
        frame.add(cargarBtn);
        frame.add(guardarBtn);
        frame.add(crearBtn);
        frame.add(scrollPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Método para crear un archivo de texto a partir de la entrada del usuario.
     * Muestra un cuadro de diálogo de entrada para obtener el contenido del usuario,
     * luego muestra un cuadro de diálogo de guardado para seleccionar dónde guardar el archivo.
     * Escribe el contenido proporcionado por el usuario en el archivo seleccionado.
     */
    public void crearArchivo() {
    String contenido = JOptionPane.showInputDialog("Introduce el contenido para el archivo:");
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("TXT files", "txt"));

    int seleccion = fileChooser.showSaveDialog(null);

    if (seleccion == JFileChooser.APPROVE_OPTION) {
        File archivo = fileChooser.getSelectedFile();
        if (!archivo.getName().toLowerCase().endsWith(".txt")) {
            archivo = new File(archivo.getParentFile(), archivo.getName() + ".txt");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(contenido);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}
    
    
    
     /**
 * Método para leer un archivo de texto seleccionado por el usuario.
 * Muestra un cuadro de diálogo de apertura de archivo y lee el archivo seleccionado,
 * mostrando su contenido en el JTextArea de la interfaz gráfica de usuario.
 * Después de leer el archivo, deshabilita la edición del JTextArea.
 */
    public void leerArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("TXT files", "txt"));

        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                textArea.setText("");
                while ((linea = br.readLine()) != null) {
                    textArea.append(linea + "\n");
                }
                textArea.setEditable(false); // Deshabilita la edición
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        }
    }

    /**
 * Método para escribir en un archivo de texto seleccionado por el usuario.
 * Muestra un cuadro de diálogo de apertura de archivo y lee el archivo seleccionado,
 * mostrando su contenido en el JTextArea de la interfaz gráfica de usuario.
 * Después de leer el archivo, habilita la edición del JTextArea para que el usuario pueda realizar cambios.
 * El archivo seleccionado se establece como el archivo actual para futuras operaciones de guardado.
 */
    public void escribirArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("TXT files", "txt"));

        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            archivoActual = fileChooser.getSelectedFile(); // Establece el archivo actual
            try (BufferedReader br = new BufferedReader(new FileReader(archivoActual))) {
                String linea;
                textArea.setText("");
                while ((linea = br.readLine()) != null) {
                    textArea.append(linea + "\n");
                }
                textArea.setEditable(true); // Habilita la edición
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        }
    }

    /**
 * Método para cargar un archivo de texto seleccionado por el usuario.
 * Muestra un cuadro de diálogo de apertura de archivo y carga el archivo seleccionado,
 * mostrando su contenido en el JTextArea de la interfaz gráfica de usuario.
 * Después de cargar el archivo, no cambia la propiedad editable del JTextArea.
 */
    public void cargarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("TXT files", "txt"));

        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                textArea.setText("");
                while ((linea = br.readLine()) != null) {
                    textArea.append(linea + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error al cargar el archivo: " + e.getMessage());
            }
        }
    }

    /**
 * Método para guardar los cambios realizados en el JTextArea al archivo actual.
 * Si no se ha seleccionado ningún archivo (es decir, archivoActual es null), imprime un mensaje de error.
 * Si se ha seleccionado un archivo, abre un BufferedWriter en el archivo y escribe el contenido del JTextArea en el archivo.
 * Si ocurre un error durante la escritura del archivo, imprime un mensaje de error.
 */
    public void guardarCambios() {
        if (archivoActual != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoActual))) {
                bw.write(textArea.getText());
            } catch (IOException e) {
                System.out.println("Error al guardar el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("No hay archivo seleccionado para guardar.");
        }
    }
    
    
    
    
    public static void main(String[] args) {
        new ManejoTxt();
    }
}