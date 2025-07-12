package Entidades;

import lombok.*;

@Setter
@Getter
public class Endereco{
 
    private int identificador;
    private Cep cep;
    private String complemento;
    private int numero;
    private TipoEndereco tipoEndereco;
    private String pontoReferencia;
    
}
