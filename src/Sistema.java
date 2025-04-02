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

    public boolean existeCiudadano(Ciudadano ciudadano){
        for(Proponente p : proponentes) {
            if (p.equals(ciudadano)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeFundacion(Fundacion fundacion){
        for(Proponente p : proponentes) {
            if (p.equals(fundacion)) {
                return true;
            }
        }
        return false;
    }

    public Ciudadano obtenerCiudadano(String nombre){
        for(Proponente p : proponentes) {
            if(p instanceof Ciudadano) {
                if (p.getNombre().equals(nombre)) {
                    return (Ciudadano) p;
                }
            }
        }
        return null;
    }

    public List<Ciudadano> todosLosCiudadanos(){
        List<Ciudadano> ciudadanos = new ArrayList<>();
        for(Proponente p : proponentes) {
            if(p instanceof Ciudadano) {
                ciudadanos.add((Ciudadano) p);
            }
        }
        return ciudadanos;
    }

    public boolean proponerProyecto(ProyectoParticipativo p){
        return this.proyectos.add(p);
    }

    public List<ProyectoParticipativo> proyectosRegistrados(){
        return this.proyectos;
    }

    public ProyectoParticipativo obtenerProyecto(String nombre){
        for(ProyectoParticipativo p : proyectos){
            if(nombre.equals(p.getTitulo())){
                return p;
            }
        }
        throw
    }

    public ProyectoParticipativo obtenerProyecto(int id){
        for(ProyectoParticipativo p : proyectos){
            if(id == p.getCodigo()){
                return p;
            }
        }
        throw
    }

    public SortedMap<Integer,ProyectoParticipativo> obtenerMapaProyectoApoyos(){
        SortedMap<Integer,ProyectoParticipativo> mapa = new TreeMap<>(Comparator.reverseOrder());

        for(ProyectoParticipativo p : proyectos){
            mapa.put(p.obtenerApoyos(), p);
        }

        return mapa;
    }

    public Map<ProyectoParticipativo, List<Ciudadano>> obtenerMapaProyectoCiudadanos(){
        Map<ProyectoParticipativo, List<Ciudadano>> mapa = new HashMap<>();

        for(ProyectoParticipativo p : proyectos){
            mapa.put(p, p.todosLosCiudadanos());
        }

        return mapa;
    }

}
