package announcements;

/**
 * Clase que representa a FollowerManagerAllMessages
 *
 * @author Diego Gonzalez
 */
public class FollowerManagerAllMessages extends FollowerManager {
    /**
     * Constructor de la clase FollowerManagerAllMessages
     * @param follower follower
     * @throws NullPointerException no se admite null
     */
    public FollowerManagerAllMessages(Follower follower) throws NullPointerException{
            super(follower);
    }

    /**
     * Metodo announce
     * @param t objeto a anunciar
     */
    @Override
    public void announce(Announcement t) {
        follower.receives(t);
    }
}
