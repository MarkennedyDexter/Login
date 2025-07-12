package Persistencia;

import Entidades.Logradouro;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class LogradouroDAO extends ConexaoComOBancoDeDados implements InterfaceDAO<Integer, Logradouro>{

    @Override
    public void salvar(Logradouro logradouro) {

        String sql = "INSERT INTO LOGRDOURO "
                + "(DESCRICACAO) VALUES "
                + "(?);";

        conectar();

        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);

            pstm.setString(1, logradouro.getDescricao());

            pstm.execute();

            getConexao().commit();

        } catch (SQLException ex) {
            System.out.println("Não foi possivel cadastrar o Logradouro " + logradouro.getDescricao());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void modificar(Logradouro logradouro) {

        String sql = "UPDATE LOGRADOURO SET "
                + "DESCRICAO = ?, "
                + "WHERE ID = ?;";

        conectar();

        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);

            pstm.setString(1, logradouro.getDescricao());
            //pstm.setString(2, estado.getSigla());
            //pstm.setInt(3, estado.getIdentificador());
            
            pstm.execute();
            
            getConexao().commit();

        } catch (Exception ex) {
            System.out.println("Não foi possivel atualizar o Logradouro " + logradouro.getDescricao());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }


    @Override
    public void deletar(Logradouro logradouro) {
    
        String sql = "DELETE FROM LOGRADOURO "
                + "WHERE IDENTIFICADOR = ?;";
        
        conectar();
        
        
        try {
            
              PreparedStatement pstm = getConexao().prepareStatement(sql);              
              pstm.setInt(1, logradouro.getIdentificador());
              pstm.execute();
              getConexao().commit();
              
        } catch (SQLException ex) {
            
            System.out.println("Não foi possivel excluir o Logradouro" + logradouro.getDescricao());
            System.out.println("Erro sql" + ex);
        }
        desconectar();
    }


    @Override
    public Logradouro buscarPorId(Integer id) {
    
        String sql = "SELECT * FROM LOGRADOURO "
                + "WHERE IDENTIFICADOR = ?;";
        
        Logradouro logradouro = new Logradouro();
        
        conectar();
        
        try {
            
             PreparedStatement pstm = getConexao().prepareStatement(sql);
             pstm.setInt(1, id);
             ResultSet lista = pstm.executeQuery();
             
             while (lista.next()){
                 
                 logradouro.setIdentificador(lista.getInt("IDENTIFICADOR"));
                 //logradouro.setDescricao(lista.getString("DESCRIÇÃO"));
                 //logradouro.setSigla(lista.getString("SIGLA"));
                 
             }
        }catch (SQLException ex){
            
            System.out.println("Não foi possivel encontrar o Logradouro" + logradouro.getDescricao());
            System.out.println("Erro SQL" + ex);
            
        }
        desconectar();
        return logradouro;
    }


    @Override
    public List<Logradouro> listarTodos() {
    
       List<Logradouro> listaDeLogradouro = new ArrayList<Logradouro>();
       
       String sql = "SELECT * FROM LOGRADOURO;";
       
       conectar();
       
        try {
            
            PreparedStatement pstm = getConexao().prepareStatement(sql);
            ResultSet lista = pstm.executeQuery();
            
            while(lista.next()){
                Logradouro logradouro = new Logradouro();
                logradouro.setIdentificador(lista.getInt("IDENTIDIGADOR"));
                logradouro.setDescricao(lista.getString("DESCRIÇÃO"));
                //logradouro.setSigla(lista.getString("SIGLA"));
                
                listaDeLogradouro.add(logradouro);
            }
            
        } catch (SQLException ex) {
        
            System.out.println("Não foi possivel encontrar o Logradouro");
            System.out.println("Erro SQL: " + ex);        
        }
       desconectar();
    return listaDeLogradouro;
    }
    
}
