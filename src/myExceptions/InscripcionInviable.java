package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar hacer una inscripción inviable
 *
 * @author Diego Gonzalez
 */
public class InscripcionInviable extends IllegalArgumentException {
    /**
     * Constructor de la clase InscripcionInviable
     * @param message mensaje de error
     */
    public InscripcionInviable(String message) {
        super(message);
    }

    /**
     * Metodo toString de la clase InscripcionInviable
     * @return string con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage()+
                "\nError por añadir a asociación con distinto representante o no vacía";
    }
}
