package es.enxenio.GGAL1701.model.dominio.capitulo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import es.enxenio.GGAL1701.controller.util.JsonViews;
import es.enxenio.GGAL1701.model.dominio.libro.Libro;
import es.enxenio.GGAL1701.model.generic.GenericEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hlorenzo on 08/09/2017.
 **/
@Entity
@Table(name = "capitulo")
public class Capitulo extends GenericEntity {

    @Column(name = "titulo")
    private String titulo;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "pagina_inicio")
    private Long paginaInicio;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "pagina_fin")
    private Integer paginaFin;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @JsonManagedReference(value="capituloAgente")
    @OneToMany(mappedBy = "capitulo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<CapituloAgente> capituloAgentes;


    public Capitulo() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getPaginaInicio() {
        return paginaInicio;
    }

    public void setPaginaInicio(Long paginaInicio) {
        this.paginaInicio = paginaInicio;
    }

    public Integer getPaginaFin() {
        return paginaFin;
    }

    public void setPaginaFin(Integer paginaFin) {
        this.paginaFin = paginaFin;
    }

    public Set<CapituloAgente> getCapituloAgentes() {
        return capituloAgentes;
    }

    public void setCapituloAgentes(Set<CapituloAgente> capituloAgentes) {
        this.capituloAgentes = capituloAgentes;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
