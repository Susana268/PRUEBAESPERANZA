 
package controlador;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ProductoDAO;
import modelo.ProductoVO; 
import vista.FrmProductos;
import modelo.MarcaDAO;
import modelo.MarcaVO;

public class ControladorProducto implements ActionListener, MouseListener,ItemListener {
    FrmProductos vProd = new FrmProductos();
    ProductoDAO pDAO = new ProductoDAO();
    ProductoVO pVO = new ProductoVO();
    MarcaDAO mDAO = new MarcaDAO();
    MarcaVO mVO = new MarcaVO();
    
     public ControladorProducto(FrmProductos vProd, ProductoDAO pDAO, ProductoVO pVO,MarcaDAO mDAO,MarcaVO mVO){
        this.vProd = vProd;
        this.pDAO = pDAO;
        this.pVO = pVO;
        this.vProd.btnIngresarP.addActionListener(this);
        this.vProd.btnActualizarP.addActionListener(this);
//        this.vProd.btnEliminarP.addActionListener(this);
        this.vProd.btnSalirP.addActionListener(this);
        this.vProd.btnLimpiarP.addActionListener(this);
        this.vProd.tblProductos.addMouseListener(this);
        LlenarTabla(vProd.tblProductos);
        llenaMarca();
        this.vProd.cbxMarcasP.addItemListener(this);
    }
    
