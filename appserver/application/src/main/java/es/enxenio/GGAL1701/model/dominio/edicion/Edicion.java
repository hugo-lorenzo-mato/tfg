package es.enxenio.GGAL1701.model.dominio.edicion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import es.enxenio.GGAL1701.controller.util.JsonViews;
import es.enxenio.GGAL1701.model.dominio.agente.Agente;
import es.enxenio.GGAL1701.model.dominio.libro.Libro;
import es.enxenio.GGAL1701.model.dominio.lugar.ciudad.Ciudad;
import es.enxenio.GGAL1701.model.dominio.lugar.pais.Pais;
import es.enxenio.GGAL1701.model.generic.GenericEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hlorenzo on 03/09/2017.
 */
@Entity
@Table(name = "edicion")
public class Edicion extends GenericEntity {

    @Column(name = "titulo")
    private String titulo;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "edicion_dia")
    private Integer edicionDia;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "edicion_mes")
    private Integer edicionMes;

    @Column(name = "edicion_ano")
    private Integer edicionAno;

    @JsonView(JsonViews.DetailedList.class)
    @ManyToOne
    @JoinColumn(name="ciudad_id")
    private Ciudad ciudad;

    @JsonView(JsonViews.DetailedList.class)
    @ManyToOne
    @JoinColumn(name="pais_id")
    private Pais pais;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "numero_paginas")
    private Long numeroPaginas;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "numero_coleccion")
    private Integer numeroColeccion;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "coleccion")
    private String coleccion;

    //Example ISBN	978-3-16-148410-0
    @Column(name = "isbn")
    private String isbn;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "tirada")
    private Integer tirada;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "descripcion")
    private String descripcion;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "localizacion")
    private String localizacion;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @JsonManagedReference(value="edicionAgente")
    @OneToMany(mappedBy = "edicion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<EdicionAgente> edicionAgentes;


    @JsonManagedReference(value="edicionOrganizacion")
    @OneToMany(mappedBy = "edicion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<EdicionOrganizacion> edicionOrganizaciones ;

    public Edicion() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getEdicionDia() {
        return edicionDia;
    }

    public void setEdicionDia(Integer edicionDia) {
        this.edicionDia = edicionDia;
    }

    public Integer getEdicionMes() {
        return edicionMes;
    }

    public void setEdicionMes(Integer edicionMes) {
        this.edicionMes = edicionMes;
    }

    public Integer getEdicionAno() {
        return edicionAno;
    }

    public void setEdicionAno(Integer edicionAno) {
        this.edicionAno = edicionAno;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Long getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Long numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getColeccion() {
        return coleccion;
    }

    public void setColeccion(String coleccion) {
        this.coleccion = coleccion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getTirada() {
        return tirada;
    }

    public void setTirada(Integer tirada) {
        this.tirada = tirada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Integer getNumeroColeccion() {
        return numeroColeccion;
    }

    public void setNumeroColeccion(Integer numeroColeccion) {
        this.numeroColeccion = numeroColeccion;
    }

    public Set<EdicionAgente> getEdicionAgentes() {
        return edicionAgentes;
    }

    public void setEdicionAgentes(Set<EdicionAgente> edicionAgentes) {
        this.edicionAgentes = edicionAgentes;
    }

    public Set<EdicionOrganizacion> getEdicionOrganizaciones() {
        return edicionOrganizaciones;
    }

    public void setEdicionOrganizaciones(Set<EdicionOrganizacion> edicionOrganizaciones) {
        this.edicionOrganizaciones = edicionOrganizaciones;
    }
}
