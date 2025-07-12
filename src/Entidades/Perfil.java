package Entidades;
import java.util.Date;
import lombok.*;

@Setter
@Getter
public class Perfil {
    
   private int identificador;
   private String nome;
   private boolean ativo;
   private Date dataCadastro;
   
   public String toString(){
       return nome;
   }
    
}
