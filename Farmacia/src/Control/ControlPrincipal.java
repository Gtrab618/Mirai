package Control;

import Modelo.ModeloRegistro;
import Vista.VistaPrincipal;
import Vista.VistaRegistro;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author Usuario
 */
public class ControlPrincipal {

    private VistaPrincipal vGes;
    private int r = 23, g = 32, b = 48;
    private VistaRegistro vReg = new VistaRegistro();
    private ModeloRegistro mReg = new ModeloRegistro();
    private validaciones vali = new validaciones();
    //creacion de los controles para cada jpanel
    private ControlRegistro cntReg= new ControlRegistro(vReg,mReg,vali);
   

    public ControlPrincipal(VistaPrincipal vGes) {

        this.vGes = vGes;
        vGes.setVisible(true);
        referenciarObjetos();
    }

    public void iniciarControl() {
        VisualizarDarkMode();
        evtCambiarPanel(vGes.getBtnTlbRegistro(), "Registrar");


    }
    
    private void referenciarObjetos(){
        cntReg.iniciarControl();
    }

    private void evtCambiarPanel(JButton panel, String name) {
        panel.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                VisualizarDarkMode();
                switch (name) {
                    case "Registrar":
                        cntReg.reiniciar();
                        vReg.setSize(890, 610);
                        vReg.setLocation(0, 0);
                        vGes.getPnlFondo().removeAll();
                        vGes.getPnlFondo().add(vReg, BorderLayout.CENTER);
                        vGes.revalidate();
                        vGes.getPnlFondo().repaint();

                        break;

                }

            }
        });
    }



    private void VisualizarDarkMode() {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FlatIntelliJLaf.setup();
                FlatLaf.updateUI();
                cambiarFondo(13, 71, 161);
                r = 13;
                g = 71;
                b = 161;

            }
        });

    }

    private void cambiarFondo(int r, int g, int b) {

        vGes.getPnlFondo().setBackground(new Color(r, g, b));
        //panel gestion

    }

}
