package proyectos;

import announcements.*;
import myExceptions.enteCiudadanoEsMiembro;
import myExceptions.errorApoyandoProyecto;
import myExceptions.proyectoMasDe60Dias;
import myExceptions.proyectoPropuestoPorSiMismo;
import proponentes.Ciudadano;
import proponentes.EnteCiudadano;
import proponentes.Proponente;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

public abstract class ProyectoParticipativo implements FollowedEntity, Comparable<ProyectoParticipativo> {
    private final int codigo;
    private final LocalDate fecha;
    private final LocalTime hora;
    private final String titulo;
    private final String descripcion;
    private final Proponente proponente;
    private LocalDateTime lastApoyo;
    protected final Set<EnteCiudadano> apoyos;
    private final Set<FollowerManager> followers;
    private static int contador_id=0;

    public ProyectoParticipativo(String titulo, String descripcion, Proponente proponente) {
        this.codigo = contador_id;
        contador_id++;
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.proponente = proponente;
        this.lastApoyo = LocalDateTime.now();
        this.apoyos = new HashSet<EnteCiudadano>();
        this.followers = new HashSet<FollowerManager>();
    }

    public String getTitulo() {
        return titulo;
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDateTime getLastApoyo() {
        return lastApoyo;
    }

    public boolean sumarApoyo(EnteCiudadano ente) throws errorApoyandoProyecto {
        try {
            if (apoyoPosible(ente)) {
                apoyos.removeIf(ente::esMiembro);
                apoyos.add(ente);
                this.actualizarApoyo();
                return true;
            }
        } catch (proyectoPropuestoPorSiMismo | enteCiudadanoEsMiembro | proyectoMasDe60Dias e) {
            throw new errorApoyandoProyecto("Error en metodo apoyar:"+ e);
        }

        return false;
    }

    public void actualizarApoyo(){
        this.lastApoyo = LocalDateTime.now();
    }

    public int obtenerApoyos(){
        int numApoyos = 0;
        for(EnteCiudadano ente : apoyos){
            numApoyos += ente.cantidadMiembros();
        }
        return numApoyos;
    }

    public Set<Ciudadano> todosLosCiudadanos(){
        Set<Ciudadano> ciudadanos = new HashSet<Ciudadano>();
        for(EnteCiudadano ente : apoyos){
            ciudadanos.addAll(ente.todosLosCiudadanos());
        }
        return ciudadanos;
    }

    private boolean apoyoPosible(EnteCiudadano ente) throws proyectoPropuestoPorSiMismo, enteCiudadanoEsMiembro, proyectoMasDe60Dias {
        if(proponente.equals(ente)) {
            throw new proyectoPropuestoPorSiMismo("\nError en ApoyoPosible: ");
        }

        for(EnteCiudadano ente2 : apoyos){
            if(ente2.esMiembro(ente)){
                throw new enteCiudadanoEsMiembro("\nError en ApoyoPosible: ");
            }
        }

        if(ChronoUnit.DAYS.between(LocalDate.now(), fecha) > 60){
            throw new proyectoMasDe60Dias("\nError en ApoyoPosible: ", ChronoUnit.DAYS.between(LocalDate.now(), fecha));
        }

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj instanceof ProyectoCiudadano){
            ProyectoParticipativo p = (ProyectoParticipativo) obj;
            return p.codigo == this.codigo;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.codigo;
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
            case AnnouncementStrategy.WHEN_N_SUPPORTS_ACHIEVED ->
                    followers.add(new FollowerManagerProjectSupport(f, this));
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

    @Override
    public int compareTo(ProyectoParticipativo o) {
        return this.codigo - o.codigo;
    }

    @Override
    public String toString() {
        return this.codigo+ ": " +
                this.titulo + ". " +
                "Proponente: " + proponente;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public LocalTime getHora() {
        return this.hora;
    }

    public Proponente getProponente() {
        return this.proponente;
    }
}
