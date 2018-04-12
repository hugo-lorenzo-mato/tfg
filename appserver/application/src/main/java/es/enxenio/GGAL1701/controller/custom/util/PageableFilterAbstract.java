package es.enxenio.GGAL1701.controller.custom.util;

import es.enxenio.GGAL1701.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * Created by crodriguez on 22/06/2016.
 */
public abstract class PageableFilterAbstract implements Serializable {

    private int page;

    private int size;

    private String sortProperty;

    private Sort.Direction sortDirection;

    private String query;

    public PageableFilterAbstract() {
    }

    // *****

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortProperty() {
        return sortProperty;
    }

    public void setSortProperty(String sortProperty) {
        this.sortProperty = sortProperty;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(Sort.Direction sortDirection) {
        this.sortDirection = sortDirection;
    }

    // Normalizamos el parámetro de búsqueda
    public String getQuery() {
        if (StringUtils.isNotEmpty(query)) {
            return StringUtil.normalize(query);
        }
        return "";
    }

    public void setQuery(String query) {
        this.query = query;
    }

    // ******

    /**
     * Genera un PageRequest a partir de los campos por separado.
     *
     * @return PageRequest
     */
    public Pageable getPageable() {
        Sort sort = null;
        if (sortProperty != null) {
            sort = new Sort(sortDirection != null ? sortDirection : Sort.Direction.ASC, sortProperty);
        }
        if (size == 0) size = Integer.MAX_VALUE;
        return new PageRequest(page, size, sort);
    }

    /**
     * @return filtro genérico para utilizar en un like.
     */
    public String getFilterToLike(String valor) {
        return "%" + StringUtil.stripAccents(valor) + "%";
    }

    /**
     * @param defaultField nombre del campo para la ordenación por defecto (ej: "titulo", "nombre", etc.)
     * @param alias        nombre de la entidad sobre la que se realizará la ordenación (ej: "a", "agente")
     * @return String de ordenación. Ej: "nombre ASC"
     */
    public String getSortToString(String defaultField, String alias) {
        String order = "";
        if (alias == null) alias = "";
        if (getSortProperty() == null) {
            order += alias + "." + defaultField;
        } else {
            order += alias + "." + getSortProperty();
        }
        if (getSortDirection() == null) {
            order += " ASC";
        } else {
            order += " " + getSortDirection();
        }
        return order;
    }

}
