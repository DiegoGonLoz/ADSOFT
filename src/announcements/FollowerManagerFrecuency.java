package announcements;

/**
 * Clase que representa a FollowerManagerFrecuency
 *
 * @author Diego Gonzalez
 */
public class FollowerManagerFrecuency extends FollowerManager{
    /**Contador tipo espero*/
    private int contador = 1;

    /**
     * Constructo de FollowerManagerFrecuency
     * @param follower follwer
     * @throws NullPointerException no se admiten nulls
     */
    public FollowerManagerFrecuency(Follower follower) throws NullPointerException{
        super(follower);
    }

    /**
     * Metodo announce
     * @param t objeto a anunciar
     */
    @Override
    public void announce(Announcement t) {
        if(this.contador == this.umbral){
            this.follower.receives(t);
            this.contador = 1;
        } else {
            this.contador++;
        }
    }
}
