/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

/**
 * La clase Movimiento es la representacion que tiene el juego de una coordenada.
 *
 * @author Ana Rosa Bernal y David Aguilera
 */
public class Movimiento {

   /**
    * Dato miembro que representa una fila del tablero;
    */
   private int fila;
   /**
    * Dato miembro que representa una columna del tablero;
    */
   private int columna;

   /**
    * Constructor que recibe como argumento la fila y la columna que constituyen
    * un determinado movimiento
    *
    * @param fila Fila del tablero.
    * @param columna Columna del tablero.
    */
   public Movimiento(int fila, int columna) {
      this.fila = fila;
      this.columna = columna;
   }

   /**
    * Metodo que devuelve el valor de la columna.
    * @return
    */
   public int getColumna() {
      return columna;
   }

   /**
    * Metodo que devuelve el valor de la fila donde sea situado la fila.
    */
   public int getFila() {
      return fila;
   }
}
