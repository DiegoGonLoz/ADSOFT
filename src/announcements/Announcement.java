package announcements;

public class Announcement implements Comparable<Announcement> {
    private final String message;

    public Announcement(String message){
        if(message == null){
            throw Exception;
        }

        this.message = message;
    }

    @Override
    public int compareTo(Announcement o) {
        return this.message.compareTo(o.message);
    }
}
