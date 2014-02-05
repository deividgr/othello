/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

/**
 * Esta clase contiene todos los datos miembro y metodos que definen el juego. Hereda
 * de la clase Tablero, que representa un tablero generico, que podria ser usado para
 * cualquier juego. Puesto que se ofrecera la posibilidad de guardar un objeto de esta
 * clase, se implementa la interfaz Serializable
 *
 * @author Davis Aguilera jimenez y Ana Rosa Bernal Blaya.
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class TableroOtelo extends Tablero implements Serializable {

   /**
    * Control de la version
    */
   private static final long serialVersionUID = 2;
   /**
    * Variable de tipo caracter que representa a las fichas blancas del juego
    */
   private static final char BLANCA = 'b';
   /**
    * Dato miembro, definido como constante, que representa el otro color de ficha de juego.
    */
   private static final char NEGRA = 'n';
   /**
    * Dato miembro que nos indica a que ficha le toca jugar en cada momento.
    */
   private char turno;
   /**
    * Lista que almacena objetos de todas las clases que estan observando el tablero logico.
    * Se declara como transient porque se quiere que esos objetos, que no son serializables,
    * se guarden cuando salvemos el tablero logico en un archivo.
    */
   private transient ArrayList<Vista> vistas;

   /**
    * Constructor por defecto, que situa las fichas centrales, asigna el turno e inicializa
    * el ArrayList de vistas.
    */
   public TableroOtelo() {
      //Se pasa al constructor de la superclase el numero de filas y de columnas que
      //tiene el tablero
      super(8, 8);
      //Se inicia el tablero con las fichas centrales
      tablero[3][3] = BLANCA;
      tablero[4][4] = BLANCA;
      tablero[3][4] = NEGRA;
      tablero[4][3] = NEGRA;
      //Segun las reglas del Otelo, las negras son las primeras en mover
      turno = NEGRA;
      this.vistas = new ArrayList<Vista>();
   }

   /**
    * Metodo que devuleve el jugador contrario al que le toca mover en un instante
    * determinado.
    *
    * @return Jugador contrario.
    */
   private char getJugadorContrario() {
      if (turno == BLANCA) {
         return NEGRA;
      }
      else {
         return BLANCA;
      }
   }

   /**
    * Metodo que permite obtener el jugador al que le toca mover en un momento determinado.
    *
    * @return turno Jugador al que le toca mover.
    */
   public char getTurno() {
      if (turno == BLANCA) {
         return BLANCA;
      }
      else {
         return NEGRA;
      }
   }

   /**
    * Metodo que cambia el turno entre los dos jugadores.
    */
   public void pasarTurno() {
      if (turno == NEGRA) {
         turno = BLANCA;
      }
      else {
         turno = NEGRA;
      }
      this.notificarVistas();
   }

   /**
    * Metodo que comprueba si el movimiento es valido en funcion de las fichas situadas
    * encima de las coordenadas recibidas.
    *
    * @param movimiento Objeto que contiene la fila y la columna a la que se quiere mover.
    * @return Devuelve verdadero si el movimiento es correcto en lo que respecta a esa comprobacion.
    */
   public boolean comprobarMovimientoArriba(Movimiento movimiento) {
      char jugadorContrario = getJugadorContrario();
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      // Si la casilla esta ocupada o es la primera fila, el movimiento hacia arriba no es valido
      // Solo se considera que sea verdadero si se intenta situar la ficha en la primera fila o mas
      // (empezando a contar desde cero)
      if (super.casillaOcupada(movimiento) || (fila <= 1)) {
         return false;
      }
      // La casilla superior tiene que ser del jugador contrario
      if (tablero[fila - 1][columna] != jugadorContrario) {
         return false;
      }
      // Saltar las fichas consecutivas del jugador contrario
      int i = fila - 1;
      while ((i > 0) && (tablero[i][columna] == jugadorContrario)) {
         i--;
      }
      // La casilla final tiene que estar ocupada por una ficha de jugador
      if (tablero[i][columna] == turno) {
         return true;
      }
      else {
         return false;
      }
   }

   /**
    * Metodo que comprueba si el movimiento es valido en funcion de las fichas situadas
    * debajo de las coordenadas recibidas.
    *
    * @param movimiento Objeto que contiene la fila y la columna a la que se quiere mover.
    * @return Devuelve verdadero si el movimiento es correcto en lo que respecta a esa comprobacion.
    */
   public boolean comprobarMovimientoAbajo(Movimiento movimiento) {
      char jugadorContrario = getJugadorContrario();
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      // Si la casilla esta ocupada o es la ultima fila, el movimiento hacia abajo no es valido
      // Si la fila en la que se intenta situar es mayor o igual que seis, no tiene sentido seguir
      // porque no podremos encontrar una ficha del jugador contrario y otra de la nuestra a continuacion
      if (super.casillaOcupada(movimiento) || (fila >= 6)) {
         return false;
      }
      // La casilla inferior tiene que ser del jugador contrario
      if (tablero[fila + 1][columna] != jugadorContrario) {
         return false;
      }
      // Saltar las fichas consecutivas del jugador contrario
      int i = fila + 1;
      while ((i < filas - 1) && (tablero[i][columna] == jugadorContrario)) {
         i++;
      }
      // La casilla final tiene que estar ocupada por una ficha de jugador
      if (tablero[i][columna] == turno) {
         return true;
      }
      else {
         return false;
      }
   }

   /**
    * Metodo que comprueba si el movimiento es valido en funcion de las fichas situadas
    * a la izquierda de las coordenadas recibidas.
    *
    * @param movimiento Objeto que contiene la fila y la columna a la que se quiere mover.
    * @return Devuelve verdadero si el movimiento es correcto en lo que respecta a esa comprobacion.
    */
   public boolean comprobarMovimientoIzquierda(Movimiento movimiento) {
      char jugadorContrario = getJugadorContrario();
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      // Si la casilla esta ocupada o es la primera columna, el movimiento hacia la izquierda no es valido
      if (super.casillaOcupada(movimiento) || (columna <= 1)) {
         return false;
      }
      // La casilla anterior tiene que ser del jugador contrario
      if (tablero[fila][columna - 1] != jugadorContrario) {
         return false;
      }
      // Saltar las fichas consecutivas del jugador contrario
      int i = columna - 1;
      while ((i > 0) && (tablero[fila][i] == jugadorContrario)) {
         i--;
      }
      // La casilla final tiene que estar ocupada por una ficha de jugador
      if (tablero[fila][i] == turno) {
         return true;
      }
      else {
         return false;
      }
   }

   /**
    * Metodo que comprueba si el movimiento es valido en funcion de las fichas situadas
    * a la derecha de las coordenadas recibidas.
    *
    * @param movimiento Objeto que contiene la fila y la columna a la que se quiere mover.
    * @return Devuelve verdadero si el movimiento es correcto en lo que respecta a esa comprobacion.
    */
   public boolean comprobarMovimientoDerecha(Movimiento movimiento) {
      char jugadorContrario = getJugadorContrario();
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      // Si la casilla esta ocupada o es la ultima columna, el movimiento hacia la derecha no es valido.
      if (super.casillaOcupada(movimiento) || (columna >= 6)) {
         return false;
      }
      // La casilla posterior tiene que ser del jugador contrario
      if (tablero[fila][columna + 1] != jugadorContrario) {
         return false;
      }
      // Saltar las fichas consecutivas del jugador contrario
      int i = columna + 1;
      while ((i < columnas - 1) && (tablero[fila][i] == jugadorContrario)) {
         i++;
      }
      // La casilla final tiene que estar ocupada por una ficha de jugador
      if (tablero[fila][i] == turno) {
         return true;
      }
      else {
         return false;
      }
   }

   /**
    * Metodo que comprueba si el movimiento es valido en funcion de las fichas situadas
    * en la diagonal superior izquierda de las coordenadas recibidas.
    *
    * @param movimiento Objeto que contiene la fila y la columna a la que se quiere mover.
    * @return Devuelve verdadero si el movimiento es correcto en lo que respecta a esa comprobacion.
    */
   public boolean comprobarMovimientoArribaDerecha(Movimiento movimiento) {
      char jugadorContrario = getJugadorContrario();
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      //Si la casilla esta ocupada o es la ultima columna o la primera fila o las dos,
      //el movimiento hacia ese lado no es valido
      if (super.casillaOcupada(movimiento) || (columna >= 6) || (fila <= 1)) {
         return false;
      }
      //La casilla superior derecha tiene que ser del jugador contrario
      if (tablero[fila - 1][columna + 1] != jugadorContrario) {
         return false;
      }
      // Saltar las fichas consecutivas del jugador contrario
      int i = fila - 1;
      int j = columna + 1;
      while ((i > 0) && (j < columnas - 1) && (tablero[i][j] == jugadorContrario)) {
         i--;
         j++;
      }
      // La casilla final tiene que estar ocupada por una ficha de jugador
      if (tablero[i][j] == turno) {
         return true;
      }
      else {
         return false;
      }
   }

   /**
    * Metodo que comprueba si el movimiento es valido en funcion de las fichas situadas
    * en la diagonal superior izquierda de las coordenadas recibidas.
    *
    * @param movimiento Objeto que contiene la fila y la columna a la que se quiere mover.
    * @return Devuelve verdadero si el movimiento es correcto en lo que respecta a esa comprobacion.
    */
   public boolean comprobarMovimientoArribaIzquierda(Movimiento movimiento) {
      char jugadorContrario = getJugadorContrario();
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();

      //Si la casilla esta ocupada o es la primera columna o la primera fila o las dos,
      //el movimiento hacia ese lado no es valido
      if (super.casillaOcupada(movimiento) || (columna <= 1) || (fila <= 1)) {
         return false;
      }
      //La casilla superior izquierda tiene que ser del jugador contrario
      if (tablero[fila - 1][columna - 1] != jugadorContrario) {
         return false;
      }
      // Saltar las fichas consecutivas del jugador contrario
      int i = fila - 1;
      int j = columna - 1;
      while ((i > 0) && (j > 0) && (tablero[i][j] == jugadorContrario)) {
         i--;
         j--;
      }
      // La casilla final tiene que estar ocupada por una ficha de jugador
      if (tablero[i][j] == turno) {
         return true;
      }
      else {
         return false;
      }
   }

   /**
    * Metodo que comprueba si el movimiento es valido en funcion de las fichas situadas
    * en la diagonal inferior izquierda de las coordenadas recibidas.
    *
    * @param movimiento Objeto que contiene la fila y la columna a la que se quiere mover.
    * @return Devuelve verdadero si el movimiento es correcto en lo que respecta a esa comprobacion.
    */
   public boolean comprobarMovimientoAbajoDerecha(Movimiento movimiento) {
      char jugadorContrario = getJugadorContrario();
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      //Si la casilla esta ocupada o es la ultima columna o la ultima fila o las dos,
      //el movimiento hacia ese lado no es valido
      if (super.casillaOcupada(movimiento) || (columna >= 6) || (fila >= 6)) {
         return false;
      }
      //La casilla inferior derecha tiene que ser del jugador contrario
      if (tablero[fila + 1][columna + 1] != jugadorContrario) {
         return false;
      }
      // Saltar las fichas consecutivas del jugador contrario
      int i = fila + 1;
      int j = columna + 1;
      while ((i < filas - 1) && (j < columnas - 1) && (tablero[i][j] == jugadorContrario)) {
         i++;
         j++;
      }
      if (tablero[i][j] == turno) {
         return true;
      }
      return false;
   }

   /**
    * Metodo que comprueba si el movimiento es valido en funcion de las fichas situadas
    * en la diagonal inferior izquierda de las coordenadas recibidas.
    *
    * @param movimiento Objeto que contiene la fila y la columna a la que se quiere mover
    * @return Devuelve verdadero si el movimiento es correcto en lo que respecta a esa comprobacion.
    */
   public boolean comprobarMovimientoAbajoIzquierda(Movimiento movimiento) {
      char jugadorContrario = getJugadorContrario();
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      //Si la casilla esta ocupada o es la primera columna o la ultima fila o las dos,
      //el movimiento hacia ese lado no es valido
      if (super.casillaOcupada(movimiento) || (columna <= 1) || (fila >= 6)) {
         return false;
      }
      //La casilla inferior izquierda tiene que ser del jugador contrario
      if (tablero[fila + 1][columna - 1] != jugadorContrario) {
         return false;
      }
      // Saltar las fichas consecutivas del jugador contrario
      int i = fila + 1;
      int j = columna - 1;
      while ((i < filas - 1) && (j > 0) && (tablero[i][j] == jugadorContrario)) {
         i++;
         j--;
      }
      // La casilla final tiene que estar ocupada por una ficha de jugador
      if (tablero[i][j] == turno) {
         return true;
      }
      else {
         return false;
      }
   }

   /**
    * Metodo que comprueba todos los posibles casos por los que el movimiento introducido
    * como parametro seria valido. Almacena en un array el resultado de los metodos
    * de comprobar movimientos hacia todos los lados.
    *
    * @param movimiento Objeto que contiene la fila y la columna a la que se quiere mover.
    * @return Array booleano donde cada posicion indica si el movimiento es correcto hacia cada lado.
    */
   private boolean[] comprobarMovimientoCorrecto(Movimiento movimiento) {
      boolean[] movimientosValidos = new boolean[8];
      movimientosValidos[0] = comprobarMovimientoArriba(movimiento);
      movimientosValidos[1] = comprobarMovimientoAbajo(movimiento);
      movimientosValidos[2] = comprobarMovimientoDerecha(movimiento);
      movimientosValidos[3] = comprobarMovimientoIzquierda(movimiento);
      movimientosValidos[4] = comprobarMovimientoArribaDerecha(movimiento);
      movimientosValidos[5] = comprobarMovimientoArribaIzquierda(movimiento);
      movimientosValidos[6] = comprobarMovimientoAbajoDerecha(movimiento);
      movimientosValidos[7] = comprobarMovimientoAbajoIzquierda(movimiento);
      return movimientosValidos;
   }

   /**
    * Metodo que comprueba todos los posibles casos por los que el movimiento introducido
    * como parametro seria valido y devuelve veradero si alguno de ellos se cumple.
    *
    * @param movimiento Objeto que contiene la fila y la columna a la que se quiere mover.
    * @return Verdadero si el movimiento es correcto por alguna de las comprobaciones.
    */
   public boolean esPosibleMovimiento(Movimiento movimiento) {
      if (comprobarMovimientoArriba(movimiento)) {
         return true;
      }
      if (comprobarMovimientoAbajo(movimiento)) {
         return true;
      }
      if (comprobarMovimientoDerecha(movimiento)) {
         return true;
      }
      if (comprobarMovimientoIzquierda(movimiento)) {
         return true;
      }
      if (comprobarMovimientoArribaDerecha(movimiento)) {
         return true;
      }
      if (comprobarMovimientoArribaIzquierda(movimiento)) {
         return true;
      }
      if (comprobarMovimientoAbajoDerecha(movimiento)) {
         return true;
      }
      if (comprobarMovimientoAbajoIzquierda(movimiento)) {
         return true;
      }
      return false;
   }

   /**
    * Metodo que combierte las fichas situadas encima de una casilla, pasada como argumento,
    * hasta llegar a una del jugador que ha situado su ficha en dicha casilla.
    *
    * @param movimiento Objeto que contiene la fila y la columna donde se ha movido.
    */
   private void convertirFichasArriba(Movimiento movimiento) {
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      int i = fila - 1;
      while (tablero[i][columna] != turno) {
         tablero[i][columna] = turno;
         i--;
      }
   }

   /**
    * Metodo que combierte las fichas situadas debajo de una casilla, pasada como argumento,
    * hasta llegar a una del jugador que ha situado su ficha en dicha casilla.
    *
    * @param movimiento Objeto que contiene la fila y la columna donde se ha movido.
    */
   private void convertirFichasAbajo(Movimiento movimiento) {
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      int i = fila + 1;
      while (tablero[i][columna] != turno) {
         tablero[i][columna] = turno;
         i++;
      }
   }

   /**
    * Metodo que combierte las fichas situadas a la derecha de una casilla, pasada como argumento,
    * hasta llegar a una del jugador que ha situado su ficha en dicha casilla.
    *
    * @param movimiento Objeto que contiene la fila y la columna donde se ha movido.
    */
   private void convertirFichasDerecha(Movimiento movimiento) {
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      int j = columna + 1;
      while (tablero[fila][j] != turno) {
         tablero[fila][j] = turno;
         j++;
      }
   }

   /**
    * Metodo que combierte las fichas situadas a la izquierda de una casilla, pasada como argumento,
    * hasta llegar a una del jugador que ha situado su ficha en dicha casilla.
    *
    * @param movimiento Objeto que contiene la fila y la columna donde se ha movido.
    */
   private void convertirFichasIzquierda(Movimiento movimiento) {
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      int j = columna - 1;
      while (tablero[fila][j] != turno) {
         tablero[fila][j] = turno;
         j--;
      }
   }

   /**
    * Metodo que combierte las fichas situadas en la diagonal superior derecha de una casilla,
    * pasada como argumento, hasta llegar a una del jugador que ha situado su ficha en dicha casilla.
    *
    * @param movimiento Objeto que contiene la fila y la columna donde se ha movido.
    */
   private void convertirFichasArribaDerecha(Movimiento movimiento) {
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      int i = fila - 1;
      int j = columna + 1;
      while (tablero[i][j] != turno) {
         tablero[i][j] = turno;
         i--;
         j++;
      }
   }

   /**
    * Metodo que combierte las fichas situadas en la diagonal superior izquierda de una casilla,
    * pasada como argumento, hasta llegar a una del jugador que ha situado su ficha en dicha casilla.
    *
    * @param movimiento Objeto que contiene la fila y la columna donde se ha movido.
    */
   private void convertirFichasArribaIzquierda(Movimiento movimiento) {
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      int i = fila - 1;
      int j = columna - 1;
      while (tablero[i][j] != turno) {
         tablero[i][j] = turno;
         i--;
         j--;
      }
   }

   /**
    * Metodo que combierte las fichas situadas en la diagonal inferior derecha de una casilla,
    * pasada como argumento, hasta llegar a una del jugador que ha situado su ficha en dicha casilla.
    *
    * @param movimiento Objeto que contiene la fila y la columna donde se ha movido.
    */
   private void convertirFichasAbajoDerecha(Movimiento movimiento) {
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      int i = fila + 1;
      int j = columna + 1;
      while (tablero[i][j] != turno) {
         tablero[i][j] = turno;
         i++;
         j++;
      }
   }

   /**
    * Metodo que combierte las fichas situadas en la diagonal inferior izquierda de una casilla,
    * pasada como argumento, hasta llegar a una del jugador que ha situado su ficha en dicha casilla.
    *
    * @param movimiento Objeto que contiene la fila y la columna donde se ha movido.
    */
   private void convertirFichasAbajoIzquierda(Movimiento movimiento) {
      int fila = movimiento.getFila();
      int columna = movimiento.getColumna();
      int i = fila + 1;
      int j = columna - 1;
      while (tablero[i][j] != turno) {
         tablero[i][j] = turno;
         i++;
         j--;
      }
   }

   /**
    * Metodo que situa una ficha en una posicion, asegurandose mediante metodos auxiliares
    * de que el movimiento que se quiere realizar es valido, y convirtiendo las fichas
    * del jugador contrario correspondientes.
    *
    * @param movimiento Objeto que contiene la fila y la columna donde se ha movido.
    */
   @Override
   void situarFicha(Movimiento movimiento) {
      boolean[] movimientoCorrecto = comprobarMovimientoCorrecto(movimiento);
      boolean movimientoRealizado = false;
      if (movimientoCorrecto[0]) {
         movimientoRealizado = true;
         convertirFichasArriba(movimiento);
      }
      if (movimientoCorrecto[1]) {
         movimientoRealizado = true;
         convertirFichasAbajo(movimiento);
      }
      if (movimientoCorrecto[2]) {
         movimientoRealizado = true;
         convertirFichasDerecha(movimiento);
      }
      if (movimientoCorrecto[3]) {
         movimientoRealizado = true;
         convertirFichasIzquierda(movimiento);
      }
      if (movimientoCorrecto[4]) {
         movimientoRealizado = true;
         convertirFichasArribaDerecha(movimiento);
      }
      if (movimientoCorrecto[5]) {
         movimientoRealizado = true;
         convertirFichasArribaIzquierda(movimiento);
      }
      if (movimientoCorrecto[6]) {
         movimientoRealizado = true;
         convertirFichasAbajoDerecha(movimiento);
      }
      if (movimientoCorrecto[7]) {
         movimientoRealizado = true;
         convertirFichasAbajoIzquierda(movimiento);
      }
      if (movimientoRealizado) {
         tablero[movimiento.getFila()][movimiento.getColumna()] = turno;
         pasarTurno();
         this.notificarVistas();
      }
      else {
      }
   }

   /**
    * Metodo que avisa a todas clases que estan observando el tablero logico de que
    * ha habido un cambio
    */
   private void notificarVistas() {
      for (Vista vista : this.vistas) {
         vista.modelChanged();
      }
   }

   /**
    * Metodo que permite a una clase anadirse como obervadora del tablero logico.
    *
    * @param vista Objeto de una clase que implementa la interfaz vista.
    */
   public void addVista(Vista vista) {
      this.vistas.add(vista);
   }

   /**
    * Metodo que permite a una clase dejar de observar los cambios producidos en el
    * tablero logico.
    *
    * @param vista Objeto de una clase que implementa la interfaz vista.
    */
   public void removeVista(Vista vista) {
      this.vistas.remove(vista);
   }

   /**
    * Metodo que hace a todas las clases que estaban observando el tablero logico dejar de hacerlo
    */
   public void removeallVistas() {
      for (Vista vista : this.vistas) {
         this.vistas.remove(vista);
      }
   }

   /**
    * El metodo clone realiza una copia del del tablero logico con las fichas situadas actualmente
    * @return Devuelve un objeto de la clase TableroOtelo con toda la informacion de la partida actual
    */
   @Override
   public TableroOtelo clone() {
      TableroOtelo tableroClonado = null;
      try {
         tableroClonado = (TableroOtelo) super.clone();
         tableroClonado.tablero = new char[this.filas][this.columnas];
         for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
               tableroClonado.tablero[i][j] = this.tablero[i][j];
            }
         }
      }
      catch (CloneNotSupportedException exception) {
         System.out.println(".....");
         exception.printStackTrace();
         System.exit(1);
      }
      return tableroClonado;
   }

   /**
    * Metodo que indica el jugador al que le toca mover tiene algun movimiento disponible.
    *
    * @return Se devuelve verdadero si hay movimientos disponibles y falso si no los hay.
    */
   public boolean hayMovimientosDisponibles() {
      for (int i = 0; i < filas; i++) {
         for (int j = 0; j < columnas; j++) {
            Movimiento m = new Movimiento(i, j);
            if (esPosibleMovimiento(m)) {
               return true;
            }
         }
      }
      return false;
   }

   /**
    * Metodo que calcula y devuelve una lista con todos los movimientos posibles que puede hacer
    * el jugador al que le toca mover
    * @return ArrayList con todos los movimientos posibles
    */
   public ArrayList<Movimiento> calcularMovimientosPosibles() {
      ArrayList<Movimiento> movimientosPosibles = new ArrayList<Movimiento>();
      for (int i = 0; i < tablero.length; i++) {
         for (int j = 0; j < tablero.length; j++) {
            Movimiento m = new Movimiento(i, j);
            if (esPosibleMovimiento(m)) {
               movimientosPosibles.add(m);
            }
         }
      }
      return movimientosPosibles;
   }

   /**
    * Este metodo asigna una puntuacion a un tablero basandose en el numero de fichas
    * que hay del jugador al que le toca mover
    * @return Puntuacion asignada al tablero
    */
   public int score() {
      int score = 0;
      for (int i = 0; i < tablero.length; i++) {
         for (int j = 0; j < tablero.length; j++) {
            if (tablero[i][j] == turno) {
               score++;
            }
         }
      }
      return score;
   }

   /**
    * Metodo que devuelve el numero de fichas blancas.
    * @return Numero de fichas blancas
    */
   public int getNumBlancas() {
      int blancas = 0;
      for (int i = 0; i < filas; i++) {
         for (int j = 0; j < columnas; j++) {
            if (tablero[i][j] == 'b') {
               blancas++;
            }
         }
      }
      return blancas;
   }
/**
 * Metodo que devuelve el numero de fichas negras.
 * @return Numero de fichas negras
 */
   public int getNumNegras() {
      int negras = 0;
      for (int i = 0; i < filas; i++) {
         for (int j = 0; j < columnas; j++) {
            if (tablero[i][j] == 'n') {
               negras++;
            }
         }
      }
      return negras;
   }

   /**
    * Metodo necesario para la correcta serializacion de la clase
    * @param out Flujo de salida
    * @throws java.io.IOException
    */
   private void writeObject(ObjectOutputStream out) throws IOException {
      System.out.println("out");
      out.defaultWriteObject();
   }

   /**
    * Metodo para la correcta desserializacion de la clase
    * @param in Flujo de entrada
    * @throws java.io.IOException
    * @throws java.lang.ClassNotFoundException
    */
   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      in.defaultReadObject();
      this.vistas = new ArrayList<Vista>();
   }
}
