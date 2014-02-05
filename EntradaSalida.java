/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import java.io.*;

/**
 * Clase que se encarga de la entrada y salida de objetos de las clases Jugador y Partida.
 *
 * @author David Aguilera Jimenez y Ana Rosa Bernal Blaya.
 */
public class EntradaSalida {

   /**
    * Metodo que guarda un objeto de la clase Partida con informacion sobre el estado del
    * tablero logico y el nivel de dificultad elegido
    *
    * @param s Ruta absoluta del archivo a guardar
    * @param partida Objeto de la clase Partida
    */
   public void guardarPartida(File f, Partida partida) {
      ObjectOutputStream oos;
      try {
         oos = new ObjectOutputStream(new FileOutputStream(f + ".otg"));
         oos.writeObject(partida);
         oos.close();
      }
      catch (IOException e) {
         e.printStackTrace();
         System.exit(1);
      }
   }

   /**
    * Recupera un archivo con informacion sobre una partida guardada anteriormente
    * @param s Ruta absoluta del archivo a cargar
    * 
    * @return partida Objeto de la clase Partida
    */
   public Partida cargarPartida(File f) {
      Partida partida = null;
      if (f != null) {
         try {
            ObjectInputStream ois;

            ois = new ObjectInputStream(new FileInputStream(f));
            partida = (Partida) ois.readObject();
            ois.close();
            return partida;
         }
         catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
         }
         catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
         }
      }
      return partida;
   }

   /**
    * Carga un achivo que contiene un objeto de la clase Jugador
    *
    * @param file Objeto de la clase File con informacion sobre la ruta donde se encuentra el archivo
    *
    * @return jugador Objeto de la clase Jugador
    */
   public Jugador cargarJugador(File file) {
      if (file != null) {
         ObjectInputStream ois;
         Jugador jugador = null;
         try {
            ois = new ObjectInputStream(new FileInputStream(file));
            jugador = (Jugador) ois.readObject();
            ois.close();
         }
         catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
         }
         catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
         }
         return jugador;
      }
      return null;
   }

   /**
    * Guarda un objeto de la clase Jugador en un archivo
    *
    * @param file Objeto de la clase File con informacion sobre la ruta donde  del archivo a guardar
    *
    * @param jugador Objeto de la clase Jugador que sera guardado
    */
   public void guardarJugador(File file, Jugador jugador) {
      if (jugador != null) {
         File archivoSalida = new File(file + ".otp");
         ObjectOutputStream oos;
         try {
            oos = new ObjectOutputStream(new FileOutputStream(archivoSalida));
            oos.writeObject(jugador);
            oos.close();
         }
         catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
         }
      }
   }

//    public String devolverRutaArchivo(File file){
//       return file.getAbsolutePath();
//    }
}
