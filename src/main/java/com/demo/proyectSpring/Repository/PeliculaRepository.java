package com.demo.proyectSpring.Repository;

import com.demo.proyectSpring.Model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    @Query(value = "select * from pelicula;", nativeQuery = true)
    List<Pelicula> getAllPeliculaRepository();

    @Query(value = "select * from pelicula WHERE id_pelicula = :id_pelicula", nativeQuery = true)
    List<Pelicula> getPeliculaByIDRepository(@Param("id_pelicula") Integer id_pelicula);

    @Query(value = "select * from pelicula_salacine ps, pelicula p, sala s WHERE ps.id_pelicula = p.id_pelicula AND ps.id_sala = s.id_sala AND ps.id_sala = :id_sala AND p.nombre = :nombre", nativeQuery = true)
    List<Map<String, Object>> getPeliculaByNameAndID(@Param("id_sala") Integer id_sala, @Param("nombre") String nombre);

    @Query(value = "select p.nombre, p.duracion, ps.fecha_publicacion, ps.fecha_fin from pelicula_salacine ps, pelicula p WHERE ps.id_pelicula = p.id_pelicula AND ps.fecha_publicacion = :fecha_publicacion", nativeQuery = true)
    List<Map<String, Object>> getPeliculasByFechaPublicacion(@Param("fecha_publicacion") String fecha_publicacion);

    @Query(value = "SELECT IF(count(*) < 3, 'SALA CASI VACIA',IF(COUNT(*) > 5, 'SALA LLENA', 'SALA CASI LLENA')) as estado_sala, COUNT(*) as asientos_ocupados FROM pelicula_salacine ps, pelicula p, sala s WHERE ps.id_pelicula = p.id_pelicula AND ps.id_sala = s.id_sala AND s.nombre = :nombre", nativeQuery = true)
    List<Map<String, Object>> getCountPeliculasByName(@Param("nombre") String nombre);

    Pelicula save(Pelicula pelicula);

    void delete(Pelicula pelicula);
}
