
package modelo;

import conexion.Conector;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductoDAO implements ConsultasProducto{
 
    @Override
    public boolean insertarProducto(ProductoVO p) {
        Conector c = new Conector();
        try{
            c.conectar();
            String query = "INSERT INTO producto (descripcion_producto, "+
                           "cantidad_producto, "+
                           "precio_producto, "+
                           "estado_producto, "+
                           "id_marca_fk ) "+
                           "VALUES ( '"+p.getDescripcionProducto()+"', "+
                                        p.getCantidadProducto()+", "+
                                        p.getPrecioProducto()+", "+
                                        p.getEstadoProducto()+", "+
                                        p.getIdMarca()+")";
            c.consultasMultiples(query);
        }catch (Exception e){
            System.err.println("Error(InsertProducto):"+e.getMessage());
            c.desconectar();
            return false;
        }
        c.desconectar();
        return true;
    }

    @Override
    public ArrayList<ProductoVO> consultaProducto() {
        Conector c = new Conector();
        ArrayList<ProductoVO> info = new ArrayList<>();
        try{
            c.conectar();
            String query="SELECT  id_producto,"+
                        "descripcion_producto, "+
                        "cantidad_producto, "+
                        "precio_producto, "+
                        "estado_producto, "+
                        "id_marca_fk "+
                        "from producto";
            ResultSet rs=c.consultaDatos(query);
            while(rs.next()){
                ProductoVO pvo = new ProductoVO();
                pvo.setIdProducto(rs.getInt(1));
                pvo.setDescripcionProducto(rs.getString(2));
                pvo.setCantidadProducto(rs.getInt(3));
                pvo.setPrecioProducto(rs.getDouble(4));
                pvo.setEstadoProducto(rs.getInt(5));
                pvo.setIdMarca(rs.getInt(6));
                info.add(pvo);
            }
            c.desconectar();
        }catch(Exception e){
            System.err.println("Error(MostrarProducto): "+e.getMessage());
            c.desconectar();
        }
        return info;
    }

    @Override
    public boolean actualizarProducto(ProductoVO p) {
        Conector c = new Conector();
        try{
            c.conectar();
            String query="UPDATE producto "+
                    "set descripcion_producto= '"+p.getDescripcionProducto()+"',"+
                    "cantidad_producto= "+p.getCantidadProducto()+","+
                    "precio_producto= "+p.getPrecioProducto()+","+
                    "estado_producto= "+p.getEstadoProducto()+","+
                    "id_marca_fk= "+p.getIdMarca()+" "+
                    "WHERE id_producto= "+p.getIdProducto();
            c.consultasMultiples(query);
        }catch (Exception e){
            System.out.println("Error(ActualizarProducto"+e.getMessage());
            c.desconectar();    
        }
        c.desconectar();
        return true;
    }
    @Override
    public boolean eliminarProducto(ProductoVO p) {
        Conector c=new Conector();
        try{
            c.conectar();
            String query="DELETE from producto WHERE id_producto= "+p.getIdProducto();
            c.consultasMultiples(query);
        }catch (Exception e){
            System.out.println("Error (EliminarProducto"+e.getMessage());
            c.desconectar();
        } 
        c.desconectar();
        return true;
    }

    @Override
    public ProductoVO mostrarProducto(ProductoVO p) {
        Conector c = new Conector();
        ProductoVO pvo = new ProductoVO();
        try{
            c.conectar();
            String query="SELECT  id_producto,"+
                        "descripcion_producto, "+
                        "cantidad_producto, "+
                        "precio_producto, "+
                        "estado_producto, "+
                        "id_marca_fk "+
                        "from producto " +
                        "WHERE id_producto= "+p.getIdProducto();
            ResultSet rs=c.consultaDatos(query);
            rs.next(); 
            pvo.setIdProducto(rs.getInt(1));
            pvo.setDescripcionProducto(rs.getString(2));
            pvo.setCantidadProducto(rs.getInt(3));
            pvo.setPrecioProducto(rs.getDouble(4));
            pvo.setEstadoProducto(rs.getInt(5));
            pvo.setIdMarca(rs.getInt(6)); 
            c.desconectar();
        }catch(Exception e){
            System.err.println("Error(MostrarProducto): "+e.getMessage());
            c.desconectar();
        }
        return pvo;
        
     }

  
 
     
}




