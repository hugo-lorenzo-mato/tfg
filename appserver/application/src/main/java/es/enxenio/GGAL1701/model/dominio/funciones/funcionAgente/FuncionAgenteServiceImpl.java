package es.enxenio.GGAL1701.model.dominio.funciones.funcionAgente;

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
public class FuncionAgenteServiceImpl implements FuncionAgenteService {

    @Inject
    private FuncionAgenteRepository funcionAgenteRepository;

    // Consulta

    @Override
    @Transactional(readOnly = true)
    public FuncionAgente get(Long id) {
        return funcionAgenteRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FuncionAgente> filter(PageableFilter filter) {
        return funcionAgenteRepository.filterByNombre(filter.getQuery(), filter.getPageable());
    }

    // Gestión

    @Override
    public FuncionAgente save(FuncionAgente funcionAgente) throws InstanceAlreadyExistsException {
        return funcionAgenteRepository.save(funcionAgente);
    }

    @Override
    public FuncionAgente update(FuncionAgente funcionAgente) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        // unused
        return funcionAgenteRepository.save(funcionAgente);
    }

    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        if (funcionAgenteRepository.findOne(id) == null) {
            throw new InstanceNotFoundException();
        }
        funcionAgenteRepository.delete(id);
    }

}
