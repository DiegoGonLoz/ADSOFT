package myExceptions;

public class formatoCifIncorrecto extends IllegalArgumentException {
    public formatoCifIncorrecto(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage()+
                "\nError en el formato del cif de la fundacion";
    }
}
