package Crontroller;

import Entidades.Estado;
import Persistencia.EstadoDAO;
import javax.swing.JOptionPane;

public class EstadoController {
    
    private Estado estado = new Estado();
    private boolean camposValidos = true;
    private StringBuilder mensagemDeErro = new StringBuilder();    
    private EstadoDAO daoEstado = new EstadoDAO();
    
    public void cadastrarEstrado(String descricao, String sigla){
        
        estado.setDescricao(descricao);
        estado.setSigla(sigla);  
        
        validarCampos(estado);
        
        if(camposValidos){
            daoEstado.salvar(estado); 
            JOptionPane.showMessageDialog(null,"Estado cadastrado com sucesso ");
                        
        } else{
            mensagemDeErro.append("Não foi possivel cadastrar o estado " +  
                    estado.getDescricao() + "/" + estado.getSigla() + "\n");
            apresentarMensagemDeErro();
        }
    }
    
    private boolean validarCampos (Estado estado){
        
        if (estado.getDescricao() == null || estado.getDescricao().equals("")){
            camposValidos = false;
            mensagemDeErro.append("O campo descrição é obrigatorio! "); 
            apresentarMensagemDeErro();
        }
        
        if (estado.getSigla() == null || estado.getSigla().equals("")){
            camposValidos = false;
            mensagemDeErro.append("O campo sigla é obrigatório! ");
            apresentarMensagemDeErro();
        }
        
        return camposValidos;
    }
    
    private void apresentarMensagemDeErro(){
        JOptionPane.showMessageDialog(null, mensagemDeErro.toString());  
        
    }
    
}
