package announcements;

public interface FollowedEntity {
    public boolean follow(Follower f);

    public boolean unfollow(Follower f);

    public void announce(Announcement t);

    public void follow(Follower f, AnnouncementStrategy ns);
}
