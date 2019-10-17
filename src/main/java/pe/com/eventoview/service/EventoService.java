package pe.com.eventoview.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.eventoview.model.request.BusquedaRequest;
import pe.com.eventoview.model.response.Item;
import pe.com.eventoview.persistence.entity.Codigo;
import pe.com.eventoview.persistence.entity.Evento;
import pe.com.eventoview.persistence.entity.Funcion;
import pe.com.eventoview.persistence.entity.Zona;
import pe.com.eventoview.persistence.entity.Configuracion;
import pe.com.eventoview.persistence.repositories.CodigoRepository;
import pe.com.eventoview.persistence.repositories.EventoRepository;
import pe.com.eventoview.persistence.repositories.FuncionRepository;
import pe.com.eventoview.persistence.repositories.ZonaRepository;
import pe.com.eventoview.persistence.repositories.ConfiguracionRepository;
import pe.com.eventoview.util.Constantes;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventoService {

    EventoRepository eventoRepository;
    FuncionRepository funcionRepository;
    ZonaRepository zonaRepository;
    CodigoRepository codigoRepository;
    ConfiguracionRepository configuracionRepository;

    public List<Evento> obtenerEvento() {
        return eventoRepository.findAll();
    }

    public List<Funcion> obtenerFunciones() {
        return funcionRepository.findAll();
    }

    public List<Zona> obtenerZonas() {
        return zonaRepository.findAll();
    }

    public Object obtenerConfiguracion() {

        Map event = new HashMap();

        configuracionRepository.findById(1).ifPresent(config -> {
            event.put("evento", eventoRepository.findById(config.getIdEvento()));
            event.put("funcion", funcionRepository.findById(config.getIdFuncion()));
            event.put("zona", zonaRepository.findById(config.getIdZona()));
        });

        return event;
    }

    public Item obtenerBusqueda(BusquedaRequest busqueda) {
        Optional<Codigo> data = codigoRepository.findByNumero(busqueda.getNumero());
        if (data.isPresent()) {
            Codigo codigo = data.get();
            if (!codigo.getIdEvento().equals(busqueda.getEvento())) {
                return Item.builder()
                        .codigo(0)
                        .valor(Constantes.TICKET_OTRO_EVENTO)
                        .build();
            } else if (!codigo.getIdFuncion().equals(busqueda.getFuncion())) {
                return Item.builder()
                        .codigo(0)
                        .valor(Constantes.TICKET_OTRO_FUNCION)
                        .build();
            } else if (!codigo.getIdZona().equals(busqueda.getZona())) {
                return Item.builder()
                        .codigo(0)
                        .valor(Constantes.TICKET_OTRO_SECTOR)
                        .build();
            } else if (codigo.isConfirmado()) {
                return Item.builder()
                        .codigo(0)
                        .valor(Constantes.TICKET_HORA_INGRESO + codigo.getFechaModificacion())
                        .build();
            } else {
                confirmarCodigo(codigo.getIdCodigo());
                return Item.builder()
                        .codigo(codigo.getIdCodigo())
                        .valor(Constantes.TICKET_OK)
                        .build();
            }
        } else {
            return Item.builder()
                    .codigo(0)
                    .valor(Constantes.TICKET_NO_ENCONTRADO)
                    .build();
        }
    }

    public Item confirmarCodigo(Integer idCodigo) {
        if (codigoRepository.existsById(idCodigo)) {
            Codigo codigo = codigoRepository.getOne(idCodigo);
            codigo.setUsuarioModificacion(1);
            codigo.setFechaModificacion(new Date());
            codigo.setConfirmado(Constantes.TICKET_CONFIRMADO);
            codigoRepository.saveAndFlush(codigo);
            return Item.builder().codigo(codigo).valor("Codigo Confirmado").build();
        } else {
            return Item.builder().codigo(1).valor("Error al confirmar codigo").build();
        }
    }

    public void guardarConfiguracion(BusquedaRequest busqueda) {
        
        configuracionRepository.findById(1).ifPresent( config -> {
            config.setIdEvento(busqueda.getEvento()); 
            config.setIdFuncion(busqueda.getFuncion()); 
            config.setIdZona(busqueda.getZona()); 
            configuracionRepository.save(config);
            });

    }

    public boolean codigoNoExiste(Integer idEvento, Integer idFuncion, Integer idZona, String numero) {
        return codigoRepository.findByIdEventoAndIdFuncionAndIdZonaAndNumeroAndConfirmadoIsFalse(idEvento, idFuncion, idZona, numero).isEmpty();
    }


    public List<Codigo> insertarCodigos(List<Codigo> codigos) {
        return codigoRepository.saveAll(codigos);
    }

    public List<Evento> guardarEventos(List<Evento> eventos) {
        return eventoRepository.saveAll(eventos);
    }

    public List<Funcion> guardarFunciones(List<Funcion> funcions) {
        return funcionRepository.saveAll(funcions);
    }

    public List<Zona> guardarZonas(List<Zona> zonas) {
        return zonaRepository.saveAll(zonas);
    }
}
