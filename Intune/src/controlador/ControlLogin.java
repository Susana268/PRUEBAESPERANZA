package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.UsuarioDAO;
import modelo.UsuarioVO;
import vista.FrmLogin;
import vista.FrmMenuP;

public class ControlLogin implements ActionListener {

    FrmLogin vLog = new FrmLogin();
    FrmMenuP vMnup = new FrmMenuP();
    UsuarioDAO uDao = new UsuarioDAO();
    UsuarioVO uVo = new UsuarioVO();

    public ControlLogin(FrmLogin vLog, FrmMenuP vMnup, UsuarioDAO uDao, UsuarioVO uVo) {
        this.vLog = vLog;
        this.vMnup = vMnup;
        this.uDao = uDao;
        this.uVo = uVo;

        this.vLog.btnIngresar.addActionListener(this);
    }

    //METODO PARA VALIDAR LAS CREDENCIALES
    public boolean validarLogin() {
        //VARIABLE PARA EL RETORNO E INDICAR QUE SÍ ENCONTRÓ DATOS EN LA BD
        boolean validar = false;

        if (!vLog.txtUsuario.getText().equals("") && !String.valueOf(vLog.txtPassword.getPassword()).equals("")) {
            //FOR EACH para buscar usuarios registrados en BD
            for (UsuarioVO uvo : uDao.consultarUsuario()) {
                if (vLog.txtUsuario.getText().equals(uvo.getCodigoUsuario())
                        && String.valueOf(vLog.txtPassword.getPassword()).equals(uvo.getClaveUsuario())) {
                    System.out.println("Se encontraron datos");
                    validar = true;

                    //VALIDANDO SI ESTÁ ACTIVO O INACTIVO
                    if (uvo.getEstadoUsuario() == 1) {
                        //VALIDANDO SI ES ADMIN o USUARIO OPERADOR
                        if (uvo.getIdTipoUsuario() == 0) {

                            this.abrirVentanaAdmin();
                            this.vMnup.jopMensajeM.showMessageDialog(vMnup, "Bienvenido " + uvo.getNombreUsuario() + " - Administrador");
                        } else {

                            this.abrirVentanaOperador();
                            this.vMnup.jopMensajeM.showMessageDialog(vMnup, "Bienvenido " + uvo.getNombreUsuario() + " - Operador");
                        }
                    }else{
                        this.vLog.jopMensajeLog.showMessageDialog(vMnup, "El usuario Inactivo");
                        this.limpiarLogin();
                    }

                } else {
                    System.out.println("No se encontraron datos");
                }
            }
            System.out.println("Todo bien, Campos llenos");
        } else {
            System.out.println("Llenar todos los campos");
        }
        return validar;
    }

    //MÉTODO PARA ABRIR EL MENU
    public void abrirMenuPrincipal() {
        if (this.validarLogin() == true) {
            this.vMnup.setVisible(true);
            this.vMnup.setResizable(false);
            this.vMnup.setLocationRelativeTo(vLog);

        } else {
            vLog.jopMensajeLog.showMessageDialog(vMnup, "No se encontraron datos");
        }
    }

    public void abrirVentanaAdmin() {
        this.vMnup.setVisible(true);
        this.vMnup.setResizable(false);
        this.vMnup.setLocationRelativeTo(vLog);
        this.vMnup.btnUsuarios.setEnabled(true);
        this.limpiarLogin();
    }

    public void abrirVentanaOperador() {
        this.vMnup.setVisible(true);
        this.vMnup.setResizable(false);
        this.vMnup.setLocationRelativeTo(vLog);
        this.vMnup.btnUsuarios.setEnabled(false);
        this.limpiarLogin();
    }

    public void limpiarLogin() {
        this.vLog.txtPassword.setText("");
        this.vLog.txtUsuario.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vLog.btnIngresar) {
            //this.abrirMenuPrincipal();
            this.validarLogin();
        }

    }

}
