package es.enxenio.GGAL1701.model.dominio.subidas.subidaPeriodico;

import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import org.springframework.data.domain.Page;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

/**
 * Created by hlorenzo on 31/08/2017.
 **/
public interface SubidaPeriodicoService{
    SubidaPeriodico save(SubidaPeriodico s, long id) throws InstanceAlreadyExistsException;

    SubidaPeriodico update(SubidaPeriodico s, long id) throws InstanceNotFoundException, InstanceAlreadyExistsException;

    SubidaPeriodico get(Long id);

    Page<SubidaPeriodico> filter(PageableFilter filter);

    void delete(Long id) throws InstanceNotFoundException;
}
