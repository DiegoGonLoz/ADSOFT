package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar añadir proponente
 *
 * @author Diego Lesma
 */
public abstract class ErrorAnadiendoProponente extends IllegalArgumentException {
    /**
     * Constructor de la clase ErrorAnadiendoProponente
     * @param message mensaje de error
     */
    public ErrorAnadiendoProponente(String message) {
        super(message);
    }
}
