package es.enxenio.GGAL1701.model.dominio.edicion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import es.enxenio.GGAL1701.model.dominio.funciones.funcionAgente.FuncionAgente;
import es.enxenio.GGAL1701.model.dominio.funciones.funcionOrganizacion.FuncionOrganizacion;
import es.enxenio.GGAL1701.model.dominio.organizacion.Organizacion;
import es.enxenio.GGAL1701.model.dominio.tipologia.Tipologia;
import es.enxenio.GGAL1701.model.generic.GenericEntity;

import javax.persistence.*;

/**
 * Created by hlorenzo on 06/09/2017.
 **/
@Entity
@Table(name = "edicion_organizacion")
public class EdicionOrganizacion extends GenericEntity{


    @JsonBackReference(value="edicionOrganizacion")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edicion_id")
    private Edicion edicion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organizacion_id")
    private Organizacion organizacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcion_organizacion_id")
    private FuncionOrganizacion funcionOrganizacion;

    public EdicionOrganizacion() {
    }

    public Edicion getEdicion() {
        return edicion;
    }

    public void setEdicion(Edicion edicion) {
        this.edicion = edicion;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public FuncionOrganizacion getFuncionOrganizacion() {
        return funcionOrganizacion;
    }

    public void setFuncionOrganizacion(FuncionOrganizacion funcionOrganizacion) {
        this.funcionOrganizacion = funcionOrganizacion;
    }
}
