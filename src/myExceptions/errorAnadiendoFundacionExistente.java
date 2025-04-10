package myExceptions;

public class errorAnadiendoFundacionExistente extends errorAnadiendoProponente {
    public errorAnadiendoFundacionExistente(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage()+
                "\nError por añadir fundacion ya existente";
    }
}
