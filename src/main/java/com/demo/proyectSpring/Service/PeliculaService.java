package com.demo.proyectSpring.Service;

import com.demo.proyectSpring.Model.MessageAlert;
import com.demo.proyectSpring.Model.Pelicula;
import com.demo.proyectSpring.Repository.PeliculaRepository;
import com.demo.proyectSpring.ServiceImp.PeliculaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PeliculaService implements PeliculaServiceImp {
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Override
    public List<Pelicula> getAllPeliculaService() {
        List<Pelicula> pelicula_data = peliculaRepository.getAllPeliculaRepository();
        return pelicula_data;
    }

    @Override
    public List<Pelicula> getPeliculaByIDService(int id_pelicula) {
        return peliculaRepository.getPeliculaByIDRepository(id_pelicula);
    }

    @Override
    public List<Map<String, Object>> getPeliculaByNameAndID(int id_sala, String nombre) {
        return peliculaRepository.getPeliculaByNameAndID(id_sala, nombre);
    }

    @Override
    public List<Map<String, Object>> getPeliculaByFechaPublicacionService(String fecha_publicacion) {
        return peliculaRepository.getPeliculasByFechaPublicacion(fecha_publicacion);
    }

    @Override
    public List<Map<String, Object>> getCountPelisByName(String nombre) {
        return peliculaRepository.getCountPeliculasByName(nombre);
    }


    @Override
    public Pelicula createPeliculaService(Pelicula pelicula) {
        return peliculaRepository.save((pelicula));
    }

    @Override
    public ResponseEntity updatePeliculaService(int id_pelicula, Pelicula pelicula) {
        Optional<Pelicula> exist_pelicula = peliculaRepository.findById((long) id_pelicula);
        if (exist_pelicula.isEmpty()){
            return ResponseEntity.status(404).body(new MessageAlert(404, "NO SE ENCONTRARON REGISTROS RELACIONADOS AL DATO INGRESADO!"));
        }

        Pelicula pelicula_selected = exist_pelicula.get();
        pelicula_selected.setNombre(pelicula.getNombre());
        pelicula_selected.setDuracion(pelicula.getDuracion());

        peliculaRepository.save(pelicula_selected);
        return ResponseEntity.status(200).body(new MessageAlert(200, "REGISTRO ACTUALIZADO CORRECTAMENTE!!"));
    }

    @Override
    public ResponseEntity deletePeliculaByID(int id_pelicula) {
        Optional<Pelicula> exist_pelicula = peliculaRepository.findById((long) id_pelicula);
        if (exist_pelicula.isEmpty()){
            return ResponseEntity.status(404).body(new MessageAlert(404, "NO SE ENCONTRARON REGISTROS RELACIONADOS AL DATO INGRESADO!"));
        }

        Pelicula pelicula_selected = exist_pelicula.get();
        peliculaRepository.delete(pelicula_selected);
        return ResponseEntity.status(200).body(new MessageAlert(200, "REGISTRO ELIMINADO CORRECTAMENTE!"));
    }
}
