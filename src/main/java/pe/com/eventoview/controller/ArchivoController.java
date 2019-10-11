package pe.com.eventoview.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pe.com.eventoview.model.response.CodigoParse;
import pe.com.eventoview.model.response.Item;
import pe.com.eventoview.persistence.entity.Codigo;
import pe.com.eventoview.persistence.entity.Evento;
import pe.com.eventoview.persistence.entity.Funcion;
import pe.com.eventoview.persistence.entity.Zona;
import pe.com.eventoview.service.EventoService;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/archivo")
public class ArchivoController {

    private EventoService eventoService;

    private static final String COMMA_DELIMITER = ",";
    private static final String CODIGOS = "CODIGOS";
    private static final String CONFIG = "CONFIGURACION";

    @PostMapping("subir")
    public List<CodigoParse> subiarArchivo(@RequestParam("file") MultipartFile file, @RequestParam("tipo") String tipo) throws IOException {
        Pattern pattern = Pattern.compile(COMMA_DELIMITER);

        if (!file.isEmpty()) {

            List<CodigoParse> erroneos = new ArrayList<>();
            if (tipo.equals(CODIGOS)) {
                List<CodigoParse> namefreq;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    namefreq = in.lines().skip(1).map(pattern::split).map(CodigoParse::new).collect(Collectors.toList());
                }
                List<Codigo> codigos = new ArrayList<>();
                namefreq.forEach(data -> {
                    if (eventoService.codigoNoExiste(data.getIdEvento(), data.getIdFuncion(), data.getIdZona(), data.getNumero())) {
                        codigos.add(Codigo.builder()
                                .fechaCreacion(new Date())
                                .usuarioCreacion(1)
                                .fechaModificacion(new Date())
                                .usuarioModificacion(1)
                                .numero(data.getNumero())
                                .idEvento(data.getIdEvento())
                                .idFuncion(data.getIdFuncion())
                                .idZona(data.getIdZona())
                                .build());
                    } else {
                        erroneos.add(data);
                    }
                });
                eventoService.insertarCodigos(codigos);
                return erroneos;
            } else if (tipo.equals(CONFIG)) {

                List<String> eventosfile;
                List<String> funcionesfile;
                List<String> zonasfile;
                List<String[]> datos;

                try (BufferedReader in = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    datos = in.lines().skip(1).map(pattern::split).collect(Collectors.toList());
                }

                datos = datos.stream().filter(x -> x.length > 0).collect(Collectors.toList());
                eventosfile = datos.stream().filter(x -> !StringUtils.isEmpty(x[0])).map(x -> x[0]).collect(Collectors.toList());
                funcionesfile = datos.stream().filter(x -> x.length > 1 && !StringUtils.isEmpty(x[1])).map(x -> x[1]).collect(Collectors.toList());
                zonasfile = datos.stream().filter(x -> x.length > 2 && !StringUtils.isEmpty(x[2])).map(x -> x[2]).collect(Collectors.toList());

                if (!eventosfile.isEmpty()) {
                    List<String> eventoss = eventoService.obtenerEvento().stream().map(Evento::getNombre).collect(Collectors.toList());
                    eventosfile.removeIf(eventoss::contains);
                    List<Evento> eventos = eventosfile.stream().map(s -> Evento.builder().usuarioCreacion(1).fechaCreacion(new Date()).usuarioModificacion(1).fechaModificacion(new Date()).estado(Boolean.TRUE).nombre(s).build()).collect(Collectors.toList());
                    eventoService.guardarEventos(eventos);
                }

                if (!funcionesfile.isEmpty()) {
                    List<String> funcioness = eventoService.obtenerFunciones().stream().map(Funcion::getNombre).collect(Collectors.toList());
                    funcionesfile.removeIf(funcioness::contains);
                    List<Funcion> funcions = funcionesfile.stream().map(s -> Funcion.builder().usuarioCreacion(1).fechaCreacion(new Date()).usuarioModificacion(1).fechaModificacion(new Date()).estado(Boolean.TRUE).nombre(s).build()).collect(Collectors.toList());
                    eventoService.guardarFunciones(funcions);
                }
                if (!zonasfile.isEmpty()) {
                    List<String> zonass = eventoService.obtenerZonas().stream().map(Zona::getNombre).collect(Collectors.toList());
                    zonasfile.removeIf(zonass::contains);
                    List<Zona> zonas = zonasfile.stream().map(s -> Zona.builder().usuarioCreacion(1).fechaCreacion(new Date()).usuarioModificacion(1).fechaModificacion(new Date()).estado(Boolean.TRUE).nombre(s).build()).collect(Collectors.toList());
                    eventoService.guardarZonas(zonas);
                }
            }
        }
        return Collections.emptyList();
    }
}
