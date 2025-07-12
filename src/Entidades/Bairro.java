package Entidades;

import lombok.*;

@Setter
@Getter
public class Bairro{
    
    private int identificador;
    private String descricao; 
    private Cidade cidade;
    
}
