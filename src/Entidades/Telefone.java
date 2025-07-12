package Entidades;

import lombok.*;

@Setter
@Getter
public class Telefone {
        
    private int identificador;
    private int ddd;
    private String numero;
    private TipoTelefone tipoTelefone;
}
