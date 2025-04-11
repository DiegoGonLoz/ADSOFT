package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar apoyar un proyecto propuesto por sí mismo
 *
 * @author Diego Lesma
 */
public class ProyectoPropuestoPorSiMismo extends IllegalArgumentException {
    /**
     * Constructor de la clase ProyectoPropuestoPorSiMismo
     * @param message mensaje de error
     */
    public ProyectoPropuestoPorSiMismo(String message) {
        super(message);
    }

    /**
     * Metodo toString de la clase ProyectoPropuestoPorSiMismo
     * @return String con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage()+
                "\nError al intentar apoyar proyecto propuesto por si mismo";
    }
}
