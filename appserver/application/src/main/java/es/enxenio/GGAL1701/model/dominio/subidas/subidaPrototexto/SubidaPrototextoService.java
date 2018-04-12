package es.enxenio.GGAL1701.model.dominio.subidas.subidaPrototexto;

import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import org.springframework.data.domain.Page;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

/**
 * Created by hlorenzo on 31/08/2017.
 **/
public interface SubidaPrototextoService{

    SubidaPrototexto save(SubidaPrototexto s, long id) throws InstanceAlreadyExistsException;

    SubidaPrototexto update(SubidaPrototexto s, long id) throws InstanceNotFoundException, InstanceAlreadyExistsException;

    SubidaPrototexto get(Long id);

    Page<SubidaPrototexto> filter(PageableFilter filter);

    void delete(Long id) throws InstanceNotFoundException;
}
