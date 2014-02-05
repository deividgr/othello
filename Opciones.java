/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author David
 */
public class Opciones extends JDialog implements ActionListener{

   /**
    * Dato miembro de la clase EntradaSalida.
    */
   private EntradaSalida es;

   /**
    * Dato miembro de la clase JuegoOtelo.
    */
   private JuegoOtelo juego;

   /**
    * Dato miembro de la clase Jugador.
    */
   private Jugador jugador;

   /**
    * Variable booleana que indica si la partida ha finalizado.
    */
   private boolean partidaTerminada;


   private JLabel label1, label2, label3;

   public JRadioButton button1;
   public JRadioButton button2;
   private ButtonGroup group;
   
   private JButton aceptar;
   private JButton ok;
   
   private JButton seleccionarJugador1;
   private JButton seleccionarJugador2;
   private JButton nuevoJugador1;
   private JButton nuevoJugador2;

   private JFileChooser fileChooser;

   private JTextField textField;
   private JPasswordField passwordField;

   private JDialog dialog1;

   /**
    * Constructor de la clase que recibe como argumentos un objeto de la clase JuegoOtelo
    * y una variable que indica si la partida ha terminado.
    *
    * @param juego Objeto de la clase JuegoOtelo.
    * @param partidaTerminada Variable que indica si la partida ha finalizado.
    */
   public Opciones(JuegoOtelo juego, boolean partidaTerminada) {

      this.partidaTerminada=partidaTerminada;
      this.juego=juego;
      es = new EntradaSalida();

      label1 = new JLabel("Seleccione el modo de juego");
      button1 = new JRadioButton("Partida Humano-Maquina", true);
      button1.addActionListener(this);

      button2 = new JRadioButton("Partida Humano-Humano");
      button2.addActionListener(this);
      group = new ButtonGroup();

      group.add(button1);
      group.add(button2);

      aceptar = new JButton("Aceptar");
      aceptar.addActionListener(this);

      seleccionarJugador1 = new JButton("Cargar Jugador 1");
      seleccionarJugador1.addActionListener(this);

      seleccionarJugador2 = new JButton("CargarJugador 2");
      seleccionarJugador2.addActionListener(this);
      seleccionarJugador2.setEnabled(button2.isSelected());

      nuevoJugador1 = new JButton("Crear Nuevo");
      nuevoJugador1.addActionListener(this);

      nuevoJugador2 = new JButton("Crear Nuevo");
      nuevoJugador2.addActionListener(this);
      nuevoJugador2.setEnabled(button2.isSelected());


      JPanel panel= new JPanel();
      JPanel panel2=new JPanel();

      panel.setLayout(new FlowLayout());


      this.setLayout(new GridLayout(0, 1));
      this.setLocation(100, 100);
      this.setTitle("Opciones de Juego");
      this.setResizable(false);
      this.setModal(true);

      this.add(label1);
      this.add(button1);
      this.add(button2);

      panel.add(seleccionarJugador1);
      panel.add(nuevoJugador1);
      panel.add(seleccionarJugador2);
      panel.add(nuevoJugador2);

      panel2.add(aceptar);
      this.add(panel);
      this.add(panel2);

      this.pack();
      this.setVisible(false);

      if (this.partidaTerminada) {
         System.out.println("Terminada");
         if (juego.getMinimaxActivo() && juego.getJugador1() == null) {
            button1.setEnabled(true);
            button1.setSelected(true);
            button2.setEnabled(false);
            seleccionarJugador2.setEnabled(false);
            nuevoJugador2.setEnabled(false);
            seleccionarJugador1.setEnabled(true);
            nuevoJugador1.setEnabled(true);
         }
         if(juego.getMinimaxActivo() && juego.getJugador1() != null){
            button1.setEnabled(true);
            button1.setSelected(true);
            button2.setEnabled(false);
            seleccionarJugador2.setEnabled(false);
            nuevoJugador2.setEnabled(false);
            seleccionarJugador1.setEnabled(true);
            nuevoJugador1.setEnabled(false);
         }
         if (!juego.getMinimaxActivo() && juego.getJugador2() == null && juego.getJugador1() == null) {
            button1.setEnabled(false);
            button2.setSelected(true);
            button2.setSelected(true);
            seleccionarJugador1.setEnabled(true);
            nuevoJugador1.setEnabled(true);
            seleccionarJugador2.setEnabled(true);
            nuevoJugador2.setEnabled(true);
         }
         if (!juego.getMinimaxActivo() && juego.getJugador1() == null && juego.getJugador2() != null) {
            button1.setEnabled(false);
            button2.setSelected(true);
            seleccionarJugador1.setEnabled(true);
            nuevoJugador1.setEnabled(true);
            seleccionarJugador2.setEnabled(false);
            nuevoJugador2.setEnabled(false);
         }
         if (!juego.getMinimaxActivo() && juego.getJugador1() != null && juego.getJugador2() == null) {
            button1.setEnabled(false);
            button2.setSelected(true);
            seleccionarJugador1.setEnabled(false);
            nuevoJugador1.setEnabled(false);
            seleccionarJugador2.setEnabled(true);
            nuevoJugador2.setEnabled(true);
         }
         if(!juego.getMinimaxActivo() && juego.getJugador1() != null && juego.getJugador2() != null){
            button1.setEnabled(false);
            button2.setSelected(true);
            seleccionarJugador1.setEnabled(false);
            nuevoJugador1.setEnabled(false);
            seleccionarJugador2.setEnabled(false);
            nuevoJugador2.setEnabled(true);
         }
      }
   }

