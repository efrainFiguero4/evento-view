package pe.com.eventoview.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.eventoview.persistence.entity.Funcion;
import pe.com.eventoview.persistence.entity.Zona;

public interface ZonaRepository extends JpaRepository<Zona, Integer> {
}
