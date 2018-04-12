package es.enxenio.GGAL1701.model.dominio.prototexto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import es.enxenio.GGAL1701.controller.util.JsonViews;
import es.enxenio.GGAL1701.model.dominio.citas.citaPrototexto.CitaPrototexto;
import es.enxenio.GGAL1701.model.dominio.lugar.ciudad.Ciudad;
import es.enxenio.GGAL1701.model.dominio.lugar.pais.Pais;
import es.enxenio.GGAL1701.model.dominio.produccionActiva.Folha;
import es.enxenio.GGAL1701.model.dominio.produccionActiva.ProduccionActiva;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaPrototexto.SubidaPrototexto;
import es.enxenio.GGAL1701.model.util.Imagen;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hlorenzo on 03/08/2017.
 */
@Entity
@Table( name = "prototexto")
@PrimaryKeyJoinColumn(name = "id")
public class Prototexto extends ProduccionActiva {


    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "primeira_linha")
    private String primeiraLinha;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "ultima_linha")
    private String ultimaLinha;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "numero_paginas")
    private Long numeroPaginas;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "descrisao")
    private String descrisao;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "localizasao")
    private String localizasao;

    /*Enumerados de la clase*/

    @Enumerated(value = EnumType.STRING)
    private TipoPrototexto tipo;

    // Soporte

    @Enumerated(value = EnumType.STRING)
    private TipoSoporte sampliado;

    @Enumerated(value = EnumType.STRING)
    private TipoSoporteReducido sreducido;

    /*De ser de categoría assinalado y optar por soporte cuaderno, surgen estos campos*/

    @Enumerated(value = EnumType.STRING)
    private Folha folha;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name= "pagina_cuaderno")
    private Integer paginaCuaderno;

    @JsonView(JsonViews.DetailedList.class)
    @ManyToOne
    @JoinColumn(name="pais_id")
    private Pais pais;

    @JsonView(JsonViews.DetailedList.class)
    @ManyToOne
    @JoinColumn(name="ciudad_id")
    private Ciudad ciudad;

    /*Soporte del prototexto*/

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "folhas_soltas")
    private boolean folhasSoltas;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "caderno")
    private boolean caderno;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "material_autonomo")
    private boolean materialAutonomo;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "biblioteca")
    private boolean biblioteca;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "correspondencia")
    private boolean correspondencia;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "hipervinculo")
    private String hipervinculo;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "autor_biblio")
    private String autorBiblioteca;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "ano_biblio")
    private Integer anoBiblioteca;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "pagina_inibiblio")
    private Integer paginaIniBiblioteca;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "pagina_finbiblio")
    private Integer paginaFinBiblioteca;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "hipervinculoCorrespondencia")
    private String hipervinculoCorrespondencia;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "autorCorrespondencia")
    private String autorCorrespondencia;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "fecha_correspondenciaDia")
    private Integer fechaCorrespondenciaDia;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "fecha_CorrespondenciaMes")
    private Integer fechaCorrespondenciaMes;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "fecha_correspondenciaAno")
    private Integer fechaCorrespondenciaAno;

    @JsonManagedReference
    @OneToMany(mappedBy = "prototexto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("referenciaPasivo")
    private Set<CitaPrototexto> citasPrototexto;


    @JsonManagedReference
    @OneToMany(mappedBy = "prototexto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("otroPath")
    private Set<SubidaPrototexto> subidaPrototextos;

    public Prototexto() {
    }

    public String getPrimeiraLinha() {
        return primeiraLinha;
    }

    public void setPrimeiraLinha(String primeiraLinha) {
        this.primeiraLinha = primeiraLinha;
    }

    public String getUltimaLinha() {
        return ultimaLinha;
    }

    public void setUltimaLinha(String ultimaLinha) {
        this.ultimaLinha = ultimaLinha;
    }

    public Long getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Long numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getDescrisao() {
        return descrisao;
    }

    public void setDescrisao(String descrisao) {
        this.descrisao = descrisao;
    }

    public String getLocalizasao() {
        return localizasao;
    }

    public void setLocalizasao(String localizasao) {
        this.localizasao = localizasao;
    }

    public TipoPrototexto getTipo() {
        return tipo;
    }

    public void setTipo(TipoPrototexto tipo) {
        this.tipo = tipo;
    }

    public Folha getFolha() {
        return folha;
    }

    public void setFolha(Folha folha) {
        this.folha = folha;
    }

    public Integer getPaginaCuaderno() {
        return paginaCuaderno;
    }

    public void setPaginaCuaderno(Integer paginaCuaderno) {
        this.paginaCuaderno = paginaCuaderno;
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

    public boolean isFolhasSoltas() {
        return folhasSoltas;
    }

    public void setFolhasSoltas(boolean folhasSoltas) {
        this.folhasSoltas = folhasSoltas;
    }

    public boolean isCaderno() {
        return caderno;
    }

    public void setCaderno(boolean caderno) {
        this.caderno = caderno;
    }

    public boolean isMaterialAutonomo() {
        return materialAutonomo;
    }

    public void setMaterialAutonomo(boolean materialAutonomo) {
        this.materialAutonomo = materialAutonomo;
    }

    public boolean isBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(boolean biblioteca) {
        this.biblioteca = biblioteca;
    }

    public boolean isCorrespondencia() {
        return correspondencia;
    }

    public void setCorrespondencia(boolean correspondencia) {
        this.correspondencia = correspondencia;
    }

    public String getHipervinculo() {
        return hipervinculo;
    }

    public void setHipervinculo(String hipervinculo) {
        this.hipervinculo = hipervinculo;
    }

    public String getAutorBiblioteca() {
        return autorBiblioteca;
    }

    public void setAutorBiblioteca(String autorBiblioteca) {
        this.autorBiblioteca = autorBiblioteca;
    }

    public Integer getAnoBiblioteca() {
        return anoBiblioteca;
    }

    public void setAnoBiblioteca(Integer anoBiblioteca) {
        this.anoBiblioteca = anoBiblioteca;
    }

    public Integer getPaginaIniBiblioteca() {
        return paginaIniBiblioteca;
    }

    public void setPaginaIniBiblioteca(Integer paginaIniBiblioteca) {
        this.paginaIniBiblioteca = paginaIniBiblioteca;
    }

    public Integer getPaginaFinBiblioteca() {
        return paginaFinBiblioteca;
    }

    public void setPaginaFinBiblioteca(Integer paginaFinBiblioteca) {
        this.paginaFinBiblioteca = paginaFinBiblioteca;
    }

    public String getHipervinculoCorrespondencia() {
        return hipervinculoCorrespondencia;
    }

    public void setHipervinculoCorrespondencia(String hipervinculoCorrespondencia) {
        this.hipervinculoCorrespondencia = hipervinculoCorrespondencia;
    }

    public String getAutorCorrespondencia() {
        return autorCorrespondencia;
    }

    public void setAutorCorrespondencia(String autorCorrespondencia) {
        this.autorCorrespondencia = autorCorrespondencia;
    }

    public Integer getFechaCorrespondenciaDia() {
        return fechaCorrespondenciaDia;
    }

    public void setFechaCorrespondenciaDia(Integer fechaCorrespondenciaDia) {
        this.fechaCorrespondenciaDia = fechaCorrespondenciaDia;
    }

    public Integer getFechaCorrespondenciaMes() {
        return fechaCorrespondenciaMes;
    }

    public void setFechaCorrespondenciaMes(Integer fechaCorrespondenciaMes) {
        this.fechaCorrespondenciaMes = fechaCorrespondenciaMes;
    }

    public Integer getFechaCorrespondenciaAno() {
        return fechaCorrespondenciaAno;
    }

    public void setFechaCorrespondenciaAno(Integer fechaCorrespondenciaAno) {
        this.fechaCorrespondenciaAno = fechaCorrespondenciaAno;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "prototexto")
    public Set<CitaPrototexto> getCitasPrototexto() {
        return citasPrototexto;
    }

    public void setCitaPrototextos(Set<CitaPrototexto> citaPrototextos) {
        this.citasPrototexto = citasPrototexto;
    }


    public Set<SubidaPrototexto> getSubidaPrototextos() {
        return subidaPrototextos;
    }

    public void setSubidaPrototextos(Set<SubidaPrototexto> subidaPrototextos) {
        this.subidaPrototextos = subidaPrototextos;
    }

    public TipoSoporte getSampliado() {
        return sampliado;
    }

    public void setSampliado(TipoSoporte sampliado) {
        this.sampliado = sampliado;
    }

    public TipoSoporteReducido getSreducido() {
        return sreducido;
    }

    public void setSreducido(TipoSoporteReducido sreducido) {
        this.sreducido = sreducido;
    }

    public void setCitasPrototexto(Set<CitaPrototexto> citasPrototexto) {
        this.citasPrototexto = citasPrototexto;
    }

}

