
package modelo;

import conexion.Conector;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAO implements ConsultasUsuario{
 
    @Override
    public boolean insertarUsuario(UsuarioVO u) {
        Conector c = new Conector();
        try{
            c.conectar();
            String query = "INSERT INTO USUARIO (nombre_usuario, "+
                           "apellido_usuario, "+
                           "codigo_usuario, "+
                           "clave_usuario, "+
                           "estado_usuario, "+
                           "id_tipo_usuario_fk ) "+
                           "VALUES ('"+u.getNombreUsuario()+"', '"+
                                        u.getApellidoUsuario()+"', '"+
                                        u.getCodigoUsuario()+"', '"+
                                        u.getClaveUsuario()+"', "+
                                        u.getEstadoUsuario()+", "+
                                        u.getIdTipoUsuario()+")";
        c.consultasMultiples(query);
        }catch (Exception e){
            System.err.println("Error(InsertUsuario):"+e.getMessage());
            c.desconectar();
            return false;
        }
        c.desconectar();
        return true;
    }

    @Override
    public ArrayList<UsuarioVO> consultarUsuario() {
        Conector c = new Conector();
        ArrayList<UsuarioVO> info = new ArrayList<>();
        try{ 
            c.conectar();
            String query="SELECT id_usuario,"+
                         "nombre_usuario,"+
                         "apellido_usuario,"+
                         "codigo_usuario,"+
                         "clave_usuario,"+
                         "estado_usuario, "+
                         "id_tipo_usuario_fk "+
                         "from usuario";
            ResultSet rs = c.consultaDatos(query);
            while (rs.next()){
                UsuarioVO uvo = new UsuarioVO();
                uvo.setIdUsuario(rs.getInt(1));
                uvo.setNombreUsuario(rs.getString(2));
                uvo.setApellidoUsuario(rs.getString(3));
                uvo.setCodigoUsuario(rs.getString(4));
                uvo.setClaveUsuario(rs.getString(5));
                uvo.setEstadoUsuario(rs.getInt(6));
                uvo.setIdTipoUsuario(rs.getInt(7));
                info.add(uvo);
            }
        }catch (Exception e){
            System.err.println("Error(MostrarUsuario): "+e.getMessage());
            c.desconectar();
        }
        return info;
    }

    @Override
    public boolean actualizarUsuario(UsuarioVO u) {
        Conector c = new Conector();
        try{
            c.conectar();
            String query="UPDATE usuario "+
                        "set nombre_usuario= '"+u.getNombreUsuario()+"',"+
                        "apellido_usuario= '"+u.getApellidoUsuario()+"',"+
                        "codigo_usuario= '"+u.getCodigoUsuario()+"',"+
                        "clave_usuario= '"+u.getClaveUsuario()+"',"+
                        "estado_usuario= "+u.getEstadoUsuario()+","+
                        "id_tipo_usuario_fk= "+u.getIdTipoUsuario()+ " "+
                        "WHERE id_usuario= "+u.getIdUsuario();
            c.consultasMultiples(query);
        }catch (Exception e){
            System.out.println("Error(ActualizarUsuario"+e.getMessage());
            c.desconectar();
        }
        c.desconectar();
        return true;
     }

    @Override
    public boolean eliminarUsuario(UsuarioVO u) {
        Conector c=new Conector();
        try{
            c.conectar();
            String query="DELETE from usuario where id_usuario="+u.getIdUsuario();
            c.consultasMultiples(query);
        }catch (Exception e){
            System.out.println("Error (EliminarUsuario"+e.getMessage());
            c.desconectar();
        }
        c.desconectar();
        return true;
    }

    public Object ConsultasUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
