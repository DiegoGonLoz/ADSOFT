package announcements;

/**
 * Clase que representa un follower manager
 *
 * @author Diego Gonzalez
 */
public abstract class FollowerManager{
    /**Objeto follower*/
    protected final Follower follower;
    /**Umbral*/
    protected int umbral = 1;

    /**
     * Constructor de la clase FollowerManager
     * @param follower follower
     * @throws NullPointerException no se admiten null
     */
    public FollowerManager(Follower follower) throws NullPointerException{
        if(follower == null){
            throw new NullPointerException("Error en el constructor FollowerManager: Follower null");
        }

        this.follower = follower;
    }

    /**
     * Metodo announce
     * @param t objeto a anunciar
     */
    public abstract void announce(Announcement t);

    /**
     * Getter de follower
     * @return objeto tipo follower
     */
    public Follower getFollower(){
        return this.follower;
    }

    /**
     * Setter de Umbral
     * @param n nuevo umbral
     * @return true o false
     */
    public boolean setUmbral(int n){
        if(n >= 1){
            this.umbral = n;
            return true;
        }

        return false;
    }

    /**
     * Metodo equals
     * @param obj objeto a comparar
     * @return true o false
     */
    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if (this == obj) {
            return true;
        }
        if (obj instanceof FollowerManager) {
            return this.follower.equals(((FollowerManager)obj).follower);
        }
        return false;
    }

    /**
     * Metodo hashCode
     * @return hashCode del objeto
     */
    @Override
    public int hashCode(){
        return follower.hashCode();
    }
}
