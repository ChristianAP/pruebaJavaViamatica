package com.demo.proyectSpring.Controller;

import com.demo.proyectSpring.Model.MessageAlert;
import com.demo.proyectSpring.Model.Pelicula;
import com.demo.proyectSpring.Service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/pelicula")

@CrossOrigin(origins = { "*" })
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;
    @GetMapping(value = "/")
    public ResponseEntity getAllPeliculas(){
        try {
            List<Pelicula> pelicula_data = peliculaService.getAllPeliculaService();

            if (pelicula_data.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageAlert(404, "NO SE ENCONTRARON REGISTROS!"));
            }
            return ResponseEntity.status(200).body(pelicula_data);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageAlert(500, e.getMessage()));
        }
    }

    @GetMapping(value = "/{id_pelicula}")
    public ResponseEntity getPeliculaBYId(@PathVariable("id_pelicula") int id_pelicula){
        try {
            List<Pelicula> peliculaByID_data = peliculaService.getPeliculaByIDService(id_pelicula);

            if (peliculaByID_data.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageAlert(404, "NO SE ENCONTRARON REGISTROS!"));
            }
            return ResponseEntity.status(200).body(peliculaByID_data);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageAlert(500, e.getMessage()));
        }
    }

    @GetMapping(value = "/findSalaPelicula/{id_sala}/{nombre}")
    public ResponseEntity getPeliculaBYNameAndID(@PathVariable("id_sala") int id_sala, @PathVariable("nombre") String nombre){
        try {
            List<Map<String, Object>> data = peliculaService.getPeliculaByNameAndID(id_sala, nombre);
            if (data.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageAlert(404, "NO SE ENCONTRARON REGISTROS!"));
            }
            return ResponseEntity.status(200).body(data);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageAlert(500, e.getMessage()));
        }
    }

    @GetMapping(value = "/peliculaByFecha/{fecha}")
    public ResponseEntity getPeliculaBYFechaPubli(@PathVariable("fecha") String fecha){
        try {
            List<Map<String, Object>> data = peliculaService.getPeliculaByFechaPublicacionService(fecha);
            if (data.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageAlert(404, "NO SE ENCONTRARON REGISTROS!"));
            }
            return ResponseEntity.status(200).body(data);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageAlert(500, e.getMessage()));
        }
    }

    @GetMapping(value = "/countPelisByName/{nombre}")
    public ResponseEntity getCountPelis(@PathVariable("nombre") String nombre){
        try {
            List<Map<String, Object>> data = peliculaService.getCountPelisByName(nombre);
            if (data.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageAlert(404, "NO SE ENCONTRARON REGISTROS!"));
            }
            return ResponseEntity.status(200).body(data);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageAlert(500, e.getMessage()));
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity createPelicula(@RequestBody  Pelicula pelicula){
        try {
            Pelicula pelicula_save = peliculaService.createPeliculaService(pelicula);
            if (pelicula_save == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageAlert(500, "ERROR AL CREAR EL REGISTRO!"));
            }
            return ResponseEntity.status(200).body(new MessageAlert(200, "REGISTRO GUARDADO CORRECTAMENTE!"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageAlert(500, e.getMessage()));
        }
    }

    @PutMapping(value = "/{id_pelicula}")
    public ResponseEntity updatePelicula(@PathVariable("id_pelicula") int id_pelicula,@RequestBody  Pelicula pelicula){
        try {
            return peliculaService.updatePeliculaService(id_pelicula, pelicula);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageAlert(500, e.getMessage()));
        }
    }

    @DeleteMapping(value = "/{id_pelicula}")
    public ResponseEntity deletePelicula(@PathVariable("id_pelicula") int id_pelicula){
        try {
            return peliculaService.deletePeliculaByID(id_pelicula);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageAlert(500, e.getMessage()));
        }
    }
}
