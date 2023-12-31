/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package guia1_ejercicio2;

import javax.swing.JOptionPane;

/**
 *
 * @author ITCA-FEPADE
 */
public class Guia1_ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String inputExamen1 = JOptionPane.showInputDialog("Ingrese el valor del Parcial 1");
        double examen1 = Double.parseDouble(inputExamen1);

        String inputExamen2 = JOptionPane.showInputDialog("Ingrese el valor del Parcial 2");
        double examen2 = Double.parseDouble(inputExamen2);

        String inputExamen3 = JOptionPane.showInputDialog("Ingrese el valor del Parcial 3");
        double examen3 = Double.parseDouble(inputExamen3);

        String inputTask = JOptionPane.showInputDialog("Ingrese el valor de la tarea");
        double tarea = Double.parseDouble(inputTask);

        examen1 = examen1 * 0.2;
        examen2 = examen2 * 0.25;
        examen3 = examen3 * 0.4;
        tarea = tarea * 0.15;
        double res = (examen1 + examen2 + examen3 + tarea);

        JOptionPane.showMessageDialog(null, "Nota: "+res);
    }
    
}
