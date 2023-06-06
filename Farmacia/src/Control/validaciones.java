package Control;

/**
 *
 * @author Usuario
 */
public class validaciones {

    public int validarCantidad(String cantidad) {

        if (cantidad.matches("-?\\d*")) {
            int numeroInt;
            //error
            try {
                numeroInt = Integer.parseInt(cantidad);
                
                if(numeroInt>0){
                    return 1;
                }else{
                    //es negativo o 0
                    return 2;
                }
                
            } catch (NumberFormatException e) {
               
                return 3;
            }

        } else {
            //tiene caracteres 
            return 3;
        }

    }

    public int validarNombreMedicamento(String nombre) {
        if (nombre.matches("[A-Za-zÑñáéíúóÁÉÍÓÚ]{0,25}")) {

            //correcto
            return 1;

        } else {
            //contiene numeros
            return 2;
        }

    }
}
