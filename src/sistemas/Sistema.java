package sistemas;

import myExceptions.errorAnadiendoCiudadanoExistente;
import myExceptions.errorAnadiendoFundacionExistente;
import myExceptions.errorAnadiendoProponente;
import myExceptions.errorApoyandoProyecto;
import proponentes.*;
import proyectos.*;
import announcements.*;

import java.time.Duration;
import java.util.*;

public class Sistema {
    private static Sistema sistema;
    Set<Proponente> proponentes;
    Set<ProyectoParticipativo> proyectos;
    Set<Announcement> anuncios;

    private Sistema() {
        this.proponentes = new LinkedHashSet<>();
        this.proyectos = new TreeSet<>();
        this.anuncios = new TreeSet<>();
    }

    public static Sistema getInstance() {
        if (sistema == null) {
            sistema = new Sistema();
        }
        return sistema;
    }

    public boolean existeProponente(Proponente proponente){
        for(Proponente p : proponentes) {
            if (p.equals(proponente)) {
                return true;
            }
        }
        return false;
    }

    public void addProponente(Proponente proponente) throws errorAnadiendoProponente {
        if(existeProponente(proponente)){
            throw proponente.repetido();
        }
        proponentes.add(proponente);
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

    public List<Proponente> todosLosUsuarios(){
        return new LinkedList<>(proponentes);
    }

    public boolean proponerProyecto(ProyectoParticipativo p){
        return this.proyectos.add(p);
    }

    public Set<ProyectoParticipativo> proyectosRegistrados(){
        return this.proyectos;
    }

    public ProyectoParticipativo obtenerProyecto(String nombre){
        for(ProyectoParticipativo p : proyectos){
            if(nombre.equals(p.getTitulo())){
                return p;
            }
        }
        return null;
    }

    public ProyectoParticipativo obtenerProyecto(int id){
        for(ProyectoParticipativo p : proyectos){
            if(id == p.getCodigo()){
                return p;
            }
        }
        return null;
    }

    public SortedMap<ProyectoParticipativo, Integer> obtenerMapaProyectoApoyos(){
        SortedMap<ProyectoParticipativo, Integer> mapa = new TreeMap<>((o1, o2) -> {
            int diff = o2.obtenerApoyos() - o1.obtenerApoyos();
            if(diff == 0){
                return o2.getLastApoyo().compareTo(o1.getLastApoyo());
            }
            return diff;
        });

        for(ProyectoParticipativo p : proyectos){
            mapa.put(p, p.obtenerApoyos());
        }

        return mapa;
    }

    public Map<ProyectoParticipativo, Set<Ciudadano>> obtenerMapaProyectoCiudadanos(){
        Map<ProyectoParticipativo, Set<Ciudadano>> mapa = new TreeMap<>();

        for(ProyectoParticipativo p : proyectos){
            mapa.put(p, p.todosLosCiudadanos());
        }

        return mapa;
    }

}
