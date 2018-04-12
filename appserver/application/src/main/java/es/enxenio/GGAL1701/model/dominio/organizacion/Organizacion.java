package es.enxenio.GGAL1701.model.dominio.organizacion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import es.enxenio.GGAL1701.controller.util.JsonViews;
import es.enxenio.GGAL1701.model.dominio.agente.Agente;
import es.enxenio.GGAL1701.model.dominio.edicion.EdicionAgente;
import es.enxenio.GGAL1701.model.dominio.edicion.EdicionOrganizacion;
import es.enxenio.GGAL1701.model.dominio.libro.Libro;
import es.enxenio.GGAL1701.model.dominio.lugar.ciudad.Ciudad;
import es.enxenio.GGAL1701.model.dominio.lugar.pais.Pais;
import es.enxenio.GGAL1701.model.dominio.tipologia.Tipologia;
import es.enxenio.GGAL1701.model.generic.GenericEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hlorenzo on 05/09/2017.
 */
@Entity
@Table(name = "organizacion")
public class Organizacion extends GenericEntity {

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "tipologia_id")
    private Tipologia tipologia;

    public Organizacion() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipologia getTipologia() {
        return tipologia;
    }

    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }
}
