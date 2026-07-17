package presentacion;

import java.awt.event.ItemEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.L_Cliente;
import logica.L_Producto;
import logica.L_TipoDocumentoVenta;
import logica.L_Venta;
import logica.L_VentaDetalle;
import modelo.Cliente;
import modelo.EstadoOperacion;
import modelo.Producto;
import modelo.Respuesta;
import modelo.TipoDocumento;
import modelo.TipoDocumentoVenta;
import modelo.Venta;
import modelo.VentaDetalle;
import util.cboUtil;

/**
 *
 * @author Migue
 */
public class FrmVentaGenerar extends javax.swing.JInternalFrame {
    private DefaultTableModel modelo;
    private DefaultTableModel modeloDetalle;
    private List<Producto> lista;
    private List<Producto> listaFiltro;
    private List<VentaDetalle> listaDetalle;
    private List<Cliente> listaCliente;
    private L_VentaDetalle objVDetalle;
    private List<TipoDocumentoVenta> listaDVenta;
    private L_Producto obj;
    private L_Venta objVenta;
    private Producto p;
    private int ID=0;
    private FrmVentas frmV;
        
    public FrmVentaGenerar(Venta venta,FrmVentas _v) {
        initComponents();
        frmV= _v;
        obj = new L_Producto();
        objVenta = new L_Venta();
        objVDetalle = new L_VentaDetalle();
        
        listaCliente = new L_Cliente().listar().getDatos();
        GenerarItemInicial();
        
        listaDVenta=L_TipoDocumentoVenta.getInstancia().obtenerListar();
        cboUtil.llenarCombo(cboTDocumentoVenta,listaDVenta );
        cargarModeloTabla();
        cargarTablaProductos();
        asignarEventoTabla();
        
        cargarModeloTablaDetalle();
        setHabilitarCajas();
        
        if(venta ==null){
            listaDetalle= new ArrayList<>();
            String fechaHora= LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            txtFecha.setText(fechaHora);
            txtTotal.setText("0");
            CambiarTDocVenta();
            
        }else{
            CargarDatosVenta(venta);
        }
        cargarDatosModeloDetalle();
                
        setClosable(true);      // Permite cerrar
    setIconifiable(true);   // Minimizar
    setMaximizable(true);   // Maximizar
    setResizable(true);     // Redimensionar
    
    }
    
    void CargarDatosVenta(Venta venta){
        listaDetalle= venta.getVentaDetalle();
        txtCorrelativo.setText(venta.getCorrelativo()+"");
        txtSerie.setText(venta.getSerie());
        txtTotal.setText(venta.getTotal().toString());
        txtFecha.setText(venta.getFechaCompleta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        TipoDocumentoVenta tdv = cboUtil.filtrarItem(listaDVenta, o->o.getTipoDocumentoVenta_Id()== venta.getTipoDocumentoVenta().getTipoDocumentoVenta_Id());
        if(tdv!=null){cboTDocumentoVenta.setSelectedItem(tdv);}
        else{cboTDocumentoVenta.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, "Hubo un error al cargar el Tipo de documento de venta");
        }
        CambiarTDocVenta();
        
        Cliente c = cboUtil.filtrarItem(listaCliente, o->o.getCliente_Id()== venta.getCliente().getCliente_Id());
            if(c!=null){cboCliente.setSelectedItem(c);}else
            {cboCliente.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, "Hubo un error al cargar el cliente");
            }
            
