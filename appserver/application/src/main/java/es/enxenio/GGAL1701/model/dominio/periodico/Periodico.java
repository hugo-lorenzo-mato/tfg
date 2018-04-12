package es.enxenio.GGAL1701.model.dominio.periodico;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import es.enxenio.GGAL1701.controller.util.JsonViews;
import es.enxenio.GGAL1701.model.dominio.citas.citaPeriodico.CitaPeriodico;
import es.enxenio.GGAL1701.model.dominio.lugar.ciudad.Ciudad;
import es.enxenio.GGAL1701.model.dominio.lugar.pais.Pais;
import es.enxenio.GGAL1701.model.dominio.produccionActiva.ProduccionActiva;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaPeriodico.SubidaPeriodico;
import es.enxenio.GGAL1701.model.util.Imagen;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hlorenzo on 04/08/2017.
 */
@Entity
@Table( name = "periodico")
@PrimaryKeyJoinColumn(name = "id")
public class Periodico extends ProduccionActiva {

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "pseudonimo")
    private Boolean pseudonimo;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "lide")
    private String lide;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "numero_volume")
    private Long numeroVolume;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "numero_paginas")
    private Long numeroPaginas;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "pagina_inicial")
    private Long paginaInicial;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "pagina_final")
    private Long paginaFinal;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "repositorio")
    private String repositorio;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "descrisao")
    private String descrisao;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "localisasao")
    private String localisasao;

    @Enumerated(value = EnumType.STRING)
    private TipoPeriodico tipo;

    @JsonView(JsonViews.DetailedList.class)
    @ManyToOne
    @JoinColumn(name="pais_id")
    private Pais pais;

    @JsonView(JsonViews.DetailedList.class)
    @ManyToOne
    @JoinColumn(name="ciudad_id")
    private Ciudad ciudad;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "notas")
    private String notas;

    @JsonManagedReference
    @OneToMany(mappedBy = "periodico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("referenciaPasivo")
    private Set<CitaPeriodico> citasPeriodico;

    @JsonManagedReference
    @OneToMany(mappedBy = "periodico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("otroPath")
    private Set<SubidaPeriodico> subidaPeriodicos;


    @JsonManagedReference(value = "periodicoAgente")
    @OneToMany(mappedBy = "periodico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("id")
    private Set<PeriodicoAgente> periodicoAgentes;


    @JsonManagedReference(value = "periodicoOrganizacion")
    @OneToMany(mappedBy = "periodico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("id")
    private Set<PeriodicoOrganizacion> periodicoOrganizaciones;

    public Periodico() {

    }

    public Boolean getPseudonimo() {
        return pseudonimo;
    }

    public void setPseudonimo(Boolean pseudonimo) {
        this.pseudonimo = pseudonimo;
    }

    public String getLide() {
        return lide;
    }

    public void setLide(String lide) {
        this.lide = lide;
    }

    public Long getNumeroVolume() {
        return numeroVolume;
    }

    public void setNumeroVolume(Long numeroVolume) {
        this.numeroVolume = numeroVolume;
    }

    public Long getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Long numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public Long getPaginaInicial() {
        return paginaInicial;
    }

    public void setPaginaInicial(Long paginaInicial) {
        this.paginaInicial = paginaInicial;
    }

    public Long getPaginaFinal() {
        return paginaFinal;
    }

    public void setPaginaFinal(Long paginaFinal) {
        this.paginaFinal = paginaFinal;
    }

    public String getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(String repositorio) {
        this.repositorio = repositorio;
    }

    public String getDescrisao() {
        return descrisao;
    }

    public void setDescrisao(String descrisao) {
        this.descrisao = descrisao;
    }

    public String getLocalisasao() {
        return localisasao;
    }

    public void setLocalisasao(String localisasao) {
        this.localisasao = localisasao;
    }

    public TipoPeriodico getTipo() {
        return tipo;
    }

    public void setTipo(TipoPeriodico tipo) {
        this.tipo = tipo;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Set<CitaPeriodico> getCitasPeriodico() {
        return citasPeriodico;
    }

    public void setCitasPeriodico(Set<CitaPeriodico> citasPeriodico) {
        this.citasPeriodico = citasPeriodico;
    }

    public Set<SubidaPeriodico> getSubidaPeriodicos() {
        return subidaPeriodicos;
    }

    public void setSubidaPeriodicos(Set<SubidaPeriodico> subidaPeriodicos) {
        this.subidaPeriodicos = subidaPeriodicos;
    }

    public Set<PeriodicoAgente> getPeriodicoAgentes() {
        return periodicoAgentes;
    }

    public void setPeriodicoAgentes(Set<PeriodicoAgente> periodicoAgentes) {
        this.periodicoAgentes = periodicoAgentes;
    }

    public Set<PeriodicoOrganizacion> getPeriodicoOrganizaciones() {
        return periodicoOrganizaciones;
    }

    public void setPeriodicoOrganizaciones(Set<PeriodicoOrganizacion> periodicoOrganizaciones) {
        this.periodicoOrganizaciones = periodicoOrganizaciones;
    }
}
