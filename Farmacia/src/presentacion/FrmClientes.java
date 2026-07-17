
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;


import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.L_Cliente;
import logica.L_TipoDocumento;
import modelo.Cliente;
import modelo.EstadoOperacion;
import modelo.Respuesta;
import modelo.TipoDocumento;
import util.cboUtil;


/**
 *
 * @author delac
 */
public class FrmClientes extends javax.swing.JInternalFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmClientes.class.getName());
    
    private L_Cliente obj;
    public L_TipoDocumento _doc;
    private DefaultTableModel tabla;
    private List<Cliente> lista;
    private int ID=0;

    public List<TipoDocumento> listaDocumento;
    
    public FrmClientes() {
        initComponents();
        
        activarBotones(false);
        
        obj = new L_Cliente();
        _doc = L_TipoDocumento.getInstancia();
        listaDocumento = _doc.obtenerListar();
        
        
        cargarModeloTabla();
        cargarTabla();
        
        cboUtil.llenarCombo(cboTDocumento, listaDocumento);
        activarBotones(false);
        setClosable(true);      // Permite cerrar
        setIconifiable(true);   // Minimizar
        setMaximizable(true);   // Maximizar
        setResizable(true);
    }
    
    void cargarModeloTabla(){tabla= new DefaultTableModel();
        tabla.addColumn("ID");
        tabla.addColumn("N° Documento");
        tabla.addColumn("Cliente");
        tabla.addColumn("Telefono");
        tabla.addColumn("Tipo Documento");
        //tabla.addColumn("Puntos Acumulado");
        tblDatosClient.setModel(tabla);
        tabla.setRowCount(0); 
        
    }

    
    private void cargarTabla() {
    Respuesta<List<Cliente>> r = obj.listar();
    tabla.setRowCount(0);

    if (r.getEstado() == EstadoOperacion.EXITO) {
        lista = r.getDatos();
        for (Cliente m : r.getDatos()) {
            tabla.addRow(new Object[]{
                m.getId(),
                m.getDni(),
                m.getNombre(),
                m.getTelefono(),
                m.getTipoDocumento().getAbreviatura(),
                m.getPuntosAcumulados(),
                m.getTipoDocumento().getTipoDocumento_Id()
            });
        }
    } else {
        JOptionPane.showMessageDialog(this, r.getMensaje());
    }
    }
    
    private void Guardar(){
        Cliente p = new Cliente(ID,(TipoDocumento) cboTDocumento.getSelectedItem()
                ,txtDocumento.getText()
                ,txtCliente.getText()
                ,txtTelefono.getText()
                
        );
        Respuesta r;
        if(p.getId()==0){
            r = obj.guardar(p);
        }else{
            r = obj.actualizar(p);
        }
        
        JOptionPane.showMessageDialog(null, r.getMensaje());
        if(r.esCorrecto()){
            cargarTabla();
            activarBotones(false);
            LimpiarCampos(null);
        }
        
    }
    
        private void activarBotones(boolean b){
        txtDocumento.setEnabled(b);
        txtCliente.setEnabled(b);
        txtTelefono.setEnabled(b);
        cboTDocumento.setEnabled(b);
        
        btnNuevo.setEnabled(!b);
        btnEditar.setEnabled(!b);
        btnEliminar.setEnabled(!b);
        btnGuardar.setEnabled(b);
        btnCancelar.setEnabled(b);
        
    }
    
    private void LimpiarCampos(Cliente p){
        txtDocumento.setText(p !=null? p.getDni(): "");
        txtCliente.setText(p !=null? p.getNombre():"");
        txtTelefono.setText(p !=null? p.getTelefono():"");
        
        if(p==null){
            cboTDocumento.setSelectedIndex(0);
        }else{
            TipoDocumento c = cboUtil.filtrarItem(listaDocumento, o->o.getTipoDocumento_Id()== p.getTipoDocumento().getTipoDocumento_Id());
            if(c!=null){cboTDocumento.setSelectedItem(c);}else{cboTDocumento.setSelectedIndex(0);}
        }
    }
    
    private void obtenerIDxTabla(){
        int fila= tblDatosClient.getSelectedRow();
        ID= fila<0?fila : Integer.parseInt(tblDatosClient.getValueAt(fila, 0).toString());
       
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        lblPuntos = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtTelefono = new javax.swing.JTextField();
        cboTDocumento = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        txtCliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatosClient = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setTitle("Clientes");

        jLabel6.setText("Puntos Acumulados:");

        lblPuntos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Registrar Cliente:"));

        cboTDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Nombres/R.Social:");

        jLabel5.setText("Tipo Documento:");

        jLabel7.setText("Telefono:");

        jLabel4.setText("N° de Documento:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtCliente)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(cboTDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(txtDocumento, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTDocumento, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        tblDatosClient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "N° de Documento", "Cliente", "Telefono", "Estado"
            }
        ));
        tblDatosClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDatosClientMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDatosClient);

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(this::btnNuevoActionPerformed);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 33, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addGap(34, 34, 34)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        activarBotones(true);
        ID=0;
        LimpiarCampos(null);
        txtDocumento.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        activarBotones(false);
        LimpiarCampos(null);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Guardar();    
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        obtenerIDxTabla();
        Cliente _cliente = lista.stream().filter(m -> m.getId()==ID).findFirst().orElse(null);
        if(_cliente==null){
            JOptionPane.showMessageDialog(null, "No hay registros seleccionados");
            return;
        }
        LimpiarCampos(_cliente);
        activarBotones(true);
        txtDocumento.requestFocus();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void tblDatosClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosClientMouseClicked
    int fila = tblDatosClient.getSelectedRow();

    if (fila == -1)
        return;

    Cliente cliente = lista.get(fila);

    ID = cliente.getId();

    txtDocumento.setText(cliente.getDni());
    txtCliente.setText(cliente.getNombre());
    txtTelefono.setText(cliente.getTelefono());
    lblPuntos.setText(Integer.toString(cliente.getPuntosAcumulados()));
    TipoDocumento c = cboUtil.filtrarItem(listaDocumento, o->o.getTipoDocumento_Id()== cliente.getTipoDocumento().getTipoDocumento_Id());
    if(c!=null){cboTDocumento.setSelectedItem(c);}else{cboTDocumento.setSelectedIndex(0);}
    //Los campos siguen bloqueados
    activarBotones(false);
    }//GEN-LAST:event_tblDatosClientMouseClicked

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
            LimpiarCampos(null);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cboTDocumento;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPuntos;
    private javax.swing.JTable tblDatosClient;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
