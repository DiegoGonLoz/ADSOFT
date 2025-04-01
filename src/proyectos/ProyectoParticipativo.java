package proyectos;

import announcements.FollowedEntity;
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

    public int obtenerVotos(){
        int votos = 0;
        for(EnteCiudadano ente : apoyos){
            votos += ente.cantidadMiembros();
        }
        return votos;
    }

    private boolean apoyoPosible(EnteCiudadano ente) {
        if(proponente.equals(ente)){
            return false;
        }

        if(apoyos.contains(ente)){
            return false;
        }

        for(EnteCiudadano ente2 : apoyos){
            if(ente2.esMiembro(ente)){
                return false;
            }
        }

        return Duration.between(LocalDate.now(), fecha).toDays() <= 60;
    }
}
