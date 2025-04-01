package announcements;

public class Announcement {
    private final String message;

    public Announcement(String message) {
        if(message == null){
            throw Exception;
        }

        this.message = message;
    }
}
