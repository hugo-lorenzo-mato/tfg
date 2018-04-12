package es.enxenio.GGAL1701.model.dominio.subidas.subidaPeriodico;

import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import es.enxenio.GGAL1701.util.ConstantesModel;
import es.enxenio.GGAL1701.util.upload.ArchivoEntidadHelper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

/**
 * Created by hlorenzo on 29/08/2017.
 **/
@Service
@Transactional
public class SubidaPeriodicoServiceImpl implements SubidaPeriodicoService {

    @Inject
    private SubidaPeriodicoRepository subidaPeriodicoRepository;

    @Inject
    private ArchivoEntidadHelper archivoEntidadHelper;

    /*
    * Realizar consultas
    */

    @Override
    @Transactional(readOnly = true)
    public SubidaPeriodico get(Long id) {
        return subidaPeriodicoRepository.findOne(id);
    }

    @Override
    public Page<SubidaPeriodico> filter(PageableFilter filter) {
        return subidaPeriodicoRepository.filter((filter.getQuery()), filter.getPageable());
    }

    /*
    * Realizar gestión
    */

    @Override
    public SubidaPeriodico save(SubidaPeriodico subidaPeriodico, long id) throws InstanceAlreadyExistsException {
        saveOtro(subidaPeriodico, id);
        return subidaPeriodicoRepository.save(subidaPeriodico);
    }
    @Override
    public SubidaPeriodico update(SubidaPeriodico subidaPeriodico, long id) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return save(subidaPeriodico, id);
    }

    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        if (subidaPeriodicoRepository.findOne(id) == null){
            throw new InstanceNotFoundException();
        }
        subidaPeriodicoRepository.delete(id);
    }

    /*
     * documentos anexados: "otros". SubidaPeriodico múltiple
     */

    private void saveOtro(SubidaPeriodico subidaPeriodico, long idPeriodico) {
        String path;
        if (subidaPeriodico.getId() != null) {
            SubidaPeriodico periodicoBD = get(subidaPeriodico.getId());
            boolean eliminar = periodicoBD.getOtroPath() != null && subidaPeriodico.getOtroPath() == null;

            path = archivoEntidadHelper.gestionarArchivo(eliminar, subidaPeriodico.getOtroTmp(), subidaPeriodico.getOtroPath(),
                periodicoBD.getOtroPath(), ConstantesModel.URL_CARPETA_PERIODICOS_OTROS, idPeriodico);
        } else {
            subidaPeriodicoRepository.save(subidaPeriodico);
            path = archivoEntidadHelper.gestionarArchivo(false, subidaPeriodico.getOtroTmp(), subidaPeriodico.getOtroPath(),
                null, ConstantesModel.URL_CARPETA_PERIODICOS_OTROS, idPeriodico);
        }
        subidaPeriodico.setOtroPath(path);
    }
}
