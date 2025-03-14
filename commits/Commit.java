package commits;

import java.util.Date;
import java.util.UUID;
import java.util.List;
import changes.Change;

public abstract class Commit {
    private static int lastId = 0;
    private static String defaultUserName = "defaultUser";
    private static String defaultDescription = "No description provided";

    protected final String userName;
    protected final Date date;
    protected final String description;
    protected final int id;
    protected final String code;

    public Commit(String userName, String description) {
        this.userName = (userName != null && !userName.isEmpty()) ? userName : defaultUserName;
        this.date = new Date();
        this.description = (description != null && !description.isEmpty()) ? description : defaultDescription;
        this.id = generateId();
        this.code = generateCode();
    }

    public Commit() {
        this(null, null);
    }

    private synchronized int generateId() {
        lastId++;
        if (lastId > 99999) {
            throw new IllegalStateException("Se ha alcanzado el límite de IDs únicos (99999).");
        }
        return lastId;
    }

    public String generateCode(){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, 15);
    }

    public int getId() {
        return id;
    }

    public String getDescription() { return description; }

    public Date getDate() {
        return date;
    }

    public void setDefaultUserName(String userName) {
        defaultUserName = userName;
    }

    public void setDefaultDescription(String description) {
        defaultDescription = description;
    }

    public abstract List<Change> changes();
    @Override
    public abstract String toString();

    public abstract int totalLinesMoved();
}
