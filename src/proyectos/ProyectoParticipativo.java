package proyectos;

import announcements.Announcement;
import announcements.AnnouncementStrategy;
import announcements.FollowedEntity;
import announcements.Follower;
import myExceptions.enteCiudadanoEsMiembro;
import myExceptions.proyectoMasDe60Dias;
import myExceptions.proyectoPropuestoPorSiMismo;
import proponentes.Asociacion;
import proponentes.Ciudadano;
import proponentes.EnteCiudadano;
import proponentes.Proponente;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ProyectoParticipativo implements FollowedEntity, Comparable<ProyectoParticipativo> {
    private final int codigo;
    private final LocalDate fecha;
    private final LocalTime hora;
    private String titulo;
    private String descripcion;
    private Proponente proponente;
    private List<EnteCiudadano> apoyos;
    private List<Follower> followers;
    private static int contador_id=0;

    public ProyectoParticipativo(String titulo, String descripcion, Proponente proponente, List<EnteCiudadano> apoyos) {
        this.codigo = contador_id++;
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.proponente = proponente;
        this.apoyos = apoyos;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void apoyar(EnteCiudadano ente) {
        if(apoyoPosible(ente)){
            apoyos.removeIf(ente::esMiembro);
            apoyos.add(ente);
            if(ente instanceof Asociacion){
                ((Asociacion)ente).announce(new Announcement(ente.getNombre() + " da apoyo al proyecto " + this.titulo + " (" + this.obtenerApoyos()+" apoyos)"));
            }
        } else {
            throw Exception
        }
    }

    public int obtenerApoyos(){
        int numApoyos = 0;
        for(EnteCiudadano ente : apoyos){
            numApoyos += ente.cantidadMiembros();
        }
        return numApoyos;
    }

    public List<Ciudadano> todosLosCiudadanos(){
        List<Ciudadano> ciudadanos = new ArrayList<Ciudadano>();
        for(EnteCiudadano ente : apoyos){
            ciudadanos.addAll(ente.todosLosCiudadanos());
        }
        return ciudadanos;
    }

    private boolean apoyoPosible(EnteCiudadano ente) {
        if(proponente.equals(ente)) {
            throw new proyectoPropuestoPorSiMismo("Error en ApoyoPosible: ");
        }

        for(EnteCiudadano ente2 : apoyos){
            if(ente2.esMiembro(ente)){
                throw new enteCiudadanoEsMiembro("Error en ApoyoPosible: ");
            }
        }

        if(Duration.between(LocalDate.now(), fecha).toDays() > 60){
            throw new proyectoMasDe60Dias("Error en ApoyoPosible: ", Duration.between(LocalDate.now(), fecha).toDays());
        }

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj instanceof ProyectoParticipativo){
            ProyectoParticipativo p = (ProyectoParticipativo) obj;
            return p.codigo == this.codigo;
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
    public boolean follow(Follower f, AnnouncementStrategy ns) {

    }

    @Override
    public int compareTo(ProyectoParticipativo o) {
        return this.codigo - o.codigo;
    }
}
