
package modelo;

import conexion.Conector;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MarcaDAO implements ConsultasMarca{

    @Override
    public boolean insertarMarca(MarcaVO m) {
        Conector c = new Conector();
        try{
            c.conectar();
            String query = "INSERT INTO marca (nombre_marca, "+
                            "estado_marca ) "+
                            "VALUES ( '"+m.getNombreMarca()+"', "+
                                         m.getEstadoMarca()+")";
            c.consultasMultiples(query);
        }catch (Exception e){
            System.err.println("Error(InsertMarca):"+e.getMessage());
            c.desconectar();
            return false;
        }
        c.desconectar();
        return true;
     }

    @Override
    public ArrayList<MarcaVO> consultarMarca() {
        Conector c = new Conector();
        ArrayList<MarcaVO> info = new ArrayList<>();
        try{
            c.conectar();
            String query="SELECT id_marca,"+
                         "nombre_marca,"+
                         "estado_marca "+
                         "from marca";
            ResultSet rs=c.consultaDatos(query);
            while(rs.next()){
                MarcaVO mvo = new MarcaVO();
                mvo.setIdMarca(rs.getInt(1));
                mvo.setNombreMarca(rs.getString(2));
                mvo.setEstadoMarca(rs.getInt(3));
                info.add(mvo);
        }
            c.desconectar();
        }catch (Exception e){
            System.err.println("Error(MostrarMarca): "+e.getMessage());
            c.desconectar();
        }
        return info;
    }

    @Override
    public boolean actualizarMarca(MarcaVO m) {
        Conector c = new Conector();
        try{
            c.conectar();
            String query = "UPDATE marca "+
                            "set nombre_marca= '"+m.getNombreMarca()+"',"+
                                "estado_marca= "+m.getEstadoMarca()+" "+
                                "Where id_marca= "+m.getIdMarca();
            c.consultasMultiples(query);
        }catch (Exception e){
            System.out.println("Error(ActualizarMarca"+e.getMessage());
            c.desconectar();
        }
        c.desconectar();
        return true;
    }

    @Override
    public boolean eliminarMarca(MarcaVO m) {
        Conector c=new Conector();
        try{
            c.conectar();
            String query="DELETE from marca where id_marca= "+m.getIdMarca();
            c.consultasMultiples(query);
        }catch (Exception e){
            System.out.println("Error (EliminarMarca"+e.getMessage());
            c.desconectar();
        }
        c.desconectar();
        return true;
     } 
}
