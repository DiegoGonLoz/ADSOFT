package myExceptions;

public class proyectoPropuestoPorSiMismo extends IllegalArgumentException {
    public proyectoPropuestoPorSiMismo(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage()+
                "\nError al intentar apoyar proyecto propuesto por si mismo";
    }
}
