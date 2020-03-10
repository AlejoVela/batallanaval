/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import Vista.InOut;
import java.util.ArrayList;


/**
 *
 * @author equipo9
 */
public class Funciones {
    
     
     InOut ob = new InOut();
 
     
    
    public int verificarRango(String m,int r1,int r2){
        int a = ob.pedirEntero(m);
        if(a>=r1 && a<=r2){
            return a;
        }else{
            ob.mostrar("Ha ingresado un numero fuera del rango");
            verificarRango(m,r1,r2);
        } 
       return a;
    }     
     
    public int [][] inicializarTablero(int tablero[][], int m, int n){
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tablero[i][j] = 0;
            }
        }
        return tablero;
    }

    public int[][] ubicarAviones(int tablero[][], int tam[], int cant) {
        int direccion, filaAleatoria, columnaAleatoria;
        
        for (int i = 0; i < cant; i++) {
            //variable para definir la direccion 
            //horizontal o vertical
            direccion = aleatorio(10);
            //definimos la posicion desde la que pondremos el barco
            do{
                filaAleatoria = aleatorio(9);
                columnaAleatoria = aleatorio(9);
            }while(comprobarPosicion(tablero[filaAleatoria][columnaAleatoria], 1, 1));
            
            if (direccion <= 5) {

                tablero = avion_ver(filaAleatoria, columnaAleatoria, tam[i], tablero);

            } else if (direccion > 5) {

                tablero = avion_hori(filaAleatoria, columnaAleatoria, tam[i], tablero);

            }

        }
        
        return tablero;
    }
    
    public boolean comprobarPosicion(int n, int numCompro, int numCompro2){
        if(n == numCompro || n == numCompro2){
            return true;
        }else{
            return false;
        }
    }
    
    public int[][] avion_ver(int filaAleatoria, int columnaAleatoria, int tam, int tablero[][]) {

            for (int i = 0; i < tam; i++) {
                //verificamos que la posicion no sea la ultima fila
                //asi empezamos a ubir hacia arriba
                if(i != 0){
                    if(filaAleatoria == 0){
                        //le sumamos para que los 1 bajen verticalmente
                        if(tablero[filaAleatoria+i][columnaAleatoria] != 1){
                            tablero[filaAleatoria+i][columnaAleatoria] = 1;
                        }else{
                            tablero = avion_hori(filaAleatoria, columnaAleatoria, (tam-i), tablero);
                            return tablero;
                        }
                    }else if(filaAleatoria == 9){
                        if(tablero[filaAleatoria-i][columnaAleatoria] != 1){
                            tablero[filaAleatoria-i][columnaAleatoria] = 1;
                        }else{
                            tablero = avion_hori(filaAleatoria, columnaAleatoria, (tam-i), tablero);
                            return tablero;                            
                        }                        
                    }else{
                        if(tablero[filaAleatoria-i][columnaAleatoria] != 1){
                            tablero[filaAleatoria-i][columnaAleatoria] = 1;
                        }else{
                            tablero = avion_hori(filaAleatoria, columnaAleatoria, (tam-i), tablero);
                            return tablero;                            
                        }
                    }
                }else{
                    //el la primera iteracion, volvemos uno la posicion actual
                    tablero[filaAleatoria][columnaAleatoria] = 1;
                }
                
            }
        
        
        return tablero;
    }

    public int[][] avion_hori(int filaAleatoria, int columnaAleatoria, int tam, int tablero[][]) {

            for (int i = 0; i < tam; i++) {
                //verificamos que la posicion no sea la ultima fila
                //asi empezamos a ubir hacia arriba
                if(i != 0){
                    if(columnaAleatoria == 0){
                        //le sumamos para que los 1 bajen verticalmente
                        if(tablero[filaAleatoria][columnaAleatoria+i] != 1){
                            tablero[filaAleatoria][columnaAleatoria+i] = 1;
                        }else{
                            tablero = avion_ver(filaAleatoria, columnaAleatoria, (tam-i), tablero);
                            return tablero;                            
                        }
                    }else if(columnaAleatoria == 9){
                        if(tablero[filaAleatoria][columnaAleatoria-i] != 1){
                            tablero[filaAleatoria][columnaAleatoria-i] = 1;
                        }else{
                            tablero = avion_ver(filaAleatoria, columnaAleatoria, (tam-i), tablero);
                            return tablero;                             
                        }                        
                    }else{
                        if(tablero[filaAleatoria][columnaAleatoria-i] != 1){
                            tablero[filaAleatoria][columnaAleatoria-i] = 1;
                        }else{
                            tablero = avion_ver(filaAleatoria, columnaAleatoria, (tam-i), tablero);
                            return tablero;                             
                        }
                    }
                }else{
                    //el la primera iteracion, volvemos uno la posicion actual
                    tablero[filaAleatoria][columnaAleatoria] = 1;
                }
                
            }
        
        
        return tablero;
    }       
  
    
    
    public int[][] rellenar_bomba(int tablero[][], int nBombas) {
        int filaAleatoria, columnaAleatoria;

        
        for (int i = 0; i < nBombas; i++) {
            do {
                filaAleatoria = aleatorio(9);
                columnaAleatoria = aleatorio(9);
            } while (comprobarPosicion(tablero[filaAleatoria][columnaAleatoria], 1, 2));
            tablero[filaAleatoria][columnaAleatoria] = 2;
        }
        return tablero;
    }

    public int aleatorio(int rango) {
        int numero = (int) (Math.random() * rango) + 0;
        return numero;

    }
    
    public String mostrarTablero(int tablero[][], int m, int n){
        String cont = "";
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cont += tablero[i][j] + " ";
            }
            cont += "\n";
        }
        return cont;
    }
            
    public int comprobarTurno(int n){
        if(n == 0){
            n = n+1;
            return n;
        }else if(n == 1){
            n = n-1;
            return n;
        }
        
        return n;
    }
    
    public boolean verificar(int[][] mapa) {
        int cont  = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(mapa[i][j] == 1 ){
                    cont+=1;
                }
            }
        }
        
        if(cont == 0){
            return true;
        }else{
            return false;
        }
    }
  
}
