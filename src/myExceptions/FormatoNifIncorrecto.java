package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar usar un nif incorrecto
 *
 * @author Diego Lesma
 */
public class FormatoNifIncorrecto extends IllegalArgumentException {
    /**
     * Constructor de la clase FormatoNifIncorrecto
     * @param message mensaje de error
     */
    public FormatoNifIncorrecto(String message) {
        super(message);
    }

    /**
     * Metodo toString de FormatoNifIncorrecto
     * @return string con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage()+
                "Error en el formato del nif del ciudadano";
    }
}
