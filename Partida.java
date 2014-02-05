/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;

import java.io.Serializable;

/**
 * Clase que contiene un dato miembro de la clase TableroOtelo, y otro de tipo primitivo
 * que almacena la dificultad elegida por el jugador. Esta clase se utilizara para poder
 * guardar en un archivo de forma conjunta un tablero y el nivel que habia elegido el usuario, de
 * forma que se pueda retomar mas adelante.
 * 
 * @author Ana Rosa Bernal y David Aguilera
 */
public class Partida implements Serializable{

   /**
    * Dato miembro de la clase TableroOtelo.
    */
   private TableroOtelo tablero;

   /**
    * Variable de tipo entera con informacion sobre la dificultad elegida.
    */
   private int dificultad;

   /**
    * Constructor de la clase que recibe como argumentos un objeto de la clase TableroOtelo y
    * una variable entera con la dificultad.
    *
    * @param tablero Tablero logico.
    * @param dificultad Dificultad elegida.
    */
   public Partida(TableroOtelo tablero, int dificultad){
      this.tablero = tablero;
      this.dificultad = dificultad;
   }

   /**
    * Metodo que devuelve el objeto asociado al dato miembro tablero.
    *
    * @return Objeto de la clase TableroOtelo.
    */
   public TableroOtelo getTablero(){
      return this.tablero;
   }

   /**
    * Metodo que devuelve el valor del dato miembro dificultad.
    *
    * @return Dificultad elegida por el jugador.
    */
   public int getDificultad(){
      return this.dificultad;
   }
}
