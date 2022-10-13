package intune;

import conexion.Conector;
import controlador.ControlLogin;
import controlador.ControlMenu;
import controlador.ControladorMarcas;
import controlador.ControladorProducto;
import controlador.ControladorUsuario;
import modelo.MarcaDAO;
import modelo.MarcaVO;
import modelo.ProductoDAO;
import modelo.ProductoVO;
import modelo.TipoUsuarioVO;
import modelo.UsuarioDAO;
import modelo.UsuarioVO;
import vista.FrmLogin;
import vista.FrmMarcas;
import vista.FrmMenuP;
import vista.FrmProductos;
import vista.FrmUsuarios;

public class Intune {

    public static void main(String[] args) {

        Conector conector = new Conector();
        conector.conectar();
        //VENTANAS
        FrmLogin vLog = new FrmLogin();
        FrmMenuP vMnup = new FrmMenuP();
        FrmUsuarios vUsua = new FrmUsuarios();
        FrmProductos vProd = new FrmProductos();
        FrmMarcas vMarc = new FrmMarcas();
        
        //OBJETOS MODELO
        //OBJETOS DAO
        UsuarioDAO uDao = new UsuarioDAO();
        //TipoUsuarioDAO tUdAo = new TipoUsuarioDAO();
        MarcaDAO mDao = new MarcaDAO();
        ProductoDAO pDAO = new ProductoDAO();
        
        
        //OBJETOS VO
        UsuarioVO uVo = new UsuarioVO();
        TipoUsuarioVO tUvO = new TipoUsuarioVO();
        MarcaVO mVO = new MarcaVO();
        ProductoVO pVO = new ProductoVO();
        
        //CONTROLADORES
        ControlLogin cLog = new ControlLogin(vLog, vMnup, uDao, uVo);
        ControlMenu cMnup = new ControlMenu(vUsua, vProd, vMarc, vMnup);
        ControladorUsuario cUsua = new ControladorUsuario(vUsua, uVo, uDao);
        ControladorMarcas cMarca = new ControladorMarcas(mDao, mVO, vMarc);
        ControladorProducto cProd = new ControladorProducto(vProd, pDAO, pVO,mDao,mVO);
        
        
        //PROPIEDADES DE LAS VENTANAS
        vLog.setLocationRelativeTo(null);
        vLog.setVisible(true);
        vLog.setResizable(false);
        
        
        
    }
}
