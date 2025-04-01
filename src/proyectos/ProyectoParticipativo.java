package proyectos;

import announcements.FollowedEntity;
import proponentes.EnteCiudadano;
import proponentes.Proponente;

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

    public void apoyar(EnteCiudadano ente) {

    }

    public int obtenerVotos(){
        return 0;
    }

    private boolean apoyoPosible(EnteCiudadano ente) {
        return false;
    }
}
