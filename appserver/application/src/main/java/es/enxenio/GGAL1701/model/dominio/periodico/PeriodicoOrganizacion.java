package es.enxenio.GGAL1701.model.dominio.periodico;

import com.fasterxml.jackson.annotation.JsonBackReference;
import es.enxenio.GGAL1701.model.dominio.agente.Agente;
import es.enxenio.GGAL1701.model.dominio.edicion.Edicion;
import es.enxenio.GGAL1701.model.dominio.funciones.funcionAgente.FuncionAgente;
import es.enxenio.GGAL1701.model.dominio.funciones.funcionOrganizacion.FuncionOrganizacion;
import es.enxenio.GGAL1701.model.dominio.organizacion.Organizacion;
import es.enxenio.GGAL1701.model.dominio.tipologia.Tipologia;
import es.enxenio.GGAL1701.model.generic.GenericEntity;

import javax.persistence.*;

/**
 * Created by hlorenzo on 07/09/2017.
 **/
@Entity
@Table(name = "periodico_organizacion")
public class PeriodicoOrganizacion extends GenericEntity{

    @JsonBackReference(value="periodicoOrganizacion")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "periodico_id")
    private Periodico periodico;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organizacion_id")
    private Organizacion organizacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcion_organizacion_id")
    private FuncionOrganizacion funcionOrganizacion;

    public PeriodicoOrganizacion() {
    }

    public Periodico getPeriodico() {
        return periodico;
    }

    public void setPeriodico(Periodico periodico) {
        this.periodico = periodico;
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
