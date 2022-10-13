
package modelo;

public class MarcaVO {
    private int idMarca;
    private String nombreMarca;
    private int estadoMarca;

    public MarcaVO() {
    }
 
    public MarcaVO(int idMarca, String nombreMarca) {
        this.idMarca = idMarca;
        this.nombreMarca = nombreMarca;
     }
      public MarcaVO( String nombreMarca) { 
        this.nombreMarca = nombreMarca;
     }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public int getEstadoMarca() {
        return estadoMarca;
    }

    public void setEstadoMarca(int estadoMarca) {
        this.estadoMarca = estadoMarca;
    }
    
    public String toString(){
        return idMarca+" - "+nombreMarca;
    }

}
