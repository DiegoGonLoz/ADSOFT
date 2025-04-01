import proponentes.*;
import proyectos.*;
import announcements.*;

import java.util.List;
import java.util.Objects;

public class Sistema {
    List<Proponente> proponentes;
    List<ProyectoParticipativo> proyectos;
    List<Announcement> anuncios;

    public Sistema(List<Proponente> proponentes, List<ProyectoParticipativo> proyectos, List<Announcement> anuncios) {
        this.proponentes = proponentes;
        this.proyectos = proyectos;
        this.anuncios = anuncios;
    }

    public boolean existeCiudadano(String nif){
        return false;
    }

    public boolean existeFundacion(String cif){
        return false;
    }

    public Ciudadano obtenerCiudadano(String nombre){
        return null;
    }

    public List<Ciudadano> todosLosCiudadanos(){
        return null;
    }

    public List<ProyectoParticipativo> proyectosRegistrados(){
        return this.proyectos;
    }

    public ProyectoParticipativo obtenerProyecto(String nombre){
        for(ProyectoParticipativo p : proyectos){
            if(Objects.equals(p.getTitulo(), nombre)){
                return p;
            }
        }
        throw new IllegalArgumentException("El proyecto no existe");
    }

    public ProyectoParticipativo obtenerProyecto(int id){
        for(ProyectoParticipativo p : proyectos){
            if(Objects.equals(p.getCodigo(), id)){
                return p;
            }
        }
        throw new IllegalArgumentException("El proyecto no existe");
    }


}
