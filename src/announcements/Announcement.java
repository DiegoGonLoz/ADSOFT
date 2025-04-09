package announcements;

public class Announcement implements Comparable<Announcement> {
    private final String message;

    public Announcement(String message) throws NullPointerException {
        if(message == null){
            throw new NullPointerException("Error en el constructor Announcement: Message es null");
        }

        this.message = message;
    }

    @Override
    public int compareTo(Announcement o) {
        return this.message.compareTo(o.message);
    }
}