   /**
    * Implementacion del metodo de la interfaz ActionListener, que realiza acciones
    * cuando se detecta un click sobre alguno de los elementos del menu escuchados.
    * @param e Evento escuchado.
    */
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == seleccionarJugador1) {
            jugador = cargarJugador();
            juego.setJugador1(jugador);
      }
      if(e.getSource()==seleccionarJugador2){
            jugador = cargarJugador();
            juego.setJugador2(jugador);
      }
      if(e.getSource()==nuevoJugador1){
         nuevoJugador();
         juego.setJugador1(jugador);
      }
      if(e.getSource()==nuevoJugador2){
         nuevoJugador();
         juego.setJugador2(jugador);
      }
      
      if(e.getSource() == button1){
         seleccionarJugador2.setEnabled(false);
         nuevoJugador2.setEnabled(false);
      }
      if(e.getSource() == button2){
         seleccionarJugador2.setEnabled(true);
         nuevoJugador2.setEnabled(true);
      }
      if(e.getSource() == aceptar){
         if(partidaTerminada){
            juego.sumarPuntuacionJugador1();
            juego.sumarPuntuacionJugador2();
         }
         if(button2.isSelected()){
            juego.setMinimaxActivo(false);
            //juego.cambiarModoDeJuego();
         }
         if(button1.isSelected()){

            //Si se habia introducido el jugador 2 y luego se selecciona la opcion de juego
            //contra la maquina, lo volvemos a poner como null
            juego.setJugador2(null);
            juego.setMinimaxActivo(true);

            //juego.cambiarModoDeJuego();
         }
         this.setVisible(false);
      }

   }

   /**
    * Metodo que permite al usuario elegir de forma grafica un archivo de la clase Jugador
    *
    * @return jugadorCargado El objeto de la clase Jugador guardado en el archivo
    */
   private Jugador cargarJugador() {
      fileChooser = new JFileChooser();
      //La extension tiene que coincidir con otp (otelo player)
      fileChooser.setFileFilter(new FileFilter() {

         @Override
         public boolean accept(File f) {
            if (f.getName().substring(f.getName().length() - 4, f.getName().length()).equals(".otp")) {
               return true;
            }
            if (f.isDirectory()) {
               return true;
            }
            return false;
         }

         @Override
         public String getDescription() {
            return ".otp";
         }
      });
      
      fileChooser.setDialogTitle("Elegir archivo");
      int returnVal = fileChooser.showOpenDialog(this);

      if (returnVal == JFileChooser.APPROVE_OPTION) {
         Jugador jugadorCargado = es.cargarJugador(fileChooser.getSelectedFile());
         return jugadorCargado;
      }
      return null;
   }

   /**
    * Metodo que permite crear un nuevo objeto de la clase Jugador de forma grafica
    */
   private void nuevoJugador() {
      dialog1 = new JDialog(this, true);

      JPanel panel1 = new JPanel();
      panel1.setLayout(new GridLayout(2, 2));
      JPanel panel2 = new JPanel();

      textField = new JTextField();
      label2 = new JLabel("Nombre del jugador");
      passwordField = new JPasswordField();
      label3 = new JLabel("Contraseña");

      ok = new JButton("Aceptar");
      ok.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            String nombre = textField.getText();
            String password = passwordField.getPassword().toString();
            jugador = new Jugador(nombre, password);
            //String ruta = guardarJugador(jugador);
            //Opciones.this.jugador.setRutaArchivo(ruta);
            dialog1.setVisible(false);
         }
      });
      panel1.add(label2);
      panel1.add(textField);
      panel1.add(label3);
      panel1.add(passwordField);

      panel2.add(ok);

      dialog1.setLayout(new GridLayout(2, 1));

      dialog1.setSize(400,110);
      dialog1.setResizable(false);
      dialog1.add(panel1);
      dialog1.add(panel2);
      dialog1.setVisible(true);


   }
}



