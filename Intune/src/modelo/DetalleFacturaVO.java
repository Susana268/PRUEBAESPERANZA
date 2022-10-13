
package modelo;


public class DetalleFacturaVO {
    private int idDetalleFactura;
    private int cantidadProducto;
    private double precioProductoUnitario;
    private double precioTotalProducto;
    private int idProducto;
    private int idFactura;

    public DetalleFacturaVO() {
    }

    public int getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(int idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public double getPrecioProductoUnitario() {
        return precioProductoUnitario;
    }

    public void setPrecioProductoUnitario(double precioProductoUnitario) {
        this.precioProductoUnitario = precioProductoUnitario;
    }

    public double getPrecioTotalProducto() {
        return precioTotalProducto;
    }

    public void setPrecioTotalProducto(double precioTotalProducto) {
        this.precioTotalProducto = precioTotalProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

}
