package pe.com.eventoview.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.eventoview.persistence.entity.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
}
