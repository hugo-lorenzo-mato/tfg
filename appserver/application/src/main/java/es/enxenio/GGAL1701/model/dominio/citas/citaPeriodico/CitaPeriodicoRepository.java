package es.enxenio.GGAL1701.model.dominio.citas.citaPeriodico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by hlorenzo on 01/09/2017.
 **/
public interface CitaPeriodicoRepository extends JpaRepository<CitaPeriodico, Long> {

    @Query(value = "SELECT c" +
        " FROM CitaPeriodico c" +
        " WHERE ( str(c.id) like %:query%" +
        " OR lower(unaccent(c.autorCitado)) like %:query%" +
        " OR lower(unaccent(c.referenciaPasivo)) like %:query% )")
    Page<CitaPeriodico> filter(@Param("query") String query, Pageable pageable);

}
