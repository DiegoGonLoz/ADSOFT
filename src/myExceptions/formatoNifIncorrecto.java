package myExceptions;

public class formatoNifIncorrecto extends IllegalArgumentException {
    public formatoNifIncorrecto(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage()+
                "\nError en el formato del nif del ciudadano";
    }
}
