package pe.com.eventoview.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.eventoview.model.request.BusquedaRequest;
import pe.com.eventoview.model.response.Item;
import pe.com.eventoview.service.EventoService;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@AllArgsConstructor
@RequestMapping("/concepto")
public class EventoController {

    private EventoService eventoService;

    @GetMapping("/evento")
    public ResponseEntity<List<Item>> obtenerEventos() {
        return ResponseEntity.ok(eventoService.obtenerEvento().stream().map(it -> Item.builder()
                .codigo(it.getIdEvento())
                .valor(it.getNombre())
                .build()).collect(toList()));
    }

    @GetMapping("/funcion")
    public ResponseEntity<List<Item>> obtenerFunciones() {
        return ResponseEntity.ok(eventoService.obtenerFunciones().stream().map(it -> Item.builder()
                .codigo(it.getIdFuncion())
                .valor(it.getNombre())
                .build()).collect(toList()));
    }

    @GetMapping("/zona")
    public ResponseEntity<List<Item>> obtenerZonas() {
        return ResponseEntity.ok(eventoService.obtenerZonas().stream().map(it -> Item.builder()
                .codigo(it.getIdZona())
                .valor(it.getNombre())
                .build()).collect(toList()));
    }

    @GetMapping("/configuracion")
    public ResponseEntity<Object> obtenerConfiguracion() {
        return ResponseEntity.ok(eventoService.obtenerConfiguracion());
    }

    @PostMapping("/guardarConfiguracion")
    public void guardarConfiguracion(@RequestBody @Valid BusquedaRequest busqueda) {

        eventoService.guardarConfiguracion(busqueda);
    }

    @PostMapping("/busqueda")
    public ResponseEntity<Item> obtenerBusqueda(@RequestBody @Valid BusquedaRequest busqueda) {
        return ResponseEntity.ok(eventoService.obtenerBusqueda(busqueda));
    }

    @GetMapping("/confirmar")
    public ResponseEntity<Item> confirmarCodigo(@RequestParam Integer codigo) {
        return ResponseEntity.ok(eventoService.confirmarCodigo(codigo));
    }

}
