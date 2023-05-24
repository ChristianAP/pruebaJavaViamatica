package com.demo.proyectSpring.ServiceImp;

import com.demo.proyectSpring.Model.Pelicula;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PeliculaServiceImp {
    List<Pelicula> getAllPeliculaService();

    List<Pelicula> getPeliculaByIDService(int id_pelicula);
    List<Map<String, Object>> getPeliculaByNameAndID(int id_sala, String nombre);

    List<Map<String, Object>> getPeliculaByFechaPublicacionService(String fecha_publicacion);
    List<Map<String, Object>> getCountPelisByName(String nombre);
    Pelicula createPeliculaService(Pelicula pelicula);

    ResponseEntity updatePeliculaService(int id_pelicula, Pelicula pelicula);

    ResponseEntity deletePeliculaByID(int id_pelicula);
}
