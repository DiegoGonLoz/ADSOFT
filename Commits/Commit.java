package Commits;

import java.util.Date;

public abstract class Commit {
    private String userName;
    private Date date;
    private String description;
    private IdChangeCommit IdCommit;

    public Commit(String userName, Date date, String description) {
        this.userName = userName;
        this.date = date;
        this.description = description;

    }

    public String generateUserName(){
        return "";
    }
    public String generateDescription(){
        return "";
    }
    public String printChange(Change c){
        return "";
    }
    public abstract String printCommit();
    public abstract int totalLinesMoved();
}
