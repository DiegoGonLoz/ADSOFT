package commits;

import java.util.Date;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import changes.Change;

public abstract class Commit {
    private static int lastId = 0;
    private static String defaultUserName = "defaultUser";
    private static String defaultDescription = "No description provided";

    protected String userName;
    protected Date date;
    protected String description;
    protected int id;
    protected String code;

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

    public void setUserName(String userName) {
        this.userName = (userName != null && !userName.isEmpty()) ? userName : defaultUserName;
    }

    public void setDescription(String description) {
        this.description = (description != null && !description.isEmpty()) ? description : defaultDescription;
    }

    public String printChange(Change c){
        return c.toString();
    }
    @Override
    public abstract String toString();

    public abstract int totalLinesMoved();
}
