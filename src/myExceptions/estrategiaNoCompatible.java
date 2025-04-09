package myExceptions;

import announcements.AnnouncementStrategy;

public class estrategiaNoCompatible extends IllegalArgumentException {
    private AnnouncementStrategy ns;

    public estrategiaNoCompatible(String message, AnnouncementStrategy ns) {
        super(message);

        this.ns = ns;
    }

    @Override
    public String toString() {
        return super.toString()+
                "\nEstrategia no compatible con la entidad seguida: " + ns.toString();
    }
}
