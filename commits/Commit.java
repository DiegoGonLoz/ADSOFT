package commits;

import java.util.Date;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Commit {
    private static int lastId = 0;
    private static String defaultUserName = "defaultUser";
    private static String defaultDescription = "No description provided";

    private String userName;
    private Date date;
    private String description;
    private int id;
    private String code;
    private List<Change> changes;

    public Commit(String userName, String description, List <Change> changes) {
        this.userName = (userName != null && !userName.isEmpty()) ? userName : defaultUserName;
        this.date = new Date();
        this.description = (description != null && !description.isEmpty()) ? description : defaultDescription;
        this.id = generateId();
        this.code = generateCode();
        this.changes = (changes != null) ? changes : new ArrayList<>();
    }

    public Commit(List<Change> changes) {
        this(null, null, changes);
    }

    public Commit(String userName, String description) {
        this(userName, description, new ArrayList<>());
    }

    public Commit() {
        this(null, null, new ArrayList<>());
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

    public void setUserName(String userName) {
        this.userName = (userName != null && !userName.isEmpty()) ? userName : defaultUserName;
    }

    public void setDescription(String description) {
        this.description = (description != null && !description.isEmpty()) ? description : defaultDescription;
    }

    public String printChange(Change c){
        return "";
    }
    public abstract String printCommit();
    public abstract int totalLinesMoved();
}
