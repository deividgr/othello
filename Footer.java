package othello;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;

/**
 * La clase Footer muestra de forma grafica datos en tiempo real sobre la partida
 * que se esta jugando al pie del tablero grafico.
 *
 * @author Ana Rosa Bernal y David Aguilera
 */
public class Footer extends JTextArea implements Vista{

   /**
    * Dato miembro de la clase TableroOtelo.
    */
    TableroOtelo tablero;

    /**
     * Constructor de la clase que recibe como argumento el tablero logico, del cual
     * se extraeran los datos que se mostraran por pantalla.
     *
     * @param tablero
     */
    public Footer(TableroOtelo tablero){

        setFont(getFont().deriveFont(Font.BOLD));
        setBackground(new Color(238,238,238));
        this.setTablero(tablero);

    }

    /**
     * Metodo que actualiza la informacion que se muestra por pantalla.
     */
    public void refrescar(){
        String turno;
        if(tablero.getTurno() == 'b')
            turno = "blancas";
        else
            turno = "negras";
        setText("Juegan las "+turno+"                                                       Negras: "+tablero.getNumNegras()+"  Blancas: "+tablero.getNumBlancas());
    }

    /**
     * Metodo que asigna al dato miembro tablero un objeto de la clase TableroOtelo
     * y realiza las operaciones necesarias para escuchar los cambios que se producen en
     * este.
     *
     * @param tablero Tablero logico
     */
    private  void setTablero(TableroOtelo tablero) {
        if (tablero != null) {
            if (this.tablero != null) {
                this.tablero.removeVista(this);
            }
            this.tablero = tablero;
            this.tablero.addVista(this);
            this.refrescar();
        }
    }

    /**
     * Implementacion del metodo de la interfaz Vista.
     */
    public void modelChanged() {
        this.refrescar();
    }

    /**
     * Metodo que gestiona las operaciones a realizar cuando se quiere cambiar el objeto
     * asociado al dato miembro tablero.
     *
     * @param tablero Nuevo tablero logico.
     */
    public void restart(TableroOtelo tablero){
        setTablero(tablero);
    }
}
