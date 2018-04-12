package es.enxenio.GGAL1701.model.dominio.libro;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import es.enxenio.GGAL1701.controller.util.JsonViews;
import es.enxenio.GGAL1701.model.dominio.capitulo.Capitulo;
import es.enxenio.GGAL1701.model.dominio.citas.citaLibro.CitaLibro;
import es.enxenio.GGAL1701.model.dominio.edicion.Edicion;
import es.enxenio.GGAL1701.model.dominio.produccionActiva.ProduccionActiva;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaLibro.SubidaLibro;
import es.enxenio.GGAL1701.model.util.Imagen;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hlorenzo on 04/08/2017.
 */
@Entity
@Table( name = "libro")
@PrimaryKeyJoinColumn(name = "id")
public class Libro extends ProduccionActiva {

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "autoria")
    private String autoria;

    /*
     Cada rama del conocimiento se va subdiviendo con dígitos del 1 al 9 y en grupos de tres dígitos.
     Por ejemplo, un libro que trate sobre juicios o recursos, tendría la siguiente CDU: 343.15. Dentro
     de las ciencias sociales que es el 3, existe el derecho que tiene asignado el 34. El derecho penal
     es 343 y como ya tenemos tres dígitos, debe existir un punto de separación. El 15 es juicios.
     Así tenemos el 343.15.
     */
    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "cdu")
    private String cdu;

    /*
    Recoge información externa sobre el registro e información sobre documentos
    anexados, si los hubiera, en "otros documentos". Pueden ser también apuntadas
    mediante hipervinculos internos las fichas contenidas en otras clases.
    */
    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "repositorio")
    private String repositorio;

    @Enumerated(value = EnumType.STRING)
    private TipoLibro tipo;

    @JsonManagedReference
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("referenciaPasivo")
    private Set<CitaLibro> citasLibro;

    @JsonManagedReference
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("otroPath")
    private Set<SubidaLibro> subidaLibros;

    @JsonManagedReference
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("titulo")
    private Set<Edicion> ediciones;

    @JsonManagedReference
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("titulo")
    private Set<Capitulo> capitulos;

    @Column(name = "numero_capitulos")
    private Integer numeroCapitulos;

    @Column(name = "numero_ediciones")
    private Integer numeroEdiciones;

    public Libro(){}

    public String getAutoria() {
        return autoria;
    }

    public void setAutoria(String autoria) {
        this.autoria = autoria;
    }

    public String getCdu() {
        return cdu;
    }

    public void setCdu(String cdu) {
        this.cdu = cdu;
    }

    public String getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(String repositorio) {
        this.repositorio = repositorio;
    }

    public TipoLibro getTipo() {
        return tipo;
    }

    public void setTipo(TipoLibro tipo) {
        this.tipo = tipo;
    }

    public Set<CitaLibro> getCitasLibro() {
        return citasLibro;
    }

    public void setCitasLibro(Set<CitaLibro> citasLibro) {
        this.citasLibro = citasLibro;
    }

    public Set<SubidaLibro> getSubidaLibros() {
        return subidaLibros;
    }

    public void setSubidaLibros(Set<SubidaLibro> subidaLibros) {
        this.subidaLibros = subidaLibros;
    }

    public Set<Edicion> getEdiciones() {
        return ediciones;
    }

    public void setEdiciones(Set<Edicion> ediciones) {
        this.ediciones = ediciones;
    }

    public Set<Capitulo> getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(Set<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }

    public Integer getNumeroCapitulos() {
        return numeroCapitulos;
    }

    public void setNumeroCapitulos(Integer numeroCapitulos) {
        this.numeroCapitulos = numeroCapitulos;
    }

    public Integer getNumeroEdiciones() {
        return numeroEdiciones;
    }

    public void setNumeroEdiciones(Integer numeroEdiciones) {
        this.numeroEdiciones = numeroEdiciones;
    }


}




