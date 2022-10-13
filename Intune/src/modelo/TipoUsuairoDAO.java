
package modelo;
 
import conexion.Conector;
import java.sql.ResultSet;
import java.util.ArrayList; 

public class TipoUsuairoDAO implements ConsultasTipoUsuario{

    @Override
    public ArrayList<TipoUsuarioVO> consultarTipoUsuario() {
        Conector c = new Conector();
        ArrayList<TipoUsuarioVO> info = new ArrayList<>();
        try{
            c.conectar();
            String query="SELECT id_tipo_usuario,"+
                         "descripcion_tipo_usuario "+
                         "from tipo_usuario";
            ResultSet rs=c.consultaDatos(query);
            while(rs.next()){
                TipoUsuarioVO tvo = new TipoUsuarioVO();
                tvo.setIdTipoUsuario(rs.getInt(1));
                tvo.setDescripcionTipo(rs.getString(2));
                info.add(tvo);
            }
            c.desconectar();
        }catch(Exception e){
            System.err.println("Error(MostrarTipo): "+e.getMessage() );
            c.desconectar();
        }
        return info;
    }
    
}
