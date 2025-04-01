package proponentes;

import announcements.Announcement;
import announcements.FollowedEntity;
import announcements.Follower;
import proyectos.ProyectoParticipativo;

import java.util.LinkedList;
import java.util.List;

public class Asociacion extends EnteCiudadano implements FollowedEntity {
    private final Ciudadano representante;
    private final List<EnteCiudadano> miembros = new LinkedList<EnteCiudadano>();
    private final List<Follower> followers = new LinkedList<Follower>();

    public Asociacion(String nombre, String contraseña, Ciudadano representante){
        super(nombre, contraseña);

        if(representante == null){
            throw Exception;
        }

        this.representante = representante;
    }

    public boolean inscribir(EnteCiudadano ente){
        if(this.esMiembro(ente)){
            return false;
        }

        return miembros.add(ente);
    }

    public boolean darDeBaja(Ciudadano ciudadano){
        return miembros.remove(ciudadano);
    }

    public void proponer(ProyectoParticipativo proyecto){
        - En proponer llamar a apoyar;
    }

    public void incribirse(Asociacion asociacion){
        if(asociacion == null){
            return;
        }
        if(this.representante == asociacion.representante && this.miembros.size() == 0){
            if(asociacion.inscribir(this)){
                inscrito.add(asociacion);
            }
        }
    }

    public boolean esMiembro(EnteCiudadano enteCiudadano){
        if(this.equals(enteCiudadano)){
            return true;
        }
        for(EnteCiudadano ente : miembros){
            if(ente.esMiembro(enteCiudadano)){
                return true;
            }
        }

        return false;
    }

    public int cantidadMiembros(){
        int total = 0;
        for(EnteCiudadano ente : miembros){
            total += ente.cantidadMiembros();
        }

        return total;
    }

    public void receives(Announcement t) {
        for(EnteCiudadano ente : miembros){
            ente.receives(t);
        }
    }

    @Override
    public String toString() {
        return this.nombre + " <asociacion con "+this.cantidadMiembros()+" ciudadanos>";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if (this == obj) {
            return true;
        }
        if (obj instanceof Asociacion) {
            return this.nombre.equals(((Asociacion)obj).nombre);
        }
        return false;
    }

    @Override
    public boolean follow(Follower f) {
        return followers.add(f);
    }

    @Override
    public boolean unfollow(Follower f) {
        return followers.remove(f);
    }

    @Override
    public void announce(Announcement t) {
        for(Follower follower : followers){
            follower.receives(t);
        }
    }

    @Override
    public void follow(Follower f, AnnouncementStrategy ns) {

    }
}
