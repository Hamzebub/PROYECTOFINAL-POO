package presentacion;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.L_Categoria;
import logica.L_Laboratorio;
import logica.L_Marca;
import logica.L_Producto;
import modelo.Categoria;
import modelo.EstadoOperacion;
import modelo.Laboratorio;
import modelo.Marca;
import modelo.Producto;
import modelo.Respuesta;
import util.cboUtil;

/**
 *
 * @author delac
 */
public class FrmProductos extends javax.swing.JInternalFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmProductos.class.getName());
    
    private L_Producto obj;
    private L_Marca _marca;
    private DefaultTableModel modelo;
    private List<Producto> lista;
    private int ID=0;
    
    private List<Marca> listaMarca;
    private List<Categoria> listaCategoria;
    private List<Laboratorio> listaLaboratorio;

    public FrmProductos() {
        initComponents();
        
        activarBotones(false);
        obj = new L_Producto();
        _marca= new L_Marca();
        listaMarca = _marca.listar().getDatos();
        listaCategoria = new L_Categoria().listar().getDatos();
        listaLaboratorio = new L_Laboratorio().listar().getDatos();
        
        cargarModeloTabla();
        cargarTabla();
        
        
        cboUtil.llenarCombo(cboMarca, listaMarca);
        cboUtil.llenarCombo(cboCategoria, listaCategoria);
        cboUtil.llenarCombo(cboLaboratorio, listaLaboratorio);
        setClosable(true);      // Permite cerrar
    setIconifiable(true);   // Minimizar
    setMaximizable(true);   // Maximizar
    setResizable(true);     // Redimensionar
    }
    
    void cargarModeloTabla(){modelo= new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        modelo.addColumn("F.Venc.");
        modelo.addColumn("Laboratorio");
        modelo.addColumn("Marca");
        modelo.addColumn("Categoria");
        
        tblDatos.setModel(modelo);
        modelo.setRowCount(0); 
        
    }
    
    private void Guardar(){
        Date fv= jdcFVen.getDate();
        LocalDate localDate = null;
        if(fv!=null){
        localDate = fv.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();
        
        }
        
        Producto p = new Producto(ID,txtCodigo.getText()
                ,txtNombre.getText(),Double.parseDouble(txtPrecio.getText())
                ,Integer.parseInt(txtStock.getText())
                ,localDate
                ,(Laboratorio) cboLaboratorio.getSelectedItem()
                ,(Marca) cboMarca.getSelectedItem()
                ,(Categoria) cboCategoria.getSelectedItem()
        );
        Respuesta r;
        if(p.getProducto_Id()==0){
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
    
    private void cargarTabla() {
    Respuesta<List<Producto>> r = obj.listar();
    modelo.setRowCount(0);

    if (r.getEstado() == EstadoOperacion.EXITO) {
        lista = r.getDatos();
        for (Producto m : r.getDatos()) {
            modelo.addRow(new Object[]{
                m.getProducto_Id(),
                m.getCodigo(),
                m.getNombre(),
                m.getPrecio(),
                m.getStock(),
                m.getFechaVencimiento(),
                m.getLaboratorio().getLaboratorio_Detalle(),
                m.getMarca().getMarca_Detalle(),
                m.getCategoria().getCategoria_Detalle()
                   
            });
        }
    } else {
        JOptionPane.showMessageDialog(this, r.getMensaje());
    }
    }
    
    private void obtenerIDxTabla(){
        int fila= tblDatos.getSelectedRow();
        ID= fila<0?fila : Integer.parseInt(tblDatos.getValueAt(fila, 0).toString());
       
    }
    private void activarBotones(boolean b){
        txtCodigo.setEnabled(b);
        txtNombre.setEnabled(b);
        txtPrecio.setEnabled(b);
        txtStock.setEnabled(b);
        jdcFVen.setEnabled(b);
        cboCategoria.setEnabled(b);
        cboLaboratorio.setEnabled(b);
        cboMarca.setEnabled(b);
        
        btnNuevo.setEnabled(!b);
        btnEditar.setEnabled(!b);
        btnEliminar.setEnabled(!b);
        btnGuardar.setEnabled(b);
        btnCancelar.setEnabled(b);
        
    }
    
    private void LimpiarCampos(Producto p){
        txtCodigo.setText(p !=null? p.getCodigo(): "");
        txtNombre.setText(p !=null? p.getNombre():"");
        txtPrecio.setText(p !=null? Double.toString(p.getPrecio()) :"0");
        txtStock.setText(p !=null? Integer.toString(p.getStock()):"0");
        
        jdcFVen.setDate(p!=null? Date.from(
        p.getFechaVencimiento().atStartOfDay(ZoneId.systemDefault()).toInstant()
        ):null);
        
        if(p==null){
            cboCategoria.setSelectedIndex(0);
            cboLaboratorio.setSelectedIndex(0);
            cboMarca.setSelectedIndex(0);
        }else{
            Categoria c = cboUtil.filtrarItem(listaCategoria, o->o.getCategoria_Id()== p.getCategoria().getCategoria_Id());
            if(c!=null){cboCategoria.setSelectedItem(c);}else{cboCategoria.setSelectedIndex(0);}
            
            Laboratorio l = cboUtil.filtrarItem(listaLaboratorio, o->o.getLaboratorio_Id()== p.getLaboratorio().getLaboratorio_Id());
            if(l!=null){cboLaboratorio.setSelectedItem(l);}else{cboLaboratorio.setSelectedIndex(0);}
            
            Marca m = cboUtil.filtrarItem(listaMarca, o->o.getMarca_Id()== p.getMarca().getMarca_Id());
            if(m!=null){cboMarca.setSelectedItem(m);}else{cboMarca.setSelectedIndex(0);}
        
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jdcFVen = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox<>();
        cboLaboratorio = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cboMarca = new javax.swing.JComboBox<>();

        setTitle("Productos");
        setToolTipText("");

        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Marca", "Acciones"
            }
        ));
        tblDatos.setName("tblDatos"); // NOI18N
        jScrollPane1.setViewportView(tblDatos);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setToolTipText("");

        jLabel1.setText("Codigo:");

        txtCodigo.setName("txtMarca"); // NOI18N

        btnGuardar.setText("Guardar");
        btnGuardar.setToolTipText("");
        btnGuardar.setName(""); // NOI18N
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(this::btnNuevoActionPerformed);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        txtNombre.setName("txtMarca"); // NOI18N

        jLabel2.setText("Nombre:");

        jLabel3.setText("Precio:");

        txtPrecio.setName("txtMarca"); // NOI18N

        jLabel4.setText("Stock:");

        txtStock.setName("txtMarca"); // NOI18N

        jLabel5.setText("F.Venc.:");

        jdcFVen.setDateFormatString("dd/MM/yyyy");

        jLabel6.setText("Laboratorio:");

        cboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboLaboratorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Marca:");

        jLabel8.setText("Categoria:");

        cboMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(34, 34, 34)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jLabel4)
                                .addGap(236, 236, 236)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cboMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8))
                                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(225, 225, 225)
                                .addComponent(btnNuevo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdcFVen, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(jdcFVen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cboMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar)
                    .addComponent(btnNuevo)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        obtenerIDxTabla();
        Producto _producto = lista.stream().filter(m -> m.getProducto_Id()==ID).findFirst()
        .orElse(null);
        if(_producto==null){
            JOptionPane.showMessageDialog(null, "No hay registros seleccionados");
            return;
        }
        LimpiarCampos(_producto);
        activarBotones(true);
        txtNombre.requestFocus();
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
        LimpiarCampos(null);
        txtNombre.requestFocus();
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        activarBotones(false);
        LimpiarCampos(null);
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cboCategoria;
    private javax.swing.JComboBox<String> cboLaboratorio;
    private javax.swing.JComboBox<String> cboMarca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcFVen;
    private javax.swing.JTable tblDatos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
