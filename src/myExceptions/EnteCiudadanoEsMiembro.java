package myExceptions;

/**
 * Clase tipo excepción que ocurre cuando un EnteCiudadano es miembro ya existente
 *
 * @author Diego Lesma
 */
public class EnteCiudadanoEsMiembro extends IllegalArgumentException {
    /**
     * Constructor de EnteCiudadanoEsMiembro
     * @param message mensaje que se le pasa al constructor
     */
    public EnteCiudadanoEsMiembro(String message) {
        super(message);
    }

    /**
     * Metodo toString de la clase EnteCiudadanoEsMiembro
     * @return string con la información de error
     */
    @Override
    public String toString() {
        return this.getMessage()+
                "\nError, contiene a ciudadanos y/o asociaciones apoyando al proyecto";
    }
}
