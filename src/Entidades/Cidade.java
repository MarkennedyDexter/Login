package Entidades;

import lombok.*;

@Setter
@Getter
public class Cidade{
        
    private int identificador;
    private String descricao; 
    private Estado estado;
    
    public String toString(){
        return descricao;
    }
}
