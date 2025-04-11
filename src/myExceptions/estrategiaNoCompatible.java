package myExceptions;

import announcements.AnnouncementStrategy;

/**
 * Clase tipo excepción que ocurre al intentar usar una estrategia no compatible
 *
 * @author Diego Gonzalez
 */
public class estrategiaNoCompatible extends IllegalArgumentException {
    /** Objeto AnnounceStrategy*/
    private AnnouncementStrategy ns;

    /**
     * Constructor de estrategiaNoCompatible
     * @param message mensaje de error
     * @param ns tipo de estrategia
     */
    public estrategiaNoCompatible(String message, AnnouncementStrategy ns) {
        super(message);

        this.ns = ns;
    }

    /**
     * Metodo toString de estrategiaNoCompatible
     * @return string con la información de error
     */
    @Override
    public String toString() {
        return super.toString()+
                "\nEstrategia no compatible con la entidad seguida: " + ns.toString();
    }
}
