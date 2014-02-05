package othello;

import java.util.*;

/**
 * La clase minimax contiene metodos que, de forma recursiva, calculan la posicion
 * en la que la maquina situara su ficha. Hereda de la clase Thread para que se
 * ejecute en una hebra distinta de la principal, de forma que mientras se esta
 * calculando la posicion, no se quede bloqueada la aplicacion.
 *
 * @author Ana Rosa Bernal y David Aguilera
 */
public class Minimax extends Thread {

   /**
    * Dato miembro que hace referencia al tablero logico
    */
    private TableroOtelo tableroOtelo;

    /**
     * Dato miembro que hace referencia a la clase controlador
     */
    private Controlador controlador;

    /**
     * Dato miembro que almacena la profundida maxima hasta la cual llegara la recursividad
     */
    public int profundidadMaxima;

    /**
     * Constructor de la clase
     * @param profundidadMaxima Profundidad maxima de la recursividad
     * @param tablero Tablero logico
     * @param controlador Gestor de eventos
     */
    public Minimax(int profundidadMaxima, TableroOtelo tablero, Controlador controlador) {
        this.profundidadMaxima = profundidadMaxima;
        tableroOtelo=tablero;
        this.controlador=controlador;
    }

    /**
     * Metodo que calcula y devuelve el mejor movimiento que ha encontrado la maquina
     * @return mejorMovimiento El movimiento calculado tal que la maquina consigue una mejor puntuacion
     */
    public Movimiento getBestMove() {
        Movimiento mejorMovimiento = null;
        int mejorScore = 0;
        List<Movimiento> movimientos;
        movimientos = tableroOtelo.calcularMovimientosPosibles();
        TableroOtelo nuevoTablero;
        int profundidad = 0;

        for (Movimiento movimiento : movimientos) {
            nuevoTablero = tableroOtelo.clone();
            nuevoTablero.situarFicha(movimiento);
            int score = getBestMoveRec(nuevoTablero, profundidad + 1);
            if (score >= mejorScore) {
                mejorMovimiento = movimiento;
                mejorScore = score;
            }
        }
        if(mejorMovimiento == null){
           System.out.println("Minimax movimiento null");
           System.out.println(movimientos.size());
        }

        return mejorMovimiento;
    }

    /**
     * Metodo recursivo auxiliar que devuelve la puntuacion de cada tablero pasado com argumento
     *
     * @param tableroOtelo Tablero logico
     * @param profundidad Profundidad maxima de la recursividad
     * @return Devuelve la mejor puntuacion, dependiendo de si estamos en la profundidad maxima, de si la profundidad es par, o de si es impar
     */

    private int getBestMoveRec(TableroOtelo tableroOtelo, int profundidad) {
        if (profundidad == profundidadMaxima) {
            return tableroOtelo.score();
        } else {
            List<Movimiento> movimientos;
            movimientos = tableroOtelo.calcularMovimientosPosibles();
            TableroOtelo nuevoTablero;
            int scoreMin = Integer.MAX_VALUE;
            int scoreMax = 0;

            if (profundidad % 2 == 0) {

                for (Movimiento movimiento : movimientos) {
                    nuevoTablero = tableroOtelo.clone();
                    nuevoTablero.situarFicha(movimiento);
                    int score = getBestMoveRec(nuevoTablero, profundidad + 1);
                    if (score < scoreMin) {
                        scoreMin = score;
                    }

                }
                return scoreMin;
            } else {
                for (Movimiento movimiento : movimientos) {
                    nuevoTablero = tableroOtelo.clone();
                    nuevoTablero.situarFicha(movimiento);
                    int score = getBestMoveRec(nuevoTablero, profundidad + 1);
                    if (score > scoreMax) {
                        scoreMax = score;
                    }
                }
                return scoreMax;
            }

        }
    }

    /**
     * Sobreescritura del metodo de la clase Thread, que permite ejecutar el minimax en una hebra nueva
     */
    @Override
    public void run() {
        Movimiento movimiento = getBestMove();
        this.controlador.movimientoMinimax(movimiento);
    }
}
