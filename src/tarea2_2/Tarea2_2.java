/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea2_2;

import static java.lang.Math.pow;
import javax.swing.JOptionPane;

/**
 *
 * @author Lissette
 */
public class Tarea2_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String inputNumA = JOptionPane.showInputDialog("Introduce el valor de a:");
        double a = Double.parseDouble(inputNumA);
        String inputNumB = JOptionPane.showInputDialog("Introduce el valor de b:");
        double b = Double.parseDouble(inputNumB);
        String inputNumC = JOptionPane.showInputDialog("Introduce el valor de c:");
        double c = Double.parseDouble(inputNumC);

        if (a == 0 && b == 0) {
            JOptionPane.showMessageDialog(null, "La ecuación es degenerada.");
        } else if (a == 0) {
            double raizUnica = -c / b;
            JOptionPane.showMessageDialog(null, "La raíz única es: " + raizUnica);
        } else {
            double discriminante = pow(b,2)- 4 * a * c;

            if (discriminante >= 0) {
                double raiz1 = (-b + Math.sqrt(discriminante)) / (2 * a);
                double raiz2 = (-b - Math.sqrt(discriminante)) / (2 * a);
                JOptionPane.showMessageDialog(null, "Las raíces reales son: " + raiz1 + " y " + raiz2);
            } else {
                double x = -b / (2 * a);
                double y = Math.sqrt(-discriminante) / (2 * a);
                JOptionPane.showMessageDialog(null, "Las raíces complejas son: " + "\n"+  x + " + " + y + "j"+ "\n y \n" + x + " - " + y + "j");
            }
        }
        
        
    }
    
}
