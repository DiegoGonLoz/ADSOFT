package announcements;

public abstract class FollowerManager{
    protected final Follower follower;
    protected int umbral = 1;

    public FollowerManager(Follower follower) throws NullPointerException{
        if(follower == null){
            throw new NullPointerException("Error en el constructor FollowerManager: Follower null");
        }

        this.follower = follower;
    }

    public abstract void announce(Announcement t);

    public Follower getFollower(){
        return this.follower;
    }

    public boolean setUmbral(int n){
        if(n >= 1){
            this.umbral = n;
            return true;
        }

        return false;
    }

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

    @Override
    public int hashCode(){
        return follower.hashCode();
    }
}
