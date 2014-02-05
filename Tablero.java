package othello;

import java.io.Serializable;

/**
 * La clase abstracta Tablero es la representacion de un tablero de dos coordenadas.
 * Se encuentran los datos miembro y metodos generales de cualquier tablero de juego.
 *
 * @author Ana Rosa Bernal y David Aguilera
 */
public abstract class Tablero implements Cloneable, Serializable {

   /**
    * Dato miembro tablero, es un array multiple que representa un tablero de dos coordenadas.
    */
   protected char tablero[][];

   /**
    * Dato miembro filas, nos proporciona el numero de filas que tendria el tablero.
    */
   protected int filas;

   /**
    * Dato miembro columnas, nos proporciona el numero de columnas que tendria el tablero.
    */
   protected int columnas;

   /**
    * Constructor de la clase. Recibe como argumento el numero de filas y de columnas
    * que tiene el tablero, dependiendo del juego del que se trate.
    *
    * @param filas Numero de filas
    * @param columnas Numero de columnas
    */
   public Tablero(int filas, int columnas) {
      this.filas = filas;
      this.columnas = columnas;
      tablero = new char[filas][columnas];
      for (int i = 0; i < filas; i++) {
         for (int j = 0; j < columnas; j++) {
            tablero[i][j] = 0;
         }
      }
   }

   //Getters

   /**
    *Metodo que devuelve el numero de filas.
    * @return
    */
   public int getFilas() {
      return filas;
   }

    /**
     * Metodo que devuelve el numero de columnas.
     * @return
     */
   public int getColumnas() {
      return columnas;
   }

   public char getJugador(int fila, int columna) {
      return tablero[fila][columna];
   }

   /**
    * Metodo que indica si la casilla donde pretendemos situar ficha esta ocupada o no.
    *
    * @param movimiento Posicion que se quiere comprobar si esta ocupada
    *
    * @return Devuelve si la casilla esta vacia o no.
    */
   boolean casillaOcupada(Movimiento movimiento) {
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();

      if (fila >= filas || columna >= columnas) {
         return false;
      }
      boolean ocupada;
      if (tablero[fila][columna] == 0) {
         ocupada = false;
      }
      else {
         ocupada = true;
      }
      return ocupada;
   }

   /**
    * Declaracion del metodo abstracto que situara una ficha tras comprobar que el
    * movimiento es valido, dependiendo de las normas de cada juego que la implemente.
    *
    * @param movimiento Posicion donde se quiere situar la ficha.
    */
   abstract void situarFicha(Movimiento movimiento);

   /**
    * Metodo que permite quitar una ficha de una casilla, en el caso de que este
    * ocupada.
    *
    * @param movimiento Posicion de donde se quiere quitar la ficha.
    */
   void vaciarCasilla(Movimiento movimiento) {
      boolean ocupada = casillaOcupada(movimiento);
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      if (ocupada == true) {
         tablero[fila][columna] = 0;
      }
   }

   /**
    * Metodo que vacia todas las casillas del tablero.
    */
   void vaciarTablero() {
      for (int i = 0; i < tablero.length; i++) {
         for (int j = 0; j < tablero.length; j++) {
            tablero[i][j] = 0;
         }
      }
   }
}
