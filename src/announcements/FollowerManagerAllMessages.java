package announcements;

public class FollowerManagerAllMessages extends FollowerManager {
    public FollowerManagerAllMessages(Follower follower) throws NullPointerException{
            super(follower);
    }

    @Override
    public void announce(Announcement t) {
        follower.receives(t);
    }
}
