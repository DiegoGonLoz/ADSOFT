package myExceptions;

public class presupuestoMenorIgualCero extends IllegalArgumentException {
    double presupuestoErroneo;

    public presupuestoMenorIgualCero(String message, double presupuestoErroneo) {
        super(message);
        this.presupuestoErroneo = presupuestoErroneo;
    }

    @Override
    public String toString() {
        return this.getMessage() +
                "\nPresupuesto erróneo: " + presupuestoErroneo;
    }
}
