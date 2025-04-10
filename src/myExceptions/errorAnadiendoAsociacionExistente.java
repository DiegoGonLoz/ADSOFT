package myExceptions;

public class errorAnadiendoAsociacionExistente extends errorAnadiendoProponente {
    public errorAnadiendoAsociacionExistente(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage()+
                "\nError por añadir asociacion ya existente";
    }
}
