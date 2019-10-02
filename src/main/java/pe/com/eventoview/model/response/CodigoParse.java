package pe.com.eventoview.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CodigoParse {
    private String numero;
    private Integer idEvento;
    private Integer idFuncion;
    private Integer idZona;

    public CodigoParse(String[] data) {
        this.numero = data[0];
        this.idEvento = Integer.valueOf(data[1]);
        this.idFuncion = Integer.valueOf(data[2]);
        this.idZona = Integer.valueOf(data[3]);
    }
}
