package myExceptions;

public class porcentajeInvalido extends IllegalArgumentException {
    double porcentajeErroneo;

    public porcentajeInvalido(String message, double porcentajeErroneo) {
        super(message);
        this.porcentajeErroneo = porcentajeErroneo;
    }

    @Override
    public String toString() {
        return this.getMessage() +
                "\nPorcentaje inválido: " + porcentajeErroneo;
    }
}
