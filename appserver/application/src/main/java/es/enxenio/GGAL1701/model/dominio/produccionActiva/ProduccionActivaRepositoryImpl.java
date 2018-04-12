package es.enxenio.GGAL1701.model.dominio.produccionActiva;

import es.enxenio.GGAL1701.controller.publica.custom.BusquedaAvanzadaFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import static es.enxenio.GGAL1701.model.util.SqlUtil.compararParamConRegExp;
import static es.enxenio.GGAL1701.model.util.SqlUtil.convertirQueryARegExp;


/**
 * Created by hlorenzo on 25/09/2017.
 */
public class ProduccionActivaRepositoryImpl implements ProduccionActivaRepositoryCustom {

    @Inject
    private EntityManager entityManager;

    @Override
    public Page<ProduccionActiva> filter(BusquedaAvanzadaFilter filter) {

        String from = " FROM ProduccionActiva a";
        String where = " WHERE 1=1";
        String order = " ORDER BY " + filter.getSortToString("id", "a");

        // Joins

        //if (StringUtils.isNotEmpty(filter.getCiudad())) from += " LEFT JOIN a.ciudades c";

        // Where


        if (StringUtils.isNotEmpty(filter.getTitulo()) || StringUtils.isNotEmpty(filter.getQuery()))
            where += " AND " + compararParamConRegExp("a.titulo", "titulo");

        if (StringUtils.isNotEmpty(filter.getResumen()) || StringUtils.isNotEmpty(filter.getQuery()))
            where += " AND " + compararParamConRegExp("a.resumo", "resumo");

        if (StringUtils.isNotEmpty(filter.getNotas()) || StringUtils.isNotEmpty(filter.getQuery()))
            where += " AND " + compararParamConRegExp("a.notas", "notas");



        //Selects
        if (filter.getAssunto() != null) where += " AND a.assunto = :assunto";
        if (filter.getGenero() != null) where += " AND a.genero = :genero";
        if (filter.getType() != null) where += " AND a.type = :type";


        // Fechas de búsqueda
        if (filter.getInicio() != null) where += " AND a.publicacionAno >= :inicio";
        if (filter.getFin() != null) where += " AND a.publicacionAno <= :fin";


        //Subida de archivos
        if (filter.getCompletoPath() != null && filter.getCompletoPath() == true) where += " AND a.completoPath != null";
        if (filter.getTranscripcionPath() != null && filter.getTranscripcionPath() == true) where += " AND a.transcripcionPath != null";
        if (filter.getGeneticaPath() != null && filter.getGeneticaPath() == true) where += " AND a.geneticaPath != null";
        if (filter.getImagenPath() != null && filter.getImagenPath() == true) where += " AND a.imagenPath != null";


        if (filter.getCompletoPath() != null && filter.getCompletoPath() == false) where += " AND a.completoPath = null";
        if (filter.getTranscripcionPath() != null && filter.getTranscripcionPath() == false) where += " AND a.transcripcionPath = null";
        if (filter.getGeneticaPath() != null && filter.getGeneticaPath() == false) where += " AND a.geneticaPath = null";
        if (filter.getImagenPath() != null && filter.getImagenPath() == false) where += " AND a.imagenPath = null";


        //Contiene
        if (filter.getIconografia() != null) where += " AND a.iconografia = :iconografia";
        if (filter.getRasuras() != null) where += " AND a.rasuras = :rasuras";
        if (filter.getSublinhado() != null) where += " AND a.sublinhado = :sublinhado";
        if (filter.getAnotasoes() != null) where += " AND a.anotasoes = :anotasoes";

        //Estado
        if (filter.getConcluido() != null) where += " AND a.concluido = :concluido";
        if (filter.getRevisado() != null) where += " AND a.revisado = :revisado";



        // Consultas y parámetros

        javax.persistence.Query qSelect = entityManager.createQuery("SELECT DISTINCT a" + from + where + order);
        javax.persistence.Query qCount = entityManager.createQuery("SELECT COUNT(DISTINCT a.id)" + from + where);

        qSelect.setMaxResults(filter.getPageable().getPageSize());
        qSelect.setFirstResult(filter.getPageable().getPageNumber() * filter.getPageable().getPageSize());

        setParametros(filter, qSelect);
        setParametros(filter, qCount);

        long numeroElementos = (long) qCount.getSingleResult();

        return new PageImpl<ProduccionActiva>(qSelect.getResultList(), filter.getPageable(), numeroElementos);
    }

    private void setParametros(BusquedaAvanzadaFilter filter, javax.persistence.Query q) {
        // Query general
        if (StringUtils.isNotEmpty(filter.getQuery()))
            q.setParameter("titulo", convertirQueryARegExp(filter.getQuery()));

        // Buscador
        if (StringUtils.isNotEmpty(filter.getTitulo()))
            q.setParameter("titulo", convertirQueryARegExp(filter.getTitulo()));

        if (StringUtils.isNotEmpty(filter.getResumen()))
            q.setParameter("resumo", convertirQueryARegExp(filter.getResumen()));

        if (StringUtils.isNotEmpty(filter.getNotas()))
            q.setParameter("notas", convertirQueryARegExp(filter.getNotas()));

        // Entidades
        //if (filter.getPais() != null) q.setParameter("paisId", filter.getPais().getId());
        //if (filter.getCiudad() != null) q.setParameter("ciudad", convertirQueryARegExp(filter.getCiudad()));

        // Fechas
        if (filter.getInicio() != null) q.setParameter("inicio", filter.getInicio());
        if (filter.getFin() != null) q.setParameter("fin", filter.getFin());

        // type
        if (filter.getType() != null) q.setParameter("type", filter.getType());

        // Enumerados
        if (filter.getGenero() != null) q.setParameter("genero", filter.getGenero());
        if (filter.getAssunto() != null) q.setParameter("assunto", filter.getAssunto());

        //Contiene
        if (filter.getIconografia() != null) q.setParameter("iconografia", filter.getIconografia());
        if (filter.getRasuras() != null) q.setParameter("rasuras", filter.getRasuras());
        if (filter.getSublinhado() != null) q.setParameter("sublinhado", filter.getSublinhado());
        if (filter.getAnotasoes() != null) q.setParameter("anotasoes", filter.getAnotasoes());

        //Estado
        if (filter.getConcluido() != null) q.setParameter("concluido", filter.getConcluido());
        if (filter.getRevisado() != null) q.setParameter("revisado", filter.getRevisado());
    }
}

