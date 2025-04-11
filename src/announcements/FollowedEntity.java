package announcements;

/**
 * Interfaz que representa una FollowedEntity
 *
 * @author Diego Gonzalez
 */
public interface FollowedEntity {
    /**
     * Metodo follow
     * @param f follower
     * @return true o false
     */
    public boolean follow(Follower f);

    /**
     * Metodo unfollow
     * @param f follower
     * @return true o false
     */
    public boolean unfollow(Follower f);

    /**
     * Metodo announce
     * @param t anucio por anunciar
     */
    public void announce(Announcement t);

    /**
     * Metodo follow con estrategia
     * @param f follower
     * @param ns tipo de estrategia
     * @return true o false
     */
    public boolean follow(Follower f, AnnouncementStrategy ns);
}
