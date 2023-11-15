/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.view;

import com.controller.*;
import com.controller.exceptions.NonexistentEntityException;
import com.entities.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lissette
 */
public class JFramePersona extends javax.swing.JFrame {

    Person personaa = new Person();
    PersonJpaController ejc = new PersonJpaController();
    private javax.swing.ButtonGroup buttonGroup1;
    /**
     * Creates new form JFrameEmpleado
     */
    public JFramePersona() {
        initComponents();
        this.setLocationRelativeTo(this);
        tablaPersona();
        tfCodigo.setEnabled(false);
        // Inicializar el ButtonGroup
        buttonGroup1 = new javax.swing.ButtonGroup();
        // Radio botones en el mismo ButtonGroup
        buttonGroup1.add(rbFemenino);
        buttonGroup1.add(rbMasculino);
    }
    
    //Método para agregar registros
    public void agregar() throws Exception {
        String codigoText = this.tfCodigo.getText();
        /*if (codigoText.isEmpty() || !codigoText.matches("\\d+")) {
        JOptionPane.showMessageDialog(null, "El código de personaa no es un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si el código no es válido
        }*/
        try {
            //empleado.setCodigoEmpleado(Integer.parseInt(codigoText));
            personaa.setNombre(this.tfNombre.getText());
            personaa.setApellidos(this.tfApellido.getText());
            if (this.rbFemenino.isSelected()) {
                personaa.setSexo("Femenino");
            } else {
                personaa.setSexo("Masculino");
            }
            personaa.setFechaNacimiento(this.dtcFechaNacimiento.getDate());
            personaa.setCargo(this.cbbCargo.getSelectedItem().toString());
            ejc.create(personaa);
            JOptionPane.showMessageDialog(null, "Datos insertados correctamente");
            limpiar();
            tablaPersona();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al convertir el código de empleado a un número", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar empleado", "Error",
            JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void tablaPersona() {
        String[] columnas = {"Código empleado", "Nombre", "Apellidos","Sexo", "Fecha Nacimiento", "Cargo"};
        Object[] obj = new Object[6];
        DefaultTableModel tabla = new DefaultTableModel(null, columnas);
        List ls;
        try {
            ls = ejc.findPersonEntities();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < ls.size(); i++) {
                personaa = (Person) ls.get(i);
                obj[0] = personaa.getCodigoPersona();
                obj[1] = personaa.getNombre();
                obj[2] = personaa.getApellidos();
                obj[3] = personaa.getSexo();
                obj[4] = dateFormat.format(personaa.getFechaNacimiento());
                obj[5] = personaa.getCargo();
                tabla.addRow(obj);
            }
            ls = ejc.findPersonEntities();
            this.tblEmpleado.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar datos" + e);
        }
    }
    
    public void llenarTabla() throws ParseException {
        int fila = this.tblEmpleado.getSelectedRow();
        llenarCampos(fila);
    }
    
    // Nuevo método para llenar los campos con la información del registro seleccionado
    private void llenarCampos(int filaSeleccionada) throws ParseException {
        if (filaSeleccionada != -1) { // Seleccionó alguna fila
            tfCodigo.setText(String.valueOf(this.tblEmpleado.getValueAt(filaSeleccionada,0)));
            tfNombre.setText(String.valueOf(this.tblEmpleado.getValueAt(filaSeleccionada,1)));
            tfApellido.setText(String.valueOf(this.tblEmpleado.getValueAt(filaSeleccionada,2)));
            String sexo = String.valueOf(this.tblEmpleado.getValueAt(filaSeleccionada, 3));
            if (sexo.toLowerCase().equals("femenino")) {
                this.rbFemenino.setSelected(true);
            } else {
                this.rbMasculino.setSelected(true);
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = dateFormat.parse((String) this.tblEmpleado.getValueAt(filaSeleccionada, 4));
            dtcFechaNacimiento.setDate(date);
            String cargo = String.valueOf(this.tblEmpleado.getValueAt(filaSeleccionada,5));
            this.cbbCargo.getModel().setSelectedItem(cargo);
        }
    }
    
    public void limpiar() {
        this.tfCodigo.setText("");
        this.tfNombre.setText("");
        this.tfApellido.setText("");
        this.cbbCargo.setSelectedIndex(0);
        this.dtcFechaNacimiento.setDate(null);
    }
    
    //Método de editar o modificar
    public void editar() throws Exception {
        String codigoText = this.tfCodigo.getText();
        /*if (codigoText.isEmpty() || !codigoText.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "El código de personaa no es un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si el código no es válido
        }*/
        try {
            int codigoEmpleado = Integer.parseInt(codigoText);
            personaa = ejc.findPerson(codigoEmpleado);
            if (personaa == null) {
                JOptionPane.showMessageDialog(null, "No se encontró un empleado con el código especificado", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si no se encuentra el personaa
            }
            personaa.setNombre(this.tfNombre.getText());
            personaa.setApellidos(this.tfApellido.getText());
            if (this.rbFemenino.isSelected()) {
                personaa.setSexo("Femenino");
            } else {
                personaa.setSexo("Masculino");
            }
            //empleado.setEdad(Integer.parseInt(this.spnEdad.getValue().toString()));
            personaa.setFechaNacimiento(((Date) this.dtcFechaNacimiento.getDate()));
            personaa.setCargo(this.cbbCargo.getSelectedItem().toString());
            int decision = JOptionPane.showConfirmDialog(this, "Desea modificar el empleado", "Modificar empleado", JOptionPane.YES_NO_OPTION);
            if (decision == 0) {
                ejc.edit(personaa); // Guardar los cambios en la base de datos
                JOptionPane.showMessageDialog(rootPane, "Modificado con éxito","Confirmación", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                tablaPersona();
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
            personaa.setCodigoPersona(codigoEmpleado);
            int decision = JOptionPane.showConfirmDialog(this, "Desea eliminar el empleado", "Eliminar empleado", JOptionPane.YES_NO_OPTION);
            if (decision == 0) {
                ejc.destroy(personaa.getCodigoPersona());
                JOptionPane.showMessageDialog(rootPane, "Eliminado con éxito", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                tablaPersona();
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
        jLabel6 = new javax.swing.JLabel();
        cbbCargo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        tfApellido = new javax.swing.JTextField();
        dtcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleado = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Citizen - Karla Romero | Parcial III");

        jLabel1.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 18)); // NOI18N
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

        jLabel5.setText("Fecha nacimiento:");

        jLabel6.setText("Cargo:");

        cbbCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Desarrollador web", "Técnico informatico", "Diseñador UI/UX", "QA", "Administracion" }));
        cbbCargo.setToolTipText("");

        jLabel7.setText("Apellidos:");

        tfApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfApellidoActionPerformed(evt);
            }
        });

        dtcFechaNacimiento.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2))
                                        .addGap(56, 56, 56))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(78, 78, 78)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(rbMasculino)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                                .addComponent(rbFemenino))
                                            .addComponent(tfCodigo))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dtcFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tfNombre)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(295, 295, 295))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(tfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(rbMasculino)
                            .addComponent(rbFemenino)
                            .addComponent(jLabel5))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(cbbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/limpiar.png"))); // NOI18N
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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnAgregar)
                .addGap(18, 18, 18)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            tablaPersona();
        } catch (Exception e) {
            Logger.getLogger(JFramePersona.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        try {
            editar();
            tablaPersona();
        } catch (Exception e) {
            Logger.getLogger(JFramePersona.class.getName()).log(Level.SEVERE, null, e);
        }

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        try {
            eliminar();
            tablaPersona();
        } catch (NonexistentEntityException e) {
            Logger.getLogger(JFramePersona.class.getName()).log(Level.SEVERE, null, e);
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tblEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadoMouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = tblEmpleado.getSelectedRow();
        try {
            llenarCampos(filaSeleccionada); // Llena los campos al hacer clic en una fila
        } catch (ParseException ex) {
            Logger.getLogger(JFramePersona.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_tblEmpleadoMouseClicked

    private void tfApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfApellidoActionPerformed
    
    
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
            java.util.logging.Logger.getLogger(JFramePersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFramePersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFramePersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFramePersona.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFramePersona().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cbbCargo;
    private com.toedter.calendar.JDateChooser dtcFechaNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbFemenino;
    private javax.swing.JRadioButton rbMasculino;
    private javax.swing.JTable tblEmpleado;
    private javax.swing.JTextField tfApellido;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JTextField tfNombre;
    // End of variables declaration//GEN-END:variables
}
