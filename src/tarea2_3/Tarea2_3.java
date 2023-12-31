/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea2_3;

import javax.swing.JOptionPane;

/**
 *
 * @author Lissette
 */
public class Tarea2_3 {
/*
    Conversión de Grados: Pide al usuario que ingrese una temperatura en grados Celsius
y conviértela a grados Fahrenheit utilizando la fórmula de conversión.
• Para convertir grados Celsius a grados Fahrenheit, puedes utilizar la siguiente
fórmula:
°F = (°C × 9/5) + 32
8
Donde:
°F es la temperatura en grados Fahrenheit.
°C es la temperatura en grados Celsius
    */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String inputNum = JOptionPane.showInputDialog("Ingrese el valor en grados C°");
        double celsius = Double.parseDouble(inputNum);
        
        double fahrenheit = (celsius * 9/5) + 32;
        
     

        JOptionPane.showMessageDialog(null, "Los grados Fahrenheit son: "+fahrenheit);
    }
    
}
