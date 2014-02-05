/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * La clase PanelTablero es un tablero grafico (hereda de JPanel) que muestra por pantalla
 * el tablero logico del juego. Implementa la interfaz Vista para actualizarse cada vez
 * que se produzca un cambio en dicho tablero logico.
 * @author David
 */
public class PanelTablero extends JPanel implements Vista {

   /**
    * Dato miembro de la clase TableroOtelo
    */
   private TableroOtelo tablero;
   /**
    * Dato miembro que almacena el tamano de cada casilla del tablero
    */
   public final static int DIMENSION_CASILLA = 50;
   /**
    * Array con las lineas de delimitacion de las cuadriculas
    */
   private ArrayList<Line2D.Float> lineasDelimitadoras;

   /**
    * Constructor de la clase que recibe como argumento un objeto de la clase TableroOtelo, sobre
    * el que se basara para pintar todos los elementos
    *
    * @param tablero Objecto de la clase TableroOtelo
    */
   public PanelTablero(TableroOtelo tablero) {
      this.setTablero(tablero);


      // Se crea el array de lineas
      lineasDelimitadoras = new ArrayList<Line2D.Float>();

      // Se crean las lineas delimitadoras
      Dimension dimension = new Dimension(tablero.getColumnas() * DIMENSION_CASILLA, tablero.getFilas() * DIMENSION_CASILLA);
      setPreferredSize(dimension);
      crearLineasDelimitadoras();
   }

   /**
    * Metodo para crear las lineas a pintar
    */
   private void crearLineasDelimitadoras() {
      Line2D.Float linea;
      int xp, yp, xl, yl;

      // Se crean las lineas verticales: tantas como columnas +1
      for (int columna = 0; columna < tablero.getColumnas() + 1; columna++) {
         // Posicion de partida: xp valdra tanto como i*DIMENSION_CASILLA
         //                      yp valdra siempre 0
         // Posicion de llegada: xl vale igual que xp
         //                      yl valdra siempre numeroFilas*DIMENSION_CASILLA
         xp = columna * DIMENSION_CASILLA;
         yp = 0;
         xl = xp;
         yl = tablero.getFilas() * DIMENSION_CASILLA;
         linea = new Line2D.Float(xp, yp, xl, yl);

         // Se agrega la linea al array de lineas
         lineasDelimitadoras.add(linea);
      }

      // Se crean las lineas horizontales: tantas como filas + 1
      for (int fila = 0; fila < tablero.getFilas() + 1; fila++) {
         // Posicion de partida: xp valdra siempre 0
         //                      yp valdra i*DIMENSION_CASILLA
         // Posicion de llegada: xl valdra numeroFilas*DIMENSION_CASILLA
         //                      yl valdra igual que yp
         xp = 0;
         yp = fila * DIMENSION_CASILLA;
         xl = tablero.getFilas() * DIMENSION_CASILLA;
         yl = yp;
         linea = new Line2D.Float(xp, yp, xl, yl);

         // Se agrega la linea al array de lineas
         lineasDelimitadoras.add(linea);
      }
   }

   /**
    * Metodo paint para hacer el pintado
    */
   @Override
   public void paint(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;

      g2.setColor(Color.WHITE);
      // Pintado de lineas delimitadoras
      for (Line2D.Float linea : lineasDelimitadoras) {
         g2.draw(linea);
      }

      // Se pintan los circulos
      Ellipse2D.Float circulo;
      // Se determina la coordenada superior izquierda en que se encuadrara el circulo
      int xSupIzq;
      int ySupIzq;

      // Se crea el circulo

      for (int fila = 0; fila < tablero.getFilas(); fila++) {
         for (int columna = 0; columna < tablero.getColumnas(); columna++) {
            xSupIzq = columna * DIMENSION_CASILLA + 5;
            ySupIzq = fila * DIMENSION_CASILLA + 5;
            if (tablero.getJugador(fila, columna) == 'b') {
               circulo = new Ellipse2D.Float(xSupIzq, ySupIzq, DIMENSION_CASILLA - 10, DIMENSION_CASILLA - 10);
               g2.setColor(Color.WHITE);
               g2.fill(circulo);
            }
            if (tablero.getJugador(fila, columna) == 'n') {
               circulo = new Ellipse2D.Float(xSupIzq, ySupIzq, DIMENSION_CASILLA - 10, DIMENSION_CASILLA - 10);
               g2.setColor(Color.BLACK);
               g2.fill(circulo);
            }
         }
      }
   }

   /**
    * Metodo que vuelve a pintar todo el tablero
    */
   public void modelChanged() {
      //Refresco de informacion de la partida
      this.repaint();
   }

   /**
    * Metodo que permite asociar al dato miembro tablero el tablero logico. Ademas,
    * se anade como clase que esta lo esta observando para que dicho tablero lo sepa.
    *
    * @param tablero Objeto de la clase TableroOtelo
    */
   private void setTablero(TableroOtelo tablero) {
      if (tablero != null) {
         if (this.tablero != null) {
            this.tablero.removeVista(this);
         }
         this.tablero = tablero;
         this.tablero.addVista(this);
         this.repaint();
      }
   }

   /**
    * Metodo que cambia el tablero logico asociado al dato miembro tablero
    *
    * @param tablero Objeto de la clase TableroOtelo
    */
   public void restart(TableroOtelo tablero) {
      setTablero(tablero);
   }
}
