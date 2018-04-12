package es.enxenio.GGAL1701.controller.custom.util;

import es.enxenio.GGAL1701.model.dominio.periodico.TipoPeriodico;

/**
 * Created by hlorenzo on 04/08/2016.
 */
public class ProduccionActivaFilter extends PageableFilter {

    private TipoPeriodico tipo;
    private Boolean revisado;
    private Boolean concluido;

    public TipoPeriodico getTipo() {
        return tipo;
    }

    public Boolean getRevisado() {
        return revisado;
    }

    public void setRevisado(Boolean revisado) {
        this.revisado = revisado;
    }

    public Boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }

    public void setTipo(TipoPeriodico tipo) {
        this.tipo = tipo;
    }

}
