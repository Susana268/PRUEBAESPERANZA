
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.UsuarioDAO;
import modelo.UsuarioVO;
import vista.FrmUsuarios;

public class ControladorUsuario implements ActionListener, MouseListener, WindowListener{
    FrmUsuarios vUs = new FrmUsuarios();
    UsuarioVO uvo = new UsuarioVO();
    UsuarioDAO udao = new UsuarioDAO(); 

    public ControladorUsuario(FrmUsuarios vUs, UsuarioVO uvo, UsuarioDAO udao) {
        this.vUs = vUs;
        this.uvo = uvo;
        this.udao = udao;        
        this.vUs.btnIngresarU.addActionListener(this);
        this.vUs.btnActualizarU.addActionListener(this); 
        this.vUs.btnSalirU.addActionListener(this);   
        this.vUs.tblUsuarios.addMouseListener(this);  
        this.vUs.addWindowListener(this);
        
        
        }
    //esto es lo que se muestra en la tabla
    public void mostTabUsuarios(JTable tblUsuarios){
    DefaultTableModel m = new DefaultTableModel();   
     m.setColumnCount(0);
     m.addColumn("Id usuario");
     m.addColumn("Nombre Usuario");
     m.addColumn("Apellido Usuario");
     m.addColumn("Códico Usuario");
     m.addColumn("Clave Usuario");
     m.addColumn("Id Tipo Usuario");
     m.addColumn("Estado Usuario");
    
     for(UsuarioVO uvo : udao.consultarUsuario()){
     m.addRow(new Object []{uvo.getIdUsuario(),uvo.getNombreUsuario(),
     uvo.getApellidoUsuario(), uvo.getCodigoUsuario(), uvo.getClaveUsuario(),
     uvo.getIdTipoUsuario(), uvo.getEstadoUsuario()
     });
     }
     
          
    vUs.tblUsuarios.setModel(m);   
    //asignar medidas columnas
   
    
    }
         
    private void ingresarUsuario(){
        uvo.setNombreUsuario(vUs.txtNombreU.getText());
        uvo.setApellidoUsuario(vUs.txtApellidoU.getText());
        uvo.setCodigoUsuario(vUs.txtUsuarioU.getText());
        uvo.setClaveUsuario(vUs.txtPasswordU.getText());
        uvo.setIdTipoUsuario(Integer.parseInt(this.vUs.txtTipoU.getText()));
        uvo.setEstadoUsuario(Integer.parseInt(this.vUs.txtEstadoU.getText()));       
     //limpiar ventana
        if(udao.insertarUsuario(uvo) == true){
        vUs.jopMensajeU.showMessageDialog(vUs, "Usuario ingresado correctamente");
        this.vUs.txtNombreU.setText("");
        this.vUs.txtApellidoU.setText("");
        this.vUs.txtUsuarioU.setText("");
        this.vUs.txtPasswordU.setText("");
        this.vUs.txtTipoU.setText("");
        this.vUs.txtEstadoU.setText(""); 
        }else{
        vUs.jopMensajeU.showMessageDialog(vUs, "No se ha registrado usuario");
        this.vUs.txtNombreU.setText("");
        this.vUs.txtApellidoU.setText("");
        this.vUs.txtUsuarioU.setText("");
        this.vUs.txtPasswordU.setText("");
        this.vUs.txtTipoU.setText("");
        this.vUs.txtEstadoU.setText("");
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vUs.btnIngresarU){
            if(!this.vUs.txtNombreU.getText().equals("")&&
               !this.vUs.txtApellidoU.getText().equals("")&&
               !this.vUs.txtUsuarioU.getText().equals("")&&
               !this.vUs.txtPasswordU.getText().equals("")&&
               !this.vUs.txtTipoU.getText().equals("")&&
               !this.vUs.txtEstadoU.getText().equals("")) {
            this.ingresarUsuario();
            }else{
            this.vUs.jopMensajeU.showMessageDialog(vUs, "Debe llenar todos los campos");
            }
            
        }
        if(e.getSource() == vUs.btnSalirU){
        vUs.dispose();
        }
        
    }

    
    @Override
    public void mouseClicked(MouseEvent e) {
       
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
    public void windowOpened(WindowEvent e) {
         mostTabUsuarios(vUs.tblUsuarios);
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
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }
    
    
}
