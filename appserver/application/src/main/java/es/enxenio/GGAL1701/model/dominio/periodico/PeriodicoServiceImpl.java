package es.enxenio.GGAL1701.model.dominio.periodico;

import com.sun.javafx.binding.StringFormatter;
import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import es.enxenio.GGAL1701.controller.custom.util.PeriodicoFilter;
import es.enxenio.GGAL1701.model.dominio.prototexto.Prototexto;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaPeriodico.SubidaPeriodico;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaPeriodico.SubidaPeriodicoService;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaPrototexto.SubidaPrototexto;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaPrototexto.SubidaPrototextoService;
import es.enxenio.GGAL1701.model.util.Imagen;
import es.enxenio.GGAL1701.util.ConstantesModel;
import es.enxenio.GGAL1701.util.upload.ArchivoEntidadHelper;
import es.enxenio.GGAL1701.util.upload.ImagenUtil;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
/**
 * Created by hlorenzo on 04/08/2017.
 */
@Service
@Transactional
public class PeriodicoServiceImpl implements PeriodicoService {

    @Inject
    private PeriodicoRepository periodicoRepository;

    @Inject
    private SubidaPeriodicoService subidaPeriodicoService;

    @Inject
    private ArchivoEntidadHelper archivoEntidadHelper;

    @Override
    public Periodico get(Long id) {
        return periodicoRepository.findOne(id);
    }

    @Override
    public Page<Periodico> filter(PageableFilter filter) {
        return periodicoRepository.filter(filter.getQuery(), ((PeriodicoFilter) filter).getTipo(), ((PeriodicoFilter) filter).getRevisado(), ((PeriodicoFilter) filter).getConcluido(), filter.getPageable());
    }

    @Override
    public Periodico save(Periodico periodico) throws InstanceAlreadyExistsException {
        saveCompleto(periodico);
        saveTranscripcion(periodico);
        saveGenetica(periodico);
        saveImagen(periodico.getImagen(), periodico);
        numeracionAutomatica(periodico);
        for (SubidaPeriodico subida: periodico.getSubidaPeriodicos()
            ) {
            subidaPeriodicoService.save(subida,periodico.getId());
        }
        return periodicoRepository.save(periodico);
    }

    @Override
    public Periodico update(Periodico periodico) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return save(periodico);
    }

    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        if (periodicoRepository.findOne(id) == null) {
            throw new InstanceNotFoundException();
        }
        periodicoRepository.delete(id);
    }



    /*
     * Imagenes y documentos anexados
     */

    // TODO: Seguramente se puede refactorizar y simplificar la manera en la que se guardan imágenes y archivos

    private void saveCompleto(Periodico periodico) {
        String path;
        if (periodico.getId() != null) {
            Periodico periodicoBD = get(periodico.getId());
            boolean eliminar = periodicoBD.getCompletoPath() != null && periodico.getCompletoPath() == null;

            path = archivoEntidadHelper.gestionarArchivo(eliminar, periodico.getCompletoTmp(), periodico.getCompletoPath(),
                periodicoBD.getCompletoPath(), ConstantesModel.URL_CARPETA_PERIODICOS_COMPLETOS, periodico.getId());
        } else {
            periodicoRepository.save(periodico);
            path = archivoEntidadHelper.gestionarArchivo(false, periodico.getCompletoTmp(), periodico.getCompletoPath(),
                null, ConstantesModel.URL_CARPETA_PERIODICOS_COMPLETOS, periodico.getId());
        }
        periodico.setCompletoPath(path);
    }


    private void saveTranscripcion(Periodico periodico) {
        String path;
        if (periodico.getId() != null) {
            Periodico periodicoBD = get(periodico.getId());
            boolean eliminar = periodicoBD.getTranscripcionPath() != null && periodico.getTranscripcionPath() == null;

            path = archivoEntidadHelper.gestionarArchivo(eliminar, periodico.getTranscripcionTmp(), periodico.getTranscripcionPath(),
                periodicoBD.getTranscripcionPath(), ConstantesModel.URL_CARPETA_PERIODICOS_TRANSCRIPCION, periodico.getId());
        } else {
            periodicoRepository.save(periodico);
            path = archivoEntidadHelper.gestionarArchivo(false, periodico.getTranscripcionTmp(), periodico.getTranscripcionPath(),
                null, ConstantesModel.URL_CARPETA_PERIODICOS_TRANSCRIPCION, periodico.getId());
        }
        periodico.setTranscripcionPath(path);
    }



    private void saveGenetica(Periodico periodico) {
        String path;
        if (periodico.getId() != null) {
            Periodico periodicoBD = get(periodico.getId());
            boolean eliminar = periodicoBD.getGeneticaPath() != null && periodico.getGeneticaPath() == null;

            path = archivoEntidadHelper.gestionarArchivo(eliminar, periodico.getGeneticaTmp(), periodico.getGeneticaPath(),
                periodicoBD.getGeneticaPath(), ConstantesModel.URL_CARPETA_PERIODICOS_CRITICA, periodico.getId());
        } else {
            periodicoRepository.save(periodico);
            path = archivoEntidadHelper.gestionarArchivo(false, periodico.getGeneticaTmp(), periodico.getGeneticaPath(),
                null, ConstantesModel.URL_CARPETA_PERIODICOS_CRITICA, periodico.getId());
        }
        periodico.setGeneticaPath(path);
    }

    private void saveImagen(Imagen imagen, Periodico periodico) {
        if (imagen == null) {
            imagen = new Imagen();
        }
        String path = archivoEntidadHelper.gestionarArchivo(imagen.getEliminar(), imagen.getArchivoTemporal(),
            imagen.getPath(), periodico.getImagenPath(), ConstantesModel.URL_CARPETA_PERIODICOS_IMAGEN, periodico.getId());

        if (path != null) {
            archivoEntidadHelper.guardarThumbnailsImagen(ImagenUtil.getThumbnailsPortada(),
                StringFormatter.format(ConstantesModel.URL_CARPETA_PERIODICOS_IMAGEN, periodico.getId()).getValue() + "/"
                    + path,
                StringFormatter.format(ConstantesModel.URL_CARPETA_PERIODICOS_IMAGEN, periodico.getId()).getValue());
        }
        periodico.setImagenPath(path);
    }


    private void numeracionAutomatica(Periodico p){
        Long idNum = p.getId();
        String idText = String.format("%03d", idNum);
        String cat;
        if (p.getTipo() != null){
         cat = (p.getTipo().toString().substring(0,3));
        } else{
            cat = "---";
        }
        String ano;
        if (p.getPublicacionAno() != null) {
            if (p.getPublicacionAno().toString().length() > 2) {
                ano = p.getPublicacionAno().toString().substring(2);
            } else {
                ano = p.getPublicacionAno().toString();
            }
            // El 03 es porque es un identificador de clase. En este caso, los prototextos serán 01
            p.setNumeracionAutomatica("03" + cat + idText + ano);
        } else{
            p.setNumeracionAutomatica("03" + cat + idText);
        }
    }

}

