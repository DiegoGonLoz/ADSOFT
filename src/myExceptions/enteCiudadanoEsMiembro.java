package myExceptions;

/**
 * Clase tipo excepcion que ocurre al intentar anadir un miembro ya existente
 */
public class enteCiudadanoEsMiembro extends IllegalArgumentException {
    /**
     * Constructor de enteCiudadanoEsMiembro
     * @param message mensaje que se le pasa al constructor
     */
    public enteCiudadanoEsMiembro(String message) {
        super(message);
    }

    /**
     * Metodo toString de la clase enteCiudadanoEsMiembro
     * @return string con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage()+
                "\nError, contiene a ciudadanos y/o asociaciones apoyando al proyecto";
    }
}
