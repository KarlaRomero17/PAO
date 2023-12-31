/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package guia1_ejercicio;

import static java.lang.Math.pow;
import javax.swing.JOptionPane;

/**
 *
 * @author ITCA-FEPADE
 */
public class Guia1_ejercicio {

    /**
     * @param args the command line arguments
     */
    
    /*
    Escriba un programa en Java que reciba a través de cuadros de dialogo el radio de
una circunferencia, calcúle su área y su diámetro, muestre los resultados obtenidos.
    */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String inputR = JOptionPane.showInputDialog("Ingrese el radio de la circunferencia ");
        double r = Double.parseDouble(inputR);
        
        double area, diametro, pi=3.1416;
        area= pi *(pow(r,2));
        diametro = r * 2;
        
       
        //mostrar los resultados
        String msj ="Resultados \n";
        msj += "Area: " +area + " \n";
        msj += "Diametro: " +diametro + " \n";
        
        JOptionPane.showMessageDialog(null,msj);
    }
    
}
