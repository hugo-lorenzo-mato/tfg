package es.enxenio.GGAL1701.model.dominio.funciones.funcionOrganizacion;

import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

/**
 * Created by hlorenzo on 07/09/2017.
 */
@Service
@Transactional
public class FuncionOrganizacionServiceImpl implements FuncionOrganizacionService {

    @Inject
    private FuncionOrganizacionRepository funcionOrganizacionRepository;

    // Consulta

    @Override
    @Transactional(readOnly = true)
    public FuncionOrganizacion get(Long id) {
        return funcionOrganizacionRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FuncionOrganizacion> filter(PageableFilter filter) {
        return funcionOrganizacionRepository.filterByNombre(filter.getQuery(), filter.getPageable());
    }

    // Gestión

    @Override
    public FuncionOrganizacion save(FuncionOrganizacion funcionOrganizacion) throws InstanceAlreadyExistsException {
        return funcionOrganizacionRepository.save(funcionOrganizacion);
    }

    @Override
    public FuncionOrganizacion update(FuncionOrganizacion funcionOrganizacion) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return save(funcionOrganizacion);
    }

    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        if (funcionOrganizacionRepository.findOne(id) == null) {
            throw new InstanceNotFoundException();
        }
        funcionOrganizacionRepository.delete(id);
    }

}
