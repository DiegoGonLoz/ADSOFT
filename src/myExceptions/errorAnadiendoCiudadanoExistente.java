package myExceptions;

public class errorAnadiendoCiudadanoExistente extends IllegalArgumentException {
    public errorAnadiendoCiudadanoExistente(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage()+
                "\nError por añadir ciudadano ya existente";
    }
}
