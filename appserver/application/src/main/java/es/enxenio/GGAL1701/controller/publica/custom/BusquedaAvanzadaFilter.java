package es.enxenio.GGAL1701.controller.publica.custom;

import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import es.enxenio.GGAL1701.model.dominio.produccionActiva.Assunto;
import es.enxenio.GGAL1701.model.dominio.produccionActiva.Genero;

/**
 * Created by hlorenzo on 25/09/2017.
 */
public class BusquedaAvanzadaFilter extends PageableFilter {

    private String titulo;
    private String resumen;
    private String notas;
    /*Fecha*/
    private Integer publicacionAno;
    /*Enumerados*/
    private Genero genero;
    private Assunto assunto;
    /*Contiene*/
    private Boolean iconografia;
    private Boolean rasuras;
    private Boolean sublinhado;
    private Boolean anotasoes;
    /*Estado*/
    private Boolean revisado;
    private Boolean concluido;
    /*Archivos subidos*/
    private Boolean imagenPath;
    private Boolean completoPath;
    private Boolean transcripcionPath;
    private Boolean geneticaPath;
    /* Intervalo fechas de búsqueda */
    private Integer inicio;
    private Integer fin;
    /*Type*/
    private String type;

    /* */

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Integer getPublicacionAno() {
        return publicacionAno;
    }

    public void setPublicacionAno(Integer publicacionAno) {
        this.publicacionAno = publicacionAno;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Assunto getAssunto() {
        return assunto;
    }

    public void setAssunto(Assunto assunto) {
        this.assunto = assunto;
    }

    public Boolean getIconografia() {
        return iconografia;
    }

    public void setIconografia(Boolean iconografia) {
        this.iconografia = iconografia;
    }

    public Boolean getRasuras() {
        return rasuras;
    }

    public void setRasuras(Boolean rasuras) {
        this.rasuras = rasuras;
    }

    public Boolean getSublinhado() {
        return sublinhado;
    }

    public void setSublinhado(Boolean sublinhado) {
        this.sublinhado = sublinhado;
    }

    public Boolean getAnotasoes() {
        return anotasoes;
    }

    public void setAnotasoes(Boolean anotasoes) {
        this.anotasoes = anotasoes;
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

    public Boolean getImagenPath() {
        return imagenPath;
    }

    public void setImagenPath(Boolean imagenPath) {
        this.imagenPath = imagenPath;
    }

    public Boolean getCompletoPath() {
        return completoPath;
    }

    public void setCompletoPath(Boolean completoPath) {
        this.completoPath = completoPath;
    }

    public Boolean getTranscripcionPath() {
        return transcripcionPath;
    }

    public void setTranscripcionPath(Boolean transcripcionPath) {
        this.transcripcionPath = transcripcionPath;
    }

    public Boolean getGeneticaPath() {
        return geneticaPath;
    }

    public void setGeneticaPath(Boolean geneticaPath) {
        this.geneticaPath = geneticaPath;
    }

    public Integer getInicio() {
        return inicio;
    }

    public void setInicio(Integer inicio) {
        this.inicio = inicio;
    }

    public Integer getFin() {
        return fin;
    }

    public void setFin(Integer fin) {
        this.fin = fin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
