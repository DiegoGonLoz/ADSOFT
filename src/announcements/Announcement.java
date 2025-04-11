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

    /**
     * Metodo equals
     * @param obj objeto a comparar
     * @return true o false
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if (this == obj) {
            return true;
        }
        if (obj instanceof Announcement) {
            return this.message.equals(((Announcement)obj).message);
        }
        return false;
    }

    /**
     * Metodo hashCode
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return this.message.hashCode();
    }

    /**
     * Metodo toString
     * @return string con el mensaje
     */
    @Override
    public String toString() {
        return this.message;
    }
}
