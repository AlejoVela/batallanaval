/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import javax.swing.JOptionPane;

/**
 *
 * @author Estudiante
 */
public class InOut {

    public void mostrar(String m){
            JOptionPane.showMessageDialog(null,m);    
    }
    
    public String pedirCad(String n){
           return JOptionPane.showInputDialog(n);
    }
    public int pedirEntero(String  a){
           return  Integer.parseInt(JOptionPane.showInputDialog(a));
    }
}
