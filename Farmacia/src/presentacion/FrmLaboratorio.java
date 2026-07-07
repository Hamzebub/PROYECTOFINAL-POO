/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.L_Laboratorio;
import modelo.EstadoOperacion;
import modelo.Laboratorio;
import modelo.Respuesta;

/**
 *
 * @author delac
 */
public class FrmLaboratorio extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmLaboratorio.class.getName());
    private L_Laboratorio obj;
    private DefaultTableModel modelo;
    private List<Laboratorio> lista= new ArrayList<Laboratorio>();
    private int ID=0;
    
    public FrmLaboratorio() {
        initComponents();
        obj = new L_Laboratorio();
        setLocationRelativeTo(null);
        modelo= new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Laboratorio");
        
        tblDatos.setModel(modelo);
        modelo.setRowCount(0); 
        cargarTabla();
        activarBotones(false);
    }
    
    private void Guardar(){
        Laboratorio m = new Laboratorio(ID,txtLaboratorioDetalle.getText());
        Respuesta r= new Respuesta();
        if(m.getLaboratorio_Id()==0){
            r = obj.guardar(m);
        }else{
            r = obj.actualizar(m);
        }
        
        JOptionPane.showMessageDialog(null, r.getMensaje());
        if(r.esCorrecto()){
            cargarTabla();
            activarBotones(false);
            txtLaboratorioDetalle.setText("");
        }
        
    }
    
    private void cargarTabla() {

    Respuesta<List<Laboratorio>> r = obj.listar();
    

    modelo.setRowCount(0);

    if (r.getEstado() == EstadoOperacion.EXITO) {
        lista = r.getDatos();
        for (Laboratorio m : r.getDatos()) {
            modelo.addRow(new Object[]{
                m.getLaboratorio_Id(),
                m.getLaboratorio_Detalle()
            });
        }
    } else {
        JOptionPane.showMessageDialog(this, r.getMensaje());
    }
    }
    
    private void obtenerIDxTabla(){
        
        int fila= tblDatos.getSelectedRow();
        ID =Integer.parseInt(tblDatos.getValueAt(fila, 0).toString());
    }
    
    private void activarBotones(boolean b){
        txtLaboratorioDetalle.setEnabled(b);
        btnNuevo.setEnabled(!b);
        btnEditar.setEnabled(!b);
        btnEliminar.setEnabled(!b);
        btnGuardar.setEnabled(b);
        btnCancelar.setEnabled(b);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtLaboratorioDetalle = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Laboratorio", "Acciones"
            }
        ));
        tblDatos.setName("tblDatos"); // NOI18N
        jScrollPane1.setViewportView(tblDatos);
        tblDatos.getAccessibleContext().setAccessibleName("");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setToolTipText("");

        jLabel1.setText("Laboratorio:");

        txtLaboratorioDetalle.setName("txtLaboratorio"); // NOI18N
        txtLaboratorioDetalle.addActionListener(this::txtLaboratorioDetalleActionPerformed);

        btnGuardar.setText("Guardar");
        btnGuardar.setToolTipText("");
        btnGuardar.setName(""); // NOI18N
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnNuevo.setText("Nuevo");
        btnNuevo.setName(""); // NOI18N
        btnNuevo.addActionListener(this::btnNuevoActionPerformed);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLaboratorioDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addGap(24, 24, 24))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtLaboratorioDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar)
                    .addComponent(btnNuevo)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        jLabel1.getAccessibleContext().setAccessibleName("Laboratorio:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtLaboratorioDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLaboratorioDetalleActionPerformed
        Guardar();
    }//GEN-LAST:event_txtLaboratorioDetalleActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        obtenerIDxTabla();
        Laboratorio _lab = lista.stream().filter(m -> m.getLaboratorio_Id()==ID).findFirst()
        .orElse(null);
        if(_lab==null){
            JOptionPane.showMessageDialog(null, "No hay registros seleccionados");
            return;
        }
        txtLaboratorioDetalle.setText(_lab.getLaboratorio_Detalle());
        activarBotones(true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        obtenerIDxTabla();
        if(ID==0){
            JOptionPane.showMessageDialog(null, "No hay registros seleccionados");
            return;
        }
        if( JOptionPane.showConfirmDialog(null, "Desea eliminar registro?")!=JOptionPane.YES_OPTION){
            return;
        }

        Respuesta r = obj.eliminar(ID);
        JOptionPane.showMessageDialog(null, r.getMensaje());
        if(r.esCorrecto()){
            cargarTabla();
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        activarBotones(true);
        ID=0;
        txtLaboratorioDetalle.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        activarBotones(false);
        txtLaboratorioDetalle.setText("");
    }//GEN-LAST:event_btnCancelarActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmLaboratorio().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDatos;
    private javax.swing.JTextField txtLaboratorioDetalle;
    // End of variables declaration//GEN-END:variables
}
