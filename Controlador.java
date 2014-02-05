package othello;

/**
 *Esta clase detecta las coordenadas del lugar en el tablero donde sea pinchado
 * mediante la implementacion de la interfaz MouseListener, y
 * se las pasa al tablero logico.
 * Si estas jugando contra la maquina, esta clase llama al minimax cuando es su turno.
 * @author David Aguilera Jimenez y Ana Rosa Bernal Blaya.
 */
import java.awt.event.*;

public class Controlador implements MouseListener {

   /**
    * Dato miembro que referencia al panel controlado
    */
   private PanelTablero panelControlado;
   /**
    * Dato miembro que referencia a la clase TableroOtelo.
    */

   private TableroOtelo tablero;
   /**
    * Dato miembro que referencia a la clase JuegoOtelo.
    */

   private JuegoOtelo juegoOtelo;
   /**
    * Dato miembro de tipo entero que almacena la dificultad del juego, en el
    * caso de que se juegue contra la maquina. La dificultad se le pasara al minimax
    * y con ella establecera la profundidad.
    */

   private int dificultad;
   /**
    * Dato miembro que almacena si se ha seleccionado el modo de juego humano-maquina.
    */

   private boolean minimaxActivo;

   /**
    * Constructor de la clase
    * @param panelControlado Objeto de la clase panelControlado que hace referencia al panel grafico donde queremos detectar las pulsaciones
    * @param tablero Objeto de la clase TableroOtelo que hace referencia al tablero logico
    * @param dificultad Indica la dificultad que ha elegido el jugador
    * @param juego Objeto de la clase JuegoOtelo
    */
   public Controlador(PanelTablero panelControlado, TableroOtelo tablero, int dificultad, JuegoOtelo juego) {
      this.panelControlado = panelControlado;
      this.tablero = tablero;
      this.juegoOtelo = juego;
      this.minimaxActivo=juego.getMinimaxActivo();
      this.dificultad=dificultad;
   }

   /**
    * Metodos a implementar por obligacion del contrato con MouseListener. Si es el turno
    * del jugador, pasa las coordenadas (fila y columna) del lugar donde se ha hecho click
    * al tablero logico para que situe la ficha.
    *
    * @param evento Evento producido al hacer click con el raton en la zona del panel
    */
   public void mouseClicked(MouseEvent evento) {
      //Se impide que el jugador situe ficha mientras el minimax esta calculando
      if (minimaxActivo && tablero.getTurno() == 'b')
      {
         return;
      }
      try {
         // Determinamos donde se ha pulsado
         int posx = evento.getX();
         int posy = evento.getY();
         int fila = posy / PanelTablero.DIMENSION_CASILLA;
         int columna = posx / PanelTablero.DIMENSION_CASILLA;
         Movimiento movimiento = new Movimiento(fila, columna);

         tablero.situarFicha(movimiento);



         if (!tablero.hayMovimientosDisponibles()) {
            System.out.println("Blancas no pueden mover y pasan turno");
            tablero.pasarTurno();
            if (!tablero.hayMovimientosDisponibles()) {
               //Volvemos a pasar turno para que el minimax no se ejecute, puesto que el juego ha finalizado
               tablero.pasarTurno();
               juegoOtelo.gameFinished();
               return;
            }
         }


         if (minimaxActivo && tablero.getTurno() == 'b') {
            
            Minimax minimax = new Minimax(this.dificultad, tablero, this);
            minimax.start();
         }
      }
      catch (NullPointerException e) {
         System.err.println(e);
      }
   }

   /**
    * Implementacion del metodo de la interfaz MouseListener que se ejecuta cuando el raton entra sobre la zona escuchada
    */
   public void mouseEntered(MouseEvent evento) {
   }

   /**
    * Implementacion del metodo de la interfaz MouseListener que se ejecuta cuando el raton sale de la zona escuchada
    */
   public void mouseExited(MouseEvent evento) {
   }

   /**
    * Implementacion del metodo de la interfaz MouseListener que se ejecuta cuando se mantiene pulsada el boton derecho del raton
    */
   public void mousePressed(MouseEvent evento) {
   }

   /**
    * Implementacion del metodo de la interfaz MouseListener que se ejecuta cuando se deja de pulsar el boton derecho del raton
    */
   public void mouseReleased(MouseEvent evento) {
   }

   /**
    * Metodo que recibe del minimax las coordenadas del movimiento que quiere realizar
    * y se las pasa al tablero logico para que situe la ficha
    *
    * @param movimiento Objeto de la clase movimiento que contiente informacion sobre la fila y la columna donde se quiere situar la ficha
    */
   public void movimientoMinimax(Movimiento movimiento){
      tablero.situarFicha(movimiento);
      if (!tablero.hayMovimientosDisponibles()) {
            tablero.pasarTurno();
            if (!tablero.hayMovimientosDisponibles()) {
               juegoOtelo.gameFinished();
            }
         }
   }

   /**
    * Metodo que sustituye el tablero logico y la dificultad seleccionada actuales por los pasados como argumento
    *
    * @param dificultad Dificultad seleccionada por el usuario
    * @param tablero Objeto de la clase TableroOtelo
    */
   public void restart(TableroOtelo tablero, int dificultad) {
      this.tablero = tablero;
      this.dificultad = dificultad;
   }

   /**
    * Este metodo cambia el valor del dato miembro minimaxActivo por el que le pasamos como argumento
    *
    * @param activo Indica si el jugador esta jugando contra la maquina o no
    */
   public void setMinimaxActivo(boolean activo){
      this.minimaxActivo=activo;
   }

   /**
    * Sustituye el valor del dato miembro dificultad por el pasado como argumento
    *
    * @param dificultad Dificultad seleccionada por el usuario
    */
   public void setDificultad(int dificultad){
      this.dificultad=dificultad;
   }
}
