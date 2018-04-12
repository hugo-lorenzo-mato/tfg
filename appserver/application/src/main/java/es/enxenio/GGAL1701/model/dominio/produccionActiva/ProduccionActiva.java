package es.enxenio.GGAL1701.model.dominio.produccionActiva;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import es.enxenio.GGAL1701.controller.util.JsonViews;
import es.enxenio.GGAL1701.model.dominio.libro.Libro;
import es.enxenio.GGAL1701.model.dominio.periodico.Periodico;
import es.enxenio.GGAL1701.model.dominio.prototexto.Prototexto;
import es.enxenio.GGAL1701.model.generic.GenericEntity;
import es.enxenio.GGAL1701.model.util.Imagen;

import javax.persistence.*;

/**
 * Created by hlorenzo on 11/08/2017.
 * Clase creada para reunir las características comunes de la producción activa
 * Extiende a su vez GenericEntity que es donde se recoger el identificador para
 * todos los recursos.
 **/
@Entity
@Table(name = "produccion_activa")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Prototexto.class, name = "prototexto"),
    @JsonSubTypes.Type(value = Libro.class, name = "libro"),
    @JsonSubTypes.Type(value = Periodico.class, name = "periodico")})
public class ProduccionActiva extends GenericEntity{

    private String titulo;

    /* Código alfanumérico composto por nº de clase + tipo (3 iniciais) + nº Id (3 cifras) + data_ano */
    @Column(name = "numeracion_automatica")
    private String numeracionAutomatica;


    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "publicacion_dia")
    private Integer publicacionDia;

    @JsonView(JsonViews.DetailedList.class)
    @Column(name = "publicacion_mes")
    private Integer publicacionMes;

    @Column(name = "publicacion_ano")
    private Integer publicacionAno;


    /* A cubrir por el administrador */

    @Column(name = "revisado")
    private boolean revisado;

    @Column(name = "concluido")
    private boolean concluido;

    /* Campos Analíticos */

    @Enumerated(value = EnumType.STRING)
    private Genero genero;

    @Enumerated(value = EnumType.STRING)
    private Assunto assunto;

    @Column(name = "resumo")
    private String resumo;

    /* Contiene */

    @Column(name = "iconografia")
    private boolean iconografia;

    @Column(name = "rasuras")
    private boolean rasuras;

    @Column(name = "sublinhado")
    private boolean sublinhado;

    @Column(name = "anotasoes")
    private boolean anotasoes;

    /* citado? */

    @Column(name = "citado")
    private boolean citado;

    @Column(name = "notas")
    private String notas;

    // Imagen asociada a la entidad

    @Column(name = "imagen_path")
    private String imagenPath;

    // Documento digital completo

    @Column(name = "completo_path")
    private String completoPath;

    // Documento de la transcripción

    @Column(name = "transcripcion_path")
    private String transcripcionPath;

    // Documento de la crítica genética

    @Column(name = "genetica_path")
    private String geneticaPath;

    @Column(name = "type")
    private String type;


    /**************************
     ******* Transient ********
     **************************/

    /*
     * La identifico como transient para recalcar su carácter temporal.
     * De serializar el objeto, su valor no se serializará. No lo alma-
     * cenamos en la BD por tanto tampoco.
     */
    @Transient
    private Imagen imagen;

    @Transient
    private String completoTmp;

    @Transient
    private String transcripcionTmp;

    @Transient
    private String geneticaTmp;

    /******************************
     ******* Fin Transient ********
     ******************************/

    public ProduccionActiva() {
    }

    public String getNumeracionAutomatica() {
        return numeracionAutomatica;
    }

    public void setNumeracionAutomatica(String numeracionAutomatica) {
        this.numeracionAutomatica = numeracionAutomatica;
    }

    public Integer getPublicacionDia() {
        return publicacionDia;
    }

    public void setPublicacionDia(Integer publicacionDia) {
        this.publicacionDia = publicacionDia;
    }

    public Integer getPublicacionMes() {
        return publicacionMes;
    }

    public void setPublicacionMes(Integer publicacionMes) {
        this.publicacionMes = publicacionMes;
    }

    public Integer getPublicacionAno() {
        return publicacionAno;
    }

    public void setPublicacionAno(Integer publicacionAno) {
        this.publicacionAno = publicacionAno;
    }

    public boolean isRevisado() {
        return revisado;
    }

    public void setRevisado(boolean revisado) {
        this.revisado = revisado;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
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

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public boolean isIconografia() {
        return iconografia;
    }

    public void setIconografia(boolean iconografia) {
        this.iconografia = iconografia;
    }

    public boolean isRasuras() {
        return rasuras;
    }

    public void setRasuras(boolean rasuras) {
        this.rasuras = rasuras;
    }

    public boolean isSublinhado() {
        return sublinhado;
    }

    public void setSublinhado(boolean sublinhado) {
        this.sublinhado = sublinhado;
    }

    public boolean isAnotasoes() {
        return anotasoes;
    }

    public void setAnotasoes(boolean anotasoes) {
        this.anotasoes = anotasoes;
    }

    public boolean isCitado() {
        return citado;
    }

    public void setCitado(boolean citado) {
        this.citado = citado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagenPath() {
        return imagenPath;
    }

    public void setImagenPath(String imagenPath) {
        this.imagenPath = imagenPath;
    }

    public String getCompletoPath() {
        return completoPath;
    }

    public void setCompletoPath(String completoPath) {
        this.completoPath = completoPath;
    }

    public String getTranscripcionPath() {
        return transcripcionPath;
    }

    public void setTranscripcionPath(String transcripcionPath) {
        this.transcripcionPath = transcripcionPath;
    }

    public String getGeneticaPath() {
        return geneticaPath;
    }

    public void setGeneticaPath(String geneticaPath) {
        this.geneticaPath = geneticaPath;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public String getCompletoTmp() {
        return completoTmp;
    }

    public void setCompletoTmp(String completoTmp) {
        this.completoTmp = completoTmp;
    }

    public String getTranscripcionTmp() {
        return transcripcionTmp;
    }

    public void setTranscripcionTmp(String transcripcionTmp) {
        this.transcripcionTmp = transcripcionTmp;
    }

    public String getGeneticaTmp() {
        return geneticaTmp;
    }

    public void setGeneticaTmp(String geneticaTmp) {
        this.geneticaTmp = geneticaTmp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
