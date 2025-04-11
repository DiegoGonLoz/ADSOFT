package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar usar un porcentaje fuera de los parámetros permitidos
 *
 * @author Diego Lesma
 */
public class porcentajeInvalido extends IllegalArgumentException {
    /** Porcentaje erroneo*/
    double porcentajeErroneo;

    /**
     * Constructor de la clase porcentajeInvalido
     * @param message mensaje de error
     * @param porcentajeErroneo double con el porcentaje
     */
    public porcentajeInvalido(String message, double porcentajeErroneo) {
        super(message);
        this.porcentajeErroneo = porcentajeErroneo;
    }

    /**
     * Metodo toString de porcentajeInvalido
     * @return String con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage() +
                "\nPorcentaje inválido: " + porcentajeErroneo;
    }
}
