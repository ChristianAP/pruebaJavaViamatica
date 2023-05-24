package com.demo.proyectSpring.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(schema = "pelicula")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pelicula;
    @Column
    @NotNull(message = "EL NOMBRE NO PUEDE SER UN CAMPO NULO!!")
    @NotBlank(message = "EL NOMBRE NO PUEDE SER UN CAMPO VACIO!!")
    private String nombre;
    @Column
    private int duracion;

    public Pelicula() {
    }

    public Pelicula(int id_pelicula, String nombre, int duracion) {
        this.id_pelicula = id_pelicula;
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public int getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(int id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
