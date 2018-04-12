package es.enxenio.GGAL1701.model.dominio.subidas.subidaLibro;

import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import es.enxenio.GGAL1701.model.generic.GenericService;
import org.springframework.data.domain.Page;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

/**
 * Created by hlorenzo on 31/08/2017.
 **/
public interface SubidaLibroService{

    SubidaLibro save(SubidaLibro s, long id) throws InstanceAlreadyExistsException;

    SubidaLibro update(SubidaLibro s, long id) throws InstanceNotFoundException, InstanceAlreadyExistsException;

    SubidaLibro get(Long id);

    Page<SubidaLibro> filter(PageableFilter filter);

    void delete(Long id) throws InstanceNotFoundException;

}
