package tests;

import changes.Change;
import repositories.*;

public class RepositoryTester {
    public static void main (String[] args) {
        User U1 = new User("test1");
        Repository repository = new Repository("Repository", Strategy.ORIGIN, U1);

        User U2 = new User("test2");
        User U3 = new User("test3");

        repository.addUser(U2);
        repository.addUser(U3);

        //new branch

        //new branch from branch

        //get existing branch

        //get non existing branch

        //add existing branch

        //change active branch

        //merge

        //imprimir
    }
}
