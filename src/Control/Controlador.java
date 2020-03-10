/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.*;
import Vista.InOut;
import java.util.ArrayList;

/**
 *
 * @author equipo9
 */
public class Controlador {

    public static void main(String[] args) {
     

        //AQUI TERMINA DE CREARSE EL JFRAME
        //OBJETOS
        Funciones funcion = new Funciones();
        InOut objVista = new InOut();

        //VARAIBLES
        boolean condicion = false;
        int vidas = 3, puntaje = 0;
        int t = 0, af = 0, ac = 0;
        int tablero[][] = new int[10][10];
        int tablero1[][] = new int[10][10];
        Jugador players[] = new Jugador[2];



        int cant = funcion.verificarRango("Ingrese número de aviones", 3, 8);
        int tam[] = new int[cant];
        for (int i = 0; i < cant; i++) {
            tam[i] = funcion.verificarRango("ingrese el tamaño para el avion " + i, 2, 6);
        }
        int bombas = cant;
  
        
        //ubicamos jugador 1
           String nombre = objVista.pedirCad("ingrese nombre del jugador");
           //llenamos el tablero de ceros
           tablero = funcion.inicializarTablero(tablero, 10, 10);
           //ubicamos los aviones
           tablero = funcion.ubicarAviones(tablero, tam, cant);
           tablero = funcion.rellenar_bomba(tablero, bombas);          
           Jugador j = new Jugador(vidas, nombre, tablero, puntaje);
           players[0] = j;
           //imprimir tablero
           objVista.mostrar("tablero jugador " + 0 + "\n\n"+funcion.mostrarTablero(tablero, 10, 10)); 
           System.out.println("tablero jugador " + 0 + "\n\n"+funcion.mostrarTablero(tablero, 10, 10));

           
           //ubicamos jugador 2
           String nombre2 = objVista.pedirCad("ingrese nombre del jugador 2");
           //llenamos el tablero de ceros
           tablero1 = funcion.inicializarTablero(tablero1, 10, 10);
           //ubicamos los aviones
           tablero1 = funcion.ubicarAviones(tablero1, tam, cant);
           tablero1 = funcion.rellenar_bomba(tablero1, bombas);          
           Jugador j1 = new Jugador(vidas, nombre2, tablero1, puntaje);
           players[1] = j1;
           //imprimir tablero
           objVista.mostrar("tablero jugador " + 1 + "\n\n"+funcion.mostrarTablero(tablero1, 10, 10)); 
           System.out.println("tablero jugador " + 1 + "\n\n"+funcion.mostrarTablero(tablero1, 10, 10));
           
      //hasta aqui ya se ha ubicado todo el mapa para ambos jugadores
        
        do{
            
            objVista.mostrar("Turno del jugador "+t+": '"+ players[t].getNombre()+"'\n");
            
            af = objVista.pedirEntero("Ingrese la fila a la que desea atacar!");
            ac = objVista.pedirEntero("Ingrese la columna a la que desea atacar!");
            if(t == 0){
                
                if(players[t+1].getMapa()[af][ac] == 1){
                    objVista.mostrar("Has golpeado algo!!\nObtiene 1 punto");
                    players[t+1].getMapa()[af][ac] = 0;
                    players[t].setPuntuacion(players[t].getPuntuacion()+1);
                    System.out.println("tablero jugador " + t + "\n"+funcion.mostrarTablero(players[t+1].getMapa(), 10, 10));
                    
                    condicion = funcion.verificar(players[t+1].getMapa());
                    
                    if(condicion == true){
                        objVista.mostrar("wow, Alparacer el jugador "+(t+1)+" \n'"+players[t+1].getNombre()+"' "
                                + "se ha quedado sin barcos\nGana el jugador "+t+"'"+players[t].getNombre()+"'");
                    }
                    
                }else if(players[t+1].getMapa()[af][ac] == 2){
                    objVista.mostrar("Has una mina :c!!\nPierdes una vida");
                    players[t].setVidas(players[t].getVidas()-1);
                    if(players[t].getVidas() == 0){
                        objVista.mostrar("Has perdido "+players[t].getNombre()+"\nGana "+players[t+1].getNombre());
                        condicion = true;
                    }                    
                }else{
                    objVista.mostrar("Has fallado!!!\nNo le diste a nadita");

                }
                //cambio de turno
                t = t + 1;
                
            }else{
                if(players[t-1].getMapa()[af][ac] == 1){
                    objVista.mostrar("Has golpeado algo!!\nObtiene 1 punto");
                    players[t-1].getMapa()[af][ac] = 0;
                    players[t].setPuntuacion(players[t].getPuntuacion()+1);
                    System.out.println("tablero jugador " + t + "\n"+funcion.mostrarTablero(players[t-1].getMapa(), 10, 10));
                    
                    condicion = funcion.verificar(players[t-1].getMapa());
                    
                    if(condicion == true){
                        objVista.mostrar("wow, Alparacer el jugador "+(t-1)+" \n'"+players[t-1].getNombre()+"' "
                                + "se ha quedado sin barcos\nGana el jugador "+t+"'"+players[t].getNombre()+"'");
                    }
                    
                }else if(players[t-1].getMapa()[af][ac] == 2){
                    objVista.mostrar("Has una mina :c!!\nPierdes una vida");
                    players[t].setVidas(players[t].getVidas()-1);
                    if(players[t].getVidas() == 0){
                        objVista.mostrar("Has perdido "+players[t].getNombre()+"\nGana "+players[t-1].getNombre());
                        condicion = true;
                    }                    
                }else{
                    objVista.mostrar("Has fallado!!!\nNo le diste a nadita");

                }

                //cambio de turno
                t = t - 1;
            }
        }while(condicion==false);
        //la variable condicion controla la victoria
        
    }
}

