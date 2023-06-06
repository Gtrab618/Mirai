/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import Control.ControlPrincipal;
import Control.validaciones;
import Modelo.ModeloRegistro;
import Vista.VistaPrincipal;
import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;

/**
 *
 * @author Usuario
 */
public class iniciar {
    
    public static void main(String[] args){
        
        VistaPrincipal vGes = new VistaPrincipal();
        ControlPrincipal cnt = new ControlPrincipal(vGes);
        
        cnt.iniciarControl();
        
        FlatCarbonIJTheme.setup();
    }
            
}
