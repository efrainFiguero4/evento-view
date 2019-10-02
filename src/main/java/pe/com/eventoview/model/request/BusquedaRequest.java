package pe.com.eventoview.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class BusquedaRequest {

    @NotNull(message = "Seleccionar evento")
    private Integer evento;

    @NotNull(message = "Seleccionar función")
    private Integer funcion;

    @NotNull(message = "Seleccionar zona")
    private Integer zona;

    @NotNull(message = "Ingresar numero")
    @NotEmpty(message = "Ingresar numero")
    private String numero;
}