    public void LlenarTabla(JTable tablaDatos){
        DefaultTableModel modeloTabla = new DefaultTableModel(){
        
        @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaDatos.setModel(modeloTabla);
        modeloTabla.addColumn("idProducto");
        modeloTabla.addColumn("Descripcio");
        modeloTabla.addColumn("Marca");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Estado");
        Object[] columna = new Object[6];
        int numRegistros = pDAO.consultaProducto().size();
        for (int i=0;i<numRegistros;i++){
            columna[0]=pDAO.consultaProducto().get(i).getIdProducto();
            columna[1]=pDAO.consultaProducto().get(i).getDescripcionProducto();
            columna[2]=pDAO.consultaProducto().get(i).getIdMarca();
            columna[3]=pDAO.consultaProducto().get(i).getCantidadProducto();
            columna[4]=pDAO.consultaProducto().get(i).getPrecioProducto();
            columna[5]=pDAO.consultaProducto().get(i).getEstadoProducto();
            modeloTabla.addRow(columna);
        }
    }
    
    public void limpliarRegistro(){ 
        vProd.txtIdProducto.setText("");
        vProd.txtDescripP.setText("");
        vProd.txtMarcaP.setText("");
        vProd.txtCantidadP.setText("");
        vProd.txtPrecioP.setText("");
        vProd.txtEstadoP.setText("");
        vProd.txtMarcaP.requestFocus();
    }
    
    public void insertarRegistro(){

        pVO.setDescripcionProducto(vProd.txtDescripP.getText());
        pVO.setCantidadProducto(Integer.parseInt(vProd.txtCantidadP.getText()));
        pVO.setPrecioProducto(Double.parseDouble(vProd.txtPrecioP.getText()));
        pVO.setEstadoProducto(Integer.parseInt(vProd.txtEstadoP.getText()));
        pVO.setIdMarca(Integer.parseInt(vProd.txtMarcaP.getText()));
        boolean respuesta = pDAO.insertarProducto(pVO);
        if (respuesta=false){
            JOptionPane.showMessageDialog(null,"Error al Insertar Producto");
            limpliarRegistro();
            LlenarTabla(vProd.tblProductos);
        }
    }
    
    public void modificarRegistro(){
        pVO.setIdProducto(Integer.parseInt(vProd.txtIdProducto.getText()));
        pVO.setDescripcionProducto(vProd.txtDescripP.getText());
        pVO.setCantidadProducto(Integer.parseInt(vProd.txtCantidadP.getText()));
        pVO.setPrecioProducto(Double.parseDouble(vProd.txtPrecioP.getText()));
        pVO.setEstadoProducto(Integer.parseInt(vProd.txtEstadoP.getText()));
        pVO.setIdMarca(Integer.parseInt(vProd.txtMarcaP.getText()));
        
        boolean respuesta = pDAO.actualizarProducto(pVO);
        if (respuesta=false){
            JOptionPane.showMessageDialog(null,"Error al Modificar Producto");
            limpliarRegistro();
            LlenarTabla(vProd.tblProductos);
        }
    }
    
    public void eliminarRegistro(){
        pVO.setIdProducto(Integer.parseInt(vProd.txtIdProducto.getText()));
        boolean respuesta=pDAO.eliminarProducto(pVO);
        if(respuesta=false){
            JOptionPane.showMessageDialog(null,"Error al Eliminar Producto");
            limpliarRegistro();
            LlenarTabla(vProd.tblProductos);
        }
     
    }
    
    public void mostrarRegistro(){
        pVO.setIdProducto(Integer.parseInt(vProd.txtIdProducto.getText()));
        pVO=pDAO.mostrarProducto(pVO);
        vProd.txtDescripP.setText(pVO.getDescripcionProducto());
        vProd.txtCantidadP.setText(String.valueOf(pVO.getCantidadProducto()));
        vProd.txtPrecioP.setText(String.valueOf(pVO.getPrecioProducto()));
        vProd.txtEstadoP.setText(String.valueOf(pVO.getEstadoProducto()));
        vProd.txtMarcaP.setText(String.valueOf(pVO.getIdMarca()));
        vProd.txtMarcaP.requestFocus();     
    }
    
    public void llenaMarca(){
        MarcaDAO mDAO = new MarcaDAO();
        ArrayList<MarcaVO> info = mDAO.consultarMarca();
        vProd.cbxMarcasP.removeAllItems();
        for(int i=0;i<info.size();i++){ 
            
         vProd.cbxMarcasP.addItem(new MarcaVO(info.get(i).getIdMarca(),
                 info.get(i).getNombreMarca()) );
         
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==vProd.btnIngresarP){
              
            if (!vProd.txtDescripP.getText().equals("") && 
                !vProd.txtMarcaP.getText().equals("") &&
                !vProd.txtCantidadP.getText().equals("") &&
                !vProd.txtEstadoP.getText().equals("")  ){
                if(vProd.txtEstadoP.getText().equals("0")||
                   vProd.txtEstadoP.getText().equals("1")){
                    insertarRegistro();
                    LlenarTabla(vProd.tblProductos);
                    limpliarRegistro();     
                }else {
                    JOptionPane.showMessageDialog(null,"Valor de Estado invalido");
                }
                    
            }else {
              JOptionPane.showMessageDialog(null,"Ingresar informacion en todos los campos");
            }
             
        }
        if (e.getSource()==vProd.btnActualizarP){
            if (!vProd.txtDescripP.getText().equals("") && 
                !vProd.txtMarcaP.getText().equals("") &&
                !vProd.txtCantidadP.getText().equals("") &&
                !vProd.txtEstadoP.getText().equals("")  ){
                if(vProd.txtEstadoP.getText().equals("0")||
                   vProd.txtEstadoP.getText().equals("1")){
                    modificarRegistro();
                    LlenarTabla(vProd.tblProductos);
                    limpliarRegistro();   
                }else{
                    JOptionPane.showMessageDialog(null,"Valor de Estado invalido");
                }
            }else {
              JOptionPane.showMessageDialog(null,"Ingresar informacion en todos los campos");
            }
            
            
        }
//        if (e.getSource()==vProd.btnEliminarP){
//           eliminarRegistro();
//            LlenarTabla(vProd.tblProductos);
//            limpliarRegistro();    
//        }
 //     if (e.getSource()==vProd.btnConsulrarP){
 //         mostrarRegistro();
 //     }
        if (e.getSource()==vProd.btnLimpiarP){
            limpliarRegistro();
        }
        if (e.getSource()==vProd.btnSalirP){
            vProd.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==vProd.tblProductos){
            vProd.txtIdProducto.setText(vProd.tblProductos.getValueAt(vProd.tblProductos.getSelectedRow(),0).toString());
            vProd.txtDescripP.setText(vProd.tblProductos.getValueAt(vProd.tblProductos.getSelectedRow(),1).toString());
            vProd.txtMarcaP.setText(vProd.tblProductos.getValueAt(vProd.tblProductos.getSelectedRow(),2).toString());
            vProd.txtCantidadP.setText(vProd.tblProductos.getValueAt(vProd.tblProductos.getSelectedRow(),3).toString());
            vProd.txtPrecioP.setText(vProd.tblProductos.getValueAt(vProd.tblProductos.getSelectedRow(),4).toString());
            vProd.txtEstadoP.setText(vProd.tblProductos.getValueAt(vProd.tblProductos.getSelectedRow(),5).toString()); 
            
        }
    }
    
    

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        System.out.println("Double click");
        if(e.getStateChange()==ItemEvent.SELECTED){
            int id = this.vProd.cbxMarcasP.getItemAt(vProd.cbxMarcasP.getSelectedIndex()).getIdMarca();
            this.vProd.txtMarcaP.setText(String.valueOf(id));
        }
    
    
    
    }

    
}
