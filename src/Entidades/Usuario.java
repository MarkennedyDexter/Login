package Entidades;

import java.util.Date;
import lombok.*;

@Getter
@Setter
public class Usuario {
    String cpf;
    String nome;
    String userName;
    String senha;
    boolean ativo;
    Perfil perfil;
    Date dataCadastro;
    char sexo;
    Date dataNascimento;
    Telefone telefone;
    Endereco endereco;
    
}
