package proponentes;

import announcements.*;
import myExceptions.*;
import proyectos.ProyectoCiudadano;
import proyectos.ProyectoParticipativo;
import sistemas.Sistema;

import java.util.*;

/**
 * Clase que representa a una asociacion
 *
 * @author Diego Gonzalez
 */
public class Asociacion extends EnteCiudadano implements FollowedEntity {
    /**Representante de la asociación*/
    private final Ciudadano representante;
    /**Set de miembros*/
    private final Set<EnteCiudadano> miembros = new HashSet<EnteCiudadano>();
    /**Set de followers*/
    private final Set<FollowerManager> followers = new HashSet<FollowerManager>();

    /**
     * Constructor de la clase Asociación
     * @param nombre nombre de la asociación
     * @param contraseña contraseña
     * @param representante representante de la asociación
     * @throws NullPointerException no se admiten null
     */
    public Asociacion(String nombre, String contraseña, Ciudadano representante) throws NullPointerException {
        super(nombre, contraseña);

        if(representante == null){
            throw new NullPointerException("Error en el constructor Asociacion: \nRepresentante null");
        }

        this.representante = representante;
        this.follow(representante);
    }

    /**
     * Metodo para proponer un proyecto
     * @param proyecto proyecto a proponer
     */
    @Override
    public boolean proponer(ProyectoParticipativo proyecto) {
        if(super.proponer(proyecto)){
            this.announce(new Announcement(this.getNombre() + " da apoyo al proyecto " + proyecto.getTitulo() + " (" + proyecto.obtenerApoyos() + " apoyos)"));
            return true;
        }
        return false;
    }

    /**
     * Metodo para inscribir un enteCiudadano
     * @param ente objeto a inscribir
     * @return true o false
     * @throws EnteCiudadanoEsMiembro error al inscribir a un ya miembro
     */
    public boolean inscribir(EnteCiudadano ente) throws EnteCiudadanoEsMiembro {
        if(ente instanceof Asociacion){
            if(((Asociacion) ente).representante == this.representante && ((Asociacion) ente).cantidadMiembros() == 1){
                if(miembros.add(ente) && this.follow(ente)) {
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

        if(miembros.add(ente) && this.follow(ente)){
            this.announce(new Announcement("Alta de " + ente.getNombre() + " en "+this.getNombre() + " (" + this.cantidadMiembros() + " miembros)"));
            return true;
        }

        return false;
    }

    /**
     * Metodo para dar de baja a un ciudadano
     * @param ciudadano ciudadano a dar de baja
     * @return true o false
     */
    public boolean darDeBaja(Ciudadano ciudadano){
        return miembros.remove(ciudadano);
    }

    /**
     * Metodo apoyar
     * @param proyecto proyecto a apoyar
     * @return true o false
     */
    @Override
    public boolean apoyar(ProyectoParticipativo proyecto){
        if(super.apoyar(proyecto)){
            announce(new Announcement(this.getNombre() + " da apoyo al proyecto " + proyecto.getTitulo() + " (" + proyecto.obtenerApoyos() + " apoyos)"));
            return true;
        }

        return false;
    }

    /**
     * Metodo para saber si un ente es miembro
     * @param enteCiudadano ente a comparar
     * @return true o false
     */
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

    /**
     * Metodo para calcular la cantidad de miembros
     * @return cantidad de miembros
     */
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

    /**
     * Metodo para obtener a todos los ciudadanos
     * @return Set de ciudadanos
     */
    public Set<Ciudadano> todosLosCiudadanos() {
        Set<Ciudadano> ciudadanos = new HashSet<Ciudadano>(List.of(this.representante));
        for(EnteCiudadano ente : miembros){
            ciudadanos.addAll(ente.todosLosCiudadanos());
        }

        return ciudadanos;
    }

    /**
     * Metodo repetido
     * @return excepción error añadiendo asociación
     */
    @Override
    public ErrorAnadiendoAsociacionExistente repetido() {
        return new ErrorAnadiendoAsociacionExistente("Asociacion " +this.getNombre()+" ya existente");
    }

    /**
     * Metodo receives de un anuncio
     * @param t anuncio a recibir
     */
    public void receives(Announcement t) {
        for(EnteCiudadano ente : miembros){
            ente.receives(t);
        }
    }

    /**
     * Metodo toString
     * @return string con la información importante de la asociación
     */
    @Override
    public String toString() {
        return this.nombre + " <asociacion con "+this.cantidadMiembros()+" ciudadanos>";
    }

    /**
     * Metodo equals
     * @param obj objeto a comparar
     * @return true o false
     */
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

    /**
     * Metodo hashCode
     * @return hashCode del objeto
     */
    @Override
    public int hashCode(){
        return Objects.hash(this.nombre);
    }

    /**
     * Metodo follow en asociación
     * @param f follower
     * @return true o false
     */
    @Override
    public boolean follow(Follower f) {
        return followers.add(new FollowerManagerAllMessages(f));
    }

    /**
     * Metodo unfollow en asociación
     * @param f follower
     * @return true o false
     */
    @Override
    public boolean unfollow(Follower f) {
        return followers.remove(f);
    }

    /**
     * Metodo announce
     * @param t anuncio por anunciar
     */
    @Override
    public void announce(Announcement t) {
        for(FollowerManager follower : followers){
            follower.announce(t);
        }
    }

    /**
     * Metodo follow con estrategia en asociación
     * @param f follower
     * @param ns estrategia a seguir
     * @return true o false
     */
    @Override
    public boolean follow(Follower f, AnnouncementStrategy ns) {
        return switch (ns) {
            case AnnouncementStrategy.ONE_IN_N_MESSAGES -> followers.add(new FollowerManagerFrecuency(f));
            default -> followers.add(new FollowerManagerAllMessages(f));
        };
    }

    /**
     * Metodo changeUmbral
     * @param f follower
     * @param umbral nuevo umbral
     * @return true o false
     */
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
