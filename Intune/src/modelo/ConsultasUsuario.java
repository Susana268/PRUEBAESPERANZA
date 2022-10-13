
package modelo;

import java.util.ArrayList;

public interface ConsultasUsuario {
  public boolean insertarUsuario(UsuarioVO u);
  public ArrayList<UsuarioVO> consultarUsuario();
  public boolean actualizarUsuario(UsuarioVO u);
  public boolean eliminarUsuario(UsuarioVO u);
}
