package es.enxenio.GGAL1701.model.dominio.subidas.subidaPrototexto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by hlorenzo on 31/08/2017.
 **/
public interface SubidaPrototextoRepository extends JpaRepository<SubidaPrototexto, Long>{

    @Query(value = "SELECT s" +
        " FROM SubidaPrototexto s" +
        " WHERE ( str(s.id) like %:query%" +
        " OR lower(unaccent(s.otroPath)) like %:query%)")
    Page<SubidaPrototexto> filter(@Param("query") String query, Pageable pageable);

}