        btnAgregar.setVisible(false);
        btnGuardar.setVisible(false);
        btnEliminar.setVisible(false);
        txtDetalle.setEnabled(false);
        txtCantidad.setEnabled(false);
        btnBuscar.setVisible(false);
        txtBuscar.setEnabled(false);
    }
    
    void GenerarItemInicial(){
        Cliente c = new Cliente();
        c.setCliente_Id(0);
        c.setNombre("Seleccione un cliente");
        TipoDocumento td = new TipoDocumento();
        td.setTipoDocumento_Id(4);
        c.setTipoDocumento(td);
        listaCliente.add(ID, c);
    }
    
    void CargarCliente(){
        TipoDocumentoVenta td = (TipoDocumentoVenta)cboTDocumentoVenta.getSelectedItem();
        List<Cliente> listaCFiltro;
        //factura 2
        //ruc - 4
        if(td.getTipoDocumentoVenta_Id()==2){
            listaCFiltro = cboUtil.filtrar(listaCliente, c-> c.getTipoDocumento().getTipoDocumento_Id()==4);
        }else{
            listaCFiltro = cboUtil.filtrar(listaCliente, c-> c.getTipoDocumento().getTipoDocumento_Id()!=4);
        }
        System.out.println(listaCliente.size());
        System.out.println(listaCFiltro.size());
        cboUtil.llenarCombo(cboCliente, listaCFiltro);
    }
    
    void setHabilitarCajas(){
        boolean b = false;
        txtCorrelativo.setEnabled(b);
        txtFecha.setEnabled(b);
        txtSerie.setEnabled(b);
        txtTotal.setEnabled(b);
    }
    
    void asignarEventoTabla(){
        tblProducto.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {

            int fila = tblProducto.getSelectedRow();

            if (fila != -1) {
                SeleccionarProducto(Integer.parseInt(tblProducto.getValueAt(fila, 0).toString()));
            }
        }
    });
    
    }
    
    void SeleccionarProducto(int id){
        p=cboUtil.filtrarItem(lista, pr -> pr.getProducto_Id()==id);
        txtDetalle.setText(p.getNombre());
        if(p.getStock()<=0){
            btnAgregar.setEnabled(false);
        }else{
            btnAgregar.setEnabled(true);
            txtCantidad.setText("1");
        }
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
        
        tblProducto.setModel(modelo);
        modelo.setRowCount(0); 
        
    }
    
    
    void cargarModeloTablaDetalle(){
        modeloDetalle= new DefaultTableModel();
        modeloDetalle.addColumn("Producto");
        modeloDetalle.addColumn("Cantidad");
        modeloDetalle.addColumn("Precio U.");
        modeloDetalle.addColumn("SubTotal");
       
        tblDatos.setModel(modeloDetalle);
        modeloDetalle.setRowCount(0); 
        
    }
    
    private void cargarTablaProductos() {
    Respuesta<List<Producto>> r = obj.listar();
    
    
    if (r.getEstado() == EstadoOperacion.EXITO) {
        lista = r.getDatos();
        listaFiltro=lista;
        cargarDatosModeloProductos();
    } else {
        JOptionPane.showMessageDialog(this, r.getMensaje());
    }
    }
    
    void cargarDatosModeloProductos(){
        modelo.setRowCount(0);
        
        for (Producto m : listaFiltro) {
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
    }
    
    void cargarDatosModeloDetalle(){
        modeloDetalle.setRowCount(0);
        
        for (VentaDetalle m : listaDetalle) {
            modeloDetalle.addRow(new Object[]{
                m.getProducto().getNombre(),
                m.getCantidad(),
                m.getPrecioUnitario(),
                m.getSubtotal()
                   
            });
        }
    }
    
    
    void CambiarTDocVenta(){
        
        TipoDocumentoVenta td = (TipoDocumentoVenta)cboTDocumentoVenta.getSelectedItem();
        System.out.println(""+td.getIndicador());
        if(td!=null){
            txtSerie.setText(td.getIndicador());
        }
        
        CargarCliente();
        
    }
    
    void BuscarProductos(){
        String buscar = txtBuscar.getText();
        listaFiltro= cboUtil.filtrar(lista,pr -> pr.getNombre().toUpperCase().contains(buscar.toUpperCase()));
        cargarDatosModeloProductos();
    
    }
    
    void calcularTotal(){
        BigDecimal total = listaDetalle.stream()
        .map(VentaDetalle::getSubtotal)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
        txtTotal.setText(total.toString());
    
    }
    
    
    private void Guardar(){
        Venta v = new Venta();
        v.setSerie(txtSerie.getText());
        v.setTipoDocumentoVenta((TipoDocumentoVenta)cboTDocumentoVenta.getSelectedItem());
        v.setCliente((Cliente)cboCliente.getSelectedItem());
        v.setTotal(new BigDecimal(txtTotal.getText()));
        v.setVentaDetalle(listaDetalle);
        
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:m");
        LocalDateTime fechaCompleta = LocalDateTime.parse(txtFecha.getText(), formateador);
        v.setFechaCompleta(fechaCompleta);

        Respuesta r= new Respuesta();
        
        if(v.getVenta_Id()==0){
            r = objVenta.guardar(v);
        }else{
            r = objVenta.actualizar(v);
        }
        if(r.esCorrecto()){
            for (VentaDetalle item : listaDetalle) {
                item.setVenta_Id(r.getId());
                objVDetalle.guardar(item);
                Producto p = item.getProducto();
                p.setStock(item.getCantidad()*-1);
                obj.actualizarSTOCK(p);
            }
        }
        
        JOptionPane.showMessageDialog(null, r.getMensaje());
        if(r.esCorrecto()){
            
            
            try{
             frmV.Listar();
             this.setClosed(true);
            }catch(PropertyVetoException e){
                System.out.println(e.getMessage());
            }
            
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtDetalle = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtCorrelativo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cboTDocumentoVenta = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cboCliente = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblDatos);

        tblProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblProducto);

        jLabel1.setText("Buscar:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(this::btnAgregarActionPerformed);

        jLabel2.setText("Cantidad:");

        txtCantidad.setText("0");

        jLabel3.setText("Producto:");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Serie:");

        jLabel5.setText("Documento:");

        cboTDocumentoVenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTDocumentoVenta.addItemListener(this::cboTDocumentoVentaItemStateChanged);

        jLabel6.setText("Correlativo:");

        jLabel7.setText("Fecha:");

        jLabel8.setText("Cliente:");

        cboCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboCliente.setMinimumSize(new java.awt.Dimension(172, 22));
        cboCliente.setPreferredSize(new java.awt.Dimension(172, 22));

        jLabel9.setText("Total:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboTDocumentoVenta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCorrelativo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCorrelativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cboTDocumentoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnBuscar))
                                .addComponent(jScrollPane2)
                                .addComponent(jScrollPane1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(297, 297, 297)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEliminar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboTDocumentoVentaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTDocumentoVentaItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED){
            CambiarTDocVenta();
        }
        
    }//GEN-LAST:event_cboTDocumentoVentaItemStateChanged

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        BuscarProductos();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        VentaDetalle _detalle = new VentaDetalle();
        int cantidad = Integer.parseInt(txtCantidad.getText());
        if(cantidad>p.getStock()){
            JOptionPane.showMessageDialog(null, "No hay stock suficiente para realizar la venta del producto");
            return;
        
        }
        _detalle.setProducto(p);
        
        BigDecimal punitario = BigDecimal.valueOf(p.getPrecio());
        BigDecimal subtotal = punitario.multiply(new BigDecimal(cantidad));
        _detalle.setCantidad(cantidad);
        _detalle.setPrecioUnitario(punitario);
        _detalle.setSubtotal(subtotal);
        
        listaDetalle.add(_detalle);
        cargarDatosModeloDetalle();
        calcularTotal();
        
        
        
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        try{
             this.setClosed(true);
            }catch(PropertyVetoException e){
                System.out.println(e.getMessage());
            }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaVisual = tblDatos.getSelectedRow();
        if (filaVisual == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.");
            return;
        }
        
        int filaModelo = tblDatos.convertRowIndexToModel(filaVisual);
            listaDetalle.remove(filaModelo);
            //DefaultTableModel mdl = (DefaultTableModel) tblDatos.getModel();
            modeloDetalle.removeRow(filaModelo);
            calcularTotal();
        
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cboCliente;
    private javax.swing.JComboBox<String> cboTDocumentoVenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblDatos;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCorrelativo;
    private javax.swing.JTextField txtDetalle;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtSerie;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
