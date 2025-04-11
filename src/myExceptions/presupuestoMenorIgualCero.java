package myExceptions;

/**
 * Clase tipo excepción que ocurre al intentar usar un presupuesto fuera de los parámetros permitidos
 *
 * @author Diego Lesma
 */
public class presupuestoMenorIgualCero extends IllegalArgumentException {
    /**Presupuesto erroneo*/
    double presupuestoErroneo;

    /**
     * Constructor de la clase presupuestoMenorIgualCero
     * @param message mensaje de error
     * @param presupuestoErroneo double con el presupuesto erroneo
     */
    public presupuestoMenorIgualCero(String message, double presupuestoErroneo) {
        super(message);
        this.presupuestoErroneo = presupuestoErroneo;
    }

    /**
     * Metodo toString de la clase presupuestoMenorIgualCero
     * @return string con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage() +
                "\nPresupuesto erróneo: " + presupuestoErroneo;
    }
}
