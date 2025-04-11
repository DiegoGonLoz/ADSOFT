package proponentes;

import announcements.*;
import myExceptions.*;
import proyectos.ProyectoCiudadano;
import proyectos.ProyectoParticipativo;
import sistemas.Sistema;

import java.util.*;

public class Asociacion extends EnteCiudadano implements FollowedEntity {
    private final Ciudadano representante;
    private final Set<EnteCiudadano> miembros = new HashSet<EnteCiudadano>();
    private final Set<FollowerManager> followers = new HashSet<FollowerManager>();

    public Asociacion(String nombre, String contraseña, Ciudadano representante) throws NullPointerException {
        super(nombre, contraseña);

        if(representante == null){
            throw new NullPointerException("Error en el constructor Asociacion: \nRepresentante null");
        }

        this.representante = representante;
    }

    public boolean inscribir(EnteCiudadano ente) throws EnteCiudadanoEsMiembro {
        if(ente instanceof Asociacion){
            if(((Asociacion) ente).representante == this.representante && ((Asociacion) ente).cantidadMiembros() == 1){
                if(miembros.add(ente)){
                    this.announce(new Announcement("Alta de " + ente.getNombre() + " en "+this.getNombre() + " (" + this.cantidadMiembros() + " miembros)"));
                    return true;
                }

                return false;
            }
            throw new InscripcionInviable("Error al inscribir a "+ente.getNombre()+" en la asociacion "+this.getNombre()+": ");
        }
        if(this.esMiembro(ente)){
            throw new EnteCiudadanoEsMiembro("Error al inscribir a "+ente.getNombre()+" en la asociacion "+this.getNombre()+": ");
        }

        if(miembros.add(ente)){
            this.announce(new Announcement("Alta de " + ente.getNombre() + " en "+this.getNombre() + " (" + this.cantidadMiembros() + " miembros)"));
            return true;
        }

        return false;
    }

    public boolean darDeBaja(Ciudadano ciudadano){
        return miembros.remove(ciudadano);
    }

    public void proponer(ProyectoParticipativo proyecto){
        if(Sistema.getInstance().proponerProyecto(proyecto)){
            this.apoyar(proyecto);
        }
    }

    @Override
    public boolean apoyar(ProyectoParticipativo proyecto){
        if(super.apoyar(proyecto)){
            announce(new Announcement(this.getNombre() + " da apoyo al proyecto " + proyecto.getTitulo() + " (" + proyecto.obtenerApoyos() + " apoyos)"));
            return true;
        }

        return false;
    }

    public boolean esMiembro(EnteCiudadano enteCiudadano){
        if(this.equals(enteCiudadano)){
            return true;
        }

        if(this.representante.equals(enteCiudadano)){
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
        int total = 1;
        for(EnteCiudadano ente : miembros){
            total += ente.cantidadMiembros();
            if(ente instanceof Asociacion){
                total -= 1;
            }
        }

        return total;
    }

    public Set<Ciudadano> todosLosCiudadanos() {
        Set<Ciudadano> ciudadanos = new HashSet<Ciudadano>(List.of(this.representante));
        for(EnteCiudadano ente : miembros){
            ciudadanos.addAll(ente.todosLosCiudadanos());
        }

        return ciudadanos;
    }

    @Override
    public ErrorAnadiendoAsociacionExistente repetido() {
        return new ErrorAnadiendoAsociacionExistente("Asociacion " +this.getNombre()+" ya existente");
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
    public int hashCode(){
        return Objects.hash(this.nombre);
    }

    @Override
    public boolean follow(Follower f) {
        return followers.add(new FollowerManagerAllMessages(f));
    }

    @Override
    public boolean unfollow(Follower f) {
        return followers.remove(f);
    }

    @Override
    public void announce(Announcement t) {
        for(FollowerManager follower : followers){
            follower.announce(t);
        }
    }

    @Override
    public boolean follow(Follower f, AnnouncementStrategy ns) {
        return switch (ns) {
            case AnnouncementStrategy.ONE_IN_N_MESSAGES -> followers.add(new FollowerManagerFrecuency(f));
            default -> followers.add(new FollowerManagerAllMessages(f));
        };
    }

    public boolean changeUmbral(Follower f, int umbral){
        for(FollowerManager follower : followers){
            if(follower.getFollower().equals(f)){
                follower.setUmbral(umbral);
                return true;
            }
        }
        return false;
    }
}
