package announcements;

public abstract class FollowerManager {
    protected final Follower follower;
    protected int umbral = 1;

    public FollowerManager(Follower follower){
        throw

        this.follower = follower;
    }

    public abstract void announce(Announcement t);

    public void setUmbral(int n){
        throw
        this.umbral = n;
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
}
