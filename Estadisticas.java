/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * La clase Estadisticas muestra de forma grafica las estadisticas de un jugador
 *
 * @author Ana Rosa Bernal y David Aguilera
 */
public class Estadisticas extends JDialog implements ActionListener{

   /**
    * Datos miembro de la clase jugador. Es el jugador del cual se muestran las estadisticas
    */
   private Jugador jugador;

   private JLabel partidasJugadas;
   private JLabel partidasGanadas;
   private JLabel partidasPerdidas;
   private JLabel maxPuntuacion;
   
   private JButton cerrar;
   private JButton reiniciarEstadisticas;
   


   /**
    * Constructor de la clase que recibe como argumento el jugador del cual se construyen
    * las estadisticas.
    *
    * @param jugador Jugador del que se realizan las estadisticas
    */
   public Estadisticas(Jugador jugador){
      this.jugador = jugador;

      //Se inicializan los Labels
      partidasJugadas = new JLabel("  Nº de partidas jugadas: "+jugador.getPartidasJugadas()+"  ");
      partidasGanadas = new JLabel("  Nº de partidas ganadas: "+jugador.getPartidasGanadas()+"  ");
      partidasPerdidas = new JLabel("  Nº de partidas perdidas: "+jugador.getPartidasPerdidas()+"  ");
      maxPuntuacion = new JLabel("  Maxima puntuacion: "+jugador.getMaxPuntuacion());

      cerrar = new JButton("Cerrar");
      reiniciarEstadisticas = new JButton("Reiniciar Estadisticas");
      cerrar.addActionListener(this);
      
      JPanel panel = new JPanel();
      panel.setLayout(new FlowLayout());
      panel.add(cerrar);
      panel.add(reiniciarEstadisticas);

      //Se anaden las Labels y el panel que contiene el boton al dialogo
      this.add(partidasJugadas);
      this.add(partidasGanadas);
      this.add(partidasPerdidas);
      this.add(maxPuntuacion);
      this.add(panel);

      //Se seleccionan propiedades del dialogo
      this.setLayout(new GridLayout(0,1));
      this.setLocation(100, 100);
      this.setTitle("Estadisticas de "+jugador.getNombre());
      this.setResizable(true);
      this.setModal(true);

      this.pack();
      this.setVisible(false);

   }

   /**
    * Implementacion del metodo declarado en la interfaz ActionListener.
    *
    * @param e Evento capturado.
    */
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == cerrar){
         this.setVisible(false);
      }
      if(e.getSource() == reiniciarEstadisticas){
         System.out.println("Ba");
         jugador.reiniciarDatos();
         System.out.println("Bu");
         //Para que se vean los datos actalizados hay que volver a abrir la ventana
         this.setVisible(false);
      }
   }



}
