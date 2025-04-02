package announcements;

public class FollowerManagerFrecuency extends FollowerManager{
    private int contador = 1;

    public FollowerManagerFrecuency(Follower follower) {
        super(follower);
    }

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
