package announcements;

/**
 * Clase que representa un anuncio
 *
 * @author Diego Gonzalez
 */
public class Announcement implements Comparable<Announcement> {
    /**Mensaje a anunciar*/
    private final String message;

    /**
     * Constructor de la clase Announcement
     * @param message mensaje a mandar
     * @throws NullPointerException no se admiten nulls
     */
    public Announcement(String message) throws NullPointerException {
        if(message == null){
            throw new NullPointerException("Error en el constructor Announcement: Message es null");
        }

        this.message = message;
    }

    /**
     * Metodo compareTo
     * @param o the object to be compared.
     * @return negativo, 0 y positivo según la comparación
     */
    @Override
    public int compareTo(Announcement o) {
        return this.message.compareTo(o.message);
    }
}
