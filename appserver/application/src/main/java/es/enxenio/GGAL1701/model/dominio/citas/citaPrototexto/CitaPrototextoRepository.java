package es.enxenio.GGAL1701.model.dominio.citas.citaPrototexto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by hlorenzo on 29/08/2017.
 **/
public interface CitaPrototextoRepository extends JpaRepository<CitaPrototexto, Long> {

    @Query(value = "SELECT c" +
        " FROM CitaPrototexto c" +
        " WHERE ( str(c.id) like %:query%" +
        " OR lower(unaccent(c.autorCitado)) like %:query%" +
        " OR lower(unaccent(c.referenciaPasivo)) like %:query% )")
    Page<CitaPrototexto> filter(@Param("query") String query, Pageable pageable);

}
