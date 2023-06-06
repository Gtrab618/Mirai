
package Control;

import Modelo.ModeloRegistro;
import Vista.VistaRegistro;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author Usuario
 */
public class ControlRegistro {

    private VistaRegistro vReg;
    private ModeloRegistro mReg;
    private validaciones vali;
    private List<ModeloRegistro> listRegistros = new ArrayList<>();

    public ControlRegistro(VistaRegistro vReg, ModeloRegistro mReg, validaciones vali) {

        this.vReg = vReg;
        this.mReg = mReg;
        this.vali = vali;
        referenciarObjetos();
    }

    public void iniciarControl() {
        vReg.getBtnPnlRegOk().addActionListener(l -> validarCampo());
        vReg.getBtnPnlPedCan().addActionListener(l->cancelar());
        vReg.getBtnPnlPedEnv().addActionListener(l->enviar());
    }

    private void referenciarObjetos() {
        desactivarAlertas();
        //seleccionar algun radiobutton
        vReg.getRdbCem().setSelected(true);
        vReg.getPnlPedido().setVisible(false);

    }

    private void validarCampo() {
        desactivarAlertas();
        // vali.validarCantidad(vReg.getTxtCantidadMedi().getText());
        int bandera = 0;
        //ATRIBUTOS 
        String nombreProduc = "";
        String cantidadProduc = "";
        String tipoMedi = "";
        String distribuidor = "";
        String sucursal = "";
        int intCanProdudc = 0;

        nombreProduc = vReg.getTxtnombreMedi().getText().trim();
        cantidadProduc = vReg.getTxtCantidadMedi().getText().trim();

        if (!nombreProduc.equals("")) {
            //tiene datos
            if (vali.validarNombreMedicamento(nombreProduc) == 2) {
                vReg.getLblAlertNomMedNum().setVisible(true);
                bandera = bandera + 1;
            }

        } else {
            //esta vacio
            vReg.getLblAlertNomMedVac().setVisible(true);
            bandera = bandera + 1;
        }

        if (!cantidadProduc.equals("")) {
            //tiene datos

            switch (vali.validarCantidad(cantidadProduc)) {

                case 2:
                    //error longitud

                    vReg.getLblAlertCantProdNeg().setVisible(true);
                    bandera = bandera + 1;
                    break;
                case 3:
                    // error de formato

                    vReg.getLblAlertCantProdLet().setVisible(true);
                    bandera = bandera + 1;
                    break;

            }

        } else {
            vReg.getLblAlertCantProdVac().setVisible(true);
            bandera = bandera + 1;
        }

        //ver si los checbox estan deseleccionados
        if (!vReg.getChcSecundario().isSelected() && !vReg.getChcPrincipal().isSelected()) {
            vReg.getLblAlertSucurVac().setVisible(true);
            bandera = bandera + 1;
        }

        if (bandera == 0) {
            //registrar
            tipoMedi = vReg.getCmbTipoMed().getSelectedItem().toString();

            //ver proveedor seleccionado
            if (vReg.getRdbCem().isSelected()) {
                distribuidor = vReg.getRdbCem().getText();
            } else if (vReg.getRdbCofar().isSelected()) {
                distribuidor = vReg.getRdbCofar().getText();
            } else {
                distribuidor = vReg.getRdbEmp().getText();
            }
            
            //parsear a int 
            try {
                intCanProdudc = Integer.parseInt(cantidadProduc);

            } catch (NumberFormatException e) {
                System.out.println("El string no es un número válido");

            }
            //Sucursal

            if (vReg.getChcPrincipal().isSelected()) {
                sucursal = "Para la farmacia PRINCIPAL situada en Calle de la Rosa n.28. \n";
            }
            if (vReg.getChcSecundario().isSelected()) {
                sucursal = sucursal + "Para la SECUNDARIA situada en Calle Alcazabilla n.3.";
            }
            ModeloRegistro reg = new ModeloRegistro();
            reg.setNombreMedicamento(nombreProduc);
            reg.setTipoMedicamento(tipoMedi);
            reg.setCantidadMedicamento(intCanProdudc);
            reg.setDistribuidor(distribuidor);
            reg.setSucursal(sucursal);
            listRegistros.add(reg);
            //mandar al siguiente panel
            vReg.getPnlRegistro().setVisible(false);
            vReg.getPnlPedido().setVisible(true);
            
            //
            
            vReg.getLblPnlPedidoTitle().setText("Pedido al distribuidor "+listRegistros.get(listRegistros.size()-1).getDistribuidor());
            vReg.getLblPnlPedidoCuerpo().setText(listRegistros.get(listRegistros.size()-1).getCantidadMedicamento()+" del " +listRegistros.get(listRegistros.size()-1).getTipoMedicamento()+" "+listRegistros.get(listRegistros.size()-1).getNombreMedicamento());
            vReg.getLblPnlPedidoPie().setText("<html>"+listRegistros.get(listRegistros.size()-1).getSucursal()+"<html>");
            vReg.getLblPedido().setText("Número de pedidos: "+(listRegistros.size()-1));
        }

    }

    public void cancelar(){
        vReg.getPnlRegistro().setVisible(true);
        vReg.getPnlPedido().setVisible(false);
        vReg.getTxtCantidadMedi().setText("");
        vReg.getTxtnombreMedi().setText("");
        vReg.getChcPrincipal().setSelected(false);
        vReg.getChcSecundario().setSelected(false);
        
        if(!listRegistros.isEmpty()){
            listRegistros.remove( listRegistros.size()- 1);
        }
    }
    
    public void reiniciar(){
        vReg.getPnlRegistro().setVisible(true);
        vReg.getPnlPedido().setVisible(false);
        vReg.getTxtCantidadMedi().setText("");
        vReg.getTxtnombreMedi().setText("");
        vReg.getChcPrincipal().setSelected(false);
        vReg.getChcSecundario().setSelected(false);
        
       
    }
    
    private void enviar(){
        JOptionPane.showMessageDialog(null, "Número de pedido: "+(listRegistros.size()));
        
    }
    
    private void desactivarAlertas() {
        vReg.getLblAlertSucurVac().setVisible(false);
        vReg.getLblAlertNomMedVac().setVisible(false);
        vReg.getLblAlertNomMedNum().setVisible(false);
        vReg.getLblAlertCantProdVac().setVisible(false);
        vReg.getLblAlertCantProdNeg().setVisible(false);
        vReg.getLblAlertCantProdLet().setVisible(false);

    }
}
