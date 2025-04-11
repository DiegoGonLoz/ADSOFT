package myExceptions;

import announcements.AnnouncementStrategy;

/**
 * Clase tipo excepción que ocurre al intentar usar una estrategia no compatible
 *
 * @author Diego Gonzalez
 */
public class EstrategiaNoCompatible extends IllegalArgumentException {
    /** Objeto AnnounceStrategy*/
    private AnnouncementStrategy ns;

    /**
     * Constructor de EstrategiaNoCompatible
     * @param message mensaje de error
     * @param ns tipo de estrategia
     */
    public EstrategiaNoCompatible(String message, AnnouncementStrategy ns) {
        super(message);

        this.ns = ns;
    }

    /**
     * Metodo toString de EstrategiaNoCompatible
     * @return string con la información de error
     */
    @Override
    public String toString() {
        return super.toString()+
                "\nEstrategia no compatible con la entidad seguida: " + ns.toString();
    }
}
