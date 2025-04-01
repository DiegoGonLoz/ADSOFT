import proponentes.*;
import proyectos.*;
import announcements.*;

import java.util.*;

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

    public boolean proponerProyecto(ProyectoParticipativo p){
        return this.proyectos.add(p);
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

    public SortedMap<Integer,ProyectoParticipativo> obtenerMapaProyectoApoyos(){
        Comparator<Integer> comparador = new Comparator<Integer>() {
            @Override
            public int compare(Integer s1, Integer s2) {
                return s2-s1;
            }
        };
        SortedMap<Integer,ProyectoParticipativo> mapa = new TreeMap<>(comparador);

        for(ProyectoParticipativo p : proyectos){
            mapa.put(p.obtenerApoyos(), p);
        }
    }

}
