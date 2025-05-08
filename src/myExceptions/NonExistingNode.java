package myExceptions;

/**
 * Clase exception que salta al querer usar un nodo no existente
 * @author Diego Gonzalez
 */
public class NonExistingNode extends RuntimeException {
    /**
     * Constructor de la clase NonExistingNode
     * @param message mensaje de error
     */
    public NonExistingNode(String message) {
        super(message);
    }
}
