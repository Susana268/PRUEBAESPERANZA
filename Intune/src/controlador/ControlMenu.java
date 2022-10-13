package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.FrmMarcas;
import vista.FrmMenuP;
import vista.FrmProductos;
import vista.FrmUsuarios;

public class ControlMenu implements ActionListener {

    FrmUsuarios vUsua = new FrmUsuarios();
    FrmProductos vProd = new FrmProductos();
    FrmMarcas vMarc = new FrmMarcas();
    FrmMenuP vMnup = new FrmMenuP();

    public ControlMenu(FrmUsuarios vUsua, FrmProductos vProd, FrmMarcas vMarc, FrmMenuP vMnup) {
        this.vUsua = vUsua;
        this.vProd = vProd;
        this.vMarc = vMarc;
        this.vMnup = vMnup;

        this.vMnup.btnUsuarios.addActionListener(this);
        this.vMnup.btnProductos.addActionListener(this);
        this.vMnup.btnMarcas.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vMnup.btnUsuarios) {
            this.vUsua.setLocationRelativeTo(null);
            this.vUsua.setResizable(false);
            this.vUsua.setVisible(true);
        }
        if (e.getSource() == this.vMnup.btnProductos) {
            this.vProd.setLocationRelativeTo(null);
            this.vProd.setResizable(false);
            this.vProd.setVisible(true);
        }
        if (e.getSource() == this.vMnup.btnMarcas) {
            this.vMarc.setLocationRelativeTo(null);
            this.vMarc.setResizable(false);
            this.vMarc.setVisible(true);
        }

    }

}
