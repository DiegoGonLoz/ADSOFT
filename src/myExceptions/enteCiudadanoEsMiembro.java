package myExceptions;

public class enteCiudadanoEsMiembro extends IllegalArgumentException {
    public enteCiudadanoEsMiembro(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage()+
                "\nError al intentar apoyar proyecto que contiene a ciudadanos y/o asociaciones apoyando al proyecto";
    }
}
