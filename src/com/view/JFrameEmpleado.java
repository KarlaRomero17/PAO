/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.view;

import com.controller.*;
import com.controller.exceptions.NonexistentEntityException;
import com.entities.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lissette
 */
public class JFrameEmpleado extends javax.swing.JFrame {

    Empleado empleado = new Empleado();
    EmpleadoJpaController ejc = new EmpleadoJpaController();
    private javax.swing.ButtonGroup buttonGroup1;
    /**
     * Creates new form JFrameEmpleado
     */
    public JFrameEmpleado() {
        initComponents();
        this.setLocationRelativeTo(this);
        tablaEmpleado();
        //tfCodigo.setEnabled(false);
        // Inicializar el ButtonGroup
        buttonGroup1 = new javax.swing.ButtonGroup();
        // Radio botones en el mismo ButtonGroup
        buttonGroup1.add(rbFemenino);
        buttonGroup1.add(rbMasculino);
    }
    
    //Método para agregar registros
    public void agregar() throws Exception {
        String codigoText = this.tfCodigo.getText();
        if (codigoText.isEmpty() || !codigoText.matches("\\d+")) {
        JOptionPane.showMessageDialog(null, "El código de empleado no es un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si el código no es válido
        }
        try {
            //empleado.setCodigoEmpleado(Integer.parseInt(codigoText));
            empleado.setNombre(this.tfNombre.getText());
            if (this.rbFemenino.isSelected()) {
                empleado.setSexo("Femenino");
            } else {
                empleado.setSexo("Masculino");
            }
            empleado.setEdad(Integer.parseInt(this.spnEdad.getValue().toString()));
            empleado.setCargo(this.cbbCargo.getSelectedItem().toString());
            ejc.create(empleado);
            JOptionPane.showMessageDialog(null, "Datos insertados correctamente");
            limpiar();
            tablaEmpleado();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al convertir el código de empleado a un número", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar empleado", "Error",
            JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void tablaEmpleado() {
        String[] columnas = {"Código empleado", "Nombre", "Sexo", "Edad", "Cargo"};
        Object[] obj = new Object[5];
        DefaultTableModel tabla = new DefaultTableModel(null, columnas);
        List ls;
        try {
            ls = ejc.findEmpleadoEntities();
            for (int i = 0; i < ls.size(); i++) {
                empleado = (Empleado) ls.get(i);
                obj[0] = empleado.getCodigoEmpleado();
                obj[1] = empleado.getNombre();
                obj[2] = empleado.getSexo();
                obj[3] = empleado.getEdad();
                obj[4] = empleado.getCargo();
                tabla.addRow(obj);
            }
            ls = ejc.findEmpleadoEntities();
            this.tblEmpleado.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar datos");
        }
    }
    
    public void llenarTabla() {
        int fila = this.tblEmpleado.getSelectedRow();
        llenarCampos(fila);
    }
    
    // Nuevo método para llenar los campos con la información del registro seleccionado
    private void llenarCampos(int filaSeleccionada) {
        if (filaSeleccionada != -1) { // Seleccionó alguna fila
            tfCodigo.setText(String.valueOf(this.tblEmpleado.getValueAt(filaSeleccionada,0)));
            tfNombre.setText(String.valueOf(this.tblEmpleado.getValueAt(filaSeleccionada,1)));
            String sexo = String.valueOf(this.tblEmpleado.getValueAt(filaSeleccionada, 2));
            if (sexo.toLowerCase().equals("femenino")) {
                this.rbFemenino.setSelected(true);
            } else {
                this.rbMasculino.setSelected(true);
            }
            int cantidad = Integer.parseInt(String.valueOf(this.tblEmpleado.getValueAt(filaSeleccionada, 3)));
            spnEdad.setValue(cantidad);
            String cargo = String.valueOf(this.tblEmpleado.getValueAt(filaSeleccionada,4));
            this.cbbCargo.getModel().setSelectedItem(cargo);
        }
    }
    
    public void limpiar() {
        this.tfCodigo.setText("");
        this.tfNombre.setText("");
        this.cbbCargo.setSelectedIndex(0);
        this.spnEdad.setValue(18);
    }
    
    //Método de editar o modificar
    public void editar() throws Exception {
        String codigoText = this.tfCodigo.getText();
        if (codigoText.isEmpty() || !codigoText.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "El código de empleado no es un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si el código no es válido
        }
        try {
            int codigoEmpleado = Integer.parseInt(codigoText);
            empleado = ejc.findEmpleado(codigoEmpleado);
            if (empleado == null) {
                JOptionPane.showMessageDialog(null, "No se encontró un empleado con el código especificado", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si no se encuentra el empleado
            }
            empleado.setNombre(this.tfNombre.getText());
            if (this.rbFemenino.isSelected()) {
                empleado.setSexo("Femenino");
            } else {
                empleado.setSexo("Masculino");
            }
            empleado.setEdad(Integer.parseInt(this.spnEdad.getValue().toString()));
            empleado.setCargo(this.cbbCargo.getSelectedItem().toString());
            int decision = JOptionPane.showConfirmDialog(this, "Desea modificar el empleado", "Modificar empleado", JOptionPane.YES_NO_OPTION);
            if (decision == 0) {
                ejc.edit(empleado); // Guardar los cambios en la base de datos
                JOptionPane.showMessageDialog(rootPane, "Modificado con éxito","Confirmación", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                tablaEmpleado();
            } else {
                limpiar();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al convertir el código de empleado a un número", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NonexistentEntityException e) {
            JOptionPane.showMessageDialog(null, "Error al editar empleado: el empleado no existe", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al editar empleado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminar() throws NonexistentEntityException {
        String codigoText = this.tfCodigo.getText().trim(); // Trimming para eliminar espacios en blanco
        if (codigoText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de código está vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si el campo de código está vacío
        }
        try {
            int codigoEmpleado = Integer.parseInt(codigoText);
            empleado.setCodigoEmpleado(codigoEmpleado);
            int decision = JOptionPane.showConfirmDialog(this, "Desea eliminar el empleado", "Eliminar empleado", JOptionPane.YES_NO_OPTION);
            if (decision == 0) {
                ejc.destroy(empleado.getCodigoEmpleado());
                JOptionPane.showMessageDialog(rootPane, "Eliminado con éxito", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                tablaEmpleado();
            } else {
                limpiar();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El código de empleado no es un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NonexistentEntityException e) {
            JOptionPane.showMessageDialog(null, "No se encontró un empleado con el código especificado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rbMasculino = new javax.swing.JRadioButton();
        rbFemenino = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        spnEdad = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        cbbCargo = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleado = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Datos empleado");

        jLabel2.setText("Codigo:");

        jLabel3.setText("Nombre:");

        tfNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNombreActionPerformed(evt);
            }
        });

        jLabel4.setText("Sexo");

        rbMasculino.setText("Masculino");

        rbFemenino.setText("Femenino");
        rbFemenino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFemeninoActionPerformed(evt);
            }
        });

        jLabel5.setText("Edad:");

        jLabel6.setText("Cargo:");

        cbbCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(tfCodigo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(rbMasculino)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addComponent(rbFemenino))
                            .addComponent(tfNombre)
                            .addComponent(spnEdad)
                            .addComponent(cbbCargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(17, 17, 17))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(58, 58, 58))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rbMasculino)
                    .addComponent(rbFemenino))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(spnEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(cbbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLimpiar)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEditar)
                            .addComponent(btnAgregar)
                            .addComponent(btnEliminar))
                        .addGap(1, 1, 1)))
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnAgregar)
                .addGap(18, 18, 18)
                .addComponent(btnEditar)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        tblEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmpleado);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNombreActionPerformed

    private void rbFemeninoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFemeninoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbFemeninoActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        try {
            agregar();
            limpiar();
            tablaEmpleado();
        } catch (Exception e) {
            Logger.getLogger(JFrameEmpleado.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        try {
            editar();
            tablaEmpleado();
        } catch (Exception e) {
            Logger.getLogger(JFrameEmpleado.class.getName()).log(Level.SEVERE, null, e);
        }

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        try {
            eliminar();
            tablaEmpleado();
        } catch (NonexistentEntityException e) {
            Logger.getLogger(JFrameEmpleado.class.getName()).log(Level.SEVERE, null, e);
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tblEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadoMouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = tblEmpleado.getSelectedRow();
        llenarCampos(filaSeleccionada); // Llena los campos al hacer clic en una fila

    }//GEN-LAST:event_tblEmpleadoMouseClicked
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameEmpleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cbbCargo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbFemenino;
    private javax.swing.JRadioButton rbMasculino;
    private javax.swing.JSpinner spnEdad;
    private javax.swing.JTable tblEmpleado;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JTextField tfNombre;
    // End of variables declaration//GEN-END:variables
}
