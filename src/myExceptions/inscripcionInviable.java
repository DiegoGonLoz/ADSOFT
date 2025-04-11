package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar hacer una inscripción inviable
 *
 * @author Diego Gonzalez
 */
public class inscripcionInviable extends IllegalArgumentException {
    /**
     * Constructor de la clase inscripcionInviable
     * @param message mensaje de error
     */
    public inscripcionInviable(String message) {
        super(message);
    }

    /**
     * Metodo toString de la clase inscripcionInviable
     * @return string con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage()+
                "\nError por añadir a asociación con distinto representante o no vacía";
    }
}
