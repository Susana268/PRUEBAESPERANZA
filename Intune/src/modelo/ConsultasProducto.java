
package modelo;

import java.util.ArrayList;

public interface ConsultasProducto {
    public boolean insertarProducto(ProductoVO p);
    public ArrayList<ProductoVO> consultaProducto();
    public boolean actualizarProducto(ProductoVO p);
    public boolean eliminarProducto(ProductoVO p);
    public ProductoVO mostrarProducto(ProductoVO p);
}
