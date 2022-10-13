
package modelo;

import java.util.ArrayList;

public interface ConsultasMarca {
    public boolean insertarMarca(MarcaVO m);
    public ArrayList<MarcaVO> consultarMarca();
    public boolean actualizarMarca(MarcaVO m);
    public boolean eliminarMarca(MarcaVO m);
}
