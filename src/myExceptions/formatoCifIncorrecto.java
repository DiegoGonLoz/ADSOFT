package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar usar un cif incorrecto
 *
 * @author Diego Lesma
 */
public class formatoCifIncorrecto extends IllegalArgumentException {
    /**
     * Constructor de formatoCifIncorrecto
     * @param message mensaje de error
     */
    public formatoCifIncorrecto(String message) {
        super(message);
    }

    /**
     * Metodo toString de formatoCifIncorrecto
     * @return string con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage()+
                "\nError en el formato del cif de la fundacion";
    }
}
