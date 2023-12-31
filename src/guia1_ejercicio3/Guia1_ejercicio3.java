/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package guia1_ejercicio3;

import javax.swing.JOptionPane;

/**
 *
 * @author ITCA-FEPADE
 */
public class Guia1_ejercicio3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String inputExamen1 = JOptionPane.showInputDialog("Ingrese el valor del Examen 1");
        double examen1 = Double.parseDouble(inputExamen1);

        String inputExamen2 = JOptionPane.showInputDialog("Ingrese el valor del Examen 2");
        double examen2 = Double.parseDouble(inputExamen2);

        String inputExamen3 = JOptionPane.showInputDialog("Ingrese el valor del Examen 3");
        double examen3 = Double.parseDouble(inputExamen3);

        double nota1 = (((examen1 + examen2 + examen3)/3)*0.55);

        String inputExamenFinal = JOptionPane.showInputDialog("Ingrese el valor del examen final");
        double ExamenFinal = Double.parseDouble(inputExamenFinal);

        double  nota2 = ExamenFinal*0.3;

        String inputExAula = JOptionPane.showInputDialog("Ingrese el valor del trabajo ex-aula");
        double ExAula = Double.parseDouble(inputExAula);

        double  nota3 = ExAula*0.15;

        double resultado = nota1 + nota2 + nota3;
        JOptionPane.showMessageDialog(null, "Nota Final: "+resultado);
    }
    
}
