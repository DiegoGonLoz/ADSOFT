package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar añadir una fundación ya existente
 *
 * @author Diego Lesma
 */
public class ErrorAnadiendoFundacionExistente extends ErrorAnadiendoProponente {
    /**
     * Constructor de la clase ErrorAnadiendoFundacionExistente
     * @param message mensaje de error
     */
    public ErrorAnadiendoFundacionExistente(String message) {
        super(message);
    }

    /**
     * Metodo toString de la clase ErrorAnadiendoFundacionExistente
     * @return string con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage()+
                "\nError por añadir fundacion ya existente";
    }
}
