/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea2_1;

import javax.swing.JOptionPane;

/**
 *
 * @author Lissette
 */
public class Tarea2_1 {

    /**
     * @param args the command line arguments
     */
    /*
    Realizar un programa que calcule e imprima la suma de los múltiplos de 5 comprendidos
entre dos valores a y b. El programa no permitirá introducir valores negativos para a y b,
y verificará que a es menor que b. Si a es mayor que b, intercambiará estos valores. 
    */
    public static void main(String[] args) {
        // TODO code application logic here
        int a, b;

        do {
            String inputNumA = JOptionPane.showInputDialog("Introduce el valor de a:");
            a = Integer.parseInt(inputNumA);
        } while (a < 0);
        
        do {
            String inputNumB = JOptionPane.showInputDialog("Introduce el valor de b:");
            b = Integer.parseInt(inputNumB);
        } while (b < 0);
        if (a > b) {
            int aux = a;
            a = b;
            b = aux;
        }
        int suma = 0;
        for (int i = a; i <= b; i++) {
            if (i % 5 == 0) {//calcular multiplos
                suma += i;
            }
        }
        JOptionPane.showMessageDialog(null, "La suma de los múltiplos de 5 comprendidos entre " + a + " y " + b + " es: " + suma);
    }
    
}
