package myExceptions;

public class inscripcionInviable extends IllegalArgumentException {
    public inscripcionInviable(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage()+
                "\nError por añadir a asociación con distinto representante o no vacía";
    }
}
