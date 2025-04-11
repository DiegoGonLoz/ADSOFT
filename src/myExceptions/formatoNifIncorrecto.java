package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar usar un nif incorrecto
 *
 * @author Diego Lesma
 */
public class formatoNifIncorrecto extends IllegalArgumentException {
    /**
     * Constructor de la clase formatoNifIncorrecto
     * @param message mensaje de error
     */
    public formatoNifIncorrecto(String message) {
        super(message);
    }

    /**
     * Metodo toString de formatoNifIncorrecto
     * @return string con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage()+
                "Error en el formato del nif del ciudadano";
    }
}
