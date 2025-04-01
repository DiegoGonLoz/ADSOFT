package proyectos;

import announcements.Announcement;
import announcements.FollowedEntity;
import announcements.Follower;
import proponentes.EnteCiudadano;
import proponentes.Proponente;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ProyectoParticipativo implements FollowedEntity {
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

    private boolean apoyoPosible(EnteCiudadano ente) {
        if(proponente.equals(ente)){
            return false;
        }

        for(EnteCiudadano ente2 : apoyos){
            if(ente2.esMiembro(ente)){
                return false;
            }
        }

        return Duration.between(LocalDate.now(), fecha).toDays() <= 60;
    }

    @Override
    public boolean follow(Follower f) {
        followers.add(f);
    }

    @Override
    public boolean unfollow(Follower f) {

    }

    @Override
    public void announce(Announcement t) {
a
    }

    @Override
    public void follow(Follower f, AnnouncementStrategy ns) {

    }
}
