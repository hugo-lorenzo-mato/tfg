package es.enxenio.GGAL1701.model.dominio.organizacion;

import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
/**
 * Created by hlorenzo on 05/09/2017.
 */
@Service
@Transactional
public class OrganizacionServiceImpl implements OrganizacionService {

    @Inject
    private OrganizacionRepository organizacionRepository;

    @Override
    @Transactional(readOnly = true)
    public Organizacion get(Long id) {
        return organizacionRepository.findOne(id);
    }

    @Override
    public Page<Organizacion> filter(PageableFilter filter) {
        return organizacionRepository.filter(filter.getQuery(), filter.getPageable());
    }

    @Override
    public Organizacion save(Organizacion organizacion) throws InstanceAlreadyExistsException {
        return organizacionRepository.save(organizacion);
    }

    @Override
    public Organizacion update(Organizacion organizacion) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return save(organizacion);
    }

    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        if(organizacionRepository.findOne(id) == null){
            throw new InstanceNotFoundException();
        }
        organizacionRepository.delete(id);
    }
}
