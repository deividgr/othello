/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import java.io.Serializable;

/**
 * Tiene todas las operaciones y datos miembros que representan a un jugador.
 * Esta clase implementa a la interface Serializable, para poder ser guardada
 * en un archivo.
 *
 * @author David Jimenez Aguilera y Ana Rosa Bernal Blaya
 */
public class Jugador implements Serializable {

   /**
    * Dato miembro que almacena el nombre del jugador.
    */
   private String nombre;
   /**
    * Dato miembro que almacena la contraseÃƒÂ±a de cada jugador.
    */
   private String password;
   /**
    * Dato miembro que guarda la maxima puntuacion conseguida por este jugador.
    */
   private int maxPuntuacion;
   /**
    * Dato miembro que se encarga de almacenar el numero de partidas que ha jugado.
    * este jugador.
    */
   private int partidasJugadas;
   /**
    * Dato miembro que se encarga de almacenar el numero de partidas ganadas.
    */
   private int partidasGanadas;
   /**
    * Dato miembro que almacena el numero de partidas perdidas.
    */
   private int partidasPerdidas;

   /**
    * Constructor de la clase que recibe como argumento el nombre del jugador.
    *
    * @param nombre Nombre del jugador.
    * @param pwd
    */
   public Jugador(String nombre, String pwd) {
      this.nombre = nombre;
      password = pwd;
      maxPuntuacion = 0;
      partidasJugadas = 0;
      partidasGanadas = 0;
      partidasPerdidas = 0;
   }

   //Setters-Getters

   /**
    * Metodo que permite recuperar el nombre del Jugador.
    *
    * @return nombre Nombre del jugador.
    */
   public String getNombre() {
      return this.nombre;
   }


   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   //Se permite cambiar el password, aunque primero es necesario dar el password actual
   public boolean setPassword(String pwdActual, String pwdNuevo) {
      if (pwdActual.equals(password)) {
         this.password = pwdNuevo;
         return true;
      }
      else {
         return false;
      }
   }

   /**
    * Metodo que permite recuperar la maxima puntuacion del jugador.
    *
    * @return maxPuntuacion Dato miembro que almacena la maxima puntuacion del jugador.
    */
   public int getMaxPuntuacion() {
      return this.maxPuntuacion;
   }

   /**
    * Metodo que permite modificar la maxima puntuacion que ha conseguido un jugador asignandole
    * la que recibe como argumento, pero solo en el caso de que este sea superior a la que ya tenia.
    *
    * @param puntuacion Nueva puntuacion conseguida.
    */
   public void setMaxPuntuacion(int puntuacion) {
      if (maxPuntuacion < puntuacion) {
         maxPuntuacion = puntuacion;
      }
   }

   /**
    * Recupera el numero total de partidas jugadas por el jugador.
    *
    * @return partidasJugadas Numero de partidas jugadas.
    */
   public int getPartidasJugadas() {
      return this.partidasJugadas;
   }

   /**
    * Recupera el numero de partidas ganadas por el jugador.
    *
    * @return partidasGanadas Numero de partidas ganadas.
    */
   public int getPartidasGanadas() {
      return this.partidasGanadas;
   }

   /**
    * Recupera el numero de partidas perdidas por le jugador.
    *
    * @return partidasPerdidas Numero de partidas perdidas.
    */
   public int getPartidasPerdidas() {
      return this.partidasPerdidas;
   }

   /**
    * Aumenta en una unidad en numero de partidas jugadas.
    */
   public void aumentarPartidasJugadas() {
      this.partidasJugadas++;
   }

   /**
    * Metodo que aumenta en una unidad el numero de partidas ganadas.
    */
   public void aumentarPartidasGanadas() {
      this.partidasGanadas++;
   }

   /**
    * Aumenta en una unidad el numero de partidas perdidas.
    */
   public void aumentarPartidasPerdidas() {
      this.partidasPerdidas++;
   }

   /**
    * Este metodo permite poner a cero todas las estaditicas del jugador.
    */
   public void reiniciarDatos() {
      this.maxPuntuacion = 0;
      this.partidasGanadas = 0;
      this.partidasJugadas = 0;
      this.partidasPerdidas = 0;
   }
}
