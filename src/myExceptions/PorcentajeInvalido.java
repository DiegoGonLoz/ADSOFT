package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar usar un porcentaje fuera de los parámetros permitidos
 *
 * @author Diego Lesma
 */
public class PorcentajeInvalido extends IllegalArgumentException {
    /** Porcentaje erroneo*/
    double porcentajeErroneo;

    /**
     * Constructor de la clase PorcentajeInvalido
     * @param message mensaje de error
     * @param porcentajeErroneo double con el porcentaje
     */
    public PorcentajeInvalido(String message, double porcentajeErroneo) {
        super(message);
        this.porcentajeErroneo = porcentajeErroneo;
    }

    /**
     * Metodo toString de PorcentajeInvalido
     * @return String con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage() +
                "\nPorcentaje inválido: " + porcentajeErroneo;
    }
}
