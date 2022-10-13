package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.Action;
import javax.swing.table.DefaultTableModel;
import modelo.MarcaDAO;
import modelo.MarcaVO;
import vista.FrmMarcas;

public class ControladorMarcas implements ActionListener, WindowListener, MouseListener{
    MarcaDAO mDAO = new MarcaDAO();
    MarcaVO mVO = new MarcaVO();
    FrmMarcas frmM = new FrmMarcas();
    
    public ControladorMarcas(MarcaDAO marcaDAO,MarcaVO marcaVO, FrmMarcas frmMarcas){
        this.mDAO = marcaDAO;
        this.mVO = marcaVO;
        this.frmM = frmMarcas;
        
        this.frmM.btnIngresarM.addActionListener(this);
        this.frmM.btnSalirM.addActionListener(this);
        this.frmM.btnActualizarM.addActionListener(this);
        this.frmM.addWindowListener(this);
        this.frmM.tblMarcas.addMouseListener(this);
    }
    
    private boolean verificarRepetidos(){
        boolean flagRepetido = false;
        String nombreMarca = this.frmM.txtNombreM.getText();
        for(MarcaVO mVo: mDAO.consultarMarca()){
            if(nombreMarca.equals(mVo.getNombreMarca())){
                flagRepetido = true;
            }
        }
        return flagRepetido;
    }
    
    private boolean verificarVacios(){
        boolean vacios = true;
        if(!(this.frmM.txtNombreM.getText().equals(""))||!(this.frmM.txtEstadoM.getText().equals(""))){
            vacios = false;
        }
        System.out.println(vacios);
        return vacios;
    }
    
    private void mostrarTablaMarcas(){
        DefaultTableModel dtm = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.setColumnCount(0);
        dtm.addColumn("ID Marca");
        dtm.addColumn("Nombre Marca");
        dtm.addColumn("Estado");
        
        for(MarcaVO mVo: mDAO.consultarMarca()){
            String estado = "";
            if(mVo.getEstadoMarca() == 1){
                estado = "Activo";
            }else{
                estado = "Desactivo";
            }
            dtm.addRow(new Object[]{mVo.getIdMarca(),mVo.getNombreMarca(),estado});
        }
        
        this.frmM.tblMarcas.setModel(dtm);
    }
    
    private void llenarDatos(){
        int row = this.frmM.tblMarcas.getSelectedRow();
        int estado = 0;
        this.frmM.txtNombreM.setText(String.valueOf(this.frmM.tblMarcas.getValueAt(row, 1)));
        this.mVO.setIdMarca(Integer.parseInt(String.valueOf(this.frmM.tblMarcas.getValueAt(row, 0))));
        if(String.valueOf(this.frmM.tblMarcas.getValueAt(row, 2)) == "Activo"){
            estado = 1;
        }
        this.frmM.txtEstadoM.setText(String.valueOf(estado));
    }
    
    private void modificarMarca(){
        if(!(verificarVacios())){
            try {
                int estado = Integer.parseInt(this.frmM.txtEstadoM.getText());
                        if((estado == 1)||(estado == 2)){
                            this.mVO.setNombreMarca(this.frmM.txtNombreM.getText());
                            this.mVO.setEstadoMarca(estado);
                            if(this.mDAO.actualizarMarca(mVO)){
                                this.frmM.jopMensajeM.showMessageDialog(frmM, "Se actualizaron los datos");
                                this.frmM.txtEstadoM.setText("");
                                this.frmM.txtNombreM.setText("");
                            }else{
                                this.frmM.jopMensajeM.showMessageDialog(frmM, "No se modificaron");
                            }
                        }else{
                            this.frmM.jopMensajeM.showMessageDialog(frmM, "Para el campo de estado unicamente ingrese\n 0) para Desactivo \n 1) para Activo");
                        }
            } catch (Exception e) {
                this.frmM.jopMensajeM.showMessageDialog(frmM, "Para el campo de estado ingrese 0 para Desactivo o 1 para Activo");
            }
        }else{
            this.frmM.jopMensajeM.showMessageDialog(frmM, "Llene todos los campos");
        }
    }
    
    public void insertarMarca(){
        if(!(verificarVacios())){
            if(!(verificarRepetidos())){
                try {
                    int estado = Integer.parseInt(this.frmM.txtEstadoM.getText());
                            if((estado == 1)||(estado == 2)){
                                this.mVO.setNombreMarca(this.frmM.txtNombreM.getText());
                                this.mVO.setEstadoMarca(estado);
                                if(this.mDAO.insertarMarca(mVO)){
                                    this.frmM.jopMensajeM.showMessageDialog(frmM, "Se insertaron los datos correctamente!");
                                    this.frmM.txtEstadoM.setText("");
                                    this.frmM.txtNombreM.setText("");
                                }else{
                                    this.frmM.jopMensajeM.showMessageDialog(frmM, "No se agregaron");
                                }
                            }else{
                                this.frmM.jopMensajeM.showMessageDialog(frmM, "Para el campo de estado unicamente ingrese\n 0) para Desactivo \n 1) para Activo");
                            }
                } catch (Exception e) {
                    this.frmM.jopMensajeM.showMessageDialog(frmM, "Para el campo de estado ingrese 0 para Desactivo o 1 para Activo");
                }
            }else{
                this.frmM.jopMensajeM.showMessageDialog(frmM, "Ya existe una marca con ese nombre");
            }
        }else{
            this.frmM.jopMensajeM.showMessageDialog(frmM, "Llene todos los campos");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.frmM.btnSalirM){
            this.frmM.dispose();
        }
        if(e.getSource() == this.frmM.btnIngresarM){
            insertarMarca();
        }
        if(e.getSource()== this.frmM.btnActualizarM){
            modificarMarca();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
        mostrarTablaMarcas();
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
            llenarDatos();
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
}
