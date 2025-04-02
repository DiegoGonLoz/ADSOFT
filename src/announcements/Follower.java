package announcements;

public interface Follower extends Comparable<Follower> {
    public void receives(Announcement t);
}
