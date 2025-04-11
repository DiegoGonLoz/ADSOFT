package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar apoyar un proyecto propuesto por sí mismo
 *
 * @author Diego Lesma
 */
public class proyectoPropuestoPorSiMismo extends IllegalArgumentException {
    /**
     * Constructor de la clase proyectoPropuestoPorSiMismo
     * @param message mensaje de error
     */
    public proyectoPropuestoPorSiMismo(String message) {
        super(message);
    }

    /**
     * Metodo toString de la clase proyectoPropuestoPorSiMismo
     * @return String con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage()+
                "\nError al intentar apoyar proyecto propuesto por si mismo";
    }
}
