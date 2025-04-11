package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar usar un cif incorrecto
 *
 * @author Diego Lesma
 */
public class FormatoCifIncorrecto extends IllegalArgumentException {
    /**
     * Constructor de FormatoCifIncorrecto
     * @param message mensaje de error
     */
    public FormatoCifIncorrecto(String message) {
        super(message);
    }

    /**
     * Metodo toString de FormatoCifIncorrecto
     * @return string con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage()+
                "\nError en el formato del cif de la fundacion";
    }
}
