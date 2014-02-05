
package othello;

/**
 * Interfaz que debe ser implementada por todas las clases que quieran observar
 * los cambios producidos en el tablero logico.
 *
 * @author Ana Rosa Bernal y David Aguilera
 */
public interface Vista {

   /**
    * Declaracion del metodo modelChanged, que realiza las operaciones indicadas
    * por las clases que lo implementan cuando se produce un cambio en el tablero
    * logico.
    */
    public void modelChanged();

}
