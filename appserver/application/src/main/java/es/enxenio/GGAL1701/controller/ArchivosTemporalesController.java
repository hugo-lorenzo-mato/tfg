package es.enxenio.GGAL1701.controller;

import es.enxenio.GGAL1701.controller.custom.util.MessageJSON;
import es.enxenio.GGAL1701.controller.util.CodeUtil;
import es.enxenio.GGAL1701.util.ConstantesModel;
import es.enxenio.GGAL1701.util.ConstantesRest;
import es.enxenio.GGAL1701.util.upload.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;

@RestController
@RequestMapping(value = ConstantesRest.URL_BASE)
@PreAuthorize(ConstantesRest.PERMIT_ALL)
public class ArchivosTemporalesController {

    @Autowired
    private FileUploadHelper fileUploadHelper;

    @RequestMapping(value = "/archivos-temporales")
    public ResponseEntity<MessageJSON> subirArchivoTemporal(@RequestParam("file") MultipartFile file,
                                                            @RequestParam("ext") String ext) {

        String[] extensions = ConstantesModel.EXTENSIONES_VALIDAS;
        if (CodeUtil.notEmpty(ext)) {
            extensions = ext.replace(".", "").replace(" ", "").split(",");
            Arrays.asList(extensions).retainAll(Arrays.asList(ConstantesModel.EXTENSIONES_VALIDAS));
        }

        if (fileUploadHelper.eExtensionValida(file.getOriginalFilename(), extensions)) {
            File directorioTemporal = fileUploadHelper.obtenerDirectorio(ConstantesModel.URL_CARPETA_ARCHIVOS_TEMPORALES);
            String archivoTemporal = fileUploadHelper.guardarArchivoTemporal(file, directorioTemporal,
                ConstantesModel.URL_CARPETA_ARCHIVOS_TEMPORALES);
            return new ResponseEntity<>(new MessageJSON(archivoTemporal), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageJSON("invalid extension"), HttpStatus.BAD_REQUEST);

        }
    }

}
/***



 Hibernate: select usuario0_.id as id1_0_, usuario0_.locked as locked2_0_, usuario0_.login as login3_0_, usuario0_.password as password4_0_, usuario0_.role as role5_0_, usuario0_.version as version6_0_ from public.usuario usuario0_ where usuario0_.login=?
 Hibernate: select count(funcionage0_.id) as col_0_0_ from funcion_agente funcionage0_ where lower(unaccent(funcionage0_.nombre)) like lower(unaccent(('%'||?||'%')))



 2017-09-08 10:30:07.502  WARN 8656 --- [nio-8080-exec-4] .c.j.MappingJackson2HttpMessageConverter : Failed to evaluate Jackson
 deserialization for type [[simple type, class es.enxenio.GGAL1701.model.dominio.funciones.funcionAgente.FuncionAgente]]: java.lang.Il
 legalArgumentException: Can not handle managed/back reference 'periodico': no back reference property found from type
 [collection type; class java.util.Set, contains [simple type, class es.enxenio.GGAL1701.model.dominio.periodico.PeriodicoOrganizacion]]
 2017-09-08 10:30:07.527  WARN 8656 --- [nio-8080-exec-4] .c.j.MappingJackson2HttpMessageConverter : Failed to evaluate
 Jackson deserialization for type [[simple type, class es.enxenio.GGAL1701.model.dominio.funciones.funcionAgente.FuncionAgente]]: java.lang.Il
 legalArgumentException: Can not handle managed/back reference 'periodico': no back reference property found from type
 [collection type; class java.util.Set, contains [simple type, class es.enxenio.GGAL1701.model.dominio.periodico.PeriodicoOrganizacion]]
 2017-09-08 10:30:07.543  WARN 8656 --- [nio-8080-exec-4] .c.j.MappingJackson2HttpMessageConverter : Failed to evaluate
 Jackson deserialization for type [[simple type, class es.enxenio.GGAL1701.model.dominio.funciones.funcionAgente.FuncionAgente]]: java.lang.Il
 legalArgumentException: Can not handle managed/back reference 'periodico': no back reference property found from type
 [collection type; class java.util.Set, contains [simple type, class es.enxenio.GGAL1701.model.dominio.periodico.PeriodicoOrganizacion]]
 2017-09-08 10:30:07.547  WARN 8656 --- [nio-8080-exec-4] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved exception
 caused by Handler execution: org.springframework.web.HttpMediaTypeNotSupportedException: Content type 'application/json;charset=UTF-8' not su
 pported






 **/
