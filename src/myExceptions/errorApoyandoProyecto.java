package myExceptions;

public class errorApoyandoProyecto extends IllegalArgumentException {
    public errorApoyandoProyecto(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
