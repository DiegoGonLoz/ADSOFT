package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar añadir una asociación ya existente
 *
 * @author Diego Lesma
 */
public class ErrorAnadiendoCiudadanoExistente extends ErrorAnadiendoProponente {
    public ErrorAnadiendoCiudadanoExistente(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage()+
                "\nError por añadir ciudadano ya existente";
    }
}
