package Persistencia;

import Entidades.TipoTelefone;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoTelefoneDAO extends ConexaoComOBancoDeDados implements InterfaceDAO<Integer, TipoTelefone>{

    @Override
    public void salvar(TipoTelefone tipoTelefone) {

        String sql = "INSERT INTO TIPO_TELEFONE "
                + "(DESCRICAO) VALUES "
                + "(?);";

        conectar();

        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);

            pstm.setString(1, tipoTelefone.getDescricao());
            //pstm.setString(2, cep.getLogradouro());

            pstm.execute();

            getConexao().commit();

        } catch (SQLException ex) {
            System.out.println("Não foi possivel cadastrar o Tipo de Telefone " + tipoTelefone.getDescricao());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void modificar(TipoTelefone tipoTelefone) {

        String sql = "UPDATE CEP SET "
                + "CEP = ?, "
                + "LOGRADOURO = ? "
                + "WHERE ID = ?;";

        conectar();

        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);

            pstm.setString(1, tipoTelefone.getDescricao());
            //pstm.setString(2, estado.getSigla());
            //pstm.setInt(3, estado.getIdentificador());
            
            pstm.execute();
            
            getConexao().commit();

        } catch (Exception ex) {
            System.out.println("Não foi possivel atualizar o Tipo de Teefone " + tipoTelefone.getDescricao());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void deletar(TipoTelefone tipoTelefone) {
    
        String sql = "DELETE FROM CEP "
                + "WHERE IDENTIFICADOR = ?;";
        
        conectar();
        
        
        try {
            
              PreparedStatement pstm = getConexao().prepareStatement(sql);              
              pstm.setString(1, tipoTelefone.getDescricao());
              pstm.execute();
              getConexao().commit();
              
        } catch (SQLException ex) {
            
            System.out.println("Não foi possivel excluir o Tipo de Telefone" + tipoTelefone.getDescricao());
            System.out.println("Erro sql" + ex);
        }
        desconectar();
    }

    @Override
    public TipoTelefone buscarPorId(Integer id) {
    
        String sql = "SELECT * FROM TIPO_TELEFONE "
                + "WHERE IDENTIFICADOR = ?;";
        
        TipoTelefone tipoTelefone = new TipoTelefone();
        
        conectar();
        
        try {
            
             PreparedStatement pstm = getConexao().prepareStatement(sql);
             pstm.setInt(1, id);
             ResultSet lista = pstm.executeQuery();
             
             while (lista.next()){
                 
                 tipoTelefone.setDescricao(lista.getString("TIPO DE TELEFONE"));
                 //cep.setDescricao(lista.getString("DESCRIÇÃO"));
                 //cep.setSigla(lista.getString("SIGLA"));
                 
             }
        }catch (SQLException ex){
            
            System.out.println("Não foi possivel encontrar o Tipo de Telefone" + tipoTelefone.getDescricao());
            System.out.println("Erro SQL" + ex);
            
        }
        desconectar();
        return tipoTelefone;
    }


    @Override
    public List<TipoTelefone> listarTodos() {
    
       List<TipoTelefone> listaDeTipoTelefone = new ArrayList<TipoTelefone>();
       
       String sql = "SELECT * FROM TIPO_TELEFONE;";
       
       conectar();
       
        try {
            
            PreparedStatement pstm = getConexao().prepareStatement(sql);
            ResultSet lista = pstm.executeQuery();
            
            while(lista.next()){
                TipoTelefone tipoTelefone = new TipoTelefone();
                tipoTelefone.setDescricao(lista.getString("TIPO DE TELEFONE"));
                //cep.setDescricao(lista.getString("DESCRIÇÃO"));
                //cep.setSigla(lista.getString("SIGLA"));
                
                listaDeTipoTelefone.add(tipoTelefone);
            }
            
        } catch (SQLException ex) {
        
            System.out.println("Não foi possivel encontrar o Tipo do Telefone");
            System.out.println("Erro SQL: " + ex);        
        }
       desconectar();
    return listaDeTipoTelefone;
    }
    
}
