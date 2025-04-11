package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar añadir una asociación ya existente
 *
 * @author Diego Lesma
 */
public class ErrorAnadiendoAsociacionExistente extends ErrorAnadiendoProponente {
    /**
     * Constructor de ErrorAnadiendoAsociacionExistente
     * @param message mensaje de error
     */
    public ErrorAnadiendoAsociacionExistente(String message) {
        super(message);
    }

    /**
     * Metodo toString de la clase ErrorAnadiendoAsociacionExistente
     * @return string con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage()+
                "\nError por añadir asociacion ya existente";
    }
}
