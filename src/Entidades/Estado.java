package Entidades;

import lombok.*;

@Setter
@Getter
public class Estado {
    
   private int identificador;
   private String descricao; 
   private String sigla;
   
   public String toString() {
       return descricao;
   }
   
}
