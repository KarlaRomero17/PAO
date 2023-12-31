/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ejercico1_1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Lissette
 */
public class MiFormulario extends javax.swing.JFrame {

    /**
     * Creates new form MiFormulario
     */
    public MiFormulario() {
        initComponents();
        configurarEventos(); // Configura los eventos de los componentes
        centrarEnPantalla(); // Centrar el JFrame en la pantalla
        configurarKeyListener(); // Configurar el KeyListener para el campo de texto
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        miCampoDeTexto = new javax.swing.JTextField();
        miEtiqueta = new javax.swing.JLabel();
        miBoton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        miCampoDeTexto.setName(""); // NOI18N

        miEtiqueta.setText("Ingresa tu nombre:");

        miBoton.setText("Guardar");
        miBoton.setName(""); // NOI18N
        miBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miBotonActionPerformed(evt);
            }
        });
        miBoton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                miBotonKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(miEtiqueta)
                .addGap(67, 67, 67)
                .addComponent(miCampoDeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(miBoton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(miCampoDeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(miEtiqueta))
                .addGap(39, 39, 39)
                .addComponent(miBoton)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miBotonActionPerformed

    private void miBotonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_miBotonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_miBotonKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton miBoton;
    private javax.swing.JTextField miCampoDeTexto;
    private javax.swing.JLabel miEtiqueta;
    // End of variables declaration//GEN-END:variables

    
    
    private void configurarEventos() {
        // Configurar el evento para el botón
        miBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Obtener el texto ingresado por el usuario
                String nombre = miCampoDeTexto.getText();

                if (!nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Hola " + nombre);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Debes ingresar un nombre ");
               }
            }
        });
    }
    private void centrarEnPantalla() {
        // Obtener el tamaño de la pantalla
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();


        // Obtener el tamaño del JFrame
        Dimension ventana = this.getSize();

        // Calcular la posición para centrar el JFrame
        int posX = (pantalla.width - ventana.width) / 2;
        int posY = (pantalla.height - ventana.height) / 2;

        // Establecer la posición del JFrame en el centro
        this.setLocation(posX, posY);
    }

    private void configurarKeyListener() {
        miCampoDeTexto.addKeyListener(new KeyListener() {
            

            @Override
            public void keyPressed(KeyEvent e) {
                // Verificar si se presionó la tecla Enter
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // Disparar el evento del botón
                miBoton.doClick();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); 
            }
        });    
    }

}