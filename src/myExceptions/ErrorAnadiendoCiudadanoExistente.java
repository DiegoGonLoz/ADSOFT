package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar añadir una asociación ya existente
 *
 * @author Diego Lesma
 */
public class ErrorAnadiendoCiudadanoExistente extends ErrorAnadiendoProponente {
    /**
     * Constructor de ErrorAnadiendoCiudadanoExistente
     * @param message mensaje de error
     */
    public ErrorAnadiendoCiudadanoExistente(String message) {
        super(message);
    }

    /**
     * Metodo toString de la clase ErrorAnadiendoCiudadanoExistente
     * @return string con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage()+
                "\nError por añadir ciudadano ya existente";
    }
}
