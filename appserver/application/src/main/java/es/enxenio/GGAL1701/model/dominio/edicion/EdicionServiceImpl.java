package es.enxenio.GGAL1701.model.dominio.edicion;

import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

@Service
@Transactional
public class EdicionServiceImpl implements EdicionService {

    @Inject
    private EdicionRepository edicionRepository;

    @Override
    @Transactional(readOnly = true)
    public Edicion get(Long id) {
        return edicionRepository.findOne(id);
    }

    @Override
    public Page<Edicion> filter(PageableFilter filter) {
        return edicionRepository.filter(filter.getQuery(), filter.getPageable());
    }

    @Override
    public Edicion save(Edicion edicion) throws InstanceAlreadyExistsException {
        return edicionRepository.save(edicion);
    }

    @Override
    public Edicion update(Edicion edicion) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return save(edicion);
    }

    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        if(edicionRepository.findOne(id) == null){
            throw new InstanceNotFoundException();
        }
        edicionRepository.delete(id);
    }
}
